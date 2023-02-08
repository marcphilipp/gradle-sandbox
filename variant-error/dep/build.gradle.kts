plugins {
    `java-library`
    `java-test-fixtures`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.compileTestFixturesJava {
    options.release.set(17)
}
