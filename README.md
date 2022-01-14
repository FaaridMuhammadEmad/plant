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

Step 1 - Register User
--------

You need to register the user in order to get the access token OR if your user is already registered, jump to Step 2.


### Endpoint

```
 https://power-plant-next-gen.herokuapp.com/api/1.0/user
```

### Body
```json
{
"email":"faarid@gmail.com",
"password":"Helloworld123#"
}
```

### JSON Response

```json
{
    "status": "successful",
    "message": "User created successfully",
    "code": 200,
}
```

Step 2 - Signin User
--------

You need to signin the user in order to get the access token for user authentication.


### Endpoint

```
 https://power-plant-next-gen.herokuapp.com/oauth/token
 ```

### Headers
```
 Authorization: 'Basic c29jb2w6c2VjcmV0'
```

### Form Data
```
 authorization: 'Basic c29jb2w6c2VjcmV0'
 grant_type: 'password'
 username: 'faarid@gmail.com'
 password: 'Hello123#'
```

### JSON Response

```json
{
    "access_token": "1568faa0-1fbb-419c-8c65-1d3940cbdd3c",
    "token_type": "bearer",
    "refresh_token": "5ede15c4-6477-4965-9817-a77b4de562a8",
    "expires_in": 86158,
    "scope": "read write trust"
}
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
