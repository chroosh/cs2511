
package unsw.venues;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;

import unsw.venues.Room;

/**
 * Venue Class
 * @author Christopher Shi
 *
 */
public class Venue {

	private String name;
	private List<Room> smallRoomList;
	private List<Room> medRoomList;
	private List<Room> largeRoomList;

	/**
	 * Constructor for Venue
	 * @param name
	 */
	public Venue(String name) {
		this.name = name;
		this.smallRoomList = new ArrayList<>();
		this.medRoomList = new ArrayList<>();
		this.largeRoomList = new ArrayList<>();
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

  /**
   * Adds a new room to the list of rooms in this class
   * @param room
   * @param size
   * @return void
   */
  public void addRoom(String room, String size) {
    Room r = new Room(room, size);
    
    if (size.equals("small")) {
      this.smallRoomList.add(r);
    } else if (size.equals("medium")) {
      this.medRoomList.add(r);
    } else if (size.equals("large")) {
      this.largeRoomList.add(r);
    }
    
  }
  
  /**
   * Books the rooms specified by a List<String> returned by checkAvailability
   * @param id
   * @param start
   * @param end
   * @param rooms
   * @return JSONArray
   */
  public JSONArray bookRoom(String id, LocalDate start, LocalDate end, List<String> rooms) {
    JSONArray roomsJson = new JSONArray();
    
    for (String name : rooms) {
      for (Room r : this.smallRoomList) {
        if (name.equals(r.getName())) {
          r.addBookingIDs(id);
          r.addStartDate(start);
          r.addEndDate(end);
        }
      }
      
      for (Room r : this.medRoomList) {
        if (name.equals(r.getName())) {
          r.addBookingIDs(id);
          r.addStartDate(start);
          r.addEndDate(end);
        }
      }
      
      for (Room r : this.largeRoomList) {
        if (name.equals(r.getName())) {
          r.addBookingIDs(id);
          r.addStartDate(start);
          r.addEndDate(end);
        }
      }
      
      roomsJson.put(name);
      
    }
      
    return roomsJson;
  }
  
  /**
   * Checks availability for rooms within this venue. Searches through all three lists of small/medium
   * /large rooms. If room is available, add to a List<String> that will be returned. If a insufficient
   * amount of rooms are available, return a new ArrayList<>
   * @param start
   * @param end
   * @param small
   * @param medium
   * @param large
   * @return List<String>
   */
  public List<String> checkAvailability(LocalDate start, LocalDate end, int small, int medium, int large) {
    List<String> rooms = new ArrayList<>();
    int sum = small + medium + large;
    
    if (small > 0) {
      for (Room r : this.smallRoomList) {
        if (small > 0 && r.isValidBooking(start, end)) {
          rooms.add(r.getName());
          small--;
        }
      }
    }

    
    if (medium > 0) {
      for (Room r : this.medRoomList) {
        if (medium > 0 && r.isValidBooking(start, end)) {
          rooms.add(r.getName());
          medium--;
        }
      }
    }

    if (large > 0) {
      for (Room r : this.largeRoomList) {
        if (large > 0 && r.isValidBooking(start, end)) {
          rooms.add(r.getName());
          large--;
        }
      }
    }
    
    if (rooms.size() != sum) {
      return new ArrayList<>();
    }
    
    
    return rooms;  
  }
  
  /**
   * Releases all the rooms under this venue that have a booking tied under the specified id.
   * @param id
   * @return void
   */
  public void releaseRooms(String id) {
    
    // for the room, delete the id, and the start date/end date in the same index
    for (Room r : this.smallRoomList) {
      for (int i = 0; i < r.getBookingIDs().size(); i++) {
        if (id.equals(r.getBookingIDs().get(i))) {
          r.getBookingIDs().remove(i);
          r.getStartDates().remove(i);
          r.getEndDates().remove(i);
        }
      }
    }
    
    for (Room r : this.medRoomList) {
      for (int i = 0; i < r.getBookingIDs().size(); i++) {
        if (id.equals(r.getBookingIDs().get(i))) {
          r.getBookingIDs().remove(i);
          r.getStartDates().remove(i);
          r.getEndDates().remove(i);
        }
      }
    }
    
    for (Room r : this.largeRoomList) {
      for (int i = 0; i < r.getBookingIDs().size(); i++) {
        if (id.equals(r.getBookingIDs().get(i))) {
          r.getBookingIDs().remove(i);
          r.getStartDates().remove(i);
          r.getEndDates().remove(i);
        }
      }
    }
  }
  
  /**
   * Generates a JSONArray containing information about each room.
   * @return JSONArray
   */
  public JSONArray generateList() {
    JSONArray result = new JSONArray();
    for (Room r : this.smallRoomList) {
      result.put(r.generateList());
    }
    for (Room r : this.medRoomList) {
      result.put(r.generateList());
    }
    for (Room r: this.largeRoomList) {
      result.put(r.generateList());
    }
    return result;
  }

}
