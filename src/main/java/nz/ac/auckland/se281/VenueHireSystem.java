package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

import java.util.ArrayList;
// import java.util.List;

public class VenueHireSystem {
  ArrayList<Venue> venueList;

  //These arraylists may become redundant.
  ArrayList<String> nameList;
  ArrayList<String> codeList;
  ArrayList<String> capList;
  ArrayList<String> feeList;

  //ArrayList<ArrayList<String>> venueList;

  public enum numWord {
    TWO("two"),
    THREE("three"),
    FOUR("four"),
    FIVE("five"),
    SIX("six"),
    SEVEN("seven"),
    EIGHT("eight"),
    NINE("nine");

    private final String word;

    private numWord (String word) {
      this.word = word;
    }

    public String getWord() {
      return word;
    }
  }

  public VenueHireSystem() {
    // Arraylist containing Venue objects.
    venueList = new ArrayList<Venue>();

    // 4 arraylists of the venues' data.
    nameList = new ArrayList<String>();
    codeList = new ArrayList<String>();
    capList = new ArrayList<String>();
    feeList = new ArrayList<String>();

  }

  public void printVenues() {
    // TODO implement this method

    int venueCount = venueList.size(); // number of venues in the system

    // Check the number of venues in the system and print message accordingly.
    if (venueCount == 0) { // if there are no venues in the system...
      MessageCli.NO_VENUES.printMessage();
    }
    else if (venueCount == 1) { // if there is 1 venue in the system...
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
    }
    else if (venueCount > 1 && venueCount < 10) { // if there are 2-9 venues in the system...
      MessageCli.NUMBER_VENUES.printMessage("are", numToWord(venueCount), "s");
    }
    else if (venueCount >= 10) { // if there are more than 10 venues in the system...
      MessageCli.NUMBER_VENUES.printMessage("are", Integer.toString(venueCount), "s");
    }

    //List the venue(s).
    for (int i = 0; i < venueCount; ++i) {
      MessageCli.VENUE_ENTRY.printMessage(
        venueList.get(i).getVenueName(),
        venueList.get(i).getVenueCode(),
        venueList.get(i).getCapacityInput(),
        venueList.get(i).getHireFeeInput(),
         "TODO");
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // TODO implement this method
    
    // Print error message if venue name argument is empty.
    if (venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    }

    // Print error message if venue code already exists in the system.
    for (Venue i : venueList) {
      if (i.getVenueCode().equals(venueCode)) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, i.getVenueName());
        return;
      }
    }

    // If the capacity or hire fee is not a positive number...
    // TODO streamline this to run 1 function that prints the right invalid number message per string to test
    if (!(checkPosInt(capacityInput) == "isPosNumber")) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", checkPosInt(capacityInput));
      return;
    }
    else if (!(checkPosInt(hireFeeInput) == "isPosNumber")) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", checkPosInt(hireFeeInput));
      return;
    }

    // If all arguments are valid, create a new Venue object.
    venueList.add(new Venue(venueName, venueCode, capacityInput, hireFeeInput));
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);

  }

  public void setSystemDate(String dateInput) {
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
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

  // Converts number from 2 to 9 into its word form.
  public String numToWord(int num) {
    if (num >= 2 && num <= 9) {
      return numWord.values()[num - 2].getWord();
    }
    else return "OUT_OF_RANGE";
  }
}