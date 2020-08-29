package com.zun.demo.repository;

import com.zun.demo.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String name);
    List<Course> findByNameOrderByIdDesc(String name);

    @Query("Select c From Course c where name like '%100 Steps'")
    List<Course> coursesWith100Steps();

    @Query(value = "Select c From Course c where name like '%100 Steps'", nativeQuery = true)
    List<Course> coursesWith100StepsNative();

    @Query(name = "query_get_100_step_courses")
    List<Course> coursesWith100StepsNamed();
}
