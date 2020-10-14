plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.testng:testng:7.3.0")
}

tasks.test {
    useTestNG()
}
