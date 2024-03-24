package nz.ac.auckland.se281;

public class Booking {
  String bookingReference;
  Venue bookingVenue;
  String bookingDate;
  String clientEmail;
  int numberOfAttendees;

  public Booking(
  String bookingReference, Venue bookingVenue, String bookingDate, String clientEmail, int numberOfAttendees
  ) {
    this.bookingReference = bookingReference;
    this.bookingVenue = bookingVenue;
    this.bookingDate = bookingDate;
    this.clientEmail = clientEmail;
    this.numberOfAttendees = numberOfAttendees;
  }
  
}
