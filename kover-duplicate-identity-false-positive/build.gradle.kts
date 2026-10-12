plugins {
    id("org.jetbrains.kotlinx.kover") version "0.9.11"
}

dependencies {
    kover(project(":a:common"))
    kover(project(":b:common"))
}
