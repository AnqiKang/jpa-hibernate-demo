package com.kang.jpa.hibernate.repository;

import com.kang.jpa.hibernate.Application;
import com.kang.jpa.hibernate.entity.Course;
import com.kang.jpa.hibernate.entity.Review;
import org.hibernate.graph.SubGraph;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager em;


    @Test
    public void findById_basic() {
        Course course = courseRepository.findById(10001L);
        assertEquals("JPA in 50 steps", course.getName());
    }

    @Test
    @Transactional
    public void findById_firstLevelCacheDemo() {
        Course course = courseRepository.findById(10001L);
        logger.info("First Course Retrieved -> {}", course);

        Course course1 = courseRepository.findById(10001L);
        logger.info("First Course Retrieved -> {}", course1);

        assertEquals("JPA in 50 steps", course.getName());
        assertEquals("JPA in 50 steps", course1.getName());
    }

    @Test
    @DirtiesContext
    public void deleteById_basic() {
        courseRepository.deleteById(10002L);
        assertNull(courseRepository.findById(10002L));
    }

    @Test
    public void save_basic() {
        // get a course from DB
        Course course = courseRepository.findById(10001L);
        assertEquals("JPA in 50 steps", course.getName());

        // update details
        course.setName("JPA in 100 steps");
        courseRepository.save(course);

        // check the value again
        Course course1 = courseRepository.findById(10001L);
        assertEquals("JPA in 100 steps", course1.getName());

    }

    @Test
    @DirtiesContext
    public void testEntityManager() {
        courseRepository.testEntityManager();

    }

    @Test
    @Transactional
    public void retrieveReviewsForCourse() {
        Course course = courseRepository.findById(10001L);
        logger.info("course.getReviews() -> {}", course.getReviews());
    }

    @Test
    @Transactional
    public void retrieveCourseForReviews() {
        Review review = em.find(Review.class, 50001L);
        logger.info("review.getCourse() -> {}", review.getCourse());
    }


}
