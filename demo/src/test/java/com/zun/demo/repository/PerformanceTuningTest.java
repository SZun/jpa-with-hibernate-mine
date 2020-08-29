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

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
class PerformanceTuningTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    void NPlusOneProblem() {
        List<Course> courses = em.createQuery("query_get_all_courses", Course.class).getResultList();
        courses.forEach(i -> logger.info("Courses -> {} Students -> {}", i, i.getStudents()));
    }

    @Test
    @Transactional
    void NPlusOneProblemSolvedWithEntityGraph() {

        EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
        entityGraph.addSubgraph("students");

        List<Course> courses = em.createQuery("query_get_all_courses", Course.class)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();

        courses.forEach(i -> logger.info("Courses -> {} Students -> {}", i, i.getStudents()));
    }

    @Test
    @Transactional
    void NPlusOneProblemSolvedWithJoinFetch() {
        List<Course> courses = em.createQuery("query_get_all_courses_with_join_fetch", Course.class).getResultList();
        courses.forEach(i -> logger.info("Courses -> {} Students -> {}", i, i.getStudents()));
    }


}