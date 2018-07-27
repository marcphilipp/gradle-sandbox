plugins {
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "0.10.0"
    `maven-publish`
}

group = "de.marcphilipp.gradle"
version = "1.3"

gradlePlugin {
    (plugins) {
        "hello" {
            id = "de.marcphilipp.gradle.example.hello"
            implementationClass = "de.marcphilipp.gradle.example.HelloPlugin"
            displayName = "Hello plugin"
            description = "Plugin that can say hello"
        }
        "goodbye" {
            id = "de.marcphilipp.gradle.example.goodbye"
            implementationClass = "de.marcphilipp.gradle.example.GoodbyePlugin"
            displayName = "Goodbye plugin"
            description = "Plugin that can say goodbye"
        }
    }
}

pluginBundle {
    website = "https://www.foo.org/"
    vcsUrl = "https://github.com/foo/bar"
    tags = listOf("greeting")
    (plugins) {
        "goodbye" {
            tags = listOf("goodbye")
        }
    }
}

publishing {
    repositories {
        maven {
            url = uri("$buildDir/repo")
        }
    }
    publications {
        afterEvaluate {
            getByName<MavenPublication>("pluginMaven") {
                groupId = "gradle.plugin.$group"
                pom {
                    name.set("My Example Plugins")
                    description.set("Example Plugins with customized POM")
                    developers {
                        developer {
                            name.set("Marc Philipp")
                        }
                    }
                }
            }
        }
    }
}
