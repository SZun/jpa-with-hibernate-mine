package com.zun.demo.repository;

import com.zun.demo.DemoApplication;
import com.zun.demo.entities.Course;
import com.zun.demo.entities.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    void findById() {
        List res = em.createQuery("Select c from Course c").getResultList();
        logger.info("res -> {}", res);
    }

    @Test
    void findByIdTyped() {
        List res = em.createNamedQuery("query_get_all_courses", Course.class).getResultList();
        logger.info("res -> {}", res);
    }

    @Test
    void findByIdWhere() {
        List res = em.createNamedQuery("query_get_100_step_courses", Course.class).getResultList();
        logger.info("res -> {}", res);
    }

    @Test
    void coursesWithoutStudents(){
       List res = em.createQuery("select c from Courses c where c.students is empty", Course.class).getResultList();
       logger.info("res -> {}", res);
    }

    @Test
    void coursesWithOrderedByStudents(){
        List res = em.createQuery("select c from Courses c ordered by size(c.students) desc", Course.class).getResultList();
        logger.info("res -> {}", res);
    }

    @Test
    void studentPassportLike1234(){
        List res = em.createQuery("select s from Students s where s.passport.number like '%1234%'", Student.class).getResultList();
        logger.info("res -> {}", res);
    }

    @Test
    void selectStudentsCoursesJoin(){
        List<Object[]> res = em.createQuery("Select c,s from Course c JOIN c.students s").getResultList();
        logger.info("res.size() -> {}", res.size());
        res.forEach(i -> logger.info("Course{} Student{}", i[0], i[1]));
    }

    @Test
    void selectStudentsCoursesLeftJoin(){
        List<Object[]> res = em.createQuery("Select c,s from Course c LEFT JOIN c.students s").getResultList();
        logger.info("res.size() -> {}", res.size());
        res.forEach(i -> logger.info("Course{} Student{}", i[0], i[1]));
    }

    @Test
    void selectStudentsCoursesCrossJoin(){
        List<Object[]> res = em.createQuery("Select c,s from Course c, Student s").getResultList();
        logger.info("res.size() -> {}", res.size());
        res.forEach(i -> logger.info("Course{} Student{}", i[0], i[1]));
    }

}