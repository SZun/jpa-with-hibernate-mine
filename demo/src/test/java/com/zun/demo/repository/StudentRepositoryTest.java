package com.zun.demo.repository;

import com.zun.demo.DemoApplication;
import com.zun.demo.entities.Address;
import com.zun.demo.entities.Passport;
import com.zun.demo.entities.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
class StudentRepositoryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    void findById() {
        Student student = em.find(Student.class, 20001L);
        logger.info("student -> {}", student);
        logger.info("passport -> {}", student.getPassport());
    }

    @Test
    @Transactional
    void findByIdOtherDirection() {
        Passport passport = em.find(Passport.class, 20001L);
        logger.info("passport -> {}", passport);
        logger.info("student -> {}", passport.getStudent());
    }

    @Test
    @Transactional
    public void retrieveStudentAndCourse(){
        Student student = em.find(Student.class, 20001L);
        logger.info("student -> {}", student);
        logger.info("course -> {}", student.getCourses());
    }

    @Test
    @Transactional
    public void setAddressDetails(){
        Student student = em.find(Student.class, 20001L);
        student.setAddress(new Address("No 101", "Some Street", "Hyderabad"));
        em.flush();
        logger.info("student -> {}", student);
        logger.info("course -> {}", student.getCourses());
    }

}