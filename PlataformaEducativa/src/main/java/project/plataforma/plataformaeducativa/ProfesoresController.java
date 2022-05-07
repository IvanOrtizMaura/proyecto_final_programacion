package project.plataforma.plataformaeducativa;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ProfesoresController implements Initializable {

    @FXML
    private Button Pantalla_Asignaturas, Pantalla_Centro, Pantalla_Curso, Pantalla_Inicial;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<Button> ventanas = new ArrayList<Button>(Arrays.asList(Pantalla_Asignaturas, Pantalla_Centro,
                Pantalla_Curso, Pantalla_Inicial));

        Ventana.gestionarVentanas(ventanas);

    }
}
