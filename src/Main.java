import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final int Max_Seats = 100;
    private static final ArrayList<String> Students = new ArrayList<>(Max_Seats);
    private static final ArrayList<String> StudentName = new ArrayList<>();
    private static int RegCount = 0;
    private static final String StudentData = "Student_Details.txt";
    public static void main(String[] args) {




        Scanner input = new Scanner(System.in);
        while (true) {
            Menu();
            System.out.println("Enter your choice :");
            int Choice = input.nextInt();
            input.nextLine();

            switch (Choice) {
                case 1:
                    checkAvailableSeats();
                    break;
                case 2:
                    Register();
                    break;

                case 3:
                    Delete();
                    break;

                case 4:
                    FindStudent();
                    break;

                case 5:
                    StoreDATA();
                    break;

                case 6:
                    LoadData();
                    break;

                case 7:
                    break;

                default:
                    System.out.println("Invalid Choice!! Try Again!!");

        }




        }

    }
    static void Menu() {
        System.out.println("1. Check available seats");
        System.out.println("2. Register student (with ID)");
        System.out.println("3. Delete student");
        System.out.println("4. Find student (with student ID)");
        System.out.println("5. Store student details into a file");
        System.out.println("6. Load student details from the file to the system");
        System.out.println("7. View the list of students based on their names");

    }
    static void checkAvailableSeats() { // Method for adding students

        int Seats = Max_Seats - RegCount;
        System.out.println(RegCount);
        System.out.println("Available Seats : " + Seats);

    }

    //Method for register students with name and ID
    private static void Register() {

        Scanner Details = new Scanner(System.in); //for input the student details


        while (RegCount<Max_Seats) {
            System.out.println("Enter the Student ID number to Proceed or press ( 0 ) to exit");
            if (Details.hasNextInt()) {
                String  input = Details.next();
                if (input.equals("0")) { // while loop running until 0 is inputted
                    System.out.println("Exiting..");
                    break;
                } else if (Students.contains(input)) {
                    System.out.println("ID Already Exists..");
                    RegCount--;
                } else {
                    Details.nextLine();
                    System.out.println("Enter the name of the Student.");
                    String name = Details.nextLine();
                    Students.add(input);
                    StudentName.add(name);
                    RegCount++;
                }
            } else {
                System.out.println("Invalid input!! Try again!!");
                Details.next();

            }
        }
    }
    private static void Delete() { // Method for deleting Students

        Scanner delete = new Scanner(System.in);

        System.out.println("Enter the Student ID that want to delete or press ( 0 ) to back.");

        while (true) {
            if (delete.hasNextInt()) {
                int remove = delete.nextInt();

                if (remove == 0) {
                    System.out.println("Exiting..");
                    break;
                } else {
                    int DeleteIndex = Students.indexOf(remove);
                    if (DeleteIndex != -1) { //Check if the ID is available or not
                        Students.remove(DeleteIndex);
                        StudentName.remove(DeleteIndex);
                        System.out.println("Deletion complete..");
                        RegCount--; //Decreasing the number of seats
                        break;
                    } else {
                        System.out.println("Student ID not found..!!");
                        delete.next();

                    }
                }
            } else {
                System.out.println("Invalid input!!");
                delete.next();
            }
        }
    }

    //To search with student ID
    private static void FindStudent () {

        Scanner Search = new Scanner(System.in);

        System.out.println("Enter the Id");
        while (true) {
            if (Search.hasNextInt()) {
                int SID = Search.nextInt();

                if (SID == 0) {
                    System.out.println("Exiting..");
                    break;
                } else {
                    int SearchIndex = Students.indexOf(SID);
                    if (SearchIndex != -1) {
                        System.out.println("Student ID : " + SID + ";  Name : " + StudentName.get(SearchIndex));
                    } else {
                        System.out.println("Student ID not found");
                    }
                }
            } else {
                System.out.println("Invalid input!!!");
                Search.next();
            }
        }
    }

    private static void StoreDATA () {

        try (BufferedWriter FileWrite = new BufferedWriter(new FileWriter(StudentData))) {

            for (int i = 0; i < Students.size(); i++) {
                String line = Students.get(i) + " : " + StudentName.get(i);
                FileWrite.write(line);
                FileWrite.newLine();
            }
            System.out.println("Student data Saved Sccessfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void LoadData () {
        try(BufferedReader FileRead = new BufferedReader(new FileReader(StudentData))) {
            String lines;
            while ((lines = FileRead.readLine()) != null) {
                String[] part = lines.split(" : ");
                if (part.length == 2) {
                    String ID = part[0].trim();
                    String Name = part[1].trim();
                    Students.add(ID);
                    StudentName.add((Name));
                } else {
                    System.out.println("Invalid Format");
                }
            }
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}