package project.plataforma.plataformaeducativa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class InicialController implements Initializable {

    @FXML
    private TextField infoDni, infoNombre, infoApellido1, infoApellido2, infoNacimiento, infoTelefono;

    @FXML
    private Button Pantalla_Asignaturas, Pantalla_Centro, Pantalla_Curso, Pantalla_Profesores;


    private String nuevoTelefono = LoginController.alumnoLogueado.getTelefono();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<Button> ventanas = new ArrayList<Button>(Arrays.asList(Pantalla_Asignaturas, Pantalla_Centro,
                Pantalla_Curso, Pantalla_Profesores));

        infoDni.setText(LoginController.alumnoLogueado.getDni());
        infoNombre.setText(LoginController.alumnoLogueado.getNombre());
        infoApellido1.setText(LoginController.alumnoLogueado.getApellido1());
        infoApellido2.setText(LoginController.alumnoLogueado.getApellido2());
        infoNacimiento.setText(LoginController.alumnoLogueado.getNacimiento());
        infoTelefono.setText(LoginController.alumnoLogueado.getTelefono());

        Ventana.gestionarVentanas(ventanas);

    }

    @FXML
    public void modificarTelefono(){
        Statement stat = null;
        try{
            stat = App.dbCon.createStatement();

            if (infoTelefono.getText().matches("[0-9]{9}")){
                stat.executeUpdate("update " + App.BDD + ".alumno " +
                        "set telefono = " + infoTelefono.getText() +
                        " where dni = '" + LoginController.alumnoLogueado.getDni() + "';");
                LoginController.alumnoLogueado.setTelefono(infoTelefono.getText());
                nuevoTelefono = infoTelefono.getText();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Información");
                alert.setContentText("Número de teléfono modificado satisfactoriamente.");
                alert.show();
            }
            else{
                infoTelefono.setText(nuevoTelefono);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("¡Error!");
                alert.setContentText("Formato incorrecto.");
                alert.show();
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
    public void descargarDatos(){

        Documentacion.extraerInfo();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Información");
        alert.setContentText("Datos del alumno almacenados en el fichero:\n" +
                "Datos - " + LoginController.alumnoLogueado.getDni() + ".txt");
        alert.show();
    }

    @FXML
    public void cerrarSesion(ActionEvent e){
        Ventana.cerrarVentanaActual(e);
        Ventana.abrirVentana("Pantalla_Login");
    }

}
