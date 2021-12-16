package com.kang.jpa.hibernate.repository;

import com.kang.jpa.hibernate.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseSpringDataRepository courseSpringDataRepository;

    @Test
    public void findById_course_present() {
        Optional<Course> courseOptional = courseSpringDataRepository.findById(10001L);
        assertTrue(courseOptional.isPresent());
    }

    @Test
    public void findById_course_not_present() {
        Optional<Course> courseOptional = courseSpringDataRepository.findById(20001L);
        assertFalse(courseOptional.isPresent());
    }

    @Test
    public void create_course() {
        Course course = new Course("Docker");
        courseSpringDataRepository.save(course);
        course.setName("Docker-updated");
        courseSpringDataRepository.save(course);
        assertEquals(course.getName(), "Docker-updated");

    }

    @Test
    public void find_all_courses() {
        List<Course> list = courseSpringDataRepository.findAll();
        logger.info("Course -> {}", list);
        logger.info("Count -> {}", courseSpringDataRepository.count());
    }

    @Test
    public void findByName() {
        logger.info("find by name -> {}", courseSpringDataRepository.findByName("SpringBoot"));

    }

}
