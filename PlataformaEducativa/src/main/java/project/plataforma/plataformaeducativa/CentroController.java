package project.plataforma.plataformaeducativa;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CentroController implements Initializable {

    @FXML
    private Button Pantalla_Asignaturas, Pantalla_Inicial, Pantalla_Curso, Pantalla_Profesores;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<Button> ventanas = new ArrayList<Button>(Arrays.asList(Pantalla_Asignaturas, Pantalla_Inicial,
                Pantalla_Curso, Pantalla_Profesores));

        Ventana.gestionarVentanas(ventanas);

    }
}
