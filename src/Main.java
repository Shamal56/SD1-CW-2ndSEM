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
    private static final String StudentSummary = "Student_Summary.txt";
    static final ArrayList<Integer> Module_1 = new ArrayList<>();
    static final ArrayList<Integer> Module_2 = new ArrayList<>();
    static final ArrayList<Integer> Module_3 = new ArrayList<>();
    static final ArrayList<Double> Average = new ArrayList<>();
    static final ArrayList<String> Module1 = new ArrayList<>();
    static final ArrayList<String> Module2 = new ArrayList<>();
    static final ArrayList<String> Module3 = new ArrayList<>();



    public static void main(String[] args) {

        System.out.println();
        Scanner input = new Scanner(System.in);
        while (true) {
            Menu();
            System.out.print("Enter your choice : ");
            int Choice = input.nextInt();
            input.nextLine();

            switch (Choice) {
                case 1:
                    System.out.println();
                    checkAvailableSeats();
                    System.out.println();
                    break;

                case 2:
                    Register();
                    System.out.println();
                    break;

                case 3:
                    Delete();
                    System.out.println();
                    break;

                case 4:
                    FindStudent();
                    System.out.println();
                    break;

                case 5:
                    StoreDATA();
                    System.out.println();
                    break;

                case 6:
                    LoadData();
                    System.out.println();
                    break;

                case 7:
                    ViewStudents();
                    System.out.println();
                    break;

                case 8:

                    displayMenu2();
                    System.out.println();
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
        System.out.println();
    }


    static void checkAvailableSeats() { // Method for adding students

        int Seats = Max_Seats - Students.size();
        System.out.println("Taken : " + RegCount);
        System.out.println("Available Seats : " + Seats);
        System.out.println();

    }

    //Method for register students with name and ID
    private static void Register() {

        Scanner Details = new Scanner(System.in); //for input the student details

        while (RegCount<Max_Seats) {
            System.out.print("Enter the Student ID number to Proceed or press ( 0 ) to exit : ");
            System.out.println();

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
                    System.out.print("Enter the name of the Student : ");
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
        System.out.println();
    }


    private static void Delete() { // Method for deleting Students

        Scanner delete = new Scanner(System.in);

        System.out.print("Enter the Student ID that want to delete or press ( 0 ) to back : ");
        System.out.println();

        while (true) {
            ViewStudents();
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
                        Module_1.remove(DeleteIndex);
                        Module_2.remove(DeleteIndex);
                        Module_3.remove(DeleteIndex);
                        Module1.remove(DeleteIndex);
                        Module2.remove(DeleteIndex);
                        Module3.remove(DeleteIndex);
                        Average.remove(DeleteIndex);

                        System.out.println("Deletion complete..");
                        RegCount--; //Decreasing the number of seats
                        StoreDATA();
                        StoreSummary();
                        StoreFullReport();
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
        System.out.println();
    }

    //To search with student ID
    private static void FindStudent () {

        Scanner Search = new Scanner(System.in);

        System.out.print("Enter the Id : ");
        System.out.println();
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
        StoreSummary();
        StoreFullReport();
    }

    private static void StoreSummary() {
        try (BufferedWriter FileWrite = new BufferedWriter(new FileWriter(StudentSummary))) {

            for (int i = 0; i < Students.size(); i++) {
                String line = Students.get(i) + " ; " + StudentName.get(i) + " ; " + Module_1.get(i) + " ; " + Module_2.get(i) + " ; " + Module_3.get(i) + " ; " + Average.get(i);
                FileWrite.write(line);
                FileWrite.newLine();
            }
            System.out.println("Student data Saved Successfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void StoreFullReport() {
        try (BufferedWriter FileWrite = new BufferedWriter(new FileWriter(StudentsReport))) {

            for (int i = 0; i < Students.size(); i++) {
                String line = Students.get(i) + " ; " + StudentName.get(i) + " ; "  + Module_1.get(i) + " ; " + Module1.get(i) + " ; " + Module_2.get(i) + " ; " + Module2.get(i) + " ; " + Module_3.get(i) + " ; " + Module1.get(i) + " ; " + Average.get(i);
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

        LoadSummaryData();
        LoadReportData();

    }

    private static void LoadSummaryData () {

        try(BufferedReader FileRead = new BufferedReader(new FileReader(StudentSummary))) {
            String lines;

            while ((lines = FileRead.readLine()) != null) {
                String[] part = lines.split(" ; ");

                if (part.length == 6) {
                    int module_1 = Integer.parseInt(part[2].trim());
                    int module_2 = Integer.parseInt(part[3].trim());
                    int module_3 = Integer.parseInt(part[4].trim());
                    double avg = Double.parseDouble(part[5].trim());
                    Module_1.add(module_1);
                    Module_2.add(module_2);
                    Module_3.add(module_3);
                    Average.add(avg);

                } else {
                    System.out.println("Invalid Format");
                }
            }
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void LoadReportData () {

        try(BufferedReader FileRead = new BufferedReader(new FileReader(StudentsReport))) {
            String lines;

            while ((lines = FileRead.readLine()) != null) {
                String[] part = lines.split(" ; ");

                if (part.length == 9) {
                    String grade_1 = part[3];
                    String grade_2 = part[5];
                    String grade_3 = part[7];
                    Module1.add(grade_1);
                    Module2.add(grade_2);
                    Module3.add(grade_3);

                } else {
                    System.out.println("Invalid Format");
                }
            }
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void ViewStudents() {
        bubbleSort(); // Sort by Average marks

        System.out.println("Sorted list of students by Average marks:");
        for (String sortedName : StudentName) {
            String id = getStudentIdByName(sortedName); // Get ID corresponding to sorted name
            double average = getAverageByStudentName(sortedName); // Get average marks corresponding to sorted name
            System.out.println("ID: " + id + ", Name: " + sortedName + ", Average Marks: " + average);
        }
    }

    // Retrieve average marks by student name
    private static double getAverageByStudentName(String name) {
        int index = StudentName.indexOf(name);
        if (index != -1) {
            return Average.get(index);
        }
        return 0.0; // Return 0 if name not found (though it shouldn't happen if sorting is correct)
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
            System.out.print("Enter your choice : ");
            String Choice = input.next();
            input.nextLine();

            switch (Choice) {
                case "a":
                    AddName();
                    System.out.println();
                    break;
                case "b":
                    Student_Mark();
                    System.out.println();
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
        System.out.print("Enter the Student Name : ");
        System.out.println();
        String name = input.nextLine();

        int index = StudentName.indexOf(name);
        if (index != -1) {
            System.out.println("Student with name '" + name + "' already exists.");
            System.out.println();
        } else {
            // Assuming student ID is managed or generated elsewhere
            System.out.print("Enter the Student ID : ");
            System.out.println();
            String id = input.nextLine();

            // Create a new student object
            Student student = new Student(id, name);

            // Add student to lists
            Students.add(id);
            StudentName.add(name);

            System.out.println("Student '" + name + "' registered with ID '" + id + "'.");
            System.out.println();
            RegCount++;
        }
    }

    private static void Student_Mark() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the Student Name or ID : ");
        System.out.println();
        String identifier = input.nextLine();

        int index = -1;
        Student student = Student.findStudentByName(identifier);

        if (student == null) {
            index = Students.indexOf(identifier);
            if (index != -1) {
                student = new Student(identifier, StudentName.get(index));
            }
        } else {
            index = StudentName.indexOf(student.getName());
        }

        if (student == null) {
            System.out.println("Student not found.");
            System.out.println();
            return;
        }

        student.inputModuleMarks(index);
        System.out.println("Marks added successfully.");
        System.out.println();
    }
    private static void Summary() {



    }
}