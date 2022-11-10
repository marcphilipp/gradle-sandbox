plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

dependencies {
    testImplementation("org.testng:testng:6.14.3")
    testRuntimeOnly("org.junit.support:testng-engine:1.0.4")
}

tasks.test {
    useJUnitPlatform()
}
