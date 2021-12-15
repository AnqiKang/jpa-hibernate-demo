package com.kang.jpa.hibernate;

import com.kang.jpa.hibernate.entity.*;
import com.kang.jpa.hibernate.repository.CourseRepository;
import com.kang.jpa.hibernate.repository.EmployeeRepository;
import com.kang.jpa.hibernate.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        employeeRepository.insertEmployee(new FullTimeEmployee("Jack", new BigDecimal("10000")));
        employeeRepository.insertEmployee(new PartTimeEmployee("Jill", new BigDecimal("50")));

        logger.info("Full time Employees -> {}", employeeRepository.retrieveAllFullTimeEmployee());
        logger.info("Part time Employees -> {}", employeeRepository.retrieveAllPartTimeEmployee());

    }
}
