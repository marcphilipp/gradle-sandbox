# Publishing and consuming Gradle plugins from custom Maven repositories

There are two subfolders: producer and consumer. The former defines two simple plugins: `org.example.a` and `org.example.b`. The latter consumes these plugins.

See it in action:

1. Run `./gradlew publishAllPublicationsToLocalRepository` in `producer`
1. Inspect `repo` folder to see what was published
1. Run `./gradlew a b` in `consumer` to see the custom tasks being executed
