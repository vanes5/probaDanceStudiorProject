package com.example.dancestudiodesktop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Statisztika {
    private static List<Course> courseList = new ArrayList<>();
    private static DBHelper dbHelper;

    public static void main(String[] args) throws SQLException {
        try {
            dbHelper = new DBHelper();
            courseList = dbHelper.getCourses();
            getGroupCourses();
            getLongestCourse();
            getCourseInstructor();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static void getGroupCourses() {
        long quantity = courseList.stream().filter(course -> Objects.equals(course.getType(), "group")).count();

        System.out.print("Csoportos kurzusok száma: " + quantity);
    }

    private static void getLongestCourse() {
        Course longest = courseList.stream().max(Comparator.comparingInt(Course::getLength)).get();
        System.out.printf("\nA leghosszabb kurzus:\n\tNév: %s\n\tTípus: %s\n\tHossz: %d\n\tOktató: %s", longest.getName(), longest.getType(), longest.getLength(), longest.getInstructor());
    }

    private static void getCourseInstructor() throws IOException {
        System.out.println("\nAdja meg a kurzus nevét: ");
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        String name = buffReader.readLine();

        Course course = courseList.stream().filter(course1 -> Objects.equals(course1.getName(), name)).findFirst().orElse(null);

        if(course == null) {
            System.out.println("Nincs ilyen kurzus");
            return;
        }

        System.out.println("A kurzus okatatója: " + course.getInstructor());

    }
}
