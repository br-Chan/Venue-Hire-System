package nz.ac.auckland.se281;

public class Booking {
  String bookingReference;
  Venue venue;
  String bookingDate;
  String clientEmail;
  int numberOfAttendees;

  public Booking(String bookingReference, Venue venue, String bookingDate, String clientEmail, int numberOfAttendees) {
    this.bookingReference = bookingReference;
    this.venue = venue;
    this.bookingDate = bookingDate;
    this.clientEmail = clientEmail;
    this.numberOfAttendees = numberOfAttendees;
  }
  
}
