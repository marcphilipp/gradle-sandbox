plugins {
    id("com.gradle.enterprise") version "3.12"
}

rootProject.name = "testcontainers-demo"

gradleEnterprise {
    server = "http://localhost:8180"
    buildScan {
        publishAlways()
    }
}
