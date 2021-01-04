plugins {
    `java-platform`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    constraints {
        api("com.gradle.enterprise:test-distribution-gradle-plugin:1.3")
    }
}
