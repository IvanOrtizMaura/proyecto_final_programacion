package project.plataforma.plataformaeducativa;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class InicialController implements Initializable {

    @FXML
    private TextField infoDni, infoNombre, infoApellido1, infoApellido2, infoNacimiento, infoTelefono;

    @FXML
    private Button Pantalla_Asignaturas, Pantalla_Centro, Pantalla_Curso, Pantalla_Profesores;


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



}
