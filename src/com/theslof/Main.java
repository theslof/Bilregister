package com.theslof;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Car> cars = new ArrayList<Car>();
    private static final ArrayList<Person> people = new ArrayList<Person>();
    private static final ArrayList<String> COLORS = new ArrayList<String>(){{
        add("Svart");
        add("Blå");
        add("Grön");
        add("Röd");
        add("Vit");}};
    private static final String[] MENU_ITEMS = {
            "(1) Skriv ut registret",
            "(2) Lägg till person",
            "(3) Lägg till bil",
            "(4) Ändra person",
            "(5) Ändra bil",
            "(0) Avsluta"};

    public static void main(String[] args) {
        int menuSelection;
        while (true) {
            menuSelection = menu();

            switch (menuSelection) {
                case 0:
                    return;
                case 1:
                    printRegistry();
                    break;
                case 2:
                    addPerson();
                    break;
                case 3:
                    addCar();
                    break;
                case 4:
                    changePerson();
                    break;
                case 5:
                    changeCar();
            }
        }
    }

    private static void changeCar() {
        if (cars.size() == 0) {
            System.out.println("Det finns inga bilar registrerade!");
            return;
        }

        System.out.println("Vem är bilen du vill ändra? ");
        int choice = arrayMenu(cars);
        Car car = cars.get(choice);

        System.out.print("Vem är den nya ägaren? ");
        choice = arrayMenu(people);
        Person owner = people.get(choice);
        System.out.print("Vilken färg har bilen? ");
        choice = arrayMenu(COLORS);
        String color = COLORS.get(choice);

        car.setOwner(owner);
        car.setColor(color);
    }

    private static void changePerson() {
        if (people.size() == 0) {
            System.out.println("Det finns inga ägare registrerade!");
            return;
        }

        System.out.println("Vem är ägaren du vill ändra? ");
        int choice = arrayMenu(people);
        Person owner = people.get(choice);

        Scanner input = new Scanner(System.in);

        System.out.print("Vad ska personen heta? ");
        String name = input.next();
        System.out.print("Hur gammal är personen? ");
        int age = input.nextInt();

        owner.setAge(age);
        owner.setName(name);
    }

    private static void addCar() {
        if (people.size() == 0) {
            System.out.println("Det finns inga ägare registrerade!");
            return;
        }

        System.out.println("Lägger till ny bil!\nVem är ägaren? ");
        int choice = arrayMenu(people);
        Person owner = people.get(choice);

        System.out.print("Vilken färg har bilen? ");
        choice = arrayMenu(COLORS);
        String color = COLORS.get(choice);

        cars.add(new Car(color, owner));

    }

    private static void addPerson() {
        Scanner input = new Scanner(System.in);

        System.out.print("Lägger till ny person!\nVad heter personen? ");
        String name = input.next();
        System.out.print("Hur gammal är personen? ");
        int age = input.nextInt();

        people.add(new Person(name, age));
    }

    private static void printRegistry() {
        for (Car c : cars) {
            System.out.println(c);
        }
    }

    private static int menu() {
        System.out.println("--- Bilregister ---");
        for (String item : MENU_ITEMS) {
            System.out.println(item);
        }
        int choice;
        System.out.print("Välj ett alternativ: ");
        do {
            choice = inputInt();
        } while (choice < 0 || choice >= MENU_ITEMS.length);

        return choice;
    }

    private static int arrayMenu(ArrayList menuList){
        int i = 0;
        for (Object item : menuList) {
            System.out.println("(" + i++ + ") " + item);
        }
        int choice;
        System.out.print("Välj ett alternativ: ");
        do {
            choice = inputInt();
        } while (choice < 0 || choice >= menuList.size());

        return choice;
    }

    private static int inputInt() {
        Scanner input = new Scanner(System.in);

        int i = 0;
        boolean waiting = true;

        do {
            try {
                i = input.nextInt();
                waiting = false;
            } catch (InputMismatchException e) {
                input.next();
            }
        } while (waiting);

        return i;
    }
}
