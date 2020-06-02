package unsw.enrolment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Enrolment {

    private CourseOffering offering;
    private Grade grade;
    private Student student;
    private List<Session> sessions;
    private SumCalculatedMark marks;

    public Enrolment(CourseOffering offering, Student student, Session... sessions) {
        this.offering = offering;
        this.student = student;
        this.grade = null; // Student has not completed course yet.
        student.addEnrolment(this);
        offering.addEnrolment(this);
        this.sessions = new ArrayList<>();
        for (Session session : sessions) {
            this.sessions.add(session);
        }
        this.marks = new SumCalculatedMark();
    }

    public Course getCourse() {
        return offering.getCourse();
    }

    public String getTerm() {
        return offering.getTerm();
    }

    public boolean hasPassed() {
        return grade != null && grade.isPassing();
    }
    
    public void addManualMark(String name, int maxMark, int mark) {
      if (!marks.updateMark(name, mark, maxMark)) {
        ManualMark m = new ManualMark(name, maxMark, mark);
        logChanges(m);
        this.marks.addMark(m);
  
      }
    }
    
    private void logChanges(ManualMark mark) {
      mark.newObserver(new Observer() {
          @Override
          public void update(Subject subject) {
              String filename = student.getZID() + "_" + offering.getCourse().getCourseCode() + "_" + offering.getTerm();
              try {
                  File file = new File(filename);
                  PrintStream out = new PrintStream(new FileOutputStream(file, true));
                  out.println(LocalDateTime.now() + " : " + mark.getName() + " = " + mark.calculateMark() + " out of " + mark.calculateMaxMark());
                  out.close();
              } catch (FileNotFoundException e) {
                  e.printStackTrace();
              }
          }
      });
  }

    
    public Mark createSumMark(String markName, String[] marksString) {
      // remove all the marks from list
      List<Mark> markList = new ArrayList<>();
      for (String name : marksString) {
        markList.add(marks.removeMark(name));
      }
      
//      System.out.println("printing size of marks");
//       System.out.println(marks.size());
  
      // add them to new list inside sumcalculatedMark
      SumCalculatedMark s = new SumCalculatedMark(markName, markList);
      
      this.marks.addMark(s);
      return s;
      
    }
    
    public Mark createAvgMark(String markName, String[] marksString) {
      // remove all the marks from list
      
      
      List<Mark> markList = new ArrayList<>();
      for (String name : marksString) {
        markList.add(marks.removeMark(name));
      }
      
      // add them to new list inside sumcalculatedMark
      AvgCalculatedMark s = new AvgCalculatedMark(markName, markList);
      
      this.marks.addMark(s);
      return s;
    }
    
    public void assignGrade() {
      this.grade = new Grade(100*marks.calculateMark()/marks.calculateMaxMark());
      
    }
    
//    Whole course marks can no longer be assigned this way.
//    public void assignMark(int mark) {
//        grade = new Grade(mark);
//    }

}
