# Lazy configuration of `Javadoc` task reproducer

## Reproduce

```
./gradlew --no-configuration-cache javadoc   # BUILD SUCCESSFUL
./gradlew clean
./gradlew --configuration-cache javadoc      # FAILS
```

Failure (Gradle 9.7.0-rc-1):

```
FAILURE: Build failed with an exception.

* What went wrong:
.../build/since-values.txt (No such file or directory)
...
Configuration cache entry discarded due to serialization error.
```

Relevant stack trace:

```
Caused by: java.io.FileNotFoundException: .../build/since-values.txt (No such file or directory)
	at kotlin.io.FilesKt__FileReadWriteKt.readLines(FileReadWrite.kt:277)
	at Build_gradle$1$1.transform(build.gradle.kts:39)
	at org.gradle.api.internal.provider.ValueSupplier$Present.transform(ValueSupplier.java:520)
	at org.gradle.api.internal.provider.TransformBackedProvider.mapValue(TransformBackedProvider.java:91)
	at org.gradle.api.internal.provider.TransformBackedProvider.calculateExecutionTimeValue(TransformBackedProvider.java:73)
	at org.gradle.internal.serialize.codecs.core.FixedValueReplacingProviderCodec.encodeProvider(ProviderCodecs.kt:102)
	at org.gradle.internal.serialize.codecs.core.ProviderCodec.encode(ProviderCodecs.kt:226)
	...
	at org.gradle.internal.serialize.codecs.core.TaskNodeCodec.writeTask(TaskNodeCodec.kt:109)
```
