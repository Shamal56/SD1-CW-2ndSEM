public class Module {

    private static int mark = 0;
    private final String grade;

    public Module(int mark) {
        this.mark = mark;
        this.grade = GradeSystem();
    }

    static String GradeSystem() {
        if (mark >= 80) {
            return "Distinction";
        } else if (mark >= 70) {
            return "Merit";
        } else if (mark >= 40) {
            return "Pass";
        } else {
            return "Fail";
        }
    }

    public int getMark() {
        return mark;
    }

    public String getGrade() {
        return grade;
    }
    
}
