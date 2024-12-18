package org.example;

import org.junit.platform.commons.support.AnnotationSupport;
import org.junit.platform.commons.support.ReflectionSupport;
import org.junit.platform.commons.util.ClassLoaderUtils;
import org.junit.platform.commons.util.ExceptionUtils;
import org.junit.platform.engine.support.hierarchical.ThrowableCollector;
import org.junit.platform.launcher.LauncherSession;
import org.junit.platform.launcher.LauncherSessionListener;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ClassLoaderReplacingLauncherSessionListener implements LauncherSessionListener {

    private final Deque<AutoCloseable> closeables = new ConcurrentLinkedDeque<>();
    private ClassLoader originalClassLoader;

    @Override
    public void launcherSessionOpened(LauncherSession session) {
        originalClassLoader = Thread.currentThread().getContextClassLoader();
        var defaultClassLoader = ClassLoaderUtils.getDefaultClassLoader();
        Thread.currentThread().setContextClassLoader(new ClassLoader() {
            @SuppressWarnings({"rawtypes", "unchecked"})
            @Override
            protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
                Class clazz = defaultClassLoader.loadClass(name);
                return AnnotationSupport.findAnnotation(clazz, SeparateClassLoader.class)
                        .map(SeparateClassLoader::factory)
                        .map(ReflectionSupport::newInstance)
                        .map(factory -> factory.create(clazz))
                        .map(classLoader -> {
                            if (classLoader instanceof AutoCloseable) {
                                closeables.push((AutoCloseable) classLoader);
                            }
                            try {
                                return classLoader.loadClass(name);
                            } catch (ClassNotFoundException e) {
                                throw ExceptionUtils.throwAsUncheckedException(e);
                            }
                        })
                        .orElse(clazz);
            }
        });
    }

    @Override
    public void launcherSessionClosed(LauncherSession session) {
        ThrowableCollector collector = new ThrowableCollector(__ -> false);
        var iterator = closeables.descendingIterator();
        while (iterator.hasNext()) {
            collector.execute(() -> {
                var closeable = iterator.next();
                System.out.println("Closing " + closeable);
                closeable.close();
                iterator.remove();
            });
        }
        Thread.currentThread().setContextClassLoader(originalClassLoader);
        collector.assertEmpty();
    }
}
