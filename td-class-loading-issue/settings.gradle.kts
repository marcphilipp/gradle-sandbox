pluginManagement {
    plugins {
        id("com.gradle.enterprise.test-distribution") version "1.3"
        id("org.gradle.test-retry") version "1.2.0"
    }
}

plugins {
    id("com.gradle.enterprise") version "3.5"
}

gradleEnterprise {
    server = "https://e.grdev.net"
    buildScan {
        publishAlways()
    }
}

rootProject.name = "td-class-loading-issue"

include("lib0")
include("lib1")
