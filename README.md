Getting Started
========================================

This project contains all the apis for getting the power plants mentioned in the test.

Java 11 is needed to run this project.

Application has been deployed to Heroku (free tier) so it will take sometime for the first hit of the API, and then will work eventually with less time from second hit onwards.

Clone by Github Repository
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

Step 2 - Sign in User
--------

You need to sign in the user in order to get the access token for user authentication.


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

Step 3 - Top and Bottom N Plants
--------

1. Ability to display top N and bottom N plants in either descending or ascending order by total generation output.
2. The value of n will be send in path variable as you can see in the url mentioned below.
3. The bearer token is your access token you will get once after successfully getting the response from the Sign in API.


### Endpoint

```
 https://power-plant-next-gen.herokuapp.com/api/1.0/plant/11
 ```

### Authorization
```
Bearer Token: f7afaa62-0680-4881-b974-48e1461be36b
```

### Response

```
1. Two separate lists named topList and bottomLists of the plants
2. In top list, the plants will be arranged with the maximum generation output on the first and so on.
3. In bottom list, the plants will be arranged with the least generation output on the first and so on.
```


Step 4 - Power Plant By Location
--------

1. Ability to filter power plants by location
2. The location code will be send in request param (along with the URL) as you can see in the url mentioned below.
3. The bearer token is your access token you will get once after successfully getting the response from the Sign in API.


### Endpoint

```
 https://power-plant-next-gen.herokuapp.com/api/1.0/plant?location=AL
 ```

### Authorization
```
Bearer Token: f7afaa62-0680-4881-b974-48e1461be36b
```

### Response

```
A list of plants those who have the same location you passed in the url.
```


Step 5 - Paginate Power Plants By Location
--------

1. Ability to paginate filtered power plants by location
2. The location code will be send in request param.
3. The page size and page number will be send in request param (along with the URL) as you can see in the url mentioned below.
4. The bearer token is your access token you will get once after successfully getting the response from the Sign in API.


### Endpoint

```
 https://power-plant-next-gen.herokuapp.com/api/1.0/plant/pagination/?location=AK&pageSize=17&pageNumber=2
 ```

### Authorization
```
Bearer Token: f7afaa62-0680-4881-b974-48e1461be36b
```

### Response

```
1. Page containing list of filtered plants by location.
2. That page will be containing that number of plants which you passed in page size.
3. The page you will be shown is that number which you passed in url.
```

Step 6 - Details Of Power Plants
--------

1. Ability to see details of a single power plant
2. The id of the plant will be send in the path variable.
3. The bearer token is your access token you will get once after successfully getting the response from the Sign in API.

### Endpoint

```
 https://power-plant-next-gen.herokuapp.com/api/1.0/plant/details/101
 ```

### Authorization
```
Bearer Token: f7afaa62-0680-4881-b974-48e1461be36b
```

### Response

```
Details of the plants having that id.
```


Step 7 - Actual And Percentage Value Of Plants Generation Output
--------

1. Show both actual and percentage value of the plants generation output by location
2. The location code of the plant will be send in the request param along with the url mentioned below.
3. The bearer token is your access token you will get once after successfully getting the response from the Sign in API.


### Endpoint

```
 https://power-plant-next-gen.herokuapp.com/api/1.0/plant/percentage?location=AK
 ```

### Authorization
```
Bearer Token: f7afaa62-0680-4881-b974-48e1461be36b
```

### Response

```
1. List of all the plants of that location with their actual values
2. Percentage of the generation output of the plants having same name is displayed along with each list
```

Step 8 - Run Unit Test Case
--------

```
1. Go to the IntelliJ
2. Open test folder and right click on java folder
3. Run All Test
4. Moreover, in the root folder, go to coverage folder and open index.html
5. Click on plant.service and you can review the lines of code and percentage of code that has been covered in the Plant's Service.
6. Mockito has been integrated for unit testing
```

Step 9 - Run Docker Image Container
--------

```
1. A Dockerfile has been created in the root directory
2. After building the JAR file, it has been converted into a Docker container
3. The docker container has been pushed to a repository at Docker Hub
4. To clone, and run the docker container from that repository, copy paste the following command
```

### Command

```
docker run -p 8081:8081 faarid10/power-plant
```
 
Step 9 - Swagger Documentation
--------

### URL

```
https://power-plant-next-gen.herokuapp.com/swagger-ui/index.html
```

