package com.kang.jpa.hibernate.repository;

import com.kang.jpa.hibernate.entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
        Course course2 = new Course("Angular JS");
        em.persist(course2);
        em.flush();

        course1.setName("Web Service updated");
        course2.setName("Angular JS updated");

        em.refresh(course1);
        em.flush();

    }
}
