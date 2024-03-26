package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Venue {
  private String venueName;
  private String venueCode;
  private int capacity;
  private int hireFee;
  private ArrayList<Booking> bookingsAtVenue;

  public Venue(String venueName, String venueCode, int capacityInput, int hireFeeInput) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacity = capacityInput;
    this.hireFee = hireFeeInput;
    bookingsAtVenue = new ArrayList<Booking>();
  }

  public String getVenueName() {
    return venueName;
  }

  public String getVenueCode() {
    return venueCode;
  }

  public int getCapacity() {
    return capacity;
  }

  public int getHireFee() {
    return hireFee;
  }

  public ArrayList<Booking> getBookingsAtVenue() {
    return bookingsAtVenue;
  }

  public void addBookingToVenue(Booking booking) {
    bookingsAtVenue.add(booking);
  }

  //Returns true if the venue already has a booking on the input date.
  public boolean isBookedOnDate(String date) {
    for (Booking i : bookingsAtVenue) {
      if (i.getBookingDate().equals(date)) {
        return true;
      }
    }
    return false;
  }


}