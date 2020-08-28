package com.zun.demo.repository;

import com.zun.demo.DemoApplication;
import com.zun.demo.entities.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
class StudentRepositoryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    void findById() {
        Student student = em.find(Student.class, 20001L);
        logger.info("student -> {}", student);
        logger.info("passport -> {}", student.getPassport());
    }

}