package com.kang.jpa.hibernate.repository;

import com.kang.jpa.hibernate.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NativeQueryTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void native_query_basic() {
        Query query = em.createNativeQuery("select * from course", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("select * from course ---> {}", resultList);
    }

    @Test
    public void native_query_where() {
        Query query = em.createNativeQuery("select * from course where id = ?", Course.class);
        query.setParameter(1, 10001L);
        List<Course> resultList = query.getResultList();
        logger.info("select * from course where id = ? ---> {}", resultList);
    }

    @Test
    public void native_query_named_parameter() {
        Query query = em.createNativeQuery("select * from course where id = :id", Course.class);
        query.setParameter("id", 10001L);
        List<Course> resultList = query.getResultList();
        logger.info("select * from course where id = ? ---> {}", resultList);
    }

    @Test
    @Transactional
    public void update_all_updated_timestamp() {
        Query query = em.createNativeQuery("update course set last_updated_date = sysdate", Course.class);
        int numberOfRowsUpdated = query.executeUpdate();
        logger.info("update course set last_updated_date = sysdate ---> {}", numberOfRowsUpdated);
    }


}
