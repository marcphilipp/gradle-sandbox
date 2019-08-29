plugins {
    java
    eclipse
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(project(":producer"))
    testImplementation(project(":producer", configuration = "testArtifacts"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.5.1")
}

tasks.test {
    useJUnitPlatform()
}
