plugins {
    groovy
    `java-library`
    id("com.gradle.enterprise.test-distribution")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.codehaus.groovy:groovy-all:2.5.12")
    testImplementation("org.spockframework:spock-core:1.3-groovy-2.5")
    testImplementation("junit:junit:4.13.1")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.7.0")
}

tasks.test {
    useJUnitPlatform()
    distribution {
        enabled.set(true)
        maxRemoteExecutors.set(0)
    }
    outputs.upToDateWhen { false }
    outputs.cacheIf { false }
}
