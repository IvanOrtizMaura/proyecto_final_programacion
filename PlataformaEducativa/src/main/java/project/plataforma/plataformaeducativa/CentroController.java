package project.plataforma.plataformaeducativa;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CentroController implements Initializable {

    @FXML
    private TextField infoNombreCentro, infoTelefonoCentro, infoDireccionCentro;
    @FXML
    private Button Pantalla_Asignaturas, Pantalla_Inicial, Pantalla_Curso, Pantalla_Profesores;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<Button> ventanas = new ArrayList<Button>(Arrays.asList(Pantalla_Asignaturas, Pantalla_Inicial,
                Pantalla_Curso, Pantalla_Profesores));
        Ventana.gestionarVentanas(ventanas);
        consultarInfoCentro();

    }
    public void consultarInfoCentro(){
        Statement stat = null;
        String query = "select " + App.BDD + ".centro.* from " + App.BDD + ".centro " +
                "inner join " +
                App.BDD + ".alumno " +
                "on "+ App.BDD+".centro.id = " + App.BDD + ".alumno.id_centro " +
                "where " + App.BDD + ".alumno.dni = '" + LoginController.alumnoLogueado.getDni() + "';";
        try {
            stat = App.dbCon.createStatement();
            ResultSet rs = stat.executeQuery(query);
            if (rs.next()) {
                infoNombreCentro.setText(rs.getString("nombre"));
                infoTelefonoCentro.setText(rs.getString("telefono"));
                infoDireccionCentro.setText(rs.getString("direccion"));

            }
        }catch (SQLException e){
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
