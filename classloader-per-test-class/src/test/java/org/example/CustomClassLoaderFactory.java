package org.example;

import org.junit.platform.commons.support.AnnotationSupport;
import org.junit.platform.commons.support.HierarchyTraversalMode;
import org.junit.platform.commons.support.ReflectionSupport;

import java.net.URL;
import java.net.URLClassLoader;

public class CustomClassLoaderFactory implements SeparateClassLoader.Factory {

    @Override
    public ClassLoader create(Class<?> testClass) {
        var method = AnnotationSupport.findAnnotatedMethods(testClass, Classpath.class, HierarchyTraversalMode.BOTTOM_UP).getFirst();
        var urls = (URL[]) ReflectionSupport.invokeMethod(method, null);
        var filteringClassLoader = new ClassLoader(ClassLoader.getSystemClassLoader()) {
            @Override
            protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
                return name.equals(testClass.getName()) ? null : super.loadClass(name, resolve);
            }
        };
        return new URLClassLoader(testClass.getName(), urls, filteringClassLoader);
    }
}
