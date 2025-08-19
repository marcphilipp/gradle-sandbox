plugins {
    `java-library`
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    testImplementation("org.junit:junit-aggregator:6.0.0-SNAPSHOT")
}

tasks {
    compileTestJava {
        options.release = 25
    }
    register<JavaExec>("mainTest") {
        mainModule = "org.example"
        mainClass = "org.example.ExampleTests"
        classpath = sourceSets["test"].runtimeClasspath
        modularity.inferModulePath = true
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
