# TalentReef Java API Interview Template

## Description

This [Spring Boot](https://spring.io/projects/spring-boot) project is provided as a starting template for the TalentReef take-home interview. Feel free to make whatever modifications are necessary to complete the exercise.

## Requirements

Java 17 -- Java can be acquired using [SDKMAN!](https://sdkman.io/)

## Running the Application

Start the server using Gradle:

```shell
./gradlew bootRun
```

See [Running your Application with Gradle](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/#running-your-application) for more information.

Execute tests using Gradle:

```shell
./gradlew test
```


## Local development

### Environment variables
Under the project directory there will be a file named `example.env` which is an example for creating our own .env file. `SPRING_PROFILES_ACTIVE` will choose the active spring profile which means a different application.yml file will be used following the convention application-${SPRING_PROFILES_ACTIVE}.yml. In then case that the file does not exist with the desired environment a file should be created following the convention
### Database connection
The default database service is MySQL, since xampp provides a simple and light MySQL service we recommend downloading it but using any MySQL service should work
You can [download Xampp here](https://www.apachefriends.org/download.html)
Once you have your database service you will have to create a database named `mitratech`

## Additional Information

TalentReef will provide you the contact information of a person who can answer questions about the exercise.
