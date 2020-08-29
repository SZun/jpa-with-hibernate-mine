package com.zun.demo.repository;

import com.zun.demo.entities.Course;
import com.zun.demo.entities.Passport;
import com.zun.demo.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.stream.Stream;

@Repository
@Transactional
public class StudentRepository {

    @Autowired
    private EntityManager em;

    public Student findById(Long id){
        return em.find(Student.class, id);
    }

    public Student save(Student student){
        if(student.getId() == null){
            em.persist(student);
        } else {
            em.merge(student);
        }
        return student;
    }

    public void deleteById(Long id){
        Student student = findById(id);
        em.remove(student);
    }

    public void saveStudentWithPassport(){
        Passport passport = new Passport("Z123456");
        em.persist(passport);
        Student student = new Student("Mike");
        student.setPassport(passport);
        em.persist(student);
    }

    public void insertHardcodedStudentAndCourse(){
        Student student = new Student("Jack");
        Course course = new Course("Microservices in 100 Steps");

        Stream.of(student, course).forEach(em::persist);

        student.addCourse(course);
        course.addStudent(student);
        em.persist(student);
    }

    public void insertStudentAndCourse(Student student, Course course){
        student.addCourse(course);
        course.addStudent(student);
        Stream.of(student, course).forEach(em::persist);
    }

}
