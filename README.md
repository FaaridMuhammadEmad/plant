Plant-Backend  with Spring Boot
========================================

This project contains all the apis for getting the power plants mentioned in the test.

Java 11 is needed to run this project.

Clone
--------

```sh
git clone https://github.com/FaaridMuhammadEmad/plant.git
```
Build the project using any tool like IntelliJ

Java 11 is needed to run this project.

Run
--------

```sh
cd power-plant
```

Step 1
--------

```You need to register the user in order to get the access token OR if your user is already registered, jump to Step 2.
```

Register User
--------

### Path to get the access token

```
 https://power-plant-next-gen.herokuapp.com/api/1.0/user
```

JSON Response:

```json
{
"email":"faarid@gmail.com",
"password":"Helloworld123#"
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
