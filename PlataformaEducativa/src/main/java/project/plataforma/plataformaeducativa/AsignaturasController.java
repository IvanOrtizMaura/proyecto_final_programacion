package project.plataforma.plataformaeducativa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AsignaturasController implements Initializable {

    @FXML
    private Button Pantalla_Inicial, Pantalla_Centro, Pantalla_Curso, Pantalla_Profesores;

    @FXML
    private TableView<Asignatura> tabla;

    @FXML
    private TableColumn<Asignatura, String> infoNombreAsignatura;

    @FXML
    private TableColumn<Asignatura, Integer> infoHorasAsignatura;

    @FXML
    private TableColumn<Asignatura, String> infoProfesorAsignatura;

    ObservableList<Asignatura> asignaturas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<Button> ventanas = new ArrayList<Button>(Arrays.asList(Pantalla_Inicial, Pantalla_Centro,
                Pantalla_Curso, Pantalla_Profesores));
        Ventana.gestionarVentanas(ventanas);

        infoNombreAsignatura.setCellValueFactory(new PropertyValueFactory<Asignatura, String>("nombre"));
        infoHorasAsignatura.setCellValueFactory(new PropertyValueFactory<Asignatura, Integer>("horas"));
        infoProfesorAsignatura.setCellValueFactory(new PropertyValueFactory<Asignatura, String>("nombreProfesor"));

        obtenerInfoAsignaturas();
        tabla.setItems(asignaturas);

    }

    public void obtenerInfoAsignaturas(){

        Statement stat = null;
        String query = "select " + App.BDD + ".asignatura.* from " + App.BDD + ".asignatura " +
                "inner join " +
                App.BDD + ".programa " +
                "on " + App.BDD + ".asignatura.id = " + App.BDD + ".programa.id_asignatura " +
                "inner join " +
                App.BDD + ".curso " +
                "on " + App.BDD + ".curso.id = " + App.BDD + ".programa.id_curso " +
                "inner join " +
                App.BDD + ".alumno " +
                "on " + App.BDD + ".alumno.id_curso = " + App.BDD + ".programa.id_curso " +
                "where " + App.BDD + ".alumno.dni = '" + LoginController.alumnoLogueado.getDni() + "';";

        try{
            stat = App.dbCon.createStatement();
            ResultSet rs = stat.executeQuery(query);

            while (rs.next()){
                asignaturas.add(new Asignatura(rs.getString("nombre"),
                        Integer.parseInt(rs.getString("horas")),
                        obtenerProfesorAsignatura(rs.getString("dni_profesor"))));


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

    public static String obtenerProfesorAsignatura(String dni){

        Statement stat = null;
        String query = "select " + App.BDD + ".profesor.* from " + App.BDD + ".profesor " +
                "where " + App.BDD + ".profesor.dni = '" + dni + "';";
        String nombreProfesor = "";

        try{
            stat = App.dbCon.createStatement();
            ResultSet rs = stat.executeQuery(query);

            if (rs.next()){
                nombreProfesor = rs.getString("nombre") + " " + rs.getString("apellido1") +
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

        return nombreProfesor;

    }
}
