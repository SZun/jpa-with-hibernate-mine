package com.zun.demo.repository;

import com.zun.demo.DemoApplication;
import com.zun.demo.entities.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
class CourseSpringDataRepositoryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseSpringDataRepository repository;

    @Test
    void findByIdPresent() {
        Optional<Course> course = repository.findById(10001L);
        assertTrue(course.isPresent());
    }

    @Test
    void findByIdNotPresent() {
        Optional<Course> course = repository.findById(20001L);
        assertFalse(course.isPresent());
    }

    @Test
    void playGround(){
//        Course course = new Course("Microservices in 100 steps");
//        repository.save(course);
//        course.setName("Microservices in 100 steps - UPDATED");
//        repository.save(course);
        logger.info("Courses -> {}", repository.findAll());
        logger.info("Count -> {}", repository.count());
    }

    @Test
    void sort(){
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
//        Sort sort = Sort.by(Sort.Direction.DESC, "name")
//                .and(Sort.by(Sort.Direction.ASC, "created_data"));
        logger.info("Courses -> {}", repository.findAll(sort));
        logger.info("Count -> {}", repository.count());
    }

    @Test
    void pagination(){
        PageRequest pageRequest = PageRequest.of(0, 3);

        Page<Course> firstPage = repository.findAll(pageRequest);
        logger.info("First Page -> {}", firstPage);
        logger.info("First Page Content -> {}", firstPage.getContent());

        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = repository.findAll(secondPageable);
        logger.info("Second Page Content -> {}", secondPage.getContent());
    }

    @Test
    void findByName(){
        List<Course> courses = repository.findByName("JPA in 50 Steps");
    }

}