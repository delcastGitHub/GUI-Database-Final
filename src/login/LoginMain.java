/****************************************************************************************************************
 * @Author      : Milko Del Castillo
 * @Version     : v. 1.0
 * @Since       : 11/09/2018
 * FileName     : LoginMain.java
 * Description  : Main class to initialize program.
 ***************************************************************************************************************/

package login;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//It loads the loginMain interface.
public class LoginMain extends Application {

    public void start(Stage stage) throws Exception{

        //create a root note to access the file login.fxml
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene scene = new Scene(root);          // crate a Scene object from root called scene
        stage.setScene(scene);                  // set the scene to Stage object stage
        stage.setTitle("M&S Protech Clients");
        stage.show();
    }// end start method

    public static void main(String[] args){
        launch(args);

    }// end main method
}// end class LoginMain
