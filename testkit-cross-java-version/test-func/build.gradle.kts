plugins {
    `java-gradle-plugin`
}

repositories {
    jcenter()
}

val pluginUnderTestMetadata = project(":plugin").tasks["pluginUnderTestMetadata"].outputs.files

dependencies {
    testRuntimeOnly(pluginUnderTestMetadata)
    testImplementation("junit:junit:4.13")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.test {
    requireNotNull(System.getenv("JDK8"))
    systemProperty("test.java.home", System.getenv("JDK8"))
}
