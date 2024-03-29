package nz.ac.auckland.se281;

public class SimpleDate {
  private int day;
  private int month;
  private int year;

  public SimpleDate(String dateString) {
    day = Integer.valueOf(splitDate(dateString)[0]);
    month = Integer.valueOf(splitDate(dateString)[1]);
    year = Integer.valueOf(splitDate(dateString)[2]);
  }

  public SimpleDate(SimpleDate other) {
    this.day = other.day;
    this.month = other.month;
    this.year = other.year;
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

  @Override
  public String toString() {
    return
        String.format("%02d", day) + "/" +
        String.format("%02d", month) + "/" +
        String.format("%04d", year)
    ; 
  }

}
