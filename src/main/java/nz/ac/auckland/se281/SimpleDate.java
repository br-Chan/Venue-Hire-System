package nz.ac.auckland.se281;

public class SimpleDate {
  private int day;
  private int month;
  private int year;
  private boolean isSet;

  public SimpleDate() {
    day = 0;
    month = 0;
    year = 0;
    isSet = false;
  }

  public SimpleDate(String dateString) {
    day = Integer.valueOf(splitDate(dateString)[0]);
    month = Integer.valueOf(splitDate(dateString)[1]);
    year = Integer.valueOf(splitDate(dateString)[2]);
    isSet = true;
  }

  public SimpleDate(SimpleDate other) {
    this.day = other.day;
    this.month = other.month;
    this.year = other.year;
    isSet = true;
  }

  public boolean getIsSet() {
    return isSet;
  }

  // Splits date into its 3 parts and returns them as a string array.
  public String[] splitDate() {
    String[] dateSplit = this.toString().split("/");
    return dateSplit;
  }

  // Splits String that is in the form of a date
  public String[] splitDate(String dateString) {
    String[] dateSplit = dateString.split("/");
    return dateSplit;
  }

  // Returns 0 if equal, -1 if this is in the past, 1 is this is in the future.
  public int compareWith(SimpleDate other) {
    int yearsCompared = Integer.compare(this.year, other.year);
    if (yearsCompared != 0) {
      return yearsCompared;
    }

    int monthsCompared = Integer.compare(this.month, other.month);
    if (monthsCompared != 0) {
      return monthsCompared;
    }
    
    int daysCompared = Integer.compare(this.day, other.day);
    if (daysCompared != 0) {
      return daysCompared;
    }

    return 0;
  }

  public void incrementDay() {
    day++;
  }

  // Changes the date to be equal to the input date but not actually become the date.
  public void changeTo(SimpleDate dateToChangeTo) {
    this.day = dateToChangeTo.day;
    this.month = dateToChangeTo.month;
    this.year = dateToChangeTo.year;
  }

  @Override
  public String toString() {
    return(
        String.format("%02d", day) + "/" +
        String.format("%02d", month) + "/" +
        String.format("%04d", year)
    ); 
  }

}
