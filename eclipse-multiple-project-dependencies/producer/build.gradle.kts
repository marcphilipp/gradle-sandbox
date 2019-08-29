plugins {
    `java-library`
    eclipse
}

repositories {
    mavenCentral()
}

val testJar by tasks.creating(Jar::class) {
    archiveClassifier.set("test")
    from(sourceSets.test.get().output)
}

val testArtifacts by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = true
    outgoing.artifact(testJar)
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.5.1")
}

tasks.test {
    useJUnitPlatform()
}
