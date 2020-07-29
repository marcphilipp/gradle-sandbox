plugins {
    `java-gradle-plugin`
}

repositories {
    jcenter()
}

dependencies {
    testImplementation("junit:junit:4.13")
}

gradlePlugin {
    val greeting by plugins.creating {
        id = "org.example.greeting"
        implementationClass = "org.example.GradleTestkitCrossJavaVersionPlugin"
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

val functionalTestSourceSet = sourceSets.create("functionalTest")

gradlePlugin.testSourceSets(functionalTestSourceSet)
configurations.getByName("functionalTestImplementation").extendsFrom(configurations.getByName("testImplementation"))

val functionalTest by tasks.registering(Test::class) {
    testClassesDirs = functionalTestSourceSet.output.classesDirs
    classpath = functionalTestSourceSet.runtimeClasspath
}

val check by tasks.getting(Task::class) {
    dependsOn(functionalTest)
}

tasks {
    withType<Test>().configureEach {
        requireNotNull(System.getenv("JDK8"))
        systemProperty("test.java.home", System.getenv("JDK8"))
    }
}
