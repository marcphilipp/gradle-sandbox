plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testImplementation(testFixtures(project(":dep")))
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.compileTestJava {
    options.release.set(11)
}
