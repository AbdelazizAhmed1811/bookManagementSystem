package com.example.First_sprint_boot;

//import com.example.First_sprint_boot.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class FirstSprintBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstSprintBootApplication.class, args);
    }



}
