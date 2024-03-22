package com.talentreef.interviewquestions;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.talentreef")
@EntityScan(basePackages = "com.talentreef")
@EnableAutoConfiguration
public class InterviewQuestionsApplication{

  public static void main(String[] args) {
    //TODO Check why it is not reading .env variables without this configuration
    Dotenv dotenv = Dotenv.load();
    dotenv.entries().forEach((envVariable) -> {
      System.setProperty(envVariable.getKey(),envVariable.getValue());
    });
    SpringApplication.run(InterviewQuestionsApplication.class, args);
  }

}
