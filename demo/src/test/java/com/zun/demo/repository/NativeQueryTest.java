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
import javax.transaction.Transactional;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
class NativeQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    void findAll() {
        List res = em.createNativeQuery("select * from course", Course.class).getResultList();
        logger.info("res -> {}", res);
    }

    @Test
    void findById() {
        List res = em.createNativeQuery("select * from course where id = ?", Course.class)
                .setParameter("id", 10001L).getResultList();

//        List res = em.createNativeQuery("select * from course where id = :id", Course.class)
//                .setParameter(1, 10001L).getResultList();

        logger.info("res -> {}", res);
    }

    @Test
    @Transactional
    void updateAll() {
        int numRowsUpdate = em.createNativeQuery("Update Course set last_updated_date = sysdate()", Course.class)
                .executeUpdate();
        logger.info("res -> {}", numRowsUpdate);
    }

}