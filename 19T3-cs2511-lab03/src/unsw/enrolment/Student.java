package unsw.enrolment;
import java.util.ArrayList;

public class Student {

    private String zid;
    private ArrayList<Enrolment> enrolments;

	public Student(String zid) {
        this.zid = zid;
        enrolments = new ArrayList<>();
    }

	public String getZID() {
		return zid;
	}

	public ArrayList<Enrolment> getEnrolments() {
		return enrolments;
	}
	
	public void addEnrolment(Enrolment e) {
		enrolments.add(e);
	}
	
	public void setGrade(String courseCode, int mark, String grade) {
		for (Enrolment e : enrolments) {
			if (e.getCourse().getCourseCode() == courseCode) {
				e.setGrade(new Grade(mark, grade));
			}
		}
	}

}
