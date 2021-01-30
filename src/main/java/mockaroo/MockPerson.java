package mockaroo;

/**
 * Class representing a mock entry in a csv.
 */
public class MockPerson {
  private String fname;
  private String lname;
  private String time;
  private String email;
  private String gender;
  private String address;

  /**
   *
   * @param time time of entry
   * @param fname first name
   * @param lname last name
   * @param email email
   * @param gender gender
   * @param address home address
   */
  public MockPerson(String time, String fname, String lname, String email, String gender,
                    String address) {
    this.fname = fname;
    this.lname = lname;
    this.time = time;
    this.email = email;
    this.gender = gender;
    this.address = address;
  }

  @Override
  public String toString() {
    String toRet = "";
    if (!time.equals("")) {
      toRet = toRet + "Entry from: " + time + ". ";
    }
    if (!fname.equals("") && lname.equals("")) {
      toRet = toRet + "Name: " + fname + ". ";
    } else if (!fname.equals("")) {
      toRet = toRet + "Name: " + fname + " " + lname + ". ";
    } else {
      if (!lname.equals("")) {
        toRet = toRet + "Name: " + lname + ". ";
      }
    }
    if (!gender.equals("")) {
      toRet = toRet + "Gender: " + gender + ". ";
    }
    if (!email.equals("")) {
      toRet = toRet + "Email: " + email + ". ";
    }
    if (!address.equals("")) {
      toRet = toRet + "Home Address: " + address + ".";
    }
    return toRet;
  }
}
