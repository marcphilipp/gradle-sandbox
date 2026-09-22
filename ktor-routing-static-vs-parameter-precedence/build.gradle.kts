plugins {
    kotlin("jvm") version "2.4.20"
    kotlin("plugin.serialization") version "2.4.20"
}

repositories {
    mavenCentral()
}

val ktorVersion = "3.6.0"

dependencies {
    implementation(platform("io.ktor:ktor-bom:$ktorVersion"))
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-resources")
    implementation("io.ktor:ktor-server-auth")

    testImplementation(platform("io.ktor:ktor-bom:$ktorVersion"))
    testImplementation("io.ktor:ktor-server-test-host")
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter:6.1.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
}
