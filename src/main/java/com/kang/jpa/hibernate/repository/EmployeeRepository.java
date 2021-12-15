package com.kang.jpa.hibernate.repository;

import com.kang.jpa.hibernate.entity.Employee;
import com.kang.jpa.hibernate.entity.FullTimeEmployee;
import com.kang.jpa.hibernate.entity.PartTimeEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    // inert an Employee
    public void insertEmployee(Employee employee) {
        em.persist(employee);
    }

    //retrieve all Employee  -- named query
//    public List<Employee> retrieveAllEmployee() {
//        Query query = em.createQuery("select e from Employee e", Employee.class);
//        List<Employee> res = query.getResultList();
//        return res;
//    }

    public List<PartTimeEmployee> retrieveAllPartTimeEmployee() {
        return em.createQuery("Select p from PartTimeEmployee p", PartTimeEmployee.class).getResultList();
    }

    public List<FullTimeEmployee> retrieveAllFullTimeEmployee() {
        return em.createQuery("Select f from FullTimeEmployee f", FullTimeEmployee.class).getResultList();
    }


    public Employee findById(Long id) {
        return em.find(Employee.class, id);
    }
}
