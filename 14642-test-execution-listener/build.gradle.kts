plugins {
    java
    application
}

repositories {
    jcenter()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.6.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-launcher")
}

application {
    mainClassName = "com.example.App"
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("STANDARD_OUT", "STANDARD_ERROR")
    }
}
