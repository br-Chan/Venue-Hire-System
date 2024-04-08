package nz.ac.auckland.se281;

public abstract class Service {
  protected Booking serviceBooking;
  
  public Service(Booking serviceBooking) {
    this.serviceBooking = serviceBooking;
  }

  public Booking getServiceBooking() {
    return serviceBooking;
  }

  // Prints the invoice entry for the particular service and returns the cost of the service.
  public abstract int printInvoiceEntry(int numberOfAttendees);

}
