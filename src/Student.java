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

        for (int i = 1; i <= 3; i++) {
            System.out.print("Enter mark for module " + i + ": ");
            int mark = input.nextInt();
            Module module = new Module(mark);
            addModule(module);

            // Update the respective module lists
            if (i == 1) {
                Main.Module_1.add(mark);
                Main.Module1.add(module.getGrade());
            } else if (i == 2) {
                Main.Module_2.add(mark);
                Main.Module2.add(module.getGrade());
            } else if (i == 3) {
                Main.Module_3.add(mark);
                Main.Module3.add(module.getGrade());
            }

            // Calculate total marks
            totalMarks += mark;
        }

        // Calculate average marks
        double AverageMarks = (double) totalMarks / 3;

        // Update the Average list at the given index
        Main.Average.add(AverageMarks);
    }

    public static Student findStudentByName(String name) {
        int index = Main.StudentName.indexOf(name);
        if (index != -1) {
            String id = Main.Students.get(index);
            return new Student(id, name);
        } else {
            return null;
        }
    }
}
