package com.kang.jpa.hibernate.repository;

import com.kang.jpa.hibernate.entity.Course;
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

}
