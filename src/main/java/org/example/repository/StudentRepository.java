package org.example.repository;

import lombok.SneakyThrows;
import org.example.config.ConnectionConfig;
import org.example.model.Student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class StudentRepository {
    @SneakyThrows
    public static void getStudentById()  {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter student id:");
        Long id= scanner.nextLong();
        Connection connection= ConnectionConfig.getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement("select * from public.student where id =?");
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Student student=new Student();
        while(resultSet.next()){
            student.setId(resultSet.getLong("id"));
            student.setFirstName(resultSet.getString("first_name"));
            student.setLastName(resultSet.getString("last_name"));
            student.setEmail(resultSet.getString("email"));
            student.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
            student.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
        }
        System.out.println(student);
    }
    @SneakyThrows
    public static void updateStudentById(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter student id:");
        Long id= scanner.nextLong();
        System.out.println("Enter student firstname you want to change:");
        String firstName= scanner.next();
        System.out.println("Enter student lastname you want to change:");
        String lastName= scanner.next();
        System.out.println("Enter student email you want to change:");
        String email= scanner.next();
        Connection connection= ConnectionConfig.getConnection();
        CallableStatement callableStatement = connection.prepareCall("call update_student(?,?,?,?)");
        callableStatement.setLong(1,id);
        callableStatement.setString(2, firstName);
        callableStatement.setString(3, lastName);
        callableStatement.setString(4, email);
        System.out.println("Succesfully...");
    }
    @SneakyThrows
    public static void deleteStudentById(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student id:");
        Long id= scanner.nextLong();
        String query = "delete from student where id=? ";
        Connection connection= ConnectionConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        preparedStatement.execute();
        System.out.println("Student deleted...");
    }
    @SneakyThrows
    public static void createStudent(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student firstname");
        String firstName= scanner.next();
        System.out.println("Enter student lastname");
        String lastName= scanner.next();
        System.out.println("Enter student email");
        String email= scanner.next();
        Connection connection= ConnectionConfig.getConnection();
        CallableStatement callableStatement = connection.prepareCall("call insert_student(?,?,?)");
        callableStatement.setString(1, firstName);
        callableStatement.setString(2, lastName);
        callableStatement.setString(3, email);
        callableStatement.execute();
        System.out.println("Student created...");
    }
}
