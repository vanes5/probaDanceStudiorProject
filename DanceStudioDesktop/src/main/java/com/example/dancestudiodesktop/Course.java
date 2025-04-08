package com.example.dancestudiodesktop;

public class Course {
    private Long id;
    private String name;
    private String type;
    private Integer length;
    private String instructor;

    public Course(String instructor, Integer length, String type, String name, Long id) {
        this.instructor = instructor;
        this.length = length;
        this.type = type;
        this.name = name;
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
