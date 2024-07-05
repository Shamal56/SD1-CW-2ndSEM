import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final int Max_Seats = 100;
    private static final ArrayList<Integer> Students = new ArrayList<>(Max_Seats);
    private static final ArrayList<String> StudentName = new ArrayList<>();
    private static int RegCount = 0;
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
                    break;

                case 6:
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


        System.out.println("Enter the Student ID number to Proceed or press ( 0 ) to exit");
        while (RegCount<Max_Seats) {
            if (Details.hasNextInt()) {
                int input = Details.nextInt();
                if (input == 0) { // while loop running until 0 is inputted
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
                    int index = Students.indexOf(remove);
                    if (index < 100 || index > 0) { //Check if the ID is available or not
                        Students.remove(index);
                        StudentName.remove(index);
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

    private static void FindStudent(){

        Scanner Search = new Scanner(System.in);


    }
}