package com.zun.demo.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "CourseDetails")
@NamedQueries(
        value = {
                @NamedQuery(name="query_get_all_courses", query = "Select c from Course c"),
                @NamedQuery(name="query_get_100_step_courses", query = "Select c from Course c where name like '%100 Steps'")
        }
)
public class Course {

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
    List<Review> reviews = new ArrayList<>();

    protected Course(){}

    public Course(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return String.format("Course[%s]", name);
    }
}
