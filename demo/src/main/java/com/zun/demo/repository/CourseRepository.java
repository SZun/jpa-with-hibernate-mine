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

    public Course save(Course course){
        if(course.getId() == null){
            em.persist(course);
        } else {
            em.merge(course);
        }
        return course;
    }

    public void deleteById(Long id){
        Course course = findById(id);
        em.remove(course);
    }

    public void playWithEntityManager(){
        Course course = new Course("Spring MVC");
        em.persist(course);
        em.flush();

        course.setName("Spring MVC Updated");
        em.flush();

        Course course2 = new Course("Angular in 50 steps");
        em.persist(course2);
        em.flush();

//        em.clear();
        em.detach(course2);

        course.setName("Angular in 50 steps - Updated");
        em.flush();

    }
}
