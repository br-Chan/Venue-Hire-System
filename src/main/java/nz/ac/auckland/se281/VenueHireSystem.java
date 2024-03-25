package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

import java.util.ArrayList;
import java.util.List;

public class VenueHireSystem {
  private ArrayList<Venue> venueList;
  private ArrayList<Booking> bookingList;

  private String systemDate;

  // Enum for printVenues when there are 2-9 venues to be listed.
  public enum NumWord {
    TWO("two"),
    THREE("three"),
    FOUR("four"),
    FIVE("five"),
    SIX("six"),
    SEVEN("seven"),
    EIGHT("eight"),
    NINE("nine");

    private final String word;

    private NumWord(String word) {
      this.word = word;
    }

    public String getWord() {
      return word;
    }
  }

  public VenueHireSystem() {
    // Arraylist containing Venue objects.
    venueList = new ArrayList<Venue>();
    bookingList = new ArrayList<Booking>();

    systemDate = "";
  }

  public void printVenues() {
    // TODO implement this method

    int venueCount = venueList.size(); // number of venues in the system

    // Check the number of venues in the system and print message accordingly.
    if (venueCount == 0) { // if there are no venues in the system...
      MessageCli.NO_VENUES.printMessage();

    } else if (venueCount == 1) { // if there is 1 venue in the system...
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");

    } else if (venueCount > 1 && venueCount < 10) { // if there are 2-9 venues in the system...
      MessageCli.NUMBER_VENUES.printMessage("are", numToWord(venueCount), "s");

    } else if (venueCount >= 10) { // if there are more than 10 venues in the system...
      MessageCli.NUMBER_VENUES.printMessage("are", Integer.toString(venueCount), "s");

    }

    //List the venue(s).
    // TODO research the use of an overriden toString method in the Venue class instead
    for (int i = 0; i < venueCount; ++i) {
      MessageCli.VENUE_ENTRY.printMessage(
      venueList.get(i).getVenueName(),
      venueList.get(i).getVenueCode(),
      Integer.toString(venueList.get(i).getCapacity()),
      Integer.toString(venueList.get(i).getHireFee()),
      "TODO"
      );
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    
    // Print error message if venue name argument is empty.
    if (venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    }

    // Print error message if venue code already exists in the system.
    if (findVenue(venueCode) != null) {
      MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(
      venueCode, findVenue(venueCode).getVenueName()
      );
      return;
    }

    // Print error message if capacity or hire fee is not a positive number.
    // TODO streamline this to run 1 function that prints 
    // the right invalid number message per string to test
    if (!(checkPosInt(capacityInput) == "isPosNumber")) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage(
          "capacity", checkPosInt(capacityInput)
      );
      return;

    } else if (!(checkPosInt(hireFeeInput) == "isPosNumber")) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage(
      "hire fee", checkPosInt(hireFeeInput)
      );
      return;
    }

    // If all arguments are valid, create a new Venue object.
    venueList.add(new Venue(venueName, venueCode, Integer.valueOf(capacityInput), Integer.valueOf(hireFeeInput)));
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);

  }

  public void setSystemDate(String dateInput) {
    systemDate = dateInput;
    MessageCli.DATE_SET.printMessage(systemDate);
  }

  public void printSystemDate() {
    // if no prior system date set, print message saying date not set. Otherwise print the date.
    if (systemDate.isEmpty()) {
      MessageCli.CURRENT_DATE.printMessage("not set.");
    } else {
      MessageCli.CURRENT_DATE.printMessage(systemDate);
    }
  }

  public void makeBooking(String[] options) {
    // TODO implement this method

    // Declare the variables to be placed into the new Booking object
    String bookingReference = BookingReferenceGenerator.generateBookingReference();
    Venue bookingVenue = findVenue(options[0]);
    String bookingDate = options[1];
    String clientEmail = options[2];
    int numberOfAttendees = Integer.valueOf(options[3]);

    // (Currently redundant) Create an array of the date split into its 3 parts
    String[] dateSplit = splitDate(bookingDate);

    // If attendees less than 25% or more than 100%, set them to 25 or 100% respectively.
    int quarterOfCapacity = bookingVenue.getCapacity()/4;
    if (numberOfAttendees < quarterOfCapacity) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          Integer.toString(numberOfAttendees),
          Integer.toString(quarterOfCapacity),
          Integer.toString(bookingVenue.getCapacity())
      );
      numberOfAttendees = quarterOfCapacity;

    } else if (numberOfAttendees > bookingVenue.getCapacity()) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          Integer.toString(numberOfAttendees),
          Integer.toString(bookingVenue.getCapacity()),
          Integer.toString(bookingVenue.getCapacity())
      );
      numberOfAttendees = bookingVenue.getCapacity();
    }

    //Assume all inputs are acceptable, and make the booking.
    bookingList.add(
        new Booking(bookingReference, bookingVenue, bookingDate, clientEmail, numberOfAttendees)
    );

    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        bookingReference, bookingVenue.getVenueName(), bookingDate, Integer.toString(numberOfAttendees)
    );
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO Abstract service class (?) with 3 sub-classes
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }

  // ***New methods implemented by me below:***

  // This method checks if the string input is a positive number, returns one of 3 output strings.
  public String checkPosInt(String testString) {
    if (testString.isEmpty()) {
      return "";
    }
    try {
      int n = Integer.parseInt(testString);
    } catch (NumberFormatException e) {
      return "";
    }
    if (Integer.parseInt(testString) <= 0) {
      return " positive";
    }

    //If the string is a number and is positive
    return "isPosNumber";
  }

  public String checkErrorPosInt(String numType, String testString) {
    // TODO implement this function to do the function of checkPosInt
    // but also print the error message and make it more streamlined
    return null;
  }

  // Converts number from 2 to 9 into its word form.
  public String numToWord(int num) {
    if (num >= 2 && num <= 9) {
      return NumWord.values()[num - 2].getWord();
    } else {
      return "OUT_OF_RANGE";
    }
  }

  // Returns the venue with an input code
  private Venue findVenue(String venueCode) {
    // Iterate through each venue in venueList and return the venue with the matching code
    for (Venue i : venueList) {
      if (i.getVenueCode().equals(venueCode)) {
        return i;
      }
    }

    //If no venue found...
    return null;
  }

  // Splits date into its 3 parts and returns them as a string array.
  private String[] splitDate(String date) {
    String[] dateSplit = date.split("/");
    return dateSplit;
  }
}