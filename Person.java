
/** 
 * abstract class person is a class that represents a person, providing
 * generic details about them. information can be extended within the 
 * customer class. This class provides the attributes of a person,
 * and setters and getters for such attributes.
 * @author Abraham Avalos
 */
public abstract class Person {
	protected String idNumber;
	protected String firstName;
	protected String lastName;
	protected String dob;
	protected String address;
	protected String phoneNum;
	
	/** constructor that initializes all fields */
    public Person(String idNumber, String firstName, String lastName, String dob, String address, String phoneNum) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.phoneNum = phoneNum;
    }
    /** default constructor */
    public Person() {
        this.idNumber = "";
        this.firstName = "";
        this.lastName = "";
        this.dob = "";
        this.address = "";
        this.phoneNum = "";
    }

    /** gets a persons Id number 
     * @return idNumber the id number associated with the person
     */
    public String getIdNumber() {
        return idNumber;
    }
    
    /** gets a persons first name 
     * @return firstName the persons first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /** gets a persons last name 
     * @return lastName the persons last name
     */
    public String getLastName() {
        return lastName;
    }
    
    /** gets a persons date of birth 
     * @return dob the persons date of birth
     */
    public String getDob() {
        return dob;
    }

    /** gets a persons address 
     * @return address the persons address
     */
    public String getAddress() {
        return address;
    }

    /** gets a persons phone number 
     * @return phoneNum the persons phone number
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /** sets a persons Id number 
     * @param idNumber the persons idNumber
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /** gets a persons first name 
     * @param firstName the persons first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /** gets a persons last name 
     * @param lastName the persons last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /** gets a persons date of birth 
     * @param dob the persons date of birth
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /** gets a persons address 
     * @param address the persons address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /** gets a persons phone number 
     * @param phoneNum the persons phone number
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /** Abstract method to display a persons info */
    public abstract void displayInfo();
    
}
