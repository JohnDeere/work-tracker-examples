[![Build Status](https://travis-ci.org/JohnDeere/work-tracker-examples.svg?branch=master)](https://travis-ci.org/JohnDeere/work-tracker-examples)
[![DepShield Badge](https://depshield.sonatype.org/badges/JohnDeere/work-tracker-examples/depshield.svg)](https://depshield.github.io)

# Work Tracker Examples
Some examples of how to use the [Work Tracker](https://github.com/JohnDeere/work-tracker) library.

The JDK 8 based examples are in the master branch.
The JDK 11 based examples are in the JDK11 branch.

## Running the Examples
### [Spring Boot](./spring-boot-example):

Since `work-tracker-spring-example` is an example project, the credentials are User:`user` and Password:`password`
```bash
cd spring-boot-example
mvn clean spring-boot:run
```

### [Spring](./spring-example):

```bash
cd spring-example
mvn clean jetty:run
```

### [Java](./java-example):

```bash
cd java-example
mvn clean jetty:run
```

---

## Contributing to these example applications
Please see the [Contribution Guidelines](./.github/CONTRIBUTING.md).

### Running tests
```bash
mvn clean verify
```

## Bump Version For Release
Run the following bash command and commit the change:
```bash
bash build/bump_version.sh MAJOR|MINOR|PATCH
```   

Example:
```bash
bash build/bump_version.sh MINOR
```
