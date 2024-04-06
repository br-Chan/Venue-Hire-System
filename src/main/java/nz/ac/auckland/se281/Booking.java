package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Booking {
  private String bookingReference;
  private Venue bookingVenue;
  private SimpleDate bookingDate;
  private String clientEmail;
  private int numberOfAttendees;
  private ArrayList<Service> servicesAtBooking;

  public Booking(
  String bookingReference, Venue bookingVenue, SimpleDate bookingDate, String clientEmail, int numberOfAttendees
  ) {
    this.bookingReference = bookingReference;
    this.bookingVenue = bookingVenue;
    this.bookingDate = new SimpleDate(bookingDate);
    this.clientEmail = clientEmail;
    this.numberOfAttendees = numberOfAttendees;
    servicesAtBooking = new ArrayList<Service>();
  }

  //TODO create more getters and setters

  public String getBookingReference() {
    return bookingReference;
  }

  public SimpleDate getBookingDate() {
    return bookingDate;
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
