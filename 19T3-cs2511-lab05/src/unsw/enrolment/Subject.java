package unsw.enrolment;

public interface Subject {
  public void newObserver(Observer o);
  public void rmObserver(Observer o);
  public void notifyObservers();
}