plugins {
    id("org.asciidoctor.jvm.convert") version "4.0.0-alpha.1"
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
