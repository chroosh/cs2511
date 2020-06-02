
package unsw.venues;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Room Class
 * @author Christopher Shi
 *
 */
public class Room {
	private String name;
	private String size;
	private List<String> bookingIDs;
	private List<LocalDate> startDates;
	private List<LocalDate> endDates;

	/**
	 * Constructor for Room
	 * @param name
	 * @param size
	 */
	public Room(String name, String size) {
		this.name = name;
		this.size = size;
		this.bookingIDs = new ArrayList<>();
		this.startDates = new ArrayList<>();
		this.endDates = new ArrayList<>();
	}

	/**
	 * Getters and Setters
	 */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public List<String> getBookingIDs() {
    return bookingIDs;
  }
  
  public void addBookingIDs(String id) {
    this.bookingIDs.add(id);
  }

  public List<LocalDate> getStartDates() {
    return startDates;
  }
  
  public void addStartDate(LocalDate s) {
    this.startDates.add(s);
  }

  public List<LocalDate> getEndDates() {
    return endDates;
  }
  
  public void addEndDate(LocalDate s) {
    this.endDates.add(s);
  }
  
  /**
   * Checks if the room is available between start date S and end date e
   * @param s
   * @param e
   * @return boolean
   */
  public boolean isValidBooking(LocalDate s, LocalDate e) {
    
    for (int i = 0; i < this.startDates.size(); i++) {
      LocalDate bookedStart = this.startDates.get(i);
      LocalDate bookedEnd = this.endDates.get(i);
      
      if (s.isAfter(bookedStart) && e.isBefore(bookedEnd)) {
        return false;
      }
      
      if (e.isAfter(bookedStart) && e.isBefore(bookedStart)) {
        return false;
      }
      
      if (s.isAfter(bookedStart) && s.isBefore(bookedEnd)) {
        return false;
      }
      
      if (s.isBefore(bookedStart) && e.isAfter(bookedEnd)) {
        return false;
      }
      
      if (s.isEqual(bookedStart) || s.isEqual(bookedEnd)) {
        return false;
      }
      if (e.isEqual(bookedStart) || e.isEqual(bookedEnd)) {
        return false;
      }
    }
    
    return true;
  }
  
  /**
   * Generates a JSONArray of reservations for this specific room
   * @return JSONArray
   */
  public JSONArray generateReservations() {
    JSONArray result = new JSONArray();
   
    // Generate sorted start date
    List<LocalDate> sortedStartDates = new ArrayList<LocalDate>(this.startDates);
    Collections.sort(sortedStartDates);
    
    List<LocalDate> sortedEndDates = new ArrayList<>();
    List<String> sortedBookingIDs = new ArrayList<>();
    
    for (LocalDate d : sortedStartDates) {
      // Find the index in original
      int index = this.startDates.indexOf(d);
      // Sort other arrays
      sortedEndDates.add(this.endDates.get(index));
      sortedBookingIDs.add(this.bookingIDs.get(index));
      
    }
    
    for (int i = 0; i < this.bookingIDs.size(); i++) {
      JSONObject reservation = new JSONObject();
      reservation.put("start", sortedStartDates.get(i));
      reservation.put("end", sortedEndDates.get(i));
      reservation.put("id", sortedBookingIDs.get(i));
      result.put(reservation); 
    }
    return result;
  }
  
  /**
   * Generates a JSONObject for this room, calling the reservations
   * and adding the name of the room to the object.
   * @return JSONObject
   */
  public JSONObject generateList() {
    JSONObject result = new JSONObject();
    result.put("reservations", this.generateReservations());
    result.put("room", this.name);
    
    return result;
  }
}
