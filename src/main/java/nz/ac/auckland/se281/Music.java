package nz.ac.auckland.se281;

public class Music extends Service {
  
  public Music(Booking serviceBooking) {
    super(serviceBooking);
  }

  @Override
  public void printInvoiceEntry(int numberOfAttendees) {
    MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(
        Integer.toString(500) // There is only 1 music service type, that costs $500.
    );
  }

}
