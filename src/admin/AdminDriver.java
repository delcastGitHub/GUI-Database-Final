/*****************************************************************************************************************
 * @Author      : Milko Del Castillo
 * @Version     : v. 1.0
 * @Since       : 11/09/2018
 * FileName     : AdminDriver.java
 * Description  : This class handles the admin GUI. You can search, add, delete information into the Database
 ****************************************************************************************************************/

package admin;

import DataBase.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

//This class manages the interaction between the user and the admin interfaces.
public class AdminDriver implements Initializable{

    @FXML
    public TextField idAdmin;
    @FXML
    public TextField nameAdmin;
    @FXML
    public TextField lastNameAdmin;
    @FXML
    public TextField emailAdmin;
    @FXML
    public TextField phoneAdmin;
    @FXML
    public TextArea resultConsole;
    @FXML
    public TextField clientID;
    @FXML
    public TextField newPhone;
    @FXML
    public TextField newEmail;
    @FXML
    private TableView clientTable;
    @FXML
    private TableColumn<ClientsData, String> idColumn;
    @FXML
    private TableColumn<ClientsData, String> nameColumn;
    @FXML
    private TableColumn<ClientsData, String> lastNameColumn;
    @FXML
    private TableColumn<ClientsData, String> emailColumn;
    @FXML
    private TableColumn<ClientsData, String> phoneColumn;

    //It initializes a connection instance named dc.
    private dbConnection dc;

    //declares a observable list which holds ClientData objects
    private ObservableList<ClientsData> clientdata;

    //It initializes database connection.
    public  void initialize (URL url, ResourceBundle rb){
        this.dc = new dbConnection(); //for new connection
    }

    /******************************************************
    **       IT LOADS ALL CLIENT INTO TABLE               **
     ******************************************************/
    @FXML
    //It will load all clients and display into the table.
    public void loadClientData(ActionEvent event){

        try{

            //Query to declare a select statement for search all client function. And to sort the result by ID (1-9)
            String sqlAll = "SELECT * FROM client ORDER BY id";

            //Establish new connction with Database
            Connection conn = dbConnection.getConnection();

            //Create observable Array List to hold client's information
            this.clientdata= FXCollections.observableArrayList();

            //It stores result of query into set variable
            ResultSet set= conn.createStatement().executeQuery(sqlAll);

            //It sets the data into the table until the last row.
            while(set.next()){ //until the last row on the database
                this.clientdata.add(new ClientsData(set.getString(1), set.getString(2),
                        set.getString(3),set.getString(4), set.getString(5)));
            }

            //It shows into the result to console if the action has successfully performed
            resultConsole.setText("All clients have been loaded successfully !");

        }catch(SQLException e){

            System.err.println("Error Client data method" + e);

            //A message will be display into the result console if an error is found.
            resultConsole.setText("Error Client data method !");
        }//end of catch

        //To display the data into table GUI
        this.idColumn.setCellValueFactory(new PropertyValueFactory<ClientsData, String>("clientID"));
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<ClientsData, String>("firstName"));
        this.lastNameColumn.setCellValueFactory(new PropertyValueFactory<ClientsData, String>("lastName"));
        this.emailColumn.setCellValueFactory(new PropertyValueFactory<ClientsData, String>("email"));
        this.phoneColumn.setCellValueFactory(new PropertyValueFactory<ClientsData, String>("phoneNumber"));

