package com.zun.demo;

import com.zun.demo.entities.Review;
import com.zun.demo.repository.CourseRepository;
import com.zun.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Review> reviews = Arrays.asList(
                new Review("5", "Great Hands-on Stuff"),
                new Review("5", "Hatsoff.")
        );
        courseRepository.addReviewsForCourse(10003L, reviews);
    }
}
