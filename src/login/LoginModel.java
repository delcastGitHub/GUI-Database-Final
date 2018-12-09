/*****************************************************************************************************************
 * @Author      : Milko Del Castillo
 * @Version     : v. 1.0
 * @Since       : 11/09/2018model
 * FileName     : LoginDriver.java
 * Description  : This class access data and checks if the credentials are correct to displays the admin or user interface.
 ****************************************************************************************************************/

package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DataBase.dbConnection;

//access data and check if is true to pass into text field.
public class LoginModel {

    Connection connection;          // connection object named connection

    // default constructor
    public LoginModel(){

        //connect date base
        try{
            this.connection= DataBase.dbConnection.getConnection();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

        if(this.connection ==null){
           System.exit(1);
        }
    }// end LoginModel constructor

    // if data base is connected, if is connected then true will be return.
    public boolean isDBConnected(){
        return this.connection !=null;
    }

    // check if is login or not.
    public boolean isLogin(String user, String pass, String typ) throws Exception{
        PreparedStatement statement=null; // precompiled SQL object and store in variable statement to access database, initialized it to null
        ResultSet set=null; // result set that is scrollable and insensitive to updates
        String sql = "SELECT * FROM login where username = ? and password = ? and type = ?"; //sql queary = select all from login table the column = username, password and type

        try{
            statement = this.connection.prepareStatement(sql);

            //set username retrieved from database (query sql) and set it to param user
            statement.setString( 1, user);
            //set password retrieved from database (query sql) and set it to param password
            statement.setString(2, pass);
            //set type retrieved from database (query sql) and set it to param typ
            statement.setString(3, typ);

            //execute query statement and store it in set
            set= statement.executeQuery();


            boolean test1; //
            if (set.next()){   //if there are more rows on database
                return true;
            }
            return false;
        }
        catch (SQLException ex){ // catch any SQL Server error generated from the server.
            return false;
        }

        //close connection
        finally {
            statement.close();
            set.close();
        }
    }//end inLogin method
}//end LoginModel class