        this.clientTable.setItems(null);            //set table to null
        this.clientTable.setItems(this.clientdata); // fill table with info
    }//end of loadClientData method



    /******************************************************
     **                   ADD CLIENT                      **
     ******************************************************/
    @FXML
    //This method will get information of a new client and set it into the table GUI.
    private void addClient(ActionEvent event){
        try{
            //To establish connection with the database
            Connection conn = dbConnection.getConnection();

            //Query to add a client to table - insert into client table = id, first name, last name, email, phone number
            String sqlAdd = "INSERT INTO client (id, fname, lname, email, phone) VALUES (?,?,?,?,?)";

            PreparedStatement statementToAdd = conn.prepareStatement(sqlAdd);

            //Sets the field text from GUI to local variables.
            statementToAdd.setString(1, this.idAdmin.getText());
            statementToAdd.setString(2, this.nameAdmin.getText());
            statementToAdd.setString(3, this.lastNameAdmin.getText());
            statementToAdd.setString(4, this.emailAdmin.getText());
            statementToAdd.setString(5, this.phoneAdmin.getText());

            //It executes the query.
            statementToAdd.execute();

            //It closes connectivity with database base
            conn.close();

            //It shows into the result to console if the action has successfully performed.
            resultConsole.setText("Client " + this.nameAdmin.getText() + " has been successfully added" );

        }catch(SQLException e){
            e.printStackTrace();

            //A message will be display into the result console if an error is found.
            resultConsole.setText("An error has occurred ");
        }
    }//end addClient method

    /******************************************************
     **       IT CLEARS THE FORM FIELD TEXTS              **
     ******************************************************/
    @FXML
    //This method clears the form adding client into the table Clients by setting text "" to the text fields from form.
    private void clearForm(ActionEvent event){
        this.idAdmin.setText("");
        this.nameAdmin.setText("");
        this.lastNameAdmin.setText("");
        this.phoneAdmin.setText("");
        this.emailAdmin.setText("");
    }

    /******************************************************
     **       IT SEARCHES A CLIENT BY ID                  **
     ******************************************************/
    @FXML
    //This method searches client by ID and displays the records of that client into the table
    private void searchClient(ActionEvent event){

        try{

            //To establish connectivity with the database
            Connection conn = dbConnection.getConnection();

            //Query to select from client's table information that matches a certain id.
            String sqlSearch= "SELECT * FROM client WHERE id = " + clientID.getText();
            this.clientdata= FXCollections.observableArrayList();

            //It executes the query and holds it into setTwo variable
            ResultSet setTwo= conn.createStatement().executeQuery(sqlSearch);

            while(setTwo.next()){ //until the last row on the database
                this.clientdata.add(new ClientsData(setTwo.getString(1), setTwo.getString(2),
                        setTwo.getString(3),setTwo.getString(4), setTwo.getString(5)));

                //System.out.println(setTwo.getString(4)); // phone number
                newPhone.setText(setTwo.getString(5));
               // System.out.println(setTwo.getString(4)); // email
                newEmail.setText(setTwo.getString(4));

            } //end of while

            //It shows into the result to console if the action has successfully performed.
            resultConsole.setText("The search was successfully performed" );

        }catch(SQLException e){
            e.printStackTrace();

            //A message will be display into the result console if an error is found.
            resultConsole.setText("An error has occurred ");
        }

        this.clientTable.setItems(null); //set table to null
        this.clientTable.setItems(this.clientdata); // fill table with info

    }//end searchClient method

    /******************************************************
     **       IT UPDATES PHONE NUMBER AND EMAIL           **
     ******************************************************/
    @FXML
    //This method sets the phone number and email with the result information from a search. Then,
    // you are able to updates the phone number and email records of the client.
    public void newPhoneNumberAndEmail(ActionEvent event){

        try{
            //To establish connectivity with the database
            Connection conn = dbConnection.getConnection();

            String sqlUpdatePhoneNumber= String.format("UPDATE client SET phone = '%s', email = '%s'" + "WHERE id='%s' ", newPhone.getText(), newEmail.getText(), clientID.getText());

            PreparedStatement statementToUpdate = conn.prepareStatement(sqlUpdatePhoneNumber);

            //It executes the query
            statementToUpdate.execute();

            //It closes connectivity with database base
            conn.close();

            //These clean the text field for ID, new phone and new email
            this.clientID.setText("");
            this.newPhone.setText("");
            this.newEmail.setText("");

            //load client's table with all info included the updated ones
            loadClientData(event);

            //It shows into the result to console if the action has successfully performed.
            resultConsole.setText("The phone number and email of has been successfully updated" );
        }

        catch(SQLException edd){
            edd.printStackTrace();

            //A message will be display into the result console if an error is found.
            resultConsole.setText("An error has occurred ");
        }
    }//end of newPhoneNumberAndEmail


    /******************************************************
     **       IT DELETES A CLIENT'S RECORDS               **
     ******************************************************/
    @FXML
    //This method will delete a complete client's record after a search is performed.
    public void deleteAClient(ActionEvent event){

        try{
            //To establish connectivity with the database
            Connection conn = dbConnection.getConnection();

            //Query : delete from table client the records with id : ..."
            String sqlDeleteRecord= "DELETE FROM client WHERE id =" + clientID.getText();

            PreparedStatement statementToDelete = conn.prepareStatement(sqlDeleteRecord);

            //It executes the query
            statementToDelete.execute();

            //It closes connectivity with database base
            conn.close();

            //These clean the text field for ID, new phone and new email
            this.clientID.setText("");
            this.newPhone.setText("");
            this.newEmail.setText("");

            //load client's table with all info included the updated ones
            loadClientData(event);

            //It shows into the result to console if the action has successfully performed.
            resultConsole.setText("The records has been successfully deleted" );
        }

        catch(SQLException edd){
            edd.printStackTrace();

            //A message will be display into the result console if an error is found.
            resultConsole.setText("An error has occurred ");
        }

    }//end of deleteAClient
}//end of AdminDriver
