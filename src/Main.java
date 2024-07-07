import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    private static final int Max_Seats = 100;
    static final ArrayList<String> Students = new ArrayList<>(Max_Seats);
    static final ArrayList<String> StudentName = new ArrayList<>();
    private static int RegCount = 0;
    private static final String StudentData = "Student_Details.txt";
    private static final String StudentsReport = "Student_Report.txt";
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
                    viewSortedStudents();
                    break;

                case 8:
                    displayMenu2();
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
        System.out.println("8. Student Data");
    }
    static void checkAvailableSeats() { // Method for adding students

        int Seats = Max_Seats - Students.size();
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
                }
                else if (Students.contains(input)) {
                    System.out.println("ID Already Exists..");
                    RegCount--;
                }
                else {
                    Details.nextLine();
                    System.out.println("Enter the name of the Student.");
                    String name = Details.nextLine();
                    Students.add(input);
                    StudentName.add(name);
                    RegCount++;
                }
            }
            else {
                System.out.println("Invalid input!! Try again!!");
                Details.next();

            }
        }
    }


    private static void Delete() { // Method for deleting Students

        Scanner delete = new Scanner(System.in);

        System.out.println("Enter the Student ID that want to delete or press ( 0 ) to back.");

        while (true) {
            if (delete.hasNextLine()) {
                String remove = String.valueOf(delete.nextInt());

                if (remove.equals("0")) {
                    System.out.println("Exiting..");
                    break;
                }
                else {
                    int DeleteIndex = Students.indexOf(remove);
                    if (DeleteIndex != -1) { //Check if the ID is available or not
                        Students.remove(DeleteIndex);
                        StudentName.remove(DeleteIndex);
                        System.out.println("Deletion complete..");
                        RegCount--; //Decreasing the number of seats
                        break;
                    }
                    else {
                        System.out.println("Student ID not found..!!");
                        delete.next();

                    }
                }
            }
            else {
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
            if (Search.hasNextLine()) {
                String SID = String.valueOf(Search.nextInt());

                if (SID.equals("0")) {
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
            System.out.println("Student data Saved Successfully.");
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

    private static void viewSortedStudents() {
        bubbleSort(); // Sort StudentName list

        System.out.println("Sorted list of students:");
        for (String sortedName : StudentName) {
            String id = getStudentIdByName(sortedName); // Get ID corresponding to sorted name
            System.out.println("ID: " + id + ", Name: " + sortedName);
        }
    }
    //For get IDs of Names
    private static String getStudentIdByName(String name) {
        //for loop for iterate and search in Students Arraylist for the index of Name
        for (int i = 0; i < StudentName.size(); i++) {
            if (StudentName.get(i).equals(name)) {
                return Students.get(i);
            }
        }
        //If not available
        return null;
    }

    private static void bubbleSort() {
        int n = Main.StudentName.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (Main.StudentName.get(j).compareTo(Main.StudentName.get(j + 1)) > 0) {
                    Collections.swap(Main.StudentName, j, j + 1);
                }
            }
        }
    }
    static void Menu2() {
        System.out.println("a. Add Name");
        System.out.println("b. Module Marks");
        System.out.println("c. Summary");
        System.out.println("d. Student Detail Report");
        System.out.println("0. go mack to main menu");
    }
    static void displayMenu2() {
        Scanner input = new Scanner(System.in);
        boolean exitMenu = false;
        while (!exitMenu) {
            Menu2();
            System.out.println("Enter your choice :");
            String Choice = input.next();
            input.nextLine();

            switch (Choice) {
                case "a":
                    AddName();
                    break;
                case "b":
                    Student_Mark();
                    break;

                case "c":

                    break;

                case "d":

                    break;

                case "0":
                    exitMenu = true;
                    System.out.println("Exiting to the main menu..");
                    System.out.println();
                    System.out.println();
                    break;

                default:
                    System.out.println("Invalid Choice!! Try Again!!");
            }
        }
    }

    static void AddName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the Student Name : ");
        String name = input.nextLine();

        int index = StudentName.indexOf(name);
        if (index != -1) {
            System.out.println("Student with name '" + name + "' already exists.");
        } else {
            // Assuming student ID is managed or generated elsewhere
            System.out.println("Enter the Student ID:");
            String id = input.nextLine();

            // Create a new student object
            Student student = new Student(id, name);

            // Add student to lists
            Students.add(id);
            StudentName.add(name);

            System.out.println("Student '" + name + "' registered with ID '" + id + "'.");
            RegCount++;
        }
    }
    private static void Student_Mark() {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the Student name : ");
        String name = input.nextLine();

        int index = StudentName.indexOf(name);
        if (index != -1) {
            Student student = new Student(Students.get(index), name);
            student.inputModuleMarks();

            System.out.println("Marks and Grades:");
            for (Module module : student.getModules()) {
                System.out.println("Mark: " + module.getMark() + ", Grade: " + module.getGrade());
            }
        } else {
            System.out.println("Student not found");
        }
    }

}