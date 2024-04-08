package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class Catering extends Service {
  private CateringType cateringType;

  public Catering(Booking serviceBooking, CateringType cateringType) {
    super(serviceBooking);

    this.cateringType = cateringType;
  }

  public CateringType getCateringType() {
    return cateringType;
  }

  @Override
  public int printInvoiceEntry(int numberOfAttendees) {
    int cateringCost = cateringType.getCostPerPerson() * numberOfAttendees;
    MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(
        cateringType.getName(),
        Integer.toString(cateringCost)
    );

    return cateringCost;
  }

}
