pluginManagement {
    repositories {
        maven {
            url = uri(file("../repo"))
        }
        gradlePluginPortal()
    }
}

rootProject.name = "consumer"
