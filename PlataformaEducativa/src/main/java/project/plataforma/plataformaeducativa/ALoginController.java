package project.plataforma.plataformaeducativa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ALoginController {

    @FXML
    private TextField usuario, contraseña;


    @FXML
    public void acceder(ActionEvent event){

        Statement stat = null;
        String query = "select * from " + App.BDD + ".administrador where usuario = '" + usuario.getText() + "';";

        try{

            stat = App.dbCon.createStatement();
            ResultSet rs = stat.executeQuery(query);

            if(rs.next()){
                if(rs.getString("contraseña").equals(contraseña.getText())){

                    Ventana.cerrarVentanaActual(event);
                    Ventana.abrirVentana("Pantalla_APanel");

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

    @FXML
    public void modoUsuario(ActionEvent e){
        Ventana.cerrarVentanaActual(e);
        Ventana.abrirVentana("Pantalla_Login");
    }
}
