package org.example;

import org.example.repository.StudentRepository;

import java.util.Scanner;

public class Main {
    public static String menu = "1.Read student \n" +
            "2.Create student\n" +
            "3.Update student\n" +
            "4.Delete student\n";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
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
                default:
                    System.out.println("Not found");
            }

        }
    }
}