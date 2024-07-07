public class Module {

    private final int mark;
    private final String grade;

    public Module(int mark) {
        this.mark = mark;
        this.grade = calculateGrade(mark);
    }

    private String calculateGrade(int mark) {
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
