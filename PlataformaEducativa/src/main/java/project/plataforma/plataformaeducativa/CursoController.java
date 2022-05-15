/**
 * @author Rodrigo García Calvo & Ivan Ortiz Maura
 * @since 15/05/2022
 */

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

public class CursoController implements Initializable {

    @FXML
    private TextField infoEtapaCurso, infoTutorCurso;

    @FXML
    private Button Pantalla_Asignaturas, Pantalla_Centro, Pantalla_Inicial, Pantalla_Profesores;


    @Override
    //Asigna a cada uno de los objetos Button su función correspondiente.
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<Button> ventanas = new ArrayList<Button>(Arrays.asList(Pantalla_Asignaturas, Pantalla_Centro,
                Pantalla_Inicial, Pantalla_Profesores));
        Ventana.gestionarVentanas(ventanas);
        consultarInfoCurso();
    }

    //Hace una consulta a la BDD para obtener la información del curso de un alumno concreto.
    public void consultarInfoCurso(){
        Statement stat = null;
        String query = "select " + App.BDD + ".curso.* from " + App.BDD + ".curso " +
                "inner join " +
                App.BDD + ".alumno " +
                "on "+ App.BDD+".curso.id = " + App.BDD + ".alumno.id_curso " +
                "where " + App.BDD + ".alumno.dni = '" + LoginController.alumnoLogueado.getDni() + "';";
        String dniTutorCurso = "";

        try {
            stat = App.dbCon.createStatement();
            ResultSet rs = stat.executeQuery(query);
            if (rs.next()) {
                dniTutorCurso = rs.getString("dni_tutor");
                infoEtapaCurso.setText(rs.getString("nombre"));
                infoTutorCurso.setText(obtenerTutorCurso(dniTutorCurso));

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

    //Hace una consulta a la BDD para obtener el nombre del tutor del curso a partir de su DNI.
    public static String obtenerTutorCurso(String dni){

        Statement stat = null;
        String query = "select " + App.BDD + ".profesor.* from " + App.BDD + ".profesor " +
                "where " + App.BDD + ".profesor.dni = '" + dni + "';";
        String nombreTutor = "";

        try{
            stat = App.dbCon.createStatement();
            ResultSet rs = stat.executeQuery(query);

            if (rs.next()){
                nombreTutor = rs.getString("nombre") + " " + rs.getString("apellido1") +
                " " + rs.getString("apellido2");
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

        return nombreTutor;

    }
}
