# maven-bom-trouble

Showcases issues with transitively imported BOMs.

The problematic BOM is `ru.vyarus.guicey:guicey-bom:5.7.0-1` which is imported in the `child` module. This BOM imports another BOM (`ru.vyarus:dropwizard-guicey:5.7.0`) which again imports another one (`io.dropwizard:dropwizard-dependencies:2.1.4`) which imports the `org.junit:junit-bom:5.9.1` while the `parent` imports `org.junit:junit-bom:5.9.3`.

## Instructions

1. Run `mvn dependency:tree validate -Dverbose` and see the build fail with

```
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ leaf ---
[INFO] org.example:leaf:jar:1.0-SNAPSHOT
[INFO] +- org.junit.jupiter:junit-jupiter:jar:5.9.3:test
[INFO] |  +- org.junit.jupiter:junit-jupiter-api:jar:5.9.1:test (version managed from 5.9.3)
...
[INFO] --- maven-enforcer-plugin:3.3.0:enforce (enforce-same-versions) @ leaf ---
Rule 0: org.apache.maven.enforcer.rules.RequireSameVersions failed with message:
Found entries with different versions
Entries with version 5.9.3
- org.junit.jupiter:junit-jupiter:jar (dependency)
Entries with version 5.9.1
- org.junit.jupiter:junit-jupiter-api:jar (dependency)
- org.junit.jupiter:junit-jupiter-params:jar (dependency)
- org.junit.jupiter:junit-jupiter-engine:jar (dependency)
```
2. Uncomment import of junit-bom in `child/pom.xml` and run `mvn dependency:tree validate -Dverbose` again
```
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ leaf ---
[INFO] org.example:leaf:jar:1.0-SNAPSHOT
[INFO] +- org.junit.jupiter:junit-jupiter:jar:5.9.3:test
[INFO] |  +- org.junit.jupiter:junit-jupiter-api:jar:5.9.3:test
...
[INFO] --- maven-enforcer-plugin:3.3.0:enforce (enforce-same-versions) @ leaf ---
[INFO] Rule 0: org.apache.maven.enforcer.rules.RequireSameVersions passed
[INFO] Rule 1: org.apache.maven.enforcer.rules.RequireSameVersions passed
...
```
