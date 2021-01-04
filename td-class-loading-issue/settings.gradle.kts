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
include("lib2")
include("lib3")
include("lib4")
include("lib5")
include("lib6")
include("lib7")
include("lib8")
include("lib9")
