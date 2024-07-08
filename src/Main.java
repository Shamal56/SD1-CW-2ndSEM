import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    private static final int Max_Seats = 100; //variable for max seat capacity
    static final String[] Students = new String[Max_Seats]; //Array list for hold Student IDs
    static final String[] StudentName = new String[Max_Seats]; //Array list for hold Student Names
    static int RegCount = 0; //register counter
    private static final String StudentsReport = "Student_Report.txt"; //File for hold Name, ID, mark1, mark2, mark3,Average,grade
    private static final String StudentSummary = "Student_Summary.txt"; //File for hold Name, ID, mark1, mark2, mark3,Average,grade with detaled view for report
    static final int[] Module_1 = new int[Max_Seats]; //Array list for hold Module 1 marks
    static final int[] Module_2 = new int[Max_Seats]; //Array list for hold Module 2 marks
    static final int[] Module_3 = new int[Max_Seats]; //Array list for hold Module 3 marks
    static final double[] Average = new double[Max_Seats]; //Array list for hold Module marks Average
    static final String[] AverageGrade = new String[Max_Seats]; //Array list for hold Modules Grade
    static final int[] TotalMarks = new int[Max_Seats]; //Array list for hold Module Total marks




    public static void main(String[] args) {
        //Displays main menu and get the user choices
        System.out.println();
        while (true) {
            Menu();
            Scanner input = new Scanner(System.in);
            System.out.print("Enter your choice : ");
            int Choice = input.nextInt();
            input.nextLine();

            switch (Choice) {
                case 1:
                    System.out.println();
                    CheckAvailableSeats();
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
                    StoreSummary();
                    System.out.println();
                    break;

                case 6:
                    LoadSummaryData();
                    System.out.println();
                    break;

                case 7:
                    System.out.println();
                    System.out.println("Sorted list of students by Average marks:");
                    System.out.println("_______________________________________________________________");
                    ViewStudents();
                    System.out.println("_______________________________________________________________");
                    System.out.println();
                    break;

                case 8:
                    DisplayMenu2();
                    System.out.println();
                    break;

                case 0:
                    System.out.println("Exiting....");
                    input.close();
                    return;
                //if the user input not in the choices
                default:
                    System.out.println("Invalid Choice!! Try Again!!");
            }
        }
    }

    //mEthod for hold the menu items
    static void Menu() {
        System.out.println("1. Check available seats");
        System.out.println("2. Register student (with ID)");
        System.out.println("3. Delete student");
        System.out.println("4. Find student (with student ID)");
        System.out.println("5. Store student details into a file");
        System.out.println("6. Load student details from the file to the system");
        System.out.println("7. View the list of students based on their names");
        System.out.println("8. Student Data");
        System.out.println("0. Exit the program");
        System.out.println();
    }

    //Method for View the available seats
    static void CheckAvailableSeats() { // Method for adding students

        int Seats = Max_Seats - RegCount;

        System.out.println("Available Seats : " + Seats);
        System.out.println();

    }

    //Method for register students with Name and ID
    private static void Register() {
        Scanner Details = new Scanner(System.in); // for input the student details

        while (RegCount < Max_Seats) {
            System.out.print("Enter the Student ID number to Proceed or press (0) to exit: ");
            System.out.println();

            if (Details.hasNextLine()) {
                String input = Details.nextLine().trim();

                //if the choice = 0 go back to main menu
                if (input.equals("0")) {
                    System.out.println("Exiting..");
                    break;

                    //if the input id already exists
                } else if (Arrays.asList(Students).contains(input)) {
                    System.out.println("ID Already Exists..");

                    //check for ID Length
                } else if (input.length() == 8) {
                    System.out.print("Enter the name of the Student: ");
                    String name = Details.nextLine().trim();
                    Students[RegCount] = input;
                    StudentName[RegCount] = name;
                    RegCount++;
                    System.out.println("Student '" + name + "' registered with ID '" + input + "'.");
                } else {
                    System.out.println("ID is shorter or longer than 8. Make sure it's 1 letter followed by 7 digits...");
                }
            } else {
                System.out.println("Invalid input!! Try again!!");
                Details.nextLine(); // Clear the invalid input
            }
        }
        System.out.println();
    }

    //Method for Delete Student details
    private static void Delete() {
        System.out.println();
        ViewStudents(); // Display list of students for deletion reference
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Student ID to delete or enter '0' to go back: ");
        System.out.println();

        String deleteId = scanner.nextLine().trim();

        if (deleteId.equals("0")) {
            System.out.println("Exiting deletion process..");
            return;
        }

        // Find the index of the student with the given ID in the array
        int deleteIndex = -1;
        for (int i = 0; i < RegCount; i++) {
            if (Main.Students[i].equals(deleteId)) {
                deleteIndex = i;
                break;
            }
        }

        if (deleteIndex != -1) {
            // Shift elements to remove the student at deleteIndex
            for (int i = deleteIndex; i < RegCount - 1; i++) {
                Main.Students[i] = Main.Students[i + 1];
                Main.StudentName[i] = Main.StudentName[i + 1];
                Main.Module_1[i] = Main.Module_1[i + 1];
                Main.Module_2[i] = Main.Module_2[i + 1];
                Main.Module_3[i] = Main.Module_3[i + 1];
                Main.Average[i] = Main.Average[i + 1];
                Main.TotalMarks[i] = Main.TotalMarks[i + 1];
                Main.AverageGrade[i] = Main.AverageGrade[i + 1];
            }

            // Set the last element to null or default values
            Main.Students[RegCount - 1] = null;
            Main.StudentName[RegCount - 1] = null;
            Main.Module_1[RegCount - 1] = 0;
            Main.Module_2[RegCount - 1] = 0;
            Main.Module_3[RegCount - 1] = 0;
            Main.Average[RegCount - 1] = 0.0;
            Main.TotalMarks[RegCount - 1] = 0;
            Main.AverageGrade[RegCount - 1] = null;

            RegCount--; // Decrease the count of registered students
            StoreSummary(); // Save updated student data

            System.out.println("Student with ID '" + deleteId + "' deleted successfully.");
        } else {
            System.out.println("Student with ID '" + deleteId + "' not found.");
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
                String SID = Search.nextLine();

                if (SID.equals("0")) {
                    System.out.println("Exiting..");
                    break;
                } else {
                    int SearchIndex = Arrays.asList(Students).indexOf(SID);
                    if (SearchIndex != -1) {
                        System.out.println("Student ID : " + SID + ";  Name : " + StudentName[SearchIndex]);
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

    //MEthod for store name,id,mark1,2,3, average ,total and grade
    private static void StoreSummary() {
        try (BufferedWriter FileWrite = new BufferedWriter(new FileWriter(StudentSummary))) {

            for (int i = 0; i < RegCount; i++) {
                String line = Students[i] + " ; " + StudentName[i] + " ; " + Module_1[i] + " ; " + Module_2[i] + " ; " + Module_3[i] + " ; " + TotalMarks[i] +  " ; " + Average[i] + " ; " + AverageGrade[i];
                FileWrite.write(line);
                FileWrite.newLine();
            }
            System.out.println("Student's data Stored Successfully.");
        } catch (IOException e) {
            System.out.println(e);;
        }
        StoreFullReport();
        System.out.println("Students Report data Stored Successfully.");
    }
    //Store the above data with details
    private static void StoreFullReport() {
        try (BufferedWriter FileWrite = new BufferedWriter(new FileWriter(StudentsReport))) {

            for (int i = 0; i < RegCount; i++) {
                String line = "Student ID : " + Students[i] + "; Student Name : " + StudentName[i] + "; Module 1 Marks : " + Module_1[i] + "; Module 2 Marks : " + Module_2[i] + "; Module 3 Marks : " + Module_3[i] + "; Total Marks : " + TotalMarks[i] + "; Average Marks : " +  Average[i] + "; Grade : " + AverageGrade[i];
                FileWrite.write(line);
                FileWrite.newLine();
            }

        } catch (IOException e) {
            System.out.println(e);;
        }
    }


    private static void LoadSummaryData() {
        try (BufferedReader FileRead = new BufferedReader(new FileReader(StudentSummary))) {
            String line;
            while ((line = FileRead.readLine()) != null) {
                String[] data = line.split(" ; ");
                String ID = data[0];
                String name = data[1];
                int mod1 = Integer.parseInt(data[2]);
                int mod2 = Integer.parseInt(data[3]);
                int mod3 = Integer.parseInt(data[4]);
                int totalMarks = Integer.parseInt(data[5]);
                double avg = Double.parseDouble(data[6]);
                String grade = data[7];

                int index = RegCount;
                Students[index] = ID;
                StudentName[index] = name;
                Module_1[index] = mod1;
                Module_2[index] = mod2;
                Module_3[index] = mod3;
                TotalMarks[index] = totalMarks;
                Average[index] = avg;
                AverageGrade[index] = grade;

                RegCount++;
            }
            System.out.println();
            System.out.println("Student Data Loaded Successfully.");
        } catch (IOException e) {
            System.out.println(e);;
        }
    }


    private static void ViewStudents() {
        // Create an array of indices to sort by average marks
        Integer[] indices = new Integer[RegCount];
        for (int i = 0; i < RegCount; i++) {
            indices[i] = i;
        }

        // Sort indices based on the Average array
        Arrays.sort(indices, Comparator.comparingDouble(i -> Average[i]));

        // Print student details in sorted order by average marks
        for (int i = 0; i < RegCount; i++) {
            int index = indices[i];
            System.out.println("Name: " + StudentName[index] + ", ID: " + Students[index] + ", Average Marks: " + Average[index]);
        }
    }

    //Method for display menu2
    static void Menu2() {
        System.out.println();
        System.out.println("a. Add Name");
        System.out.println("b. Add Module Marks");
        System.out.println("c. View Summary");
        System.out.println("d. Student Details Report");
        System.out.println("0. go mack to main menu");
        System.out.println();
    }

    static void DisplayMenu2() {
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
                    Summary();
                    break;

                case "d":
                    Report();
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

    //Same as register Student
    static void AddName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the Student Name : ");
        System.out.println();
        String name = input.nextLine();

        int index = findIndexByName(name);
        if (index != -1) {
            System.out.println("Student with name '" + name + "' already exists.");
            System.out.println();
        } else {
            // Assuming student ID is managed or generated elsewhere
            System.out.print("Enter the Student ID : ");
            System.out.println();
            String id = input.nextLine();

            // Add student to lists
            Students[RegCount] = id;
            StudentName[RegCount]= name;

            System.out.println("Student '" + name + "' registered with ID '" + id + "'.");
            System.out.println();
            RegCount++;
        }
    }


    private static int findIndexByName(String name) {
        for (int i = 0; i < RegCount; i++) {
            if (StudentName[i].equals(name)) {
                return i;
            }
        }
        return -1; // Not found
    }

    //Method for Add Marks and get the total and average
    private static void Student_Mark() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the Student Name or ID : ");
        System.out.println();
        String identifier = input.nextLine();

        int index = findIndexByIdentifier(identifier);
        if (index == -1) {
            System.out.println("Student not found.");
            System.out.println();
            return;
        }

        // Create a new Student instance using the found index
        Student student = new Student(Students[index], StudentName[index]);

        // Input module marks for the student
        student.inputModuleMarks(index);

        System.out.println("Marks added successfully.");
        System.out.println();
    }

    // Helper method to find index by identifier (name or ID)
    private static int findIndexByIdentifier(String identifier) {
        for (int i = 0; i < RegCount; i++) {
            if (Students[i].equals(identifier) || StudentName[i].equals(identifier)) {
                return i;
            }
        }
        return -1; // Not found
    }

    //Method for View the registered student count and students who got 40 each module
    private static void Summary() {
        System.out.println();
        System.out.println("Total Registered Students : " + RegCount);
        System.out.println();
        System.out.println("Total no of students who are scored more than 40 marks in Module 1, 2 and 3.");
        System.out.println("_________________________________________________________________________");
        for (int i = 0;i < RegCount; i++) {
            if (Module_1[i] > 40 && Module_2[i] > 40 && Module_3[i] > 40) {
                System.out.println("Student ID : " + Students[i] + " Student Name : " + StudentName[i]);
            }
        }
        System.out.println("_________________________________________________________________________");
    }

    //Method for view the full Report
    private static void Report() {

        System.out.println();
        //Reading the File data
        try (BufferedReader Read = new BufferedReader(new FileReader(StudentsReport))) {
            String line;
            while ((line = Read.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error Occurred : " + e);
        }
        System.out.println();
    }
}