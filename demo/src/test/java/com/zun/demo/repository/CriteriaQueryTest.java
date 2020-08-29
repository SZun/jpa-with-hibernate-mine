package com.zun.demo.repository;

import com.zun.demo.DemoApplication;
import com.zun.demo.entities.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
class CriteriaQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    void findAllCourses() {
        CriteriaQuery<Course> cq = em.getCriteriaBuilder().createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);

        List<Course> courses = em.createQuery(cq.select(courseRoot)).getResultList();
        logger.info("Courses -> {}", courses);
    }

    @Test
    void findAllCoursesLike100Steps() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);

        Predicate like = cb.like(courseRoot.get("name"), "%100 Steps");
        cq.where(like);

        List<Course> courses = em.createQuery(cq.select(courseRoot)).getResultList();
        logger.info("Courses -> {}", courses);
    }

    @Test
    void findAllCoursesWithoutStudents() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);

        Predicate empty = cb.isEmpty(courseRoot.get("students"));
        cq.where(empty);

        List<Course> courses = em.createQuery(cq.select(courseRoot)).getResultList();
        logger.info("Courses -> {}", courses);
    }

    @Test
    void join() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);

        courseRoot.join("students");

        List<Course> courses = em.createQuery(cq.select(courseRoot)).getResultList();
        logger.info("Courses -> {}", courses);
    }

    @Test
    void leftJoin() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);

        courseRoot.join("students", JoinType.LEFT);

        List<Course> courses = em.createQuery(cq.select(courseRoot)).getResultList();
        logger.info("Courses -> {}", courses);
    }

}