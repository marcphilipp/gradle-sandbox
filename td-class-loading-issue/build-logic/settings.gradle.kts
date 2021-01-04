dependencyResolutionManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

include("build-platform")
include("integration-testing")
include("jvm")
