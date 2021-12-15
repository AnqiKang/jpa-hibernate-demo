package com.kang.jpa.hibernate;

import com.kang.jpa.hibernate.entity.Course;
import com.kang.jpa.hibernate.entity.Review;
import com.kang.jpa.hibernate.entity.Student;
import com.kang.jpa.hibernate.repository.CourseRepository;
import com.kang.jpa.hibernate.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        List<Review> reviews = new ArrayList<>(Arrays.asList(
//                new Review("1", "new review -- 1"),
//                new Review("2", "new review -- 2")));
//
//        courseRepository.addReviewsForCourse(10003L, reviews);

        studentRepository.insertStudentAndCourse(new Student("Jack"), new Course("Jenkins pipeline"));


    }
}
