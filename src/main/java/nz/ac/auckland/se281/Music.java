package nz.ac.auckland.se281;

public class Music extends Service {
  
  public Music(Booking serviceBooking) {
    super(serviceBooking);
  }

  // Overridden printInvoiceEntry method  from super class.
  @Override
  public int printInvoiceEntry(int numberOfAttendees) {
    MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(
        Integer.toString(500) // There is only 1 music service type, that costs $500.
    );

    return 500;
  }

}
