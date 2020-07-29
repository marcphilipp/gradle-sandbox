plugins {
    `java-gradle-plugin`
}

gradlePlugin {
    val greeting by plugins.creating {
        id = "org.example.greeting"
        implementationClass = "org.example.GradleTestkitCrossJavaVersionPlugin"
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
