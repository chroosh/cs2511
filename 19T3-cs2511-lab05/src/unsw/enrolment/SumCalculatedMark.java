package unsw.enrolment;   

import java.util.List;
import java.util.ArrayList;

public class SumCalculatedMark implements Mark {
  private String name;
  private int maxMark;
  private int mark;
  private List<Mark> markList;
 
  
  public SumCalculatedMark() {
    this.maxMark = 0;
    this.mark = 0;
    this.markList = new ArrayList<>();
  }
  
  
  public SumCalculatedMark(String markName, List<Mark> markList) {
    this.name = markName;
    this.maxMark = 0;
    this.mark = 0;
    this.markList = markList;
    
    calculateMaxMark();
    calculateMark();
    
  }
  
  public String getName() {
    return this.name;
  }
  
  // TODO remove this later as you shoudn't need it
  public List<Mark> getMarkList() {
    return this.markList;
  }
  
  public void addMark(Mark mark) {
    this.markList.add(mark);
  }

  public int calculateMaxMark() {
    int sum = 0;
    
    for (Mark m : this.markList) {
      sum+=m.calculateMaxMark();
    }
    
    this.maxMark = sum;
    
    return sum;
  }
  
  public int calculateMark() {
    int sum = 0;
    for (Mark m : this.markList) {
      sum+=m.calculateMark();
    }
    
    this.mark = sum;
    
    return sum;
  }
  
  public Mark removeMark(String name) {
    for (Mark m : markList) {
      if (m.getName().equals(name)) {
        markList.remove(m);
        return m;
      }
    }
    
    return null;
  }
  
  public int size() {
    return this.markList.size();
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