package com.zun.demo.repository;

import com.zun.demo.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CourseRepository {

    @Autowired
    private EntityManager em;

    public Course findById(Long id){
        return em.find(Course.class, id);
    }

//    public Course save(Course course){}
//
    public void deleteById(Long id){
        Course course = findById(id);
        em.remove(course);
    }

}
