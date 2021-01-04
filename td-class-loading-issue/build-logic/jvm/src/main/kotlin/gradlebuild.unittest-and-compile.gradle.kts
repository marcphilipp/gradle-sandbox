import com.gradle.enterprise.gradleplugin.testdistribution.TestDistributionPlugin
import gradlebuild.integrationtests.tasks.IntegrationTest

plugins.apply(TestDistributionPlugin::class.java)

tasks {
    withType<Test>().configureEach {
        useJUnitPlatform()
        distribution {
            enabled.set(true)
            maxLocalExecutors.set(0)
        }
    }
    register<IntegrationTest>("integrationTest")
}
