import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final int Max_Seats = 100; //variable for max seat capacity
    static final String[] Students = new String[Max_Seats]; //Array list for hold Student IDs
    static final String[] StudentName = new String[Max_Seats]; //Array list for hold Student Names
    static int RegCount = 0; //register counter
    private static final String StudentsReport = "Student_Report.txt"; //File for hold Name, ID, mark1, mark2, mark3,Average,grade
    private static final String StudentSummary = "Student_Summary.txt"; //File for hold Name, ID, mark1, mark2, mark3,Average,grade with detailed view for report
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
    static void CheckAvailableSeats() {

        int Seats = Max_Seats - RegCount;

        System.out.println("Available Seats : " + Seats);

    }

    //Method for register students with Name and ID
    private static void Register() {

        System.out.println();
        Scanner Details = new Scanner(System.in); // for input the student details

        while (RegCount < Max_Seats) {
            System.out.print("Enter the Student ID number to Proceed or press (0) to exit: ");


            if (Details.hasNextLine()) {
                String input = Details.nextLine().trim();

                //if the choice = 0 go back to main menu
                if (input.equals("0")) {
                    System.out.println("Exiting..");
                    break;

                } else if (Arrays.asList(Students).contains(input)) { //if the input id already exists
                    System.out.println("ID Already Exists..");

                } else if (input.length() == 8) { //check for ID Length
                    System.out.print("Enter the name of the Student: ");
                    String name = Details.nextLine().trim();
                    System.out.println();
                    Students[RegCount] = input;
                    StudentName[RegCount] = name;
                    RegCount++;
                    System.out.println("Student '" + name + "' registered with ID '" + input + "'.");
                    System.out.println();
                } else {
                    System.out.println();
                    System.out.println("ID is shorter or longer than 8. Make sure it's 1 (w) and 7 digits...");
                    System.out.println();
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
        //Display list of students for deletion reference
        ViewStudents();
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Student ID to delete or enter '0' to go back: ");
        System.out.println();

        String deleteId = scanner.nextLine().trim();

        //if the choice = 0 go back to main menu
        if (deleteId.equals("0")) {
            System.out.println("Exiting deletion process..");
            return;
        }

        //Find the index of the student with the given ID in the array
        int deleteIndex = -1;
        for (int i = 0; i < RegCount; i++) {
            if (Main.Students[i].equals(deleteId)) {
                deleteIndex = i;
                break;
            }
        }

        if (deleteIndex != -1) {
            //Switching the inputted ID index with below indexes
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

            //Set the last element to null or default values
            Main.Students[RegCount - 1] = null;
            Main.StudentName[RegCount - 1] = null;
            Main.Module_1[RegCount - 1] = 0;
            Main.Module_2[RegCount - 1] = 0;
            Main.Module_3[RegCount - 1] = 0;
            Main.Average[RegCount - 1] = 0.0;
            Main.TotalMarks[RegCount - 1] = 0;
            Main.AverageGrade[RegCount - 1] = null;

            RegCount--; // Decrease the count of registered students
            System.out.println("Student with ID '" + deleteId + "' deleted successfully.");
            System.out.println();
            StoreSummary(); // Save updated student data
        } else {
            System.out.println("Student with ID '" + deleteId + "' not found.");
        }
    }

    //To search with student ID
    private static void FindStudent () {

        //get user input for ID
        Scanner Search = new Scanner(System.in);
        System.out.println();


        while (true) {
            System.out.print("Enter the Id or press '0' to go back : ");
            if (Search.hasNextLine()) {
                String SID = Search.nextLine();
                System.out.println();

                //if the choice = 0 go back to main menu
                if (SID.equals("0")) {
                    System.out.println("Exiting..");
                    break;
                } else {
                    //get the index of the Student ID in Students Array and use it for get the Student Name from StudentName Array
                    int SearchIndex = Arrays.asList(Students).indexOf(SID);
                    if (SearchIndex != -1) {
                        System.out.println("Student ID : " + SID + ";  Name : " + StudentName[SearchIndex]);
                        System.out.println();
                    } else {
                        System.out.println("Student ID not found");
                        System.out.println();
                    }
                }
            } else {
                System.out.println("Invalid input!!!");
                Search.next();
            }
        }
    }

    //Method for store name,id,mark1,2,3, average ,total and grade
    private static void StoreSummary() {
        try (BufferedWriter FileWrite = new BufferedWriter(new FileWriter(StudentSummary))) {

            //Writing format
            for (int i = 0; i < RegCount; i++) {
                String line = Students[i] + " ; " + StudentName[i] + " ; " + Module_1[i] + " ; " + Module_2[i] + " ; " + Module_3[i] + " ; " + TotalMarks[i] +  " ; " + Average[i] + " ; " + AverageGrade[i];
                FileWrite.write(line);
                FileWrite.newLine();
            }
            System.out.println();
            System.out.println("Student's data Stored Successfully.");
            StoreFullReport();
            System.out.println("Student's Report data Stored Successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File not Found!!!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Store the above data with details
    private static void StoreFullReport() {
        try (BufferedWriter FileWrite = new BufferedWriter(new FileWriter(StudentsReport))) {

            //Writing format
            for (int i = 0; i < RegCount; i++) {
                String line = "Student ID : " + Students[i] + "; Student Name : " + StudentName[i] + "; Module 1 Marks : " + Module_1[i] + "; Module 2 Marks : " + Module_2[i] + "; Module 3 Marks : " + Module_3[i] + "; Total Marks : " + TotalMarks[i] + "; Average Marks : " +  Average[i] + "; Grade : " + AverageGrade[i];
                FileWrite.write(line);
                FileWrite.newLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not Found!!!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //MMethod for load data to their arrays
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

                //Adding data from file ro arrays
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
        } catch (FileNotFoundException e) {
            System.out.println("File not Found!!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Method for Sorted view of Students
    private static void ViewStudents() {
        //Create an array for hold the sorted average marks
        Integer[] average = new Integer[RegCount];
        for (int i = 0; i < RegCount; i++) {
            average[i] = i;
        }

        //Bubble sort based on Avereage marks
        for (int i = 0; i < RegCount - 1; i++) {
            for (int j = 0; j < RegCount - i - 1; j++) {
                if (Average[average[j]] > Average[average[j + 1]]) {
                    // Swap indices
                    int temp = average[j];
                    average[j] = average[j + 1];
                    average[j + 1] = temp;
                }
            }
        }

        //Print student details in sorted order by average marks
        for (int i = 0; i < RegCount; i++) {
            int index = average[i];
            System.out.println("Name: " + StudentName[index] + ", ID: " + Students[index] + ", Average Marks: " + Average[index]);
        }
    }

    //Method for menu2
    static void Menu2() {
        System.out.println();
        System.out.println("a. Add Name");
        System.out.println("b. Add Module Marks");
        System.out.println("c. View Summary");
        System.out.println("d. Student Details Report");
        System.out.println("0. go mack to main menu");
        System.out.println();
    }

    //Method for Display Menu 2
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
        System.out.println();
        Scanner Details = new Scanner(System.in);

        while (RegCount < Max_Seats) {
            System.out.print("Enter the Student Name or press '0' to exit : ");
            if (Details.hasNextLine()) {
                String input = Details.nextLine().trim();

                //if the choice = 0 go back to main menu
                if (input.equals("0")) {
                    System.out.println("Exiting..");
                    break;
                } else if (Arrays.asList(StudentName).contains(input)) { //check if the name is available in the Array
                    System.out.println();
                    System.out.println("Name already exist...");
                    System.out.println();

                } else {
                    //input the ID
                    System.out.print("Enter the ID : ");
                    String id = Details.nextLine();
                    if (id.length() == 8) {
                        Students[RegCount] = id;
                        StudentName[RegCount] = input;
                        RegCount++;
                        System.out.println("Student Name ; " + input + " Registered successfully...");
                        System.out.println();
                    } else {
                        System.out.println("ID is shorter or longer than 8. Make sure it's 1 (w) and 7 digits...");
                    }
                }
            }
        }
        System.out.println();
    }

    //Method for Add Marks and get the total and average
    private static void Student_Mark() {
        System.out.println();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the Student Name or ID : ");
        String Name = input.nextLine();

        int index = FindIndex(Name);
        if (index == -1) {
            System.out.println("Student not found.");
            System.out.println();
            return;
        }

        //Create a new Student instance using the found index
        Student student = new Student(Students[index], StudentName[index]);

        //Input module marks for the student
        student.inputModuleMarks(index);

        System.out.println("Marks added successfully.");
    }

    //Method to find index by Name or ID
    private static int FindIndex(String Name) {

        for (int i = 0; i < RegCount; i++) {
            if (Students[i].equals(Name) || StudentName[i].equals(Name)) {
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
        //Filtering the students who got marks higher than 40 for each module
        for (int i = 0;i < RegCount; i++) {
            if (Module_1[i] > 40 && Module_2[i] > 40 && Module_3[i] > 40) {
                //Displaying Students
                System.out.println("Student ID : " + Students[i] + " | " + " Student Name : " + StudentName[i] + " | " + Average[i]);
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
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}