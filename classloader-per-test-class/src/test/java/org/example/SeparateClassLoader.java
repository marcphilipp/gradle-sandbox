package org.example;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(SeparateClassLoader.Extension.class)
public @interface SeparateClassLoader {

    Class<CustomClassLoaderFactory> factory();

    interface Factory {
        ClassLoader create(Class<?> testClass);
    }

    class Extension implements BeforeAllCallback, AfterAllCallback {

        private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(Extension.class);
        private static final String KEY = "originalClassLoader";

        @Override
        public void beforeAll(ExtensionContext context) {
            context.getStore(NAMESPACE).put(KEY, Thread.currentThread().getContextClassLoader());
            Thread.currentThread().setContextClassLoader(context.getRequiredTestClass().getClassLoader());
        }

        @Override
        public void afterAll(ExtensionContext context) {
            var originalClassLoader = context.getStore(NAMESPACE).get(KEY, ClassLoader.class);
            Thread.currentThread().setContextClassLoader(originalClassLoader);
        }
    }

}
