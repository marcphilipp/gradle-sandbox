plugins {
    application
    java
}

application {
    mainClass.set("org.example.Demo")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.jodah:failsafe:2.4.0")
}
