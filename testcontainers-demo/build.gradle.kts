plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter("5.9.1")
            dependencies {
                implementation("org.testcontainers:junit-jupiter:1.17.6")
                implementation("org.testcontainers:postgresql:1.17.6")
                runtimeOnly("ch.qos.logback:logback-core:1.4.5")
                runtimeOnly("ch.qos.logback:logback-classic:1.4.5")
            }
        }
    }
}
