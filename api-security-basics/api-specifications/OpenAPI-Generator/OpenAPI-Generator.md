# OpenAPI Generator

## Setup

Download the openapi-generator-cli-5.2.1.jar file from the Maven Repository and store it in a local directory.
```
$ java -jar generator/openapi-generator-cli-5.2.1.jar help
usage: openapi-generator-cli <command> [<args>]

The most commonly used openapi-generator-cli commands are:
    author        Utilities for authoring generators or customizing templates.
    batch         Generate code in batch via external configs.
    config-help   Config help for chosen lang
    generate      Generate code with the specified generator.
    help          Display help information about openapi-generator
    list          Lists the available generators
    meta          MetaGenerator. Generator for creating a new template set and configuration for Codegen.  The output will be based on the language you specify, and includes default templates to include.
    validate      Validate specification
    version       Show version information used in tooling
```

## Generate the Service Implementation

```
$ java -jar generator/openapi-generator-cli-5.2.1.jar generate -g spring --library spring-boot -i ArticleService.yaml -o ./ -p groupId=org.se.lab -p artifactId=REST-ArticleService -p artifactVersion=1.0.0-SNAPSHOT

$ tree src/
src/
└── main
    ├── java
    │   └── org
    │       └── openapitools
    │           ├── api
    │           │   ├── ApiUtil.java
    │           │   ├── ArticlesApiController.java
    │           │   └── ArticlesApi.java
    │           ├── configuration
    │           │   ├── HomeController.java
    │           │   └── OpenAPIDocumentationConfig.java
    │           ├── model
    │           │   └── Article.java
    │           ├── OpenAPI2SpringBoot.java
    │           └── RFC3339DateFormat.java
    └── resources
        └── application.properties
```



## References
* [OpenAPI Generator](https://openapi-generator.tech/)
* [Config Options for Spring](https://openapi-generator.tech/docs/generators/spring)

* [Maven Repository](https://mvnrepository.com/artifact/org.openapitools/openapi-generator-cli/5.2.1)


*Egon Teiniker, 2020-2021, GPL v3.0*