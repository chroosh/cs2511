package unsw.enrolment;

public interface Mark {

  public int calculateMaxMark();
  public int calculateMark();
  public String getName();
  
  public boolean updateMark(String name, int mark, int maxMark);
  
}