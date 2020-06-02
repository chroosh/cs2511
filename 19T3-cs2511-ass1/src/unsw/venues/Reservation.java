
package unsw.venues;

import java.time.LocalDate;

import unsw.venues.Venue;

/**
 * Reservation Class
 * @author Christopher Shi
 *
 */
public class Reservation {
	private String id;
	private LocalDate startDate;
	private LocalDate endDate;
	private int smallCount;
	private int medCount;
	private int largeCount;
	private Venue venue;

	/**
	 * Constructor for Reservation
	 * @param id
	 * @param start
	 * @param end
	 * @param small
	 * @param medium
	 * @param large
	 * @param venue
	 */
	public Reservation(String id, LocalDate start, LocalDate end, int small, int medium, int large, Venue venue) {
	  this.id = id;
	  this.startDate = start;
	  this.endDate = end;
	  
	  this.smallCount = small;
	  this.medCount = medium;
	  this.largeCount = large;
	  this.venue = venue;
	}

	/**
	 * Getters and Setters
	 */
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public int getSmallCount() {
    return smallCount;
  }

  public void setSmallCount(int smallCount) {
    this.smallCount = smallCount;
  }

  public int getMedCount() {
    return medCount;
  }

  public void setMedCount(int medCount) {
    this.medCount = medCount;
  }

  public int getLargeCount() {
    return largeCount;
  }

  public void setLargeCount(int largeCount) {
    this.largeCount = largeCount;
  }

  public Venue getVenue() {
    return venue;
  }
  
  /**
   * Update all fields of the reservation except for the id.
   * @param start
   * @param end
   * @param small
   * @param med
   * @param large
   * @return void
   */
  public void updateReservation(LocalDate start, LocalDate end, int small, int med, int large) {
    this.setStartDate(start);
    this.setEndDate(end);
    this.setSmallCount(small);
    this.setMedCount(med);
    this.setLargeCount(large);
  }
	
	
}
