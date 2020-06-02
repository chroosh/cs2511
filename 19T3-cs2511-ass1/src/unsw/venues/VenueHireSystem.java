/**
 *
 */
package unsw.venues;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.venues.Venue;
import unsw.venues.Reservation;

/** Venue Hire System for COMP2511.  
 * A basic prototype to serve as the "back-end" of a venue hire system. Input
 * and output is in JSON format.
 *
 * @author Robert Clifton-Everest
 * @author Christopher Shi
 */
public class VenueHireSystem {

  /**
   * Constructs a venue hire system. Initially, the system contains no venues,
   * rooms, or bookings.
   */

	private List<Reservation> reservations;
	private List<Venue> venues;
 
  /**
   * Constructor for VenueHireSystem
   * @param none
   */
  public VenueHireSystem() {
		this.reservations = new ArrayList<>();
		this.venues = new ArrayList<>();
  }
 
  /**
   * Processes input command from .json file and prints response to stdout
   * @param json
   * @return void
   */
  private void processCommand(JSONObject json) {
    switch (json.getString("command")) {

    case "room":
      String venue = json.getString("venue");
      String room = json.getString("room");
      String size = json.getString("size");
      addRoom(venue, room, size);
      break;

    case "request":
      String request_id = json.getString("id");
      LocalDate request_start = LocalDate.parse(json.getString("start"));
      LocalDate request_end = LocalDate.parse(json.getString("end"));
      int request_small = json.getInt("small");
      int request_medium = json.getInt("medium");
      int request_large = json.getInt("large");

      JSONObject request_result = request(request_id, request_start, request_end, request_small, request_medium, request_large);

      System.out.println(request_result.toString(2));
      break;

    case "change":
      String change_id = json.getString("id");
      LocalDate change_start = LocalDate.parse(json.getString("start"));
      LocalDate change_end = LocalDate.parse(json.getString("end"));
      int change_small = json.getInt("small");
      int change_medium = json.getInt("medium");
      int change_large = json.getInt("large");

      JSONObject change_result = change(change_id, change_start, change_end, change_small, change_medium, change_large);

      System.out.println(change_result.toString(2));
      break;
  	
    	
    case "cancel":
      String cancel_id = json.getString("id");  
      cancel(cancel_id);
      break;
    	
    case "list":
      String list_venue = json.getString("venue");
      JSONArray list_result = list(list_venue);
      System.out.println(list_result.toString(2));
      break;
    
    }
  }


  /**
   * Adds room to the list of venues. Creates a new venue if none exists yet
   * @param venue
   * @param room
   * @param size
   * @return void
   */
  private void addRoom(String venue, String room, String size) {
		boolean exists = false;

		for (Venue v : venues) {
		  if (venue.equals(v.getName())) {
		    exists = true;
		    v.addRoom(room, size);
		    return;
		    
		  }
		}
		if (exists == false) {
		  Venue v = new Venue(venue);
		  v.addRoom(room, size);
		  this.venues.add(v);
		  
		} 
  }



  /**
   * Requests a booking given the following parameters. Each venue is checked to determine
   * if it can satisfy the number of rooms required. For the first venue that can satisfy 
   * the requirement, the rooms are allocated under it. If no venue could be found with the
   * required amount of rooms available, return JSON rejected.
   * @param id
   * @param start
   * @param end
   * @param small
   * @param medium
   * @param large
   * @return JSONObject
   */
  public JSONObject request(String id, LocalDate start, LocalDate end,
          int small, int medium, int large) {
      
		JSONObject result = new JSONObject();
		
    boolean outcome = false;
    for (Venue v : venues) {
      List<String> availableRooms = v.checkAvailability(start, end, small, medium, large);
      if (availableRooms.isEmpty() == false) {
        JSONArray bookedRooms = v.bookRoom(id, start, end, availableRooms);
        Reservation r = new Reservation(id, start, end, small, medium, large, v);
        this.reservations.add(r);       
        result.put("status", "success");
        result.put("venue", v.getName());     
        result.put("rooms", bookedRooms);     
        outcome = true;
        return result;
      }
    }
    
    if (outcome == false) {
      result.put("status", "rejected");
    }
    
    return result;
  }

  
	
