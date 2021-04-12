plugins {
    id("org.asciidoctor.jvm.convert") version "3.3.2"
}

repositories {
    mavenCentral()
}

tasks.asciidoctor {
    sources {
        include("**/index.adoc")
    }
    doFirst {
        println(sourceFileTree.files)
        println(secondarySourceFileTree.files)
    }
}
