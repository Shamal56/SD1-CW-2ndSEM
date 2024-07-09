import java.util.Scanner;

public class Student {
    private final String id;
    private final String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    //Method to get the id
    public String getId() {
        return id;
    }

    //Method to get the anme
    public String getName() {
        return name;
    }

    //Method for input marks to the modules one by one
    public void inputModuleMarks(int index) {
        Scanner input = new Scanner(System.in);
        int totalMarks = 0;
        System.out.println();
        for (int i = 1; i <= 3; i++) {
            System.out.print("Enter mark for module " + i + ": ");
            int mark = input.nextInt();
            Module module = new Module(mark);
            Module.AddModule(module);

            // Update the respective module lists
            if (i == 1) {
                Main.Module_1[index] = mark;
            } else if (i == 2) {
                Main.Module_2[index] = mark;
            } else {
                Main.Module_3[index] = mark;
            }

            // Calculate total marks
            totalMarks += module.getMark();
        }

        Main.TotalMarks[index] = totalMarks;
        // Calculate average marks
        double AverageMarks = (double) totalMarks / 3;

        // Update the Average list at the given index
        Main.Average[index] = AverageMarks;
        String Grade = AVGGrade(AverageMarks);
        Main.AverageGrade[index] = Grade;
        System.out.println();
        System.out.println("Student ID: " + getId());
        System.out.println("Student Name: " + getName());
        System.out.println("Grade : " + Grade);
    }

    //Method for get the Average Grade
    private String AVGGrade(double AverageMarks) {
        String Grade;
        if (AverageMarks >= 80) {
            Grade = "Distinction";
        } else if (AverageMarks >= 70) {
            Grade = "Merit";
        } else if (AverageMarks >= 40) {
            Grade = "Pass";
        } else {
            Grade = "Fail";
        }
        return Grade;
    }
}