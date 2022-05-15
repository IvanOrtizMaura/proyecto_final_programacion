/**
 * @author Rodrigo García Calvo & Ivan Ortiz Maura
 * @since 15/05/2022
 */

package project.plataforma.plataformaeducativa;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddController {

    @FXML
    public TextField newDNI, newNombre, newApellido1, newApellido2, newTelefono, newPIN, newIDCurso, newIDCentro;

    @FXML
    public DatePicker newNacimiento;

    @FXML
    //Inserta un nuevo alumno en la BDD.
    public void agregarAlumno(){

        Statement stat = null;
        String PatronID = "[0-9]{1,3}";
        String PatronTelefono = "[0-9]{9}";
        String PatronDNI = "[0-9]{8}[A-Z]";
        String PatronPIN = "[0-9]{6}";

        try{
            stat = App.dbCon.createStatement();

            if(newIDCentro.getText().matches(PatronID) && newIDCurso.getText().matches(PatronID)
                    && newTelefono.getText().matches(PatronTelefono) && newDNI.getText().matches(PatronDNI)
                    && newPIN.getText().matches(PatronPIN)){

                if(!alumnoRepetido()){
                    stat.executeUpdate("insert into " + App.BDD + ".alumno" +
                            " values " +
                            "('" + newDNI.getText() +"'," +
                            " '" + newNombre.getText() + "'," +
                            " '" + newApellido1.getText() + "'," +
                            " '" + newApellido2.getText() + "'," +
                            " '" + newNacimiento.getValue().toString() + "'," +
                            " '" + newTelefono.getText() + "'," +
                            " '" + newPIN.getText() + "'," +
                            " " + newIDCurso.getText() + "," +
                            " " + newIDCentro.getText() + ");");

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Información");
                    alert.setContentText("Alumno agregado correctamente.");
                    alert.show();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("¡Error!");
                    alert.setContentText("Ya existe un alumno con ese DNI.");
                    alert.show();
                }

            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("¡Error!");
                alert.setContentText("Formato incorrecto.");
                alert.show();
            }

        }
        catch(SQLException e){
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
    //Comprueba que el alumno a insertar no esté repetido.
    public boolean alumnoRepetido(){

        Statement stat = null;
        String query = "select * from " + App.BDD + ".alumno where dni = '" + newDNI.getText() + "';";

        try{

            stat = App.dbCon.createStatement();
            ResultSet rs = stat.executeQuery(query);

            if(rs.next()){
                return true;
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
        return false;
    }

}
