package nz.ac.auckland.se281;

public abstract class Service {
  protected Booking serviceBooking;
  protected int cost;
  
  public Service(Booking serviceBooking) {
    this.serviceBooking = serviceBooking;
    this.cost = 0; // Will be changed when sub-class is constructed.
  }

  public Booking getServiceBooking() {
    return serviceBooking;
  }

  public int getCost() {
    return cost;
  }

}
