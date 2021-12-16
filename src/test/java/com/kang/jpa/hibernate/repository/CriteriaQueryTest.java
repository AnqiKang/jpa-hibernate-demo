package com.kang.jpa.hibernate.repository;

import com.kang.jpa.hibernate.Application;
import com.kang.jpa.hibernate.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CriteriaQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void retrieveAllCourse() {
        // define "select c from Course c" using Java
        // 1. User Criteria Builder to create a Criteria Query returning the expected result object.
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3, define predicates etc using Criteria Builder
        // 4: add Predicate etc to the Criteria Query
        // 5: build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> res = query.getResultList();
        logger.info("Typed Query -> {}", res);

    }

    @Test
    public void all_courses_having_50(){
        // select c from Course c where name like '%50%
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);

        // define predicates etc using Criteria Builder --like
        Predicate like50 = cb.like(courseRoot.get("name"),"%50%");
        // 4: add Predicate etc to the Criteria Query -- where
        cq.where(like50);

        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> res = query.getResultList();
        logger.info("Typed Query -> {}", res);

    }

    @Test
    public void courses_without_student(){
        // select c from Course c where c.students is empty
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);

        Predicate withoutStudent = cb.isEmpty(courseRoot.get("students"));
        cq.where(withoutStudent);

        TypedQuery<Course> query  = em.createQuery(cq.select(courseRoot));
        List<Course> res = query.getResultList();
        logger.info("Typed Query -> {}", res);
    }

    @Test
    public void course_join_student(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);

        Join<Object, Object> join = courseRoot.join("students");

        TypedQuery<Course> query  = em.createQuery(cq.select(courseRoot));
        List<Course> res = query.getResultList();
        logger.info("Typed Query -> {}", res);
    }

    @Test
    public void course_left_join_student(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);

        Join<Object, Object> join = courseRoot.join("students",JoinType.LEFT);

        TypedQuery<Course> query  = em.createQuery(cq.select(courseRoot));
        List<Course> res = query.getResultList();
        logger.info("Typed Query -> {}", res);
    }
}
