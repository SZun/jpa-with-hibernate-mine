package com.zun.demo.repository;

import com.zun.demo.entities.Course;
import com.zun.demo.entities.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.stream.Stream;

@Repository
@Transactional
public class CourseRepository {

    Logger logger = LoggerFactory.getLogger(this.getClass());

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

        Course course2 = findById(10001L);
        course2.setName("JPA Updated");
    }

    public void addReviewsForCourse() {
        Course course = findById(10003L);
        logger.info("course reviews -> {}", course.getReviews());

        Review review = new Review("5", "Great Hands-on Stuff");
        Review review1 = new Review("5", "Hatsoff.");

        Stream.of(review,review1).forEach(i -> {
            course.addReview(i);
            i.setCourse(course);
            em.persist(i);
        });
    }
}
