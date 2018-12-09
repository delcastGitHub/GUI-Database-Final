/*****************************************************************************************************************
 * @Author      : Milko Del Castillo
 * @Version     : v. 1.0
 * @Since       : 11/09/2018
 * FileName     : ClientData.java
 * Description  : This class set all spec for client objects
 *****************************************************************************************************************/

package admin;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

//This methods sets all the specifications of a client.
public class ClientsData {

    //variables for client's table columns
    private final StringProperty clientID;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;
    private final StringProperty phoneNumber;

    //overloaded constructor to set values of client's table.
    public ClientsData(String clientid, String firstname, String lastname, String email, String phonenumber){

        this.clientID= new SimpleStringProperty(clientid);
        this.firstName= new SimpleStringProperty(firstname);
        this.lastName= new SimpleStringProperty(lastname);
        this.email= new SimpleStringProperty(email);
        this.phoneNumber= new SimpleStringProperty(phonenumber);
    }// end of ClientsData Constructor


    public String getClientID() {
        return clientID.get();
    }

    public StringProperty clientIDProperty() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID.set(clientID);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }
}//end of class ClientsData
