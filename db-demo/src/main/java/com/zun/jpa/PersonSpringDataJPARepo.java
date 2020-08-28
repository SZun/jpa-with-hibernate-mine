package com.zun.jpa;

import com.zun.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonSpringDataJPARepo extends JpaRepository<Person, Integer> {
}
