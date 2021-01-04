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

includeBuild("build-logic")

include("lib0")
include("lib1")
