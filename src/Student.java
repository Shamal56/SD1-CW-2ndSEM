import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student {
    private final String id;
    private final String name;
    private final List<Module> modules;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.modules = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addModule(Module module) {
        if (modules.size() < 3) {
            modules.add(module);
        } else {
            System.out.println("Cannot add more than 3 modules");
        }
    }

    public List<Module> getModules() {
        return modules;
    }

    public void inputModuleMarks(int index) {
        Scanner input = new Scanner(System.in);
        int totalMarks = 0;
        System.out.println();
        for (int i = 1; i <= 3; i++) {
            System.out.print("Enter mark for module " + i + ": ");
            int mark = input.nextInt();
            Module module = new Module(mark);
            addModule(module);

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
        System.out.println("Grade : " + Grade);
        System.out.println();
    }

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