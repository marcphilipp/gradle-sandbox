plugins {
    `java-library`
    `maven-publish`
    `ivy-publish`
    `signing`
}

group = "org.gradle.demo"
version = "1.0"

val sourcesJar = tasks.create<Jar>("sourcesJar") {
    from(java.sourceSets["main"].allSource)
    classifier = "sources"
}

signing {
    sign(publishing.publications)
}

publishing {
    repositories {
        maven(url = uri("$buildDir/repos/maven"))
        ivy(url = uri("$buildDir/repos/ivy"))
    }
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifact(sourcesJar)
            pom {
                name.set("custom-name")
                description.set("custom-description")
                url.set("http://example.org/project")
                inceptionYear.set("2018")
                organization {
                    name.set("Example Organization")
                    url.set("https://example.org")
                }
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("jane.doe")
                        name.set("Jane Doe")
                        email.set("jane.doe@example.org")
                    }
                }
                contributors {
                    contributor {
                        name.set("John Doe")
                        email.set("john.doe@example.org")
                    }
                }
                scm {
                    connection.set("scm:git:git://example.org/some-repo.git")
                    developerConnection.set("scm:git:git://example.org/some-repo.git")
                    url.set("https://example.org/some-repo")
                }
                issueManagement {
                    system.set("Some Issue Tracker")
                    url.set("https://issues.example.org/")
                }
                ciManagement {
                    system.set("Some CI Server")
                    url.set("https://ci.example.org/")
                }
                distributionManagement {
                    downloadUrl.set("https://example.org/download/")
                    relocation {
                        groupId.set("new-group")
                        artifactId.set("new-artifact-id")
                        version.set("42")
                    }
                }
                mailingLists {
                    mailingList {
                        name.set("Users")
                        subscribe.set("users-subscribe@lists.example.org")
                        unsubscribe.set("users-unsubscribe@lists.example.org")
                        post.set("users@lists.example.org")
                        archive.set("http://lists.example.org/users/")
                        otherArchives.set(listOf("http://archive.org/", "http://backup.example.org/"))
                    }
                    mailingList {
                        name.set("Developers")
                        post.set("devs@lists.example.org")
                    }
                }
            }
        }
        create<IvyPublication>("ivyJava") {
            from(components["java"])
            artifact(sourcesJar)
            descriptor {
                license {
                    name.set("The Apache License, Version 2.0")
                    url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                }
                author {
                    name.set("Jane Doe")
                    url.set("http://example.com/users/jane")
                }
                description {
                    text.set("A concise description of my library")
                    homepage.set("http://www.example.com/library")
                }
            }
        }
    }
}
