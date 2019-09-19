# Caching of exec-maven-plugin based yarn execution

This example shows how to setup caching for a yarn based JavaScript build that is executed using the [exec-maven-plugin](https://www.mojohaus.org/exec-maven-plugin).

## Setup

The `pom.xml` of this project features three important configurations:

- The exec-maven-plugin is configured to call `yarn install` followed by `yarn build` in the `compile` phase of the maven lifecyle.
- The maven-clean-plugin is configured to also clean the yarn output directory.
- The gradle-enterprise-maven-extension is configured to cache the `yarn-build` execution of the exec-maven-plugin. The `yarn-install` execution is intentionally not cached, because using yarn's dependency caching and downloading is more efficient than using the cache in this case.
