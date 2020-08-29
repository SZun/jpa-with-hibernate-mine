package com.zun.demo.repository;

import com.zun.demo.entities.Employee;
import com.zun.demo.entities.FullTimeEmployee;
import com.zun.demo.entities.PartTimeEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    public void insert(Employee employee){
        em.persist(employee);
    }

    public List<PartTimeEmployee> retrieveAllPartTimeEmployees(){
        return em.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
    }

    public List<FullTimeEmployee> retrieveAllFullTimeEmployee(){
        return em.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
    }


}
