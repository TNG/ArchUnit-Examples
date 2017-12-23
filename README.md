# ArchUnit Examples

This module presents some examples on how to use the latest release of 
[ArchUnit](http://archunit.org).

All example rules you can find within `src/test` refer to classes from `src/main`.
These tests are all designed to fail, to demonstrate how production code could violate
typical architectural constraints (like layer dependencies).

You can run them with Gradle

```
./gradlew clean build
```

Otherwise the tests can be run directly from any IDE.