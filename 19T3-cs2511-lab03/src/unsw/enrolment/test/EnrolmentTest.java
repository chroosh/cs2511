package unsw.enrolment.test;

import unsw.enrolment.Course;
import unsw.enrolment.CourseOffering;
import unsw.enrolment.Lecture;
import unsw.enrolment.Student;
import unsw.enrolment.Enrolment;
import unsw.enrolment.Grade;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EnrolmentTest {

    public static void main(String[] args) {

    	// Create courses
        Course comp1511 = new Course("COMP1511", "Programming Fundamentals");
        Course comp1531 = new Course("COMP1531", "Software Engineering Fundamentals");
        comp1531.addPrereq(comp1511);
        Course comp2521 = new Course("COMP2521", "Data Structures and Algorithms");
        comp2521.addPrereq(comp1511);

        CourseOffering comp1511Offering = new CourseOffering(comp1511, "19T1");
        CourseOffering comp1531Offering = new CourseOffering(comp1531, "19T1");
        CourseOffering comp2521Offering = new CourseOffering(comp2521, "19T2");

        // TODO Create some sessions for the courses
        comp1511Offering.addSession(new Lecture("Ainsworth", DayOfWeek.of(1), LocalTime.of(9, 0), LocalTime.of(11, 0), "andrewt"));
        comp1531Offering.addSession(new Lecture("Goldstein", DayOfWeek.of(2), LocalTime.of(11, 0), LocalTime.of(13, 0), "hsmith"));
        comp2521Offering.addSession(new Lecture("Science Theatre", DayOfWeek.of(3), LocalTime.of(15, 0), LocalTime.of(17, 0), "jashank"));

        // TODO Create a student
		Student chris = new Student("z5165244");
		System.out.println(chris.getZID() + " successfully created");

        // TODO Enrol the student in COMP1511 for T1 (this should succeed)
		if (checkPrereq(comp1511Offering, chris) == true) {
			Enrolment e = new Enrolment(comp1511Offering, chris);
			chris.addEnrolment(e);
			System.out.println("enrolment into " + e.getCourse().getCourseCode() + " for " + e.getTerm() + " was successful");
		} else {
			System.out.println("enrolment was unsuccessful");
		}

				
		
        // TODO Enrol the student in COMP1531 for T1 (this should fail as they
        // have not met the prereq)
		
		if (checkPrereq(comp1531Offering, chris) == true) {
			Enrolment e = new Enrolment(comp1531Offering, chris);
			chris.addEnrolment(e);
			System.out.println("enrolment into " + e.getCourse().getCourseCode() + " for " + e.getTerm() + " was successful");
		} else {
			System.out.println("enrolment was unsuccessful");
		}

        // TODO Give the student a passing grade for COMP1511
		chris.setGrade("COMP1511", 50, "PASS");
				
        // TODO Enrol the student in 2521 (this should succeed as they have met
        // the prereqs)
		
		if (checkPrereq(comp2521Offering, chris) == true) {
			Enrolment e = new Enrolment(comp2521Offering, chris);
			chris.addEnrolment(e);
			System.out.println("enrolment into " + e.getCourse().getCourseCode() + " for " + e.getTerm() + " was successful");
		} else {
			System.out.println("enrolment was unsuccessful");
		}
		
    }
    
    public static boolean checkPrereq(CourseOffering offering, Student student) {
    	List<Course> prereqs = offering.getCourse().getPrereqs();
    	
    	for (Course p : prereqs) {
    		boolean found = false;
    		ArrayList<Enrolment> eList = student.getEnrolments();
    		for (Enrolment e : eList) {
       			if (e.getCourse().getCourseCode() == p.getCourseCode() && e.getGrade() != null && e.getGrade().getMark() > 49) {
    				found = true;
    			}
    		}
    		if (found == false) {
    			return false;
    		}
    		
    	}
    	
    	return true;
    }
}
