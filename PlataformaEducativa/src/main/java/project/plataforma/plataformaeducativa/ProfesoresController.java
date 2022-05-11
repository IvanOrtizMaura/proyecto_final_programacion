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

public class ProfesoresController implements Initializable {

    @FXML
    private TextField infoNombreProfesor,infoApellido1Profesor,infoApellido2Profesor, infoCentroProfesor,
            infoDepartamentoProfesor;
    @FXML
    private Button Pantalla_Asignaturas, Pantalla_Centro, Pantalla_Curso, Pantalla_Inicial;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<Button> ventanas = new ArrayList<Button>(Arrays.asList(Pantalla_Asignaturas, Pantalla_Centro,
                Pantalla_Curso, Pantalla_Inicial));
        Ventana.gestionarVentanas(ventanas);
        consultarInfoProfesor();

    }

    public void consultarInfoProfesor(){

        Statement stat = null;
        String query = "select " + App.BDD + ".profesor.* from " + App.BDD + ".profesor" +
                " inner join " +
                App.BDD + ".curso" +
                " on " + App.BDD + ".profesor.dni = " + App.BDD + ".curso.dni_tutor" +
                " inner join " +
                App.BDD + ".alumno " +
                "on " + App.BDD + ".alumno.id_curso = " + App.BDD + ".curso.id " +
                "where " + App.BDD + ".alumno.dni = '" + LoginController.alumnoLogueado.getDni() + "';";

        System.out.println(query);

        try{
            stat = App.dbCon.createStatement();
            ResultSet rs = stat.executeQuery(query);

            if (rs.next()){
                infoNombreProfesor.setText(rs.getString("nombre"));
                infoApellido1Profesor.setText(rs.getString("apellido1"));
                infoApellido2Profesor.setText(rs.getString("apellido2"));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
    
}
