package com.theslof;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    //Skapa ArrayList som ska hålla alla bilar och personer.
    // <Car> säger åt kompilatorn att listan kommer att endast kunna hålla Car
    private static final ArrayList<Car> cars = new ArrayList<Car>();
    private static final ArrayList<Person> people = new ArrayList<Person>();

    //Skapa en ArrayList som håller de olika färgerna vi kan välja till våra bilar
    private static final ArrayList<String> COLORS = new ArrayList<String>() {{
        add("Svart");
        add("Blå");
        add("Grön");
        add("Röd");
        add("Vit");
    }};

    //Skapa en ArrayList som håller huvudmenyn
    private static final ArrayList<String> MENU_ITEMS = new ArrayList<String>() {{
        add("Skriv ut registret");
        add("Lägg till person");
        add("Ändra person");
        add("Ta bort person");
        add("Lägg till bil");
        add("Ändra bil");
        add("Ta bort bil");
        add("Avsluta");
    }};

    /*--------------------------
        Början på programmet
    --------------------------*/

    public static void main(String[] args) {

        //Skapa några personer att fylla registret med.
        //ArrayList-metoden .add() lägger till ett nytt element i listan
        people.add(new Person("Tripp", 20));
        people.add(new Person("Trapp", 19));
        people.add(new Person("Trull", 18));
        people.add(new Person("Kålle", 43));
        people.add(new Person("Ada", 47));

        //Skapa några bilar med slumpad ägare och slumpad färg
        cars.add(new Car(COLORS.get((int)(Math.random() * COLORS.size())), people.get((int)(Math.random() * people.size()))));
        cars.add(new Car(COLORS.get((int)(Math.random() * COLORS.size())), people.get((int)(Math.random() * people.size()))));
        cars.add(new Car(COLORS.get((int)(Math.random() * COLORS.size())), people.get((int)(Math.random() * people.size()))));
        cars.add(new Car(COLORS.get((int)(Math.random() * COLORS.size())), people.get((int)(Math.random() * people.size()))));
        cars.add(new Car(COLORS.get((int)(Math.random() * COLORS.size())), people.get((int)(Math.random() * people.size()))));
        cars.add(new Car(COLORS.get((int)(Math.random() * COLORS.size())), people.get((int)(Math.random() * people.size()))));

        int menuSelection;
        //Starta vår program-loop. Denna körs tills man väljer att avsluta programmet.
        while (true) {

            //Kör metoden arrayMenu med vår huvudmeny från tidigare som argument.
            //Metoden returnerar vårt menyval
            menuSelection = arrayMenu(MENU_ITEMS);

            //Kör metod baserat på vårt menyval
            switch (menuSelection) {
                case 0:
                    //Skriv ut vårt register
                    printRegistry();
                    break;
                case 1:
                    //Lägg till en ny person
                    addPerson();
                    break;
                case 2:
                    //Ändra namn och ålder på en person
                    changePerson();
                    break;
                case 3:
                    //Ändra namn och ålder på en person
                    deletePerson();
                    break;
                case 4:
                    //Lägg till en ny bil
                    addCar();
                    break;
                case 5:
                    //Ändra ägare och färg på en bil
                    changeCar();
                    break;
                case 6:
                    //Ändra ägare och färg på en bil
                    deleteCar();
                    break;
                case 7:
                    //Avslutar programmet, eftersom vi är i main()
                    return;
            }
        }
    }

    //Lägg till en ny bil
    private static void addCar() {
        //Kolla om vår person-lista är tom
        if (people.size() == 0) {
            //Listan är tom, informera användaren och avbryt
            System.out.println("Det finns inga ägare registrerade!");
            return;
        }

        System.out.println("Lägger till ny bil!\nVem är ägaren? ");
        //Kalla på metoden arrayMenu med vår person-lista som argument.
        //Vi får ett index som motsvarar en person som svar
        int choice = arrayMenu(people);
        //Välj personen på valt index och lägg den i owner
        //ArrayList-metoden .get(i) returnerar objektet på index i
        Person owner = people.get(choice);

        System.out.print("Vilken färg har bilen? ");
        //Kalla på metoden arrayMenu med vår färg-lista som argument.
        choice = arrayMenu(COLORS);
        String color = COLORS.get(choice);

        //Skapa en ny bil med färg och ägare, lägg till den i vår bil-lista
        cars.add(new Car(color, owner));

    }

    private static void addPerson() {
        //Skapa en ny Scanner så vi kan läsa in namn. Ålder sköts av inputInt, som är safe.
        Scanner input = new Scanner(System.in);

        System.out.println("Lägger till ny person!\nVad heter personen? ");
        //Läs in en sträng, fram till första mellanrummet
        String name = input.nextLine();
        System.out.print("Hur gammal är personen? ");
        //Läs in en int
        int age = inputInt();

        //Skapa en ny person med namn och ålder, lägg till den i vår person-lista
        people.add(new Person(name, age));
    }

    //Ändra ägare och färg för en bil
    private static void changeCar() {
        //Kolla om vår bil-lista är tom
        if (cars.size() == 0) {
            //Listan är tom, informera användaren och avbryt
            System.out.println("Det finns inga bilar registrerade!");
            return;
        }

        System.out.println("Vilken bil vill du ändra? ");
        //Kalla på metoden arrayMenu med vår bil-lista som argument.
        int choice = arrayMenu(cars);
        Car car = cars.get(choice);

        System.out.print("Vem är den nya ägaren? ");
        //Kalla på metoden arrayMenu med vår person-lista som argument.
        choice = arrayMenu(people);
        Person owner = people.get(choice);

        System.out.print("Vilken färg har bilen? ");
        //Kalla på metoden arrayMenu med vår färg-lista som argument.
        choice = arrayMenu(COLORS);
        String color = COLORS.get(choice);

        //Ändra ägare och färg för vår bil, vi behöver aldrig skapa en ny bil för att ersätta en gammal
        car.setOwner(owner);
        car.setColor(color);
    }

    //Ändra namn och ålder för en person
    private static void changePerson() {
        //Kolla om vår person-lista är tom
        if (people.size() == 0) {
            //Listan är tom, informera användaren och avbryt
            System.out.println("Det finns inga ägare registrerade!");
            return;
        }

        System.out.println("Vem är ägaren du vill ändra? ");
        //Kalla på metoden arrayMenu med vår person-lista som argument.
        int choice = arrayMenu(people);
        Person owner = people.get(choice);

        //Skapa en ny Scanner så vi kan läsa in namn. Ålder sköts av inputInt, som är safe.
        Scanner input = new Scanner(System.in);

        System.out.print("Vad ska personen heta? ");
        //Läs in en sträng, fram till första mellanrummet
        String name = input.nextLine();

        System.out.print("Hur gammal är personen? ");
        int age = inputInt();

        //Ändra på ålder och namn i personens objekt
        owner.setAge(age);
        owner.setName(name);
    }

    //Ta bort en bil
    private static void deleteCar() {
        //Kolla om vår bil-lista är tom
        if (cars.size() == 0) {
            //Listan är tom, informera användaren och avbryt
            System.out.println("Det finns inga bilar registrerade!");
            return;
        }

        System.out.println("Vilken bil vill du ta bort? ");
        //Kalla på metoden arrayMenu med vår bil-lista som argument.
        int choice = arrayMenu(cars);
        Car c = cars.get(choice);

        //Radera bilen från ägarens lista övr bilar
        cars.get(choice).getOwner().removeCar(c);
        cars.remove(choice);
    }

    //Ta bort en person
    private static void deletePerson() {
        //Kolla om vår person-lista är tom
        if (people.size() == 0) {
            //Listan är tom, informera användaren och avbryt
            System.out.println("Det finns inga personer registrerade!");
            return;
        }

        System.out.println("Vilken person vill du ta bort? ");
        //Kalla på metoden arrayMenu med vår person-lista som argument.
        int choice = arrayMenu(people);
        Person p = people.get(choice);

        //Kolla om personen äger bilar
        if(p.getCars().size() > 0){
            //Skriv ut alla bilar som ägs av personen
            System.out.println(p.getName() + " äger följande bilar:");
            for(Car c : p.getCars())
                System.out.println(c);
            //Fråga om användaren vill radera personen trots att dessa bilar raderas
            System.out.println("Dessa bilar kommer att raderas. Fortsätt? J/N");

            //Läs in svaret, J eller N
            Scanner input = new Scanner(System.in);
            char s;
            do{
                //Läs in en versal
                s = input.next().toUpperCase().toCharArray()[0];
            }while (s != 'J' && s != 'N');
            //Om användaren inte vill radera, avbryt
            if(s == 'N')
                return;
            //Radera alla bilar som ägs av personen
            cars.removeAll(p.getCars());
        }
        //Radera personen
        people.remove(p);
    }

    //Skriv ut vårt bilregister
    private static void printRegistry() {
        //Kolla om vår bil-lista är tom
        if(cars.size() == 0){
            //Listan är tom, informera användaren och avbryt
            System.out.println("Registret är tomt!");
        }

        //För alla bilar c i listan cars, en typ av for-loop.
        for (Car c : cars) {
            //Skriv ut bilen, dvs. c.toString()
            System.out.println(c);
        }
        System.out.println("");
    }

    //Skriv ut en numrerad meny baserat på argumentet, fråga efter val och returnera valet
    //Tar en ArrayList som argument, vilket betyder att den kan skriva ut Car, Person, MENU_ITEMS och COLORS
    private static int arrayMenu(ArrayList menuList) {
        //Vi behöver i efter loopen, så den deklareras här
        int i = 1;

        //För varje item i menuList, jag använder Object eftersom den är kompatibel med alla former av ArrayList
        for (Object item : menuList) {
            //Skriv ut menyn på formen "(i) item"
            System.out.println("(" + i++ + ") " + item);
        }

        int choice;
        System.out.print("Välj ett alternativ: ");
        //Läs in användarens val minus 1 då menyn börjar på 1, men index börjar på 0
        do {
            choice = inputInt()-1;
            //Kör denna loopen så länge användaren inte väljer ett av valen från listan
        } while (choice < 0 || choice >= menuList.size());

        //Returnera valet
        return choice;
    }

    //Läs en int från input
    private static int inputInt() {
        //Skapa en ny Scanner
        Scanner input = new Scanner(System.in);

        int i = 0;
        //waiting håler reda på om vi väntar eller har en int redo
        boolean waiting = true;

        do {
            //Try/catch för att undvika krasch
            try {
                //Om Try lyckas så har vi en int, waiting sätts till false;
                i = input.nextInt();
                waiting = false;
            } catch (InputMismatchException e) {
                //Vi lyckades inte läsa en int, hoppa till nästa ord i input
                input.next();
            }
        } while (waiting);

        return i;
    }
}
