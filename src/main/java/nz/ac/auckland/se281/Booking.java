package nz.ac.auckland.se281;

public class Booking {
  private String bookingReference;
  private Venue bookingVenue;
  private SimpleDate bookingDate;
  private String clientEmail;
  private int numberOfAttendees;

  public Booking(
  String bookingReference, Venue bookingVenue, SimpleDate bookingDate, String clientEmail, int numberOfAttendees
  ) {
    this.bookingReference = bookingReference;
    this.bookingVenue = bookingVenue;
    this.bookingDate = new SimpleDate(bookingDate);
    this.clientEmail = clientEmail;
    this.numberOfAttendees = numberOfAttendees;
  }

  //TODO create more getters and setters

  public SimpleDate getBookingDate() {
    return bookingDate;
  }
  
}
