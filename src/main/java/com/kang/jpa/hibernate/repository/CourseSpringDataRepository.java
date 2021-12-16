package com.kang.jpa.hibernate.repository;

import com.kang.jpa.hibernate.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//@RepositoryRestResource(path="/courses")

public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String name);

    List<Course> findByNameAndId(String name, Long id);

    List<Course> countByName(String name);

    List<Course> findByNameOrderByIdDesc(String name);

    List<Course> deleteByName(String name);

    @Query("Select c from Course c where c.name like '%50%'")
    List<Course> courseWith50();

    @Query(value = "select * from course c where name like '%50%'", nativeQuery = true)
    List<Course> courseWith50UsingNativeQuery();

    //@Query(name = "this_is_a_named_query")
   // List<Course> courseWithNamedQuery();
}
