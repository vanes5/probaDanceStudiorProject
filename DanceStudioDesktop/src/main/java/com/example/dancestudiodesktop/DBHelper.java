package com.example.dancestudiodesktop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class DBHelper {

    private Connection conn;
    static final String DB_URL = "jdbc:mysql://localhost:3306/dancestudio";
    static final String USER = "root";
    static final String PASS = "";


    public DBHelper() throws SQLException {
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
    }

    public List<Course> getCourses() throws SQLException {
        List<Course> courseList = new ArrayList<Course>();
        String sql = "SELECT * FROM courses";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String instructor = rs.getString("instructor");
            int length = rs.getInt("length");
            String type = rs.getString("type");

            Course course = new Course(instructor, length, type, name, (long) id);
            courseList.add(course);
        }
        return courseList;
    }

    public boolean DeleteCourse(Long id) throws SQLException {
        String sql = "DELETE FROM courses WHERE id = " + id;
        Statement stmt = conn.createStatement();

        return stmt.executeUpdate(sql) > 0;
    }
}
