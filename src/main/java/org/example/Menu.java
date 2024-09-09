package org.example;

import org.example.repository.CourseRepository;
import org.example.repository.StudentRepository;

import java.util.Scanner;

public class Menu {
    public static String menu = "1.Read student \n" +
            "2.Create student\n" +
            "3.Update student\n" +
            "4.Delete student\n" +
            "5.Read course\n" +
            "6.Create course\n" +
            "7.Delete course\n" +
            "8.Update course";

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("------------MENU---------------");
            System.out.println(menu);
            System.out.println("Choose option:");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    StudentRepository.getStudentById();
                    break;
                case 2:
                    StudentRepository.createStudent();
                    break;
                case 3:
                    StudentRepository.updateStudentById();
                    break;
                case 4:
                    StudentRepository.deleteStudentById();
                    break;
                case 5:
                    CourseRepository.getCourseById();
                    break;
                case 6:
                    CourseRepository.createCourse();
                    break;
                case 7:
                    CourseRepository.deleteCourseById();
                    break;
                case 8:
                    CourseRepository.updateCourseById();
                    break;
                default:
                    System.out.println("Not found");
            }
        }
    }
}
