plugins {
    `java-library`
    id("gradlebuild.unittest-and-compile")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
}
