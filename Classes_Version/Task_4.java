import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Task_4{
    static Scanner sc = new Scanner(System.in);
    static patient[] booths = new patient[6]; /* https://www.youtube.com/watch?v=cCNpZZVslik */
    static int no_of_stock = 150;
    static linkedlist AstraZeneca = new linkedlist();
    static linkedlist Sinopharm = new linkedlist();
    static linkedlist Pfizer = new linkedlist();

    public static void main(String[] args) {
        String menu_selector;
        for (int i = 0; i < booths.length; i++) {
            patient p1 = new patient(null, "null", "null", 0, "null", "null");
            booths[i] = p1;
        }
        do {
            System.out.println(
                    "\n---------------------------------------------------------------\n               Vaccine Center Selection Screen\n---------------------------------------------------------------\n\n 100 or VVB: View all Vaccination Booths \n 101 or VEB: View all Empty Booths \n 102 or APB: Add Patient to a Booth \n 103 or RPB: Remove Patient from a Booth \n 104 or VPS: View Patients Sorted in alphabetical order \n 105 or SPD: Store Program Data into file \n 106 or LPD: Load Program Data from file \n 107 or VRV: View Remaining Vaccinations \n 108 or AVS: Add Vaccinations to the Stock \n 999 or EXT: Exit the Program \n---------------------------------------------------------------");
            menu_selector = GetInput();
            menu_selector = menu_selector.toUpperCase();
            switch (menu_selector) {
                case ("VVB"):                                   //https://howtodoinjava.com/java7/strings-in-switch-statement/
                case ("100"):
                    ViewBooths();
                    break;
                case ("VEB"):
                case ("101"):
                    ViewEmptyBooths();
                    break;
                case ("APB"):
                case ("102"):
                    AddPatient();
                    break;
                case ("RPB"):
                case ("103"):
                    RemovePatient();
                    break;
                case ("VPS"):
                case ("104"):
                    SortPatient();
                    break;
                case ("SPD"):
                case ("105"):
                    try {
                        StoreData();
                    } catch (IOException fe) {
                        System.out.println("You ran into a Input/Output error when writing to file try again..");
                    }
                    break;
                case ("LPD"):
                case ("106"):
                    try {
                        LoadData();
                    } catch (IOException fe) {
                        System.out.println("You ran into a Input/Output error when writing to file try again..");
                    }
                    break;
                case ("VRV"):
                case ("107"):
                    System.out.println("Number of vaccinations in stock :" + no_of_stock);
                    break;
                case ("AVS"):
                case ("108"):
                    System.out.println("How many vaccinations have been recieved?");
                    try {
                        no_of_stock += sc.nextInt();
                    } catch (NumberFormatException e) {
                        System.out.println("You are supposed to enter an Integer!");
                    }
                    break;
            }

        } while (!menu_selector.equals("999") && !menu_selector.equalsIgnoreCase("EXT"));
    }

    private static String GetInput() {

        System.out.println("Type in your selection :");
        String choice = sc.nextLine();
        int val;
        try {
            val = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            val = 0;
        }

        while ((val < 100 || val > 108) && val != 999 && !choice.equalsIgnoreCase("VVB")
                && !choice.equalsIgnoreCase("VEB") && !choice.equalsIgnoreCase("APB") && !choice.equalsIgnoreCase("RPB")
                && !choice.equalsIgnoreCase("VPS") && !choice.equalsIgnoreCase("SPD") && !choice.equalsIgnoreCase("LPD")
                && !choice.equalsIgnoreCase("VRV") && !choice.equalsIgnoreCase("AVS")
                && !choice.equalsIgnoreCase("EXT")) {
            System.out.println("Please type in a valid choice for your selection :");
            choice = sc.nextLine();
            try {
                val = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                val = 0;
            }
        }
        return choice;
    }

    public static void ViewBooths() {
        System.out.println("\n---------------------------\nAll Booths\n---------------------------\n");
        for (int i = 0; i < 6; i++) {
            if (booths[i].person != null) {
                System.out.println("Booth " + (i + 1) + " is occupied by: " + booths[i].person);
            } else {
                System.out.println("Booth " + (i + 1) + " is empty");
            }
        }

    }

    public static void ViewEmptyBooths() {
        boolean flag = false;
        System.out.println("\n---------------------------\nEmpty Booths\n---------------------------\n");
        for (int i = 0; i < 6; i++) {
            if (booths[i].person == null) {
                System.out.println("Booth " + (i + 1) + " is empty");
                flag = true;
            }
        }
        if (flag == false) {
            System.out.println("All Booths are currently filled at the moment.");
        }
    }

    public static void AddPatient() {
        int index = 0;
        String comparer;

        do {
            System.out.println("Which vaccination do you want? (AstraZeneca,Sinopharm,Pfizer) (Type ext to Exit)");
            comparer = sc.nextLine();
            if (comparer.equals("ext")) {
                return;
            }
            while (!comparer.equalsIgnoreCase("AstraZeneca") && !comparer.equalsIgnoreCase("Sinopharm")
                    && !comparer.equalsIgnoreCase("Pfizer")) {
                System.out.println("Please input a valid vaccintaion name!");
                comparer = sc.nextLine();
                if (comparer.equals("ext")) {
                    return;
                }
            }
            if (comparer.equalsIgnoreCase("AstraZeneca") && !booths[0].surname.equals("null")
                    && !booths[1].surname.equals("null")) {
                AstraZeneca.put(LinkListDetails());
                System.out.println("\nYou have been added to a waiting line and will be added shortly.");
                continue;
            }
            if (comparer.equalsIgnoreCase("Sinopharm") && !booths[2].surname.equals("null")
                    && !booths[3].surname.equals("null")) {
                Sinopharm.put(LinkListDetails());
                System.out.println("\nYou have been added to a waiting line and will be added shortly.");
                continue;
            }
            if (comparer.equalsIgnoreCase("Pfizer") && !booths[4].surname.equals("null")
                    && !booths[5].surname.equals("null")) {
                Pfizer.put(LinkListDetails());
                System.out.println("\nYou have been added to a waiting line and will be added shortly.");
                continue;
            }
            if (comparer.equalsIgnoreCase("AstraZeneca") && booths[0].surname.equals("null")) {
                index = 0;
                booths[index].vacReq = "AstraZeneca";
                GetDetails(index);
            } else if (comparer.equalsIgnoreCase("AstraZeneca") && booths[1].surname.equals("null")) {
                index = 1;
                booths[index].vacReq = "AstraZeneca";
                GetDetails(index);

            }
            if (comparer.equalsIgnoreCase("Sinopharm") && booths[2].surname.equals("null")) {
                index = 2;
                booths[index].vacReq = "Sinopharm";
                GetDetails(index);
            } else if (comparer.equalsIgnoreCase("Sinopharm") && booths[3].surname.equals("null")) {
                index = 3;
                booths[index].vacReq = "Sinopharm";
                GetDetails(index);
            }
            if (comparer.equalsIgnoreCase("Pfizer") && booths[4].surname.equals("null")) {
                index = 4;
                booths[index].vacReq = "Pfizer";
                GetDetails(index);
            } else if (comparer.equalsIgnoreCase("Pfizer") && booths[5].surname.equals("null")) {
                index = 5;
                booths[index].vacReq = "Pfizer";
                GetDetails(index);
            }

        } while (true);

    }

    public static void RemovePatient() {

        int[] occupied = new int[booths.length + 1];
        int k = 0, j = 0, removeBooth = -1;
        String taken = " ";
        boolean Empty = true;

        for (int i = 0; i < 6; i++) {
            if (booths[i].person != null) {
                j = i + 1;
                Empty = false;
                occupied[k] = j;
                k += 1;
                taken = taken + j + " ";

            }
        }
        if (Empty != false) {
            System.out.println("None of the booths have any patients in them");
            return; // https://www.codegrepper.com/code-examples/java/how+to+exit+a+function+in+java
        }
        ViewBooths();

        outerloop: while (Empty == false) {
            try {
                System.out.println(
                        "\nFrom which Booth do you want to remove the patient? (Occupied room :" + taken + ")");
                removeBooth = sc.nextInt();

                for (int i = 0; i < occupied.length; i++) {
                    if (removeBooth == occupied[i]) {
                        break outerloop;
                    }
                }
            } catch (InputMismatchException ex) {
                sc.next();
            }

        }
        int rb = removeBooth - 1;
        booths[rb].person = null;
        booths[rb].surname = "null";
        booths[rb].age = 0;
        booths[rb].city = "null";
        booths[rb].NIC = "null";
        booths[rb].vacReq = "null";
        System.out.println("Patient has been successfully removed from booth " + removeBooth);
        if ((rb == 1 || rb == 0) && !Objects.isNull(AstraZeneca.head)) {
            booths[rb] = AstraZeneca.head.data;
            AstraZeneca.head = AstraZeneca.head.next; // sets head.next data to head therefore changing the starting
                                                      // value of the link list
            no_of_stock -= 1;
            if (no_of_stock <= 20) {
                System.out.println("Your running out of stock! only " + no_of_stock + " Remaining!");
            }

        }
        if ((rb == 2 || rb == 3) && !Objects.isNull(Sinopharm.head)) {
            booths[rb] = Sinopharm.head.data;
            Sinopharm.head = Sinopharm.head.next;
            no_of_stock -= 1;
            if (no_of_stock <= 20) {
                System.out.println("Your running out of stock! only " + no_of_stock + " Remaining!");
            }

        }
        if ((rb == 4 || rb == 5) && !Objects.isNull(Pfizer.head)) {
            booths[rb] = Pfizer.head.data;
            Pfizer.head = Pfizer.head.next;
            no_of_stock -= 1;
            if (no_of_stock <= 20) {
                System.out.println("Your running out of stock! only " + no_of_stock + " Remaining!");
            }

        }

    }

    public static void SortPatient() {
        String temp;
        String[] Arranged = new String[booths.length];
        for (int i = 0; i < booths.length; i++) {
            if (booths[i].person == null) {
                Arranged[i] = "__________";
            } else {
                Arranged[i] = booths[i].person;
            }
        }
        for (int k = 0; k < Arranged.length; k++) {
            for (int l = k + 1; l < Arranged.length; l++) {

                if (Arranged[k].compareTo(Arranged[l]) > 0) {

                    temp = Arranged[k];
                    Arranged[k] = Arranged[l]; // https://www.geeksforgeeks.org/sorting-strings-using-bubble-sort-2/
                    Arranged[l] = temp;

                }
            }
        }
        System.out.println(
                "\n------------------------------\nPatients in alphabetical order\n------------------------------\n");
        for (int i = 0; i < Arranged.length; i++) {
            if (Arranged[i] != "__________") {
                System.out.println((i + 1) + ": " + Arranged[i]);
            }

        }

    }

    public static void StoreData() throws IOException {
        String str = "";
        FileWriter fw = new FileWriter("TheBooths.txt"); // https://www.geeksforgeeks.org/file-handling-java-using-filewriter-filereader/
        for (int i = 0; i < booths.length; i++) {
            str = "Booth " + i + " :" + booths[i].person + "/" + booths[i].surname + "/" + booths[i].city + "/"
                    + booths[i].age + "/" + booths[i].NIC + "/" + booths[i].vacReq;
            fw.write(str + "\n");
        }
        fw.close();
        System.out.println("Successfully created file!");

    }

    public static void LoadData() throws IOException {
        String text, value;
        try {
            Scanner s1 = new Scanner(new File("TheBooths.txt"));
            for (int i = 0; i < booths.length; i++) { // https://www.youtube.com/watch?v=hQut8sCGcDw
                text = s1.nextLine();
                String[] parts = text.split("\\/"); // splits the text at / and places values int the array parts
                                                    // //https://stackoverflow.com/questions/7683448/in-java-how-to-get-substring-from-a-string-till-a-character-c
                if (parts.length != 6) {
                    System.out.println(
                            "The file your are trying to load from has data stored in an incorrect format please store proper data in file.");
                    return;
                }
                int endpos = text.indexOf("/"); // looks for the index position of /
                                                // //https://stackoverflow.com/questions/7683448/in-java-how-to-get-substring-from-a-string-till-a-character-c
                value = text.substring(9, endpos);
                if (value.equals("null")) {
                    booths[i].person = null;
                } else {
                    booths[i].person = value;
                }

                booths[i].surname = parts[1];
                booths[i].city = parts[2];
                booths[i].age = Integer.parseInt(parts[3]);
                booths[i].NIC = parts[4];
                booths[i].vacReq = parts[5];
            }
            System.out.println("\nPatients added to Booth.");
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found, please create one first.");
        }

    }

    private static void GetDetails(int index) {
        System.out.println("Type in your first name :");
        String name = sc.nextLine();
        while (name.isEmpty()) {
            System.out.println("Please type in your first name :");
            name = sc.nextLine();
        }
        String capName = name.substring(0, 1).toUpperCase() + name.substring(1); // capitalizes first letter for ease in
                                                                                 // the sorting algorithm
        booths[index].person = capName; 
        System.out.println("Type in your last name :");
        booths[index].surname = sc.nextLine();
        while (booths[index].surname.isEmpty()) {
            System.out.println("Please type in your last name :");
            booths[index].surname = sc.nextLine();
        }
        System.out.println("Type in your city :");
        booths[index].city = sc.nextLine();
        while (booths[index].city.isEmpty()) {
            System.out.println("Please type in your city :");
            booths[index].city = sc.nextLine();
        }

        System.out.println("type in your Age :");
        while (true) {
            try {
                booths[index].age = Integer
                        .parseInt(sc.nextLine()); /*
                                                   * https://stackoverflow.com/questions/35936799/validation-so-input-is
                                                   * -only-integer-in-java/35937125
                                                   */
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Please type a valid number!");
                continue;
            }
        }
        System.out.println("Type in your NIC number :");
        booths[index].NIC = sc.nextLine();
        while (booths[index].NIC.isEmpty()) {
            System.out.println("Please type in your NIC number :");
            booths[index].NIC = sc.nextLine();
        }
        no_of_stock -= 1;
        if (no_of_stock <= 20) {
            System.out.println("Your running out of stock! only " + no_of_stock + " Remaining!");
        }

    }

    private static patient LinkListDetails() {
        patient p1 = new patient(null, "null", "null", 0, "null", "null");
        System.out.println("Type in your first name :");
        String name = sc.nextLine();
        while (name.isEmpty()) {
            System.out.println("Please type in your first name :");
            name = sc.nextLine();
        }
        String capName = name.substring(0, 1).toUpperCase() + name.substring(1);
        p1.person = capName;
        System.out.println("Type in your last name :");
        p1.surname = sc.nextLine();
        while (p1.surname.isEmpty()) {
            System.out.println("Please type in your last name :");
            p1.surname = sc.nextLine();
        }
        System.out.println("Type in your city :");
        p1.city = sc.nextLine();
        while (p1.city.isEmpty()) {
            System.out.println("Please type in your city :");
            p1.city = sc.nextLine();
        }

        System.out.println("type in your Age :");
        while (true) {
            try {
                p1.age = Integer.parseInt(sc.nextLine()); //https://stackoverflow.com/questions/35936799/validation-so-input-is-only-integer-in-java/35937125                              
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Please type a valid number!");
                continue;
            }
        }
        System.out.println("Type in your NIC number :");
        p1.NIC = sc.nextLine();
        while (p1.NIC.isEmpty()) {
            System.out.println("Please type in your NIC number :");
            p1.NIC = sc.nextLine();
        }

        return (p1);

    }

}
