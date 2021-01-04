package gradlebuild.integrationtests.tasks

import org.gradle.api.tasks.testing.Test

open class IntegrationTest : Test() {
    init {
        doLast {
            val provider = project.rootProject.extensions.extraProperties.properties["TestDistributionBuildContextProvider"]!!
            var classLoader = provider.javaClass.classLoader
            while (classLoader != null) {
                print("ClassLoader $classLoader loading ${provider.javaClass}: ")
                try {
                    classLoader.loadClass(provider.javaClass.name)
                    println("found")
                    classLoader = classLoader.parent
                } catch (e: ClassNotFoundException) {
                    println("not found")
                    classLoader = null
                }
            }
        }
        doLast {
            val method = Test::class.java.declaredMethods.find { it.name == "createTestExecuter" }!!
            method.isAccessible = true
            val testExecuter = method.invoke(this)
            println("TestExecuter $testExecuter was loaded with ${testExecuter.javaClass.classLoader}")
        }
    }
}
