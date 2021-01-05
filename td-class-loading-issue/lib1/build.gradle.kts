plugins {
    id("com.gradle.enterprise.test-distribution")
    id("org.gradle.test-retry")
    `java-library`
}

println(com.gradle.enterprise.gradleplugin.testdistribution.TestDistributionPlugin::class.java.classLoader)

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
    distribution {
        enabled.set(true)
        maxLocalExecutors.set(0)
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
}
