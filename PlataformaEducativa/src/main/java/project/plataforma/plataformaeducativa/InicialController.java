package project.plataforma.plataformaeducativa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class InicialController implements Initializable {

    @FXML
    private TextField infoDni;

    @FXML
    private TextField infoNombre;

    @FXML
    private TextField infoApellido1;

    @FXML
    private TextField infoApellido2;

    @FXML
    private TextField infoNacimiento;

    @FXML
    private TextField infoTelefono;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        infoDni.setText(LoginController.alumnoLogueado.getDni());
        infoDni.setFocusTraversable(false);
        infoNombre.setText(LoginController.alumnoLogueado.getNombre());
        infoNombre.setFocusTraversable(false);
        infoApellido1.setText(LoginController.alumnoLogueado.getApellido1());
        infoApellido1.setFocusTraversable(false);
        infoApellido2.setText(LoginController.alumnoLogueado.getApellido2());
        infoApellido2.setFocusTraversable(false);
        infoNacimiento.setText(LoginController.alumnoLogueado.getNacimiento());
        infoNacimiento.setFocusTraversable(false);
        infoTelefono.setText(LoginController.alumnoLogueado.getTelefono());
        infoTelefono.setFocusTraversable(false);

    }

    @FXML
    public void pruebaB(){
        infoTelefono.setText("ª");
    }

}
