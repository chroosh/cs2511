package unsw.enrolment;

import java.util.List;
import java.util.ArrayList;

public class AvgCalculatedMark implements Mark {
  private String name;
  private int maxMark;
  private int mark;
  private List<Mark> markList;
  
  public AvgCalculatedMark(String markName, List<Mark> markList) {
    this.name = markName;
    this.maxMark = 0;
    this.mark = 0;
    this.markList = markList;
    
    calculateMark();
    calculateMaxMark();
    
  }
  
  public String getName() {
    return this.name;
  }
  
  
  public void addMark(Mark mark) {
    this.markList.add(mark);
  }

  public int calculateMaxMark() {
    int sum = 0;
    for (Mark m : this.markList) {
      sum+=m.calculateMaxMark();
    }
    this.maxMark = sum/markList.size();
        
    return maxMark;
  }
  
  public int calculateMark() {
    int sum = 0;
    for (Mark m : this.markList) {
      sum+=m.calculateMark();
    }
    
    this.mark = sum/markList.size();
    
    return mark;
  }
  
  public boolean updateMark(String name, int mark, int maxMark) {
    for (Mark m : markList) {
      if (m.updateMark(name, mark, maxMark)) {
        return true;
      }
    }
    return false;
  }
  
}