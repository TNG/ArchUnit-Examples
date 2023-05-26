# ArchUnit Examples

This module presents some examples on how to use the latest release of 
[ArchUnit](http://archunit.org).

The different subprojects demonstrate the type of test support: 
* `example-junit4` shows how to use the JUnit 4 test support including the `ArchUnitRunner`
* `example-junit5` shows how to use the JUnit 5 test support where test classes are simply being picked up by being annotated with `@AnalyzeClasses`
* `example-plain` shows how to use ArchUnit independently of any specific test framework, even though as a runtime environment these tests use JUnit 4 as well

All example rules you find within `src/test` refer to classes from `src/main`.
These tests are all designed to fail, to demonstrate how production code could violate
typical architectural constraints (like layer dependencies).

You can run them with Gradle

```
./gradlew build
```

Otherwise the tests can be run directly from any IDE.

## Regarding issues

If you have found any issues with the examples or have any question, please direct them to the [main repository](https://github.com/TNG/ArchUnit/issues) instead. This repository is an autogenerated version of the latest released version of https://github.com/TNG/ArchUnit/tree/main/archunit-example (with some simplified project setup for illustration)
