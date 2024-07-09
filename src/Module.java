import java.util.ArrayList;
import java.util.List;

public class Module {
    private static List<Module> modules;
    private int mark = 0;

    public Module(int mark) {
        this.mark = mark;
        modules = new ArrayList<>();
    }

    //Method for get the marks
    public int getMark() {
        return mark;
    }

    //Method for add modules
    public static void AddModule(Module module) {
        if (modules.size() < 3) {
            modules.add(module);
        } else {
            System.out.println("Cannot add more than 3 modules");
        }
    }
}
