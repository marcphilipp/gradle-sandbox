plugins {
    `java-library`
    `maven-publish`
    `signing`
}

group = "org.gradle.demo"
version = "1.0"

publishing {
    repositories {
        maven(url = uri("$buildDir/repos/maven"))
    }
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}

signing {
    sign(publishing.publications)
}
