package com.kang.jpa.hibernate.repository;

import com.kang.jpa.hibernate.entity.Course;
import com.kang.jpa.hibernate.entity.Passport;
import com.kang.jpa.hibernate.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StudentRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("N1426");
        em.persist(passport);

        Student student = new Student("Mike");
        student.setPassport(passport);
        em.persist(student);
    }

    public void insertStudentAndCourseHardCode() {
        Student student = new Student("Jack");
        Course course = new Course("Jenkins pipeline");

        em.persist(student);
        em.persist(course);

        student.addCourse(course);
        course.addStudent(student);
        em.persist(student);

    }
    public void insertStudentAndCourse(Student student, Course course){
        em.persist(student);
        em.persist(course);

        student.addCourse(course);
        course.addStudent(student);
        em.persist(student);
    }


}
