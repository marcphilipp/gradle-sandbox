# ant-call-maven

Example on how to configure an ant build that calls maven using a `<macrodef>`.

## Setup

The `build.xml` defines a [MacroDef](https://ant.apache.org/manual/Tasks/macrodef.html) that setup a configurable task called `mvn`.
The then macro configures an [Exec task](https://ant.apache.org/manual/Tasks/exec.html) that calls the [Maven wrapper](https://github.com/takari/maven-wrapper) that is also contained in this repository.

## Publishing Build Scans

The way this example is set up it will publish build scans against https://scans.gradle.com, asking the user to agree to the Terms of Service.
Since programs started by the [Exec task](https://ant.apache.org/manual/Tasks/exec.html) can not react to user input this would cause the build to hang at the end.
For this reason Maven builds are started in batch mode from ant.
This will in turn result in no build scan being published to scans.gradle.com.

There are several options on how to fix this depending on your situation:

- Automatically agree to scans.gradle.com terms of service by adding the [necessary configuration](https://docs.gradle.com/enterprise/maven-extension/#accept_the_scans_gradle_com_terms_of_service) to `gradle-enterprise.xml`.
- Use an [on premise installation](https://gradle.com/pricing/) of Gradle Enterprise.
