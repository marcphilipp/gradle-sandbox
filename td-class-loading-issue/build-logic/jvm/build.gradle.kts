plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(platform(project(":build-platform")))
    implementation(project(":integration-testing"))
    implementation("com.gradle.enterprise:test-distribution-gradle-plugin")
}
