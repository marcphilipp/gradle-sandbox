plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    testCompileOnly("junit:junit:4.13.2")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.8.1") {
        exclude(group = "junit", module = "junit")
    }
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.8.1") {
        because("otherwise Gradle uses the bundled version")
    }
}

tasks.test {
    useJUnitPlatform()
}
