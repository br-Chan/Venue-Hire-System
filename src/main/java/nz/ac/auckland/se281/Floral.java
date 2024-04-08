package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.FloralType;

public class Floral extends Service {
  private FloralType floralType;

  public Floral(Booking serviceBooking, FloralType floralType) {
    super(serviceBooking);

    this.floralType = floralType;
  }

  public FloralType getFloralType() {
    return floralType;
  }

  @Override
  public void printInvoiceEntry(int numberOfAttendees) {
    // numberofAttendees is not used by the Floral class
    MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(
        floralType.getName(),
        Integer.toString(floralType.getCost())
    );
  }
  
}
