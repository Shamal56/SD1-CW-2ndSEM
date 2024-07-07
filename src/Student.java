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

    public static Student findStudentByName(String name) {
        int index = Main.StudentName.indexOf(name);
        if (index != -1) {
            String id = Main.Students.get(index);
            return new Student(id, name);
        } else {
            return null;
        }
    }

    public void inputModuleMarks() {
        Scanner input = new Scanner(System.in);
        for (int i = 1; i <= 3; i++) {
            System.out.println("Enter mark for module " + i + ": ");
            int mark = input.nextInt();
            addModule(new Module(mark));
        }
    }
}
