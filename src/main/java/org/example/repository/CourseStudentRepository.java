package org.example.repository;

import lombok.SneakyThrows;
import org.example.config.ConnectionConfig;
import org.example.model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CourseStudentRepository {
    @SneakyThrows
    public static void getCourseByStudentId(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter student id:");
        Long id= scanner.nextLong();
        Connection connection= ConnectionConfig.getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement("SELECT c.*\n" +
                "FROM course c\n" +
                "INNER JOIN student_course sc ON c.id = sc.course_id\n" +
                "WHERE sc.student_id = ?");
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

            System.out.println(course);
        }
    }
}
