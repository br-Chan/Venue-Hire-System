package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Venue {
  private String venueName;
  private String venueCode;
  private int capacity;
  private int hireFee;
  private ArrayList<Booking> bookingsAtVenue;
  private SimpleDate nextAvailableDate;

  public Venue(
      String venueName,
      String venueCode,
      int capacityInput,
      int hireFeeInput,
      SimpleDate systemDate
  ) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacity = capacityInput;
    this.hireFee = hireFeeInput;
    bookingsAtVenue = new ArrayList<Booking>();
    nextAvailableDate = new SimpleDate(systemDate);
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

  public SimpleDate getNextAvailableDate() {
    return nextAvailableDate;
  }

  // Updates the next available date.
  // System date is an input parameter but not necessarily becomes the next available date.
  public void updateNextAvailableDate(SimpleDate systemDate) {
    // Return with nextAvailableDate being the system date if there are no bookings.
    if (bookingsAtVenue.size() == 0) {
      nextAvailableDate.changeTo(systemDate);
      return;
    }

    // Initially change the next available date to the system date.
    nextAvailableDate.changeTo(systemDate);

    // If the venue is booked on the system date, check the next day until not booked.
    while (this.isBookedOnDate(nextAvailableDate)) {
      nextAvailableDate.incrementDay();
    }

    return;
  }

  public void addBooking(Booking booking) {
    bookingsAtVenue.add(booking);
  }

  // Returns true if the venue already has a booking on the input date.
  public boolean isBookedOnDate(SimpleDate date) {
    for (Booking i : bookingsAtVenue) {
      if (i.getBookingDate().compareWith(date) == 0) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return venueName;
  }

}