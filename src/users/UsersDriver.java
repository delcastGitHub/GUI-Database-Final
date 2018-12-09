/*****************************************************************************************************************
 * @Author      : Milko Del Castillo
 * @Version     : v. 1.0
 * @Since       : 11/09/2018
 * FileName     : UsersDriver.java
 * Description  : This class handles the user GUI. You can only view information from the table.
 ****************************************************************************************************************/

package users;

import DataBase.dbConnection;
import admin.ClientsData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

//This class manages the interaction between the user and the user interface.
public class UsersDriver implements Initializable {

    @FXML
    private TextField clientIDView;
    @FXML
    private TableView clientTableView;
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

    //to initialize a connection instance named dc.
    private dbConnection dc;

    //declares a observable list which holds ClientData objects.
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

            //Establish new connection with Database.
            Connection conn = dbConnection.getConnection();

            //Create observable Array List to hold client's information
            this.clientdata= FXCollections.observableArrayList();

            //It stores result of query into set variable.
            ResultSet set= conn.createStatement().executeQuery(sqlAll);

            //It sets the data into the table until the last row.
            while(set.next()){ //until the last row on the database
                this.clientdata.add(new ClientsData(set.getString(1), set.getString(2),
                        set.getString(3),set.getString(4), set.getString(5)));
            }

        }catch(SQLException e){

            System.err.println("Error Client data method" + e);

        }//end of catch

        //To display the data into table GUI
        this.idColumn.setCellValueFactory(new PropertyValueFactory<ClientsData, String>("clientID"));
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<ClientsData, String>("firstName"));
        this.lastNameColumn.setCellValueFactory(new PropertyValueFactory<ClientsData, String>("lastName"));
        this.emailColumn.setCellValueFactory(new PropertyValueFactory<ClientsData, String>("email"));
        this.phoneColumn.setCellValueFactory(new PropertyValueFactory<ClientsData, String>("phoneNumber"));

        this.clientTableView.setItems(null);            //set table to null
        this.clientTableView.setItems(this.clientdata); // fill table with info

    }//end of loadClientData method


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
            String sqlSearch= "SELECT * FROM client WHERE id = " + clientIDView.getText();
            this.clientdata= FXCollections.observableArrayList();

            //It executes the query and holds it into setTwo variable
            ResultSet setTwo= conn.createStatement().executeQuery(sqlSearch);

            while(setTwo.next()){ //until the last row on the database
                this.clientdata.add(new ClientsData(setTwo.getString(1), setTwo.getString(2),
                        setTwo.getString(3),setTwo.getString(4), setTwo.getString(5)));
            } //end of while

        }catch(SQLException e){
            e.printStackTrace();
        }

        this.clientTableView.setItems(null); //set table to null
        this.clientTableView.setItems(this.clientdata); // fill table with info

        clientIDView.setText("");
    }//end searchClient method
}//end of UsersDriver

