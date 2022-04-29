package project.plataforma.plataformaeducativa;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {

    @FXML
    private TextField usuario;

    @FXML
    private TextField pin;

    @FXML
    public void Acceder(){

        Statement stat = null;
        String query = "select * from " + App.BDD + ".alumno where dni = '" + usuario.getText() + "';";

        try{
            stat = App.dbCon.createStatement();
            ResultSet rs = stat.executeQuery(query);
            if(rs.next()){

                if(rs.getString("pin") == pin.getText()){
                    pin.setText("Login correcto");
                }
                else{
                    pin.setText("Contraseña incorrecta");
                }
            }
            else{
                pin.setText("Login incorrecto");
            }
        }
        catch (SQLException e){
            e.getMessage();
        }

    }
}