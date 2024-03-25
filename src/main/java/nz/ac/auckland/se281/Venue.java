package nz.ac.auckland.se281;

public class Venue {
  private String venueName;
  private String venueCode;
  private int capacity;
  private int hireFee;

  public Venue(String venueName, String venueCode, int capacityInput, int hireFeeInput) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacity = capacityInput;
    this.hireFee = hireFeeInput;
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


}