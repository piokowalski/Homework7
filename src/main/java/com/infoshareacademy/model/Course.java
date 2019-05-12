package com.infoshareacademy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static java.util.stream.Collectors.toList;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "COURSES")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "course_name", length = 20)
    @NotNull
    private String name;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    private List<Student> students;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Course{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", students=").append(students
            .stream()
            .map(Student::getId)
            .collect(toList())
        );
        sb.append('}');
        return sb.toString();
    }
}
