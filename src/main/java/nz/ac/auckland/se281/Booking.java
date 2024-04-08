package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Booking {
  private String bookingReference;
  private Venue bookingVenue;
  private SimpleDate bookingDate; // date that the booking is for
  private SimpleDate dateMade; // date that the booking was made
  private String clientEmail;
  private int numberOfAttendees;
  private ArrayList<Service> servicesAtBooking;

  public Booking(
  String bookingReference, Venue bookingVenue, SimpleDate bookingDate, SimpleDate systemDate, String clientEmail, int numberOfAttendees
  ) {
    this.bookingReference = bookingReference;
    this.bookingVenue = bookingVenue;
    this.bookingDate = new SimpleDate(bookingDate);
    this.dateMade = new SimpleDate(systemDate);
    this.clientEmail = clientEmail;
    this.numberOfAttendees = numberOfAttendees;
    servicesAtBooking = new ArrayList<Service>();
  }

  public String getBookingReference() {
    return bookingReference;
  }

  public Venue getBookingVenue() {
    return bookingVenue;
  }

  public SimpleDate getBookingDate() {
    return bookingDate;
  }
  
  public SimpleDate getDateMade() {
    return dateMade;
  }

  public String getClientEmail() {
    return clientEmail;
  }

  public int getNumberOfAttendees() {
    return numberOfAttendees;
  }

  public ArrayList<Service> getServicesAtBooking() {
    return servicesAtBooking;
  }

  public void addService(Service service) {
    servicesAtBooking.add(service);
  }

  // Overridden toString method to be used in printBookings method in VenueHireSystem class.
  @Override
  public String toString() {
    String bookingEntry = MessageCli.PRINT_BOOKINGS_ENTRY.getMessage(
        getBookingReference(),
        getBookingDate().toString()
    );

    return bookingEntry;
  }
  
}
