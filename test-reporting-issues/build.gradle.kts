import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events(TestLogEvent.STARTED)
    }
}
