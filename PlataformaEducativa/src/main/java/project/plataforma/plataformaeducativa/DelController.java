package project.plataforma.plataformaeducativa;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DelController {

    @FXML
    public TextField delDNI;

    @FXML
    public void suprimirAlumno(){

        Statement stat  = null;

        try{
            stat = App.dbCon.createStatement();

            if(existeDNI()) {
                stat.executeUpdate("delete from " + App.BDD + ".alumno where dni = '" + delDNI.getText() + "';");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Información");
                alert.setContentText("Alumno con DNI " + delDNI.getText() + " suprimido satisfactoriamente.");
                alert.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("¡Error!");
                alert.setContentText("No existe un alumno con ese DNI.");
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
    public boolean existeDNI(){
        Statement stat = null;
        String query = "select * from " + App.BDD + ".alumno where dni = '" + delDNI.getText() + "';";

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


