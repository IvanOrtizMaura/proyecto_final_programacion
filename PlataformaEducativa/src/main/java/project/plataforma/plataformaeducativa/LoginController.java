package project.plataforma.plataformaeducativa;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

                if(rs.getString("pin").equals(pin.getText())){
                    pin.setText("Login correcto");
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error!");
                    alert.setContentText("Contraseña incorrecta");
                    alert.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error!");
                alert.setContentText("Login incorrecto");
                alert.showAndWait();
            }
        }
        catch (SQLException e){
            e.getMessage();
        }

    }
}