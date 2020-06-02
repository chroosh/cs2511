package unsw.enrolment;

public class Grade {
    private double mark;
    private String grade;

    public Grade(double mark2) {
        this.mark = mark2;
        if (mark2 < 50)
            grade = "FL";
        else if (mark2 < 65)
            grade = "PS";
        else if (mark2 < 75)
            grade = "DN";
        else
            grade = "HD";
    }

    public boolean isPassing() {
        return mark >= 50;
    }
}
