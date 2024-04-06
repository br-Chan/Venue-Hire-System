package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

import java.util.ArrayList;
import java.util.List;

public class VenueHireSystem {
  private ArrayList<Venue> venueList;
  private ArrayList<Booking> bookingList;

  private SimpleDate systemDate;

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

    systemDate = new SimpleDate();
  }

  public void printVenues() {
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
      // Check if the system date is set or not.
      String nextAvailableDate;
      if (systemDate == null) {
        nextAvailableDate = "[Date not set]";
      } else {
        nextAvailableDate = venueList.get(i).getNextAvailableDate().toString();
      }
      MessageCli.VENUE_ENTRY.printMessage(
      venueList.get(i).getVenueName(),
      venueList.get(i).getVenueCode(),
      Integer.toString(venueList.get(i).getCapacity()),
      Integer.toString(venueList.get(i).getHireFee()),
      nextAvailableDate
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
    venueList.add(new Venue(
        venueName, venueCode, Integer.valueOf(capacityInput), Integer.valueOf(hireFeeInput), systemDate));
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);

  }

  public void setSystemDate(String dateInput) {
    systemDate = new SimpleDate(dateInput);
    MessageCli.DATE_SET.printMessage(systemDate.toString());

    for (Venue i : venueList) {
      i.updateNextAvailableDate(systemDate);
    }
  }

  public void printSystemDate() {
    // if no prior system date set, print message saying date not set. Otherwise print the date.
    if (!systemDate.getIsSet()) {
      MessageCli.CURRENT_DATE.printMessage("not set.");
    } else {
      MessageCli.CURRENT_DATE.printMessage(systemDate.toString());
    }
  }

  // Checks conditions, makes the booking if all are met.
  public void makeBooking(String[] options) {
    // CONDITION 1: System date must be set.
    if (!systemDate.getIsSet()) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    }

    // Take booking & system dates and create arrays of the dates split into their 3 parts.
    //String bookingDate = options[1];
    SimpleDate bookingDate = new SimpleDate(options[1]);

    // CONDITION 2: Booking date must not be in the past.
    if (bookingDate.compareWith(systemDate) == -1) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(bookingDate.toString(), systemDate.toString());
      return; // booking date is in the past.
    }

    // CONDITION 3: There must be at least 1 venue in the system.
    if (venueList.size() == 0) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }

    Venue bookingVenue = findVenue(options[0]);
    if (bookingVenue == null) {
      // CONDITION 4: The venue code must exist.
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(options[0]);
      return;

    } else if (bookingVenue.isBookedOnDate(bookingDate)) {
      // CONDITION 5: The venue must not already have a booking for the booking date.
      MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(
          bookingVenue.getVenueName(),
          bookingDate.toString()
      );
      return;
    }


    // Declare the remaining variables.
    String bookingReference = BookingReferenceGenerator.generateBookingReference();
    String clientEmail = options[2];
    int numberOfAttendees = Integer.valueOf(options[3]);

    // If attendees less than 25% or more than 100%, set them to 25% or 100% respectively.
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

    // Make the booking (add to system's bookingList) and add it to the venue object's arraylist of bookings.
    bookingList.add(
        new Booking(bookingReference, bookingVenue, bookingDate, clientEmail, numberOfAttendees)
    );
    bookingVenue.addBooking(bookingList.get(bookingList.size()-1));
    
    // Update the venue's system date, now that a new booking has been added to it.
    bookingVenue.updateNextAvailableDate(systemDate);

    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        bookingReference, bookingVenue.getVenueName(), bookingDate.toString(), Integer.toString(numberOfAttendees)
    );
  }

  public void printBookings(String venueCode) {
    // CASE 1: The venue code doesn't exist/there are no venues in the system.
    if (venueList.isEmpty() || findVenue(venueCode) == null) {
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
      return;
    }

    // Print the header for bookings at the venue (cases 2 & 3 always have this).
    Venue venue = findVenue(venueCode);
    MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venue.getVenueName());

    // CASE 2: There are no bookings for the specified venue (but venue exists).
    if (venue.getBookingsAtVenue().isEmpty()) {
      MessageCli.PRINT_BOOKINGS_NONE.printMessage(venue.getVenueName());
      return;
    }

    // CASE 3: There is at least 1 booking for the specified venue.
    for (Booking booking : venue.getBookingsAtVenue()) {
      System.out.println(booking);
    }
    
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    if (checkServiceBooking("Catering", bookingReference) == true) {
      // Create a new catering service (add to the booking's arraylist of services).
      Booking serviceBooking = findBooking(bookingReference);
      serviceBooking.addService(new Catering(serviceBooking, cateringType));

      // Print relevant success message.
      MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Catering (" + cateringType.getName() + ")", bookingReference);
    }

  }

  public void addServiceMusic(String bookingReference) {
    if (checkServiceBooking("Music", bookingReference) == true) {
      // Create a new music service (add to the booking's arraylist of services).
      Booking serviceBooking = findBooking(bookingReference);
      serviceBooking.addService(new Music(serviceBooking));

      // Print relevant success message.
      MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
    }
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    if (checkServiceBooking("Floral", bookingReference) == true) {
      // Create a new floral service (add to the booking's arraylist of services).
      Booking serviceBooking = findBooking(bookingReference);
      serviceBooking.addService(new Floral(serviceBooking, floralType));

      // Print relevant success message.
      MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Floral (" + floralType.getName() + ")", bookingReference);
    }
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }

  // ***Methods implemented by me below:***

  // This method checks if the string input is a positive number, returns one of 3 output strings.
  public String checkPosInt(String testString) {
    if (testString.isEmpty()) {
      return "";
    }
    try {
      Integer.parseInt(testString);
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
      return NumWord.values()[num - 2].getWord();
    } else {
      return "OUT_OF_RANGE";
    }
  }

  // Returns the venue that has the venue code input.
  public Venue findVenue(String venueCode) {
    // Iterate through each venue in venueList and return the venue with the matching code
    for (Venue venue : venueList) {
      if (venue.getVenueCode().equals(venueCode)) {
        return venue;
      }
    }

    //If no venue found...
    return null;
  }

  // Returns the venue that has the booking reference input.
  public Booking findBooking(String bookingReference) {
    // Iterate through each booking in bookingList and return the booking with the matching reference
    for (Booking booking : bookingList) {
      if (booking.getBookingReference().equals(bookingReference)) {
        return booking;
      }
    }

    //If no venue found...
    return null;
  }

  // Prints error message if booking reference doesn't exist and returns false, returns true if found.
  public boolean checkServiceBooking(String serviceType, String bookingReference) {
    if (findBooking(bookingReference) == null) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage(serviceType, bookingReference);
      return false;
    }

    return true;
  }

}