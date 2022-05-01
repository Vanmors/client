package com.company.Requests;

import com.company.data.House;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HouseRequest {
    static public House request() {
        while (true) {
            try {
                Scanner houseSc = new Scanner(System.in);
                System.out.println("Введите name of house");
                if (!houseSc.hasNextLine()) {
                    System.exit(0);
                }
                String Name = houseSc.nextLine();
                System.out.println("Введите year of house");
                if (!houseSc.hasNextLine()) {
                    System.exit(0);
                }
                Integer year = houseSc.nextInt();
                System.out.println("Введите number of flats on floor");
                if (!houseSc.hasNextLine()) {
                    System.exit(0);
                }
                Integer numberOfFlatsOnFloor = houseSc.nextInt();
                if (year >= 0 && numberOfFlatsOnFloor >= 0) {
                    return new House(Name, year, numberOfFlatsOnFloor);
                } else {
                    System.out.println("Некорректно введённые данные");
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректно введённые данные");
            }
        }
    }
}
