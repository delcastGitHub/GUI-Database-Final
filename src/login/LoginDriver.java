/*****************************************************************************************************************
 * @Author      : Milko Del Castillo
 * @Version     : v. 1.0
 * @Since       : 11/09/2018
 * FileName     : LoginDriver.java
 * Description  : This class handles the login credentials to display a GUI depends of your credential
 *                  admin = full control or User = view only.
 ****************************************************************************************************************/
package login;

import admin.AdminDriver;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import users.UsersDriver;

//This class manages the admin and user interface depending of the credential from the login interface.
public class LoginDriver implements Initializable {

    //instant for the loginModel class
    LoginModel loginModel = new LoginModel();

    //to access ID of login GUI (login.fxml)
    @FXML
    private Label dbstatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<seleccion> seleccionbox;
    @FXML
    private Button loginbutt;
    @FXML
    private Label loginStatus;


    // This initializes controls on login GUI.
    public void initialize(URL url, ResourceBundle rb){

        //Display in login GUI the db connectivity status.
        if (this.loginModel.isDBConnected()){
            this.dbstatus.setText("DB connected");      //display DB Connected if database is connected
        }else{
            this.dbstatus.setText("Not connected");     //display Not Connected if database is not connected
        }

        //set the comboBox named seleccionBox to seleccion enum (admin / user)
        this.seleccionbox.setItems(FXCollections.observableArrayList(seleccion.values()));
    }//end of initialize method

    @FXML
    //login action for login button
    public void Login (ActionEvent event){
        try{
            //Class the method isLogin from LoginModel class and pass 3 param, it should return a true or false
            if(this.loginModel.isLogin(this.username.getText(), this.password.getText(), ((seleccion)this.seleccionbox.getValue()).toString())){
                Stage stage = (Stage)this.loginbutt.getScene().getWindow();
                stage.close();

                switch (((seleccion)this.seleccionbox.getValue()).toString()){
                    case "Admin":{       //if we select Admin from the ComboBox then it calls the adminLogin method
                        adminLogin();
                        break;}
                    case "User":{       //if we select User from the ComboBox then it calls the userLogin method
                        userLogin();
                        break;}
                }//end switch
            }//end if
            else{
                this.loginStatus.setText("wrong username or password");
            }

        }catch(Exception LocalExpection){ }

    } // end of Login method

    public void userLogin(){
        try{
            Stage userStage= new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/users/users.fxml").openStream());

            //User controls
            UsersDriver usersDriver = (UsersDriver)loader.getController();

            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Users View");
            userStage.setResizable(false);
            userStage.show();


        }catch (IOException ex){
            ex.printStackTrace();
        }
    }//end of userLogin method

    //define the Admin login. it will show Admin GUI
    public void adminLogin(){
        try{
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminroot = (Pane) adminLoader.load(getClass().getResource("/admin/Admin.fxml").openStream());

            //Admin controls
            AdminDriver adminDriver = (AdminDriver)adminLoader.getController();

            Scene scene = new Scene(adminroot);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin View");
            adminStage.setResizable(false);
            adminStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }//end of adminLogin method
} //end of LoginDriver class
