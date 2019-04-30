package com.infoshareacademy.model;

import com.sun.tools.javac.util.List;

import javax.persistence.*;

@Entity
@Table(name = "TEACHERS")

public class Teacher {

    @Id
    @Column(name = "pesel", length = 15, unique = true)
    private String pesel;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String surname;

    @ManyToMany
    @JoinTable(name = "TEACHERS_TO_COURSES",
            joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "pesel"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"teacher_id", "course_id"}))
    private List<Course> courses;

    public Teacher() {
    }

    public Teacher(String pesel, String surname, String surname1, List<Course> courses) {
        this.pesel = pesel;
        this.surname = surname;
        this.surname = surname1;
        this.courses = courses;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "pesel='" + pesel + '\'' +
                ", surname='" + surname + '\'' +
                ", surname='" + surname + '\'' +
                ", courses=" + courses +
                '}';
    }
}
