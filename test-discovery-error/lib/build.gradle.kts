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
}

tasks.test {
    useJUnitPlatform()
}
