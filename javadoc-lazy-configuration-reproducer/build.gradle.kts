import org.gradle.external.javadoc.JavadocOptionFileOption
import org.gradle.external.javadoc.internal.JavadocOptionFileWriterContext

plugins {
    java
}

val generateSinceValues = tasks.register("generateSinceValues") {
    val outputFile = layout.buildDirectory.file("since-values.txt")
    outputs.file(outputFile)
    doLast {
        outputFile.get().asFile.writeText("1.0\n2.0\n")
    }
}

tasks.javadoc {
    val valuesFile = generateSinceValues.map { it.outputs.files.singleFile }
    inputs.file(valuesFile)
        .withPathSensitivity(PathSensitivity.NONE)
        .withPropertyName("since-values-file")
    (options as StandardJavadocDocletOptions).apply {
        addOption(ValuesOption("-since", valuesFile.map { file ->
            file.readLines().filter { it.isNotBlank() }
        }))
    }
}

// Custom option that lazily reads its values from a Provider (like JUnit's JavadocValuesOption)
class ValuesOption(private val option: String, private var values: Provider<List<String>>) :
    JavadocOptionFileOption<Provider<List<String>>> {

    override fun getOption() = option

    override fun getValue() = values

    override fun setValue(value: Provider<List<String>>) {
        this.values = value
    }

    override fun write(writerContext: JavadocOptionFileWriterContext) {
        writerContext.writeValuesOption(option, values.get(), ",")
    }
}
