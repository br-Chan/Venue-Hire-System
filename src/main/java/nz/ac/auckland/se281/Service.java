package nz.ac.auckland.se281;

public abstract class Service {
  protected Booking serviceBooking;
  
  public Service(Booking serviceBooking) {
    this.serviceBooking = serviceBooking;
  }

  public Booking getServiceBooking() {
    return serviceBooking;
  }

  public abstract void printInvoiceEntry(int numberOfAttendees);

}
