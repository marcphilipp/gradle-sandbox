plugins {
    id("org.asciidoctor.jvm.convert") version "4.0.4"
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
