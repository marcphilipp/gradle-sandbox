import javax.inject.Inject

plugins {
	`jvm-toolchains`
}

interface HelloParameters : WorkParameters {
	val message: Property<String>
}

abstract class HelloAction : WorkAction<HelloParameters> {
	override fun execute() {
		println("${parameters.message.get()} from Java ${System.getProperty("java.version")}")
	}
}

abstract class HelloTask : DefaultTask() {

	@get:Inject
	abstract val workerExecutor: WorkerExecutor

	@get:Nested
	abstract val javaLauncher: Property<JavaLauncher>

	@TaskAction
	fun run() {
		workerExecutor.processIsolation {
			forkOptions.executable = javaLauncher.get().executablePath.asFile.absolutePath
		}.submit(HelloAction::class) {
			message = "Hello"
		}
	}
}

// Passes with -Pjdk=25, fails with the default (28)
tasks.register<HelloTask>("hello") {
	javaLauncher = javaToolchains.launcherFor {
		languageVersion = JavaLanguageVersion.of(providers.gradleProperty("jdk").getOrElse("28"))
	}
}
