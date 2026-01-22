plugins {
    kotlin("jvm") version "2.3.0"
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("io.kotest:kotest-bom:6.1.0"))
    testImplementation("io.kotest:kotest-runner-junit5")
    testImplementation("io.kotest:kotest-assertions-core")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.test {
    useJUnitPlatform()
}
