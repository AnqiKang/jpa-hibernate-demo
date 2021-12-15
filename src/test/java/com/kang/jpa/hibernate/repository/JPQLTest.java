package com.kang.jpa.hibernate.repository;

import com.kang.jpa.hibernate.entity.Course;
import com.kang.jpa.hibernate.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void jpql_basic() {
        Query query = em.createNamedQuery("query_get_all_courses");
        List resultList = query.getResultList();
        logger.info("Select c from Course c ---> {}", resultList);
    }

    @Test
    public void jpql_typed() {
        TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c from Course c (typed) ---> {}", resultList);
    }

    @Test
    public void jpql_where() {
        TypedQuery<Course> query = em.createNamedQuery("query_get_50_steps_courses", Course.class);
        List<Course> res = query.getResultList();
        logger.info("select c from Course c where c.name like '%50 steps' -> {}", res);
    }

    @Test
    public void jpql_courses_without_students() {
        TypedQuery<Course> query = em.createQuery("Select c from Course c where c.students is empty", Course.class);
        List<Course> res = query.getResultList();
        logger.info(" Select c from Course c where c.students is empty -> {}", res);
    }

    @Test
    public void jpql_courses_with_atleast_2_students() {
        TypedQuery<Course> query = em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
        List<Course> res = query.getResultList();
        logger.info(" Select c from Course c where size(c.students) >= 2 -> {}", res);
    }

    @Test
    public void jpql_order_courses_by_number_of_students() {
        TypedQuery<Course> query = em.createQuery("select c from Course c order by size(c.students) desc", Course.class);
        List<Course> res = query.getResultList();
        logger.info(" select c from Course c order by size(c.students) desc -> {}", res);
    }

    @Test
    public void jpql_students_with_passports_in_a_certain_pattern() {
        TypedQuery<Student> query = em.createQuery("select s from Student s where s.passport.number like '%123%'", Student.class);
        List<Student> res = query.getResultList();
        logger.info("select s from Student s where s.passport.number like '%123%' -> {}", res);
    }

    @Test
    public void join() {
        Query query = em.createQuery("select c, s from Course c join c.students s");
        List<Object[]> res = query.getResultList();
        logger.info("size -> {}", res.size());

        res.forEach(r -> {
            logger.info("Course -> {}, Student ->{}", r[0], r[1]);
        });

    }

    @Test
    public void left_join() {
        Query query = em.createQuery("select c, s from Course c left join c.students s");
        List<Object[]> res = query.getResultList();
        logger.info("size -> {}", res.size());

        res.forEach(r -> {
            logger.info("Course -> {}, Student ->{}", r[0], r[1]);
        });

    }
    @Test
    public void cross_join() {
        Query query = em.createQuery("select c, s from Course c, Student s");
        List<Object[]> res = query.getResultList();
        logger.info("size -> {}", res.size());

        res.forEach(r -> {
            logger.info("Course -> {}, Student ->{}", r[0], r[1]);
        });

    }


}
