pluginManagement {
    plugins {
        id("com.gradle.enterprise.test-distribution") version "1.3"
    }
}

plugins {
    id("com.gradle.enterprise") version "3.5"
}

gradleEnterprise {
    server = "https://ge.gradle.org"
    buildScan {
        publishAlways()
    }
}

rootProject.name = "td-spock-invocation-interceptor"

include("lib")
