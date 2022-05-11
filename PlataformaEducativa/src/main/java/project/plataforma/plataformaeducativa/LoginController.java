package project.plataforma.plataformaeducativa;

import javafx.event.ActionEvent;
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

    public static Alumno alumnoLogueado;

    @FXML
    public void acceder(ActionEvent event){

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

                    Ventana.cerrarVentanaActual(event);
                    Ventana.abrirVentana("Pantalla_Inicial");

                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("¡Error!");
                    alert.setContentText("Contraseña incorrecta.");
                    alert.show();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("¡Error!");
                alert.setContentText("Usuario incorrecto.");
                alert.showAndWait();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally{
            try{
                stat.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

}