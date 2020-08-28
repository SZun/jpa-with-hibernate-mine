package com.zun;

import com.zun.entity.Person;
import com.zun.jpa.PersonSpringDataJPARepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PersonSpringDataJPARepo repository;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("All users -> {}", repository.findAll());

		logger.info("User id 10001 -> {}", repository.findById(10001));

//		logger.info("Deleting 10002 -> No of Rows Deleted - {}");
		repository.deleteById(10002);

		logger.info("Inserting 10004 -> {}",
				repository.save(new Person(10004, "Tara", "Berlin", new Date())));

		logger.info("Update 10003 -> {}",
				repository.save(new Person(10003, "Pieter", "Utrecht", new Date())));
	}
}
