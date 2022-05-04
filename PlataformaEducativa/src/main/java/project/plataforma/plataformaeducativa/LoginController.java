package project.plataforma.plataformaeducativa;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {

    @FXML
    private TextField usuario;

    @FXML
    private TextField pin;

    public static Alumno alumnoLogueado;

    @FXML
    public void Acceder(){

        Statement stat = null;
        String query = "select * from " + App.BDD + ".alumno where dni = '" + usuario.getText() + "';";

        try{

            stat = App.dbCon.createStatement();
            ResultSet rs = stat.executeQuery(query);

            if(rs.next()){
                if(rs.getString("pin").equals(pin.getText())){

                    alumnoLogueado = new Alumno (rs.getString("dni"), rs.getString("nombre"),
                            rs.getString("apellido1"), rs.getString("apellido2"),
                            rs.getString("fecha_nacimiento"), rs.getString("telefono"),
                            Integer.parseInt(rs.getString("id_curso")),
                            Integer.parseInt(rs.getString("id_centro")));

                    try{
                        Stage actual = (Stage) usuario.getScene().getWindow();
                        actual.close();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Pantalla_Inicial.fxml"));
                        Parent root = loader.load();

                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setTitle("Eduform - " + alumnoLogueado.nombre + " " + alumnoLogueado.apellido1 + " " +
                                alumnoLogueado.apellido2);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(scene);
                        stage.show();
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }

                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("¡Error!");
                    alert.setContentText("Contraseña incorrecta");
                    alert.show();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("¡Error!");
                alert.setContentText("Usuario incorrecto");
                alert.showAndWait();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

}