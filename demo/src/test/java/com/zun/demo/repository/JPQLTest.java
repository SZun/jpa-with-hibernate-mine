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
        List res = em.createQuery("Select c from Course c", Course.class).getResultList();
        logger.info("res -> {}", res);
    }

    @Test
    void findByIdWhere() {
        List res = em.createQuery("Select c from Course c where name like '%100 Steps'", Course.class).getResultList();
        logger.info("res -> {}", res);
    }

}