package com.zun.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "CourseDetails")
@NamedQueries(
        value = {
                @NamedQuery(name = "query_get_all_courses", query = "Select c from Course c"),
                @NamedQuery(name = "query_get_100_step_courses", query = "Select c from Course c where name like '%100 Steps'")
        }
)
@Cacheable
@SQLDelete(sql= "update course set is_deleted = true where id = ?")
@Where(clause = "is_deleted = false")
public class Course {

    final static Logger LOGGER = LoggerFactory.getLogger(Course.class);

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
//    @Column(name = "fullname", nullable = false)
    private String name;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "course")
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
//    @JoinTable(name = "STUDENT_COURSES",
//            joinColumns = @JoinColumn(name = "STUDENTS_ID"),
//            inverseJoinColumns = @JoinColumn(name = "COURSES_ID")
//    )
    private List<Student> students = new ArrayList<>();

    private boolean isDeleted;

    protected Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    @PreRemove
    private void preRemove(){
        LOGGER.info("Setting is deleted to true");
        isDeleted = true;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public String toString() {
        return String.format("Course[%s]", name);
    }
}
