Plant-Backend  with Spring Boot
========================================

This project contains all the apis for getting the power plants mentioned in the test.

Java 11 is needed to run this project.

Clone
--------

```sh
git clone https://github.com/FaaridMuhammadEmad/plant.git
```

Run
--------

```sh
cd power-plant
```

```sh
./gradlew bootRun
```

Access
--------

### Path to get the access token

```
http://localhost:8081/oauth/token
```

JSON Response:

```json
{
    "access_token": "784aa790-34b5-4a2f-878a-3213ed535ba5",
    "token_type": "bearer",
    "refresh_token": "c88489dd-71a0-40d3-8b9d-f8370e50cc9d",
    "expires_in": 86399,
    "scope": "read write trust"
}
```

### Update

```
http://localhost:8080/update?name=Kyoto
```

JSON Response:

```json
[{"id":1,"name":"Kyoto"},{"id":2,"name":"New York"},{"id":3,"name":"London"}]
```

Edit
--------

Use Eclipse 4.4 or above.

Generate all Eclipse files with Gradle.

```sh
./gradlew eclipse
```

License
-------

Apache License, Version 2.0

[doma]: https://github.com/domaframework/doma
[spring-boot]: https://github.com/spring-projects/spring-boot
