package com.zun.demo.repository;

import com.zun.demo.DemoApplication;
import com.zun.demo.entities.Course;
import com.zun.demo.entities.Review;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
class CourseRepositoryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    void findById() {
        Course course = repository.findById(10001L);
        assertEquals("JPA in 50 Steps", course.getName());
    }

    @Test
    @DirtiesContext
    void deleteById() {
        repository.deleteById(10001L);
        assertNull(repository.findById(10001L));
    }

    @Test
    @DirtiesContext
    void save() {
        Course course = repository.findById(10001L);
        assertEquals("JPA in 50 Steps", course.getName());

        course.setName("JPA in 50 Steps - Updated");

        repository.save(course);

        course = repository.findById(10001L);
        assertEquals("JPA in 50 Steps - Updated", course.getName());
    }

    @Test
    @DirtiesContext
    void playWithEntityManager(){
        repository.playWithEntityManager();
    }

    @Test
    @Transactional
    void retrieveReviewsForCourse(){
        Course course = repository.findById(10003L);
        logger.info("{}", course.getReviews());
    }

    @Test
    @Transactional
    void retrieveCourseForReview(){
        Review review = em.find(Review.class, 50001L);
        logger.info("{}", review.getCourse());
    }

}