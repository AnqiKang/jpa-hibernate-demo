package com.kang.jpa.hibernate.repository;

import com.kang.jpa.hibernate.entity.Passport;
import com.kang.jpa.hibernate.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EntityManager em;

//    @Test
//    @Transactional
//    public void someTest() {
//        // DB Operation 1: retrieve student
//        Student student = studentRepository.findById(20001L);
//        // DB Operation 2: retrieve passport
//        Passport passport = student.getPassport();
//        // DB Operation 3: update student
//        passport.setNumber("NEW1111");
//        // DB Operation 4: update passport
//        student.setName("Anqi Kang");
//    }

    @Test
    @Transactional
    public void retrieveStudentAndPassword() {
        Student student = em.find(Student.class, 20001L);
        logger.info("Student -> {}", student);
        logger.info("passport  -> {}", student.getPassport());

    }

    @Test
    @Transactional
    public void retrievePassportAssociatedStudent() {
        Passport passport = em.find(Passport.class, 40001L);
        logger.info("Passport -> {}", passport);
        logger.info("Student  -> {}", passport.getStudent());

    }
}
