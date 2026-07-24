plugins {
    `java-library`
}

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    testImplementation("com.github.junit-team.junit-framework:junit-aggregator:experiment~junit-aggregator-SNAPSHOT")
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
