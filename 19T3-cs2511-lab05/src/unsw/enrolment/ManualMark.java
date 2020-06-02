package unsw.enrolment;

import java.util.ArrayList;
import java.util.List;

public class ManualMark implements Mark, Subject {
  private String name;
  private int maxMark;
  private int mark;
  private List<Observer> observers;
  
    
  public ManualMark(String name, int maxMark, int mark) {
    this.name = name;
    this.maxMark = maxMark;
    this.mark = mark;
    this.observers = new ArrayList<>();

  }
  
  public int calculateMaxMark() {
    return this.maxMark;
  }
  
  public int calculateMark() {
    return this.mark;
  }
      
  public String getName() {
    return this.name;
  }
  
  public void newObserver(Observer o) {
    observers.add(o);
    
  }
  
  public void rmObserver(Observer o) {
    observers.remove(o);
    
  }
  
  public void notifyObservers() {
    for (Observer o : observers) {
      o.update(this);
    }
    
  }
  
  public boolean updateMark(String name, int mark, int maxMark) {
    if (name.equals(this.name)) {
      this.mark = mark;
      this.maxMark = maxMark;
      notifyObservers();
      return true;
    }
    return false;
  }
  
}