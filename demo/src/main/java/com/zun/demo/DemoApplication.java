package com.zun.demo;

import com.zun.demo.entities.FullTimeEmployee;
import com.zun.demo.entities.PartTimeEmployee;
import com.zun.demo.repository.CourseRepository;
import com.zun.demo.repository.EmployeeRepository;
import com.zun.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal(50)));
        employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal(10000)));
        logger.info("All Employees {} ->", employeeRepository.retrieveAllEmployees());
    }
}
