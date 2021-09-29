plugins {
    id("com.gradle.enterprise") version "3.7"
    id("com.gradle.enterprise.test-distribution") version "2.2"
}

gradleEnterprise {
    server = "https://<ge-server>"
    buildScan {
        publishAlways()
    }
}

rootProject.name = "test-distribution-thread-dump"
