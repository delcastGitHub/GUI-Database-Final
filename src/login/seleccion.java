/*****************************************************************************************************************
 * @Author      : Milko Del Castillo
 * @Version     : v. 1.0
 * @Since       : 11/09/2018model
 * FileName     : Seleccion.java
 * Description  : This class checks the seleccion type of login. IT returns the selection.
 ****************************************************************************************************************/


package login;

public enum seleccion {

    Admin,      // for administrator
    User;       // for users

    private seleccion(){}

    private String value(){
        return name();
    }

    public static seleccion fromvalue(String val){
        return valueOf(val);
    }
}
