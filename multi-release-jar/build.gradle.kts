import jar.UpdateJarAction
import org.gradle.api.tasks.PathSensitivity.RELATIVE

plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

val java15SourceSet = sourceSets.create("mainRelease15") {
    compileClasspath += sourceSets.main.get().output
    runtimeClasspath += sourceSets.main.get().output
    java {
        setSrcDirs(setOf("src/main/java15"))
    }
}

configurations.named(java15SourceSet.compileClasspathConfigurationName).configure {
    extendsFrom(configurations.compileClasspath.get())
}

tasks {
    compileJava {
        options.release = 8
    }
    compileTestJava {
        options.release = 8
    }

    val java15CompileTask = named<JavaCompile>(java15SourceSet.compileJavaTaskName)
    java15CompileTask.configure {
        options.release = 15
    }
    jar {
        dependsOn(java15CompileTask)
        val java15ClassesDir = java15SourceSet.output.classesDirs.singleFile
        inputs.dir(java15ClassesDir).withPathSensitivity(RELATIVE)
        doLast(objects.newInstance(UpdateJarAction::class).apply {
            javaLauncher = project.javaToolchains.launcherFor(java.toolchain)
            args.addAll(
                "--file", archiveFile.get().asFile.absolutePath,
                "--release", "9",
                "-C", java15ClassesDir.absolutePath, "."
            )
        })
    }

    withType<Test>().configureEach {
        useJUnitPlatform()
    }
    test {
        // Put JAR on test runtime classpath rather than classes/resources dirs
        classpath -= files(sourceSets.main.map { it.output.classesDirs })
        classpath -= files(sourceSets.main.map { it.output.resourcesDir })
        classpath += files(jar.map { it.archiveFile })
    }

    // Register an extra test task that runs on Java 8
    val testOnJava8 by registering(Test::class) {
        testClassesDirs = files(test.map { it.testClassesDirs })
        classpath = files(test.map { it.classpath })
        javaLauncher = project.javaToolchains.launcherFor {
            languageVersion = JavaLanguageVersion.of(8)
        }
    }
    check {
        dependsOn(testOnJava8)
    }
}
