import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Task3{
    static Scanner sc = new Scanner(System.in);
    static String[] booth = { null, null, null, null, null, null }; // create a value for booth.length and initialize
                                                                    // this to [6]
    static int no_of_stock = 150;

    public static void main(String[] args) {

        String menu_selector;
        do {
            System.out.println(
                    "\n---------------------------------------------------------------\n               Vaccine Center Selection Screen\n---------------------------------------------------------------\n\n 100 or VVB: View all Vaccination Booths \n 101 or VEB: View all Empty Booths \n 102 or APB: Add Patient to a Booth \n 103 or RPB: Remove Patient from a Booth \n 104 or VPS: View Patients Sorted in alphabetical order \n 105 or SPD: Store Program Data into file \n 106 or LPD: Load Program Data from file \n 107 or VRV: View Remaining Vaccinations \n 108 or AVS: Add Vaccinations to the Stock \n 999 or EXT: Exit the Program \n---------------------------------------------------------------");
            menu_selector = GetInput();
            menu_selector = menu_selector.toUpperCase();
            switch (menu_selector) {
                case ("VVB"):
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
                    System.out.println("\nVaccinations remaining in stock:" + no_of_stock);
                    break;
                case ("AVS"):
                case ("108"):
                    System.out.println("\nHow Vaccinations have been recieved?");
                    try {
                        no_of_stock += sc.nextInt();
                    } catch (NumberFormatException e) {
                        System.out.println("You are supposed to enter an Integer!");
                    }
                    break;
            }

        } while (!menu_selector.equals("999") && !menu_selector.equalsIgnoreCase("EXT"));
    }

    // create loops of all the functions so users can input multiple patients at a
    // time and leave when they want
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
        int endpos;
        String firstname;
        System.out.println("\n---------------------------\nAll Booths\n---------------------------\n");
        for (int i = 0; i < 6; i++) {
            if (booth[i] == null) {
                System.out.println("Booth " + (i + 1) + " is empty");

            } else {
                endpos = booth[i].indexOf("/");
                firstname = booth[i].substring(0, endpos);
                System.out.println("Booth " + (i + 1) + " is occupied by: " + firstname);
            }
        }

    }

    public static void ViewEmptyBooths() {
        boolean flag = false;
        System.out.println("\n---------------------------\nEmpty Booths\n---------------------------\n");
        for (int i = 0; i < 6; i++) {
            if (booth[i] == null) {
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
        String comparer,lname;

        do {
            System.out.println("Which vaccination do you want? (AstraZeneca,Sinopharm,Pfizer) (Type ext to exit)");
            comparer = sc.nextLine();
            if (comparer.equals("ext")) {
                return;
            }
            while (!comparer.equalsIgnoreCase("AstraZeneca") && !comparer.equalsIgnoreCase("Sinopharm")
                    && !comparer.equalsIgnoreCase("Pfizer")) {
                System.out.println("Please input a valid vaccintaion name!");
                comparer = sc.nextLine();
            }
            if (comparer.equalsIgnoreCase("Astrazeneca") && booth[0] != null && booth[1] != null) {
                System.out.println("Sorry booths pertaining to that vaccination are currently full.");
                continue;
            }
            if (comparer.equalsIgnoreCase("Sinopharm") && booth[2] != null && booth[3] != null) {
                System.out.println("Sorry booths pertaining to that vaccination are currently full.");
                continue;
            }
            if (comparer.equalsIgnoreCase("Pfizer") && booth[4] != null && booth[5] != null) {
                System.out.println("Sorry booths pertaining to that vaccination are currently full.");
                continue;
            }
            if (comparer.equalsIgnoreCase("Astrazeneca") && booth[0] == null) {
                index = 0;
            } else if (comparer.equalsIgnoreCase("Astrazeneca") && booth[1] == null) {
                index = 1;
            }
            if (comparer.equalsIgnoreCase("Sinopharm") && booth[2] == null) {
                index = 2;
            } else if (comparer.equalsIgnoreCase("Sinopharm") && booth[3] == null) {
                index = 3;
            }
            if (comparer.equalsIgnoreCase("Pfizer") && booth[4] == null) {
                index = 4;
            } else if (comparer.equalsIgnoreCase("Pfizer") && booth[5] == null) {
                index = 5;
            }

            System.out.println("Type in your first name :");
            String name = sc.nextLine();
            while(name==""){
                System.out.println("Please type in your first name :");
                name = sc.nextLine();
            }
            String capName = name.substring(0, 1).toUpperCase() + name.substring(1); //capitalizes the first letter of the name for better sorting
            booth[index] = capName;                     
            System.out.println("Type in your last name: ");
            lname=sc.nextLine();
            while(lname==""){
                System.out.println("Please type in your last name :");
                lname=sc.nextLine();
            }
            name += lname;
            booth[index] += "/" + name;
            booth[index] += "/" + comparer;
            no_of_stock -= 1;
            if (no_of_stock <= 20) {
                System.out.println("Your running out of stock! only " + no_of_stock + " Remaining!");
            }
        } while (true);

    }

    public static void RemovePatient() {

        int[] occupied = new int[booth.length + 1]; //new array for storing the indexes of occupied booths
        int k = 0, j = 0, removeBooth = -1;         
        String taken = " ";                         //string to display taken
        boolean Empty = true;

        for (int i = 0; i < 6; i++) {
            if (booth[i] != null) {
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
        booth[removeBooth - 1] = null;
        System.out.println("Patient has been successfully removed from booth " + removeBooth);
    }

    public static void SortPatient() {
        String temp;
        String[] Arranged = new String[booth.length];
        for (int i = 0; i < booth.length; i++) {
            if (booth[i] == null) {
                Arranged[i] = "__________";
            } else {
                Arranged[i] = booth[i];
            }
        }
        for (int k = 0; k < Arranged.length; k++) {
            for (int l = k + 1; l < Arranged.length; l++) {

                if (Arranged[k].compareTo(Arranged[l]) > 0) { // try do while if time allows

                    temp = Arranged[k];
                    Arranged[k] = Arranged[l]; // https://www.geeksforgeeks.org/sorting-strings-using-bubble-sort-2/
                    Arranged[l] = temp;

                }
            }
        }
        System.out.println(
                "\n------------------------------\nPatients in alphabetical order\n------------------------------\n");
        for (int i = 0; i < Arranged.length; i++) {
            if (Arranged[i] == "__________") {
                System.out.println((i + 1) + ": empty");
            } else {
                System.out.println((i + 1) + ": " + Arranged[i]);
            }

        }

    }

    public static void StoreData() throws IOException {
        String str = "";
        FileWriter fw = new FileWriter("TheBooths.txt"); // https://www.geeksforgeeks.org/file-handling-java-using-filewriter-filereader/
        for (int i = 0; i < booth.length; i++) {
            str = "Booth " + i + " :" + booth[i];
            fw.write(str + "\n");
        }
        fw.close();
        System.out.println("Successfully created file!");

    }

    public static void LoadData() throws IOException {
        String text, value;
        try {
            Scanner s1 = new Scanner(new File("TheBooths.txt"));
            for (int i = 0; i < booth.length; i++) { // https://www.youtube.com/watch?v=hQut8sCGcDw
                text = s1.nextLine();
                value = text.substring(9, text.length());
                if (value.equals("null")) {
                    booth[i] = null;
                } else {
                    booth[i] = value;
                }
            }
            System.out.println("\nPatients added to Booth.");
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found, please create one first.");
        }

    }

}
