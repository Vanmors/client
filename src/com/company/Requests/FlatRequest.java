package com.company.Requests;

import com.company.Commands.AddCommand;
import com.company.data.Coordinates;
import com.company.data.Flat;
import com.company.data.House;
import com.company.data.View;

import java.io.IOException;
import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FlatRequest {
    static public Flat request(String user) throws IOException {
        Flat f = new Flat(1, setName(), setCoordinates(), setCreationDate(),
                setArea(), setNumberOfRooms(), setFurniture(), setTimeToMetroOnFoot(),
                setView(), setHouse(), user);
        return f;
}

    /**
     * считывает и возвращает Name
     *
     * @return Name
     */
    static public String setName() {
        Scanner nameSc = new Scanner(System.in);
        System.out.println("Введите Name");
        if (!nameSc.hasNextLine()) {
            System.exit(0);
        }
        String Name = nameSc.nextLine();
        while ((Name.trim()).equals("")) {
            System.out.println("Введите Name");
            if (!nameSc.hasNextLine()) {
                System.exit(0);
            }
            Name = nameSc.nextLine();
        }
        return Name;
    }

    /**
     * считывает и возвращает Coordinates
     *
     * @return Coordinates
     */
    static public Coordinates setCoordinates() {
        while (true) {
            try {
                Scanner coordSc = new Scanner(System.in);
                System.out.println("Введите Coordinate X");
                if (!coordSc.hasNextLine()) {
                    System.exit(0);
                }
                int x = coordSc.nextInt();
                System.out.println("Введите Coordinate Y");
                if (!coordSc.hasNextLine()) {
                    System.exit(0);
                }
                long y = coordSc.nextLong();
                if (x >= 0 && y >= 0) {
                    return new Coordinates(x, y);
                } else {
                    System.out.println("Некорректно введённые данные");
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректно введённые данные");
            }
        }
    }

    /**
     * возвращает CreationDate
     *
     * @return CreationDate
     */
    static public ZonedDateTime setCreationDate() {
        return ZonedDateTime.now();
    }

    static public int setArea() {
        while (true) {
            try {
                Scanner areaSc = new Scanner(System.in);
                System.out.println("Введите area");
                if (!areaSc.hasNextLine()) {
                    System.exit(0);
                }
                int area = areaSc.nextInt();
                if (area >= 0) {
                    return area;
                } else {
                    System.out.println("Некорректно введённые данные");
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректно введённые данные");
            }
        }
    }

    /**
     * считывает и возвращает NumberOfRooms
     *
     * @return NumberOfRooms
     */
    static public Long setNumberOfRooms() {
        while (true) {
            try {
                Scanner NORsc = new Scanner(System.in);
                System.out.println("Введите number of rooms");
                if (!NORsc.hasNextLine()) {
                    System.exit(0);
                }
                Long numberOfRooms = NORsc.nextLong();
                if (numberOfRooms >= 0) {
                    return numberOfRooms;
                } else {
                    System.out.println("Некорректно введённые данные");
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректно введённые данные");
            }
        }
    }

    /**
     * считывает и возвращает furniture
     *
     * @return furniture
     */
    static public Boolean setFurniture() {
        while (true) {
            try {
                Scanner furSc = new Scanner(System.in);
                System.out.println("Введите furniture");
                if (!furSc.hasNextLine()) {
                    System.exit(0);
                }
                Boolean furniture = furSc.nextBoolean();
                return furniture;
            } catch (InputMismatchException e) {
                System.out.println("Некорректно введённые данные");
            }
        }
    }

    /**
     * считывает и возвращает timeToMetroOnFoot
     *
     * @return timeToMetroOnFoot
     */
    static public Long setTimeToMetroOnFoot() {
        while (true) {
            try {
                Scanner metroSc = new Scanner(System.in);
                System.out.println("Введите time to metro on foot");
                if (!metroSc.hasNextLine()) {
                    System.exit(0);
                }
                Long timeToMetroOnFoot = metroSc.nextLong();
                if (timeToMetroOnFoot >= 0) {
                    return timeToMetroOnFoot;
                } else {
                    System.out.println("Некорректно введённые данные");
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректно введённые данные");
            }
        }
    }

    /**
     * считывает и возвращает View
     *
     * @return View
     */
    static public View setView() {
        View v = null;
        while (true) {
            System.out.println("Введите view: TERRIBLE, STREET, BAD, PARK");
            Scanner viewSc = new Scanner(System.in);
            if (!viewSc.hasNextLine()) {
                System.exit(0);
            }
            String view = viewSc.next();
            if (view.equals("TERRIBLE")) {
                v = View.TERRIBLE;
                break;
            } else if (view.equals("STREET")) {
                v = View.STREET;
                break;
            } else if (view.equals("BAD")) {
                v = View.BAD;
                break;
            } else if (view.equals("PARK")) {
                v = View.PARK;
                break;
            }
        }
        return v;
    }

    /**
     * считывает и возвращает House
     *
     * @return House
     */
    static public House setHouse() {
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

