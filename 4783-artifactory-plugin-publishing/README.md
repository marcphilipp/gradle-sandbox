# Publishing Gradle Plugin to Artifactory

Sample for https://github.com/gradle/gradle/issues/4783.

## Starting Artifactory

`docker run --name artifactory -d -p 8081:8081 docker.bintray.io/jfrog/artifactory-oss:latest`

## Automatic Publishing (broken)

`./gradlew artifactoryPublish`

## Manual Publishing (working)

`./gradlew artifactoryPublish -PmanualPublishing`
