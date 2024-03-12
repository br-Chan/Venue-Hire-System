package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

import java.util.ArrayList;
// import java.util.List;

public class VenueHireSystem {
  ArrayList<String> nameList;
  ArrayList<String> codeList;
  ArrayList<String> capList;
  ArrayList<String> feeList;

  ArrayList<ArrayList<String>> venueList;

  public VenueHireSystem() {
    // 4 arraylists of the venues' data.
    nameList = new ArrayList<String>();
    codeList = new ArrayList<String>();
    capList = new ArrayList<String>();
    feeList = new ArrayList<String>();

    // Arraylist containing the 4 arraylists of the venues' data in order of their arguments in createVenue.
    venueList = new ArrayList<ArrayList<String>>();
    venueList.add(nameList);
    venueList.add(codeList);
    venueList.add(capList);
    venueList.add(feeList);

  }

  public void printVenues() {
    // TODO implement this method

    int venueCount = nameList.size(); // number of venues in the system

    // Check the number of venues in the system by using nameList.
    // If nameList has n elements, as will the other 3 arraylists, there are n venues.
    if (nameList.isEmpty()) { // if there are no venues in the system...
      MessageCli.NO_VENUES.printMessage();
    }
    else if (venueCount == 1) { // if there is 1 venue in the system...
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
    }
    else if (venueCount > 1 && venueCount < 10) { // if there are 2-9 venues in the system...
      MessageCli.NUMBER_VENUES.printMessage("are", Integer.toString(venueCount), "s");
      //TODO: fix the printed number of venues to be in words (i.e. one, two, three) and not int
    }
    else if (venueCount >= 10) { // if there are more than 10 venues in the system...
      MessageCli.NUMBER_VENUES.printMessage("are", Integer.toString(venueCount), "s");
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // TODO implement this method
    venueList.get(0).add(venueName);
    venueList.get(1).add(venueCode);
    venueList.get(2).add(capacityInput);
    venueList.get(3).add(hireFeeInput);

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
}
