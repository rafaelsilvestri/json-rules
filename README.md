# Json Rules

A json based rules engine that orchestrate external api calls or class execution 
based on a json file and human-readable syntax.

## Getting Started
[My Fake API](https://my-json-server.typicode.com/rafaelsilvestri/json-rules)

Get Definitions:
```
curl --location --request GET 'http://localhost:8080/validators/nome/configuration'
```

Invoke external API and returns success:
```
curl --location --request POST 'http://localhost:8080/validators' \
--header 'Content-Type: application/json' \
--data-raw '{
    "onStep":"step1",
    "processName":"process_xpto",
    "type": "xpto"
}'
```

Invoke external API and returns fail:
```
curl --location --request POST 'http://localhost:8080/validators' \
--header 'Content-Type: application/json' \
--data-raw '{
    "onStep":"step1",
    "processName":"return_404",
    "type": "xpto"
}'
```
Invoke a implementation class into the same codebase:
````
curl --location --request POST 'http://localhost:8080/validators' \
--header 'Content-Type: application/json' \
--data-raw '{
    "onStep":"step2",
    "processName":"process_bar",
    "type": "bar"
}'
````

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.4/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.4/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.4/reference/htmlsingle/#boot-features-developing-web-applications)
* [Json Placeholder](https://jsonplaceholder.typicode.com/)


### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

