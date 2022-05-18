plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    api("org.apache.commons:commons-math3:3.6.1")
    implementation("com.google.guava:guava:30.1.1-jre")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    outputs.upToDateWhen { false }
    outputs.cacheIf { false }
}
