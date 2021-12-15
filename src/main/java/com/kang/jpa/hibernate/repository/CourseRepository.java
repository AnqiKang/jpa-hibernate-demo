package com.kang.jpa.hibernate.repository;

import com.kang.jpa.hibernate.entity.Course;
import com.kang.jpa.hibernate.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }


    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            em.persist(course); // insert
        } else {
            em.merge(course); // update
        }
        return course;
    }

    public void testEntityManager() {
        Course course1 = new Course("Web Services");
        em.persist(course1);

        course1.setName("Web Service updated");

        Course course2 = findById(10001L);
        course2.setName("JPA in 50 steps -- updated");

    }

    public void addReviewsForCourseHardCode() {
        // get the course
        Course course = findById(10003L);
        logger.info("Course.getReviews()-> {}", course.getReviews());
        // add 2 reviews to it  and set review to specific course, set the course on the review
        Review review1 = new Review("4", "new Review 1");
        Review review2 = new Review("5", "new Review 2");
        // set relationship
        course.addReview(review1);
        review1.setCourse(course);
        course.addReview(review2);
        review2.setCourse(course);

        // save it to DB
        em.persist(review1);
        em.persist(review2);
    }

    // avoid hard code
    public void addReviewsForCourse(Long courseId, List<Review> reviewList) {
        Course course = findById(courseId);
        logger.info("course.getReview() -> {}", course.getReviews());

        for (Review review : reviewList) {
            course.addReview(review);
            review.setCourse(course);
            em.persist(review);
        }


    }
}
