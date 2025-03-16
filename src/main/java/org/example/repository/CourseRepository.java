package org.example.repository;

import lombok.SneakyThrows;
import org.example.config.ConnectionConfig;
import org.example.model.Course;
import org.example.model.Student;

import java.sql.*;
import java.util.Scanner;

public class CourseRepository {
    @SneakyThrows
    public static void getCourseById()  {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter course id:");
        Long id= scanner.nextLong();
        Connection connection= ConnectionConfig.getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement("select * from course where id =?");
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Course course=new Course();
        while(resultSet.next()){
            course.setId(resultSet.getLong("id"));
            course.setCourseName(resultSet.getString("course_name"));
            course.setCourseIsActive(resultSet.getBoolean("course_is_active"));
            course.setDescription(resultSet.getString("description"));
            course.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
            course.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
        }
        System.out.println(course);
    }
    @SneakyThrows
    public static void createCourse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of course:");
        String courseName= scanner.nextLine();
        System.out.println("Enter course description:");
        String description= scanner.nextLine();
        Connection connection= ConnectionConfig.getConnection();
        CallableStatement callableStatement = connection.prepareCall("call insert_course(?,?)");
        callableStatement.setString(1, courseName);
        callableStatement.setString(2, description);
        callableStatement.execute();
        System.out.println("Course created...");
    }
    @SneakyThrows
    public static void deleteCourseById(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter course id:");
        Long id= scanner.nextLong();
        String query = "delete from course where id=? ";
        Connection connection= ConnectionConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        preparedStatement.execute();
        System.out.println("Course deleted...");
    }
    @SneakyThrows
    public static void updateCourseById(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter course id:");
        Long id= scanner.nextLong();
        System.out.println("Enter course name you want to change:");
        String courseName= scanner.next();
        System.out.println("Enter course description you want to change:");
        String description= scanner.next();
        Connection connection= ConnectionConfig.getConnection();
        CallableStatement callableStatement = connection.prepareCall("call public.update_course(?,?,?)");
        callableStatement.setLong(1,id);
        callableStatement.setString(2, courseName);
        callableStatement.setString(3, description);
        System.out.println("Succesfully...");
    }
}
