import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

tasks {
    compileJava {
        options.release = 21
    }
    compileKotlin {
        compilerOptions.jvmTarget = JvmTarget.JVM_21
    }
}
