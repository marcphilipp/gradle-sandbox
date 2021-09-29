import com.gradle.enterprise.gradleplugin.testdistribution.internal.TestDistributionExtensionInternal
import java.time.Duration

plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.junit.platform:junit-platform-launcher")
}

tasks {
    withType<JavaCompile>().configureEach {
        options.release.set(11)
    }
    test {
        useJUnitPlatform()
        distribution {
            enabled.set(true)
            maxLocalExecutors.set(0)
            (this as TestDistributionExtensionInternal).forkedVMShutdownTimeout.set(Duration.ofSeconds(60))
        }
    }
}
