package unsw.enrolment;
import java.util.List;
import java.util.ArrayList;

public class Enrolment {

    private CourseOffering offering;
    private Grade grade;
    private Student student;

    public Enrolment(CourseOffering offering, Student student) {
    	
        this.offering = offering;
        this.student = student;
    }
    
    

    public Course getCourse() {
        return offering.getCourse();
    }

    public Grade getGrade() {
		return grade;
	}
    

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public String getTerm() {
        return offering.getTerm();
    }

}