  /**
   * Change a booking given the following parameters. The current venue is checked for available 
   * space, firstly if there has been a reduction in the amount of rooms required. Then if there 
   * is an increase in the amount of rooms required
   * @param id
   * @param start
   * @param end
   * @param small
   * @param medium
   * @param large
   * @return JSONObject
   */
  public JSONObject change(String id, LocalDate start, LocalDate end,
          int small, int medium, int large) {
    
    JSONObject result = new JSONObject();
    

    for (Reservation r : reservations) {
      if (id.equals(r.getId())) {
        
        List<String> availableRooms;
        
        Venue currVenue = r.getVenue();

        // If downsizing rooms or no change within the same venue
        if (small-r.getSmallCount() <= 0 && medium-r.getMedCount() <= 0 && large-r.getLargeCount() <= 0) {
          currVenue.releaseRooms(id);
          availableRooms = currVenue.checkAvailability(start, end, small, medium, large);
          r.updateReservation(start, end, small, medium, large);
          
          JSONArray bookedRooms = currVenue.bookRoom(id, start, end, availableRooms);
          result.put("status", "success");
          result.put("venue", currVenue.getName());
          result.put("rooms", bookedRooms);
          return result;
          
        } else {
          // else if upsizing rooms within the same venue
          availableRooms = currVenue.checkAvailability(start, end, small-r.getSmallCount(), medium-r.getMedCount(), large-r.getLargeCount());
          if (availableRooms.isEmpty() == false) {
            currVenue.releaseRooms(id);
            availableRooms = currVenue.checkAvailability(start, end, small, medium, large);
            r.updateReservation(start, end, small, medium, large);
            
            JSONArray bookedRooms = currVenue.bookRoom(id, start, end, availableRooms);
            result.put("status", "success");
            result.put("venue", currVenue.getName());
            result.put("rooms", bookedRooms);
            return result;
          }
        }
        
        // If no available rooms are found, check other venues
        for (Venue v : venues) {
          if (v.getName().equals(currVenue.getName()) == false) {
            availableRooms = v.checkAvailability(start, end, small, medium, large);
            if (availableRooms.isEmpty() == false) {
              currVenue.releaseRooms(id);
              r.updateReservation(start, end, small, medium, large);
                
              JSONArray bookedRooms = v.bookRoom(id, start, end, availableRooms);
              result.put("status", "success");
              result.put("venue", v.getName());
              result.put("rooms", bookedRooms);
              return result;
            }
          }
        }
        
        break;
      }
    }
    
    result.put("status", "rejected");

    return result;
	}
  
  
	/**
	 * Cancels a reservation using the id. Calls functions in venue class to release all the rooms
	 * under that venue, registered to a specific id.
	 * @param id
	 * @return void
	 */
	public void cancel(String id) {
	  for (Reservation r : reservations) {
	    if (r.getId().equals(id)) {
	      Venue currVenue = r.getVenue();
	      currVenue.releaseRooms(id);
	    }
	  }
	} 

	/**
	 * Prints out all the rooms under the venue specified with their reservations, including start
	 * date, end date and reservation id.
	 * @param venue
	 * @return
	 */
	public JSONArray list(String venue) {
	  for (Venue v : venues) {
	    if (venue.equals(v.getName())) {
	      return v.generateList();
	    }
	  }
	  
	  // FIXME
	  return null;
	}
	
	/**
	 * Main
	 * @param args
	 */
  public static void main(String[] args) {
    VenueHireSystem system = new VenueHireSystem();

    Scanner sc = new Scanner(System.in);

    while (sc.hasNextLine()) {
        String line = sc.nextLine();
        if (!line.trim().equals("")) {
            JSONObject command = new JSONObject(line);
            system.processCommand(command);
        }
    }
    sc.close();
  }

}
