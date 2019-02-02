val baseProject = project(":base")

subprojects {
    apply(plugin = "java-library")

    repositories {
        mavenCentral()
    }

    dependencies {
        val testImplementation by configurations.getting
        testImplementation("org.junit.jupiter:junit-jupiter:5.4.0-RC2")
        if (project != baseProject) {
            testImplementation(baseProject.the<SourceSetContainer>()["test"].output.classesDirs)
        }
    }

    tasks {
        withType<Test>().configureEach {
            useJUnitPlatform()
        }
    }

}
