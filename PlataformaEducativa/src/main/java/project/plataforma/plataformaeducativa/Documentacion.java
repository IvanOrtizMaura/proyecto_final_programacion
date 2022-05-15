/**
 * @author Rodrigo García Calvo & Ivan Ortiz Maura
 * @since 15/05/2022
 */

package project.plataforma.plataformaeducativa;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Documentacion {

    private static ArrayList<String> datosGlobales;

    //Genera o sobreescribe un documento de texto con los datos asociados a un alumno concreto.
    public static void extraerInfo(){

        datosGlobales = new ArrayList<String>();

        datosAlumno();
        datosCentro();
        datosCurso();
        datosAsignaturas();
        datosTutor();

        String s = "";

        try(FileWriter fw = new FileWriter("Datos - " + LoginController.alumnoLogueado.getDni() +
                ".txt", false); PrintWriter pw = new PrintWriter(fw)){

            for (String datos : datosGlobales) {
                s = datos;
                pw.println(s);
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    //Conjunto de métodos que permiten, a partir de consultas a la BDD, obtener todos los datos en forma de String
    //asociados a un alumno concreto.

    private static void datosAlumno(){

        String infoAlumno = "";

        infoAlumno = "ALUMNO:\n" +
                "\nDNI: " + LoginController.alumnoLogueado.getDni() +
                "\nNombre: " + LoginController.alumnoLogueado.getNombre() +
                "\nApellido1: " + LoginController.alumnoLogueado.getApellido1() +
                "\nApellido2: " + LoginController.alumnoLogueado.getApellido2() +
                "\nFecha de nacimiento: " + LoginController.alumnoLogueado.getNacimiento() +
                "\nTeléfono: " + LoginController.alumnoLogueado.getTelefono();

        datosGlobales.add(infoAlumno);
    }

    private static void datosCentro(){

        Statement stat = null;
        String query = "select " + App.BDD + ".centro.* from " + App.BDD + ".centro " +
                "inner join " +
                App.BDD + ".alumno " +
                "on "+ App.BDD+".centro.id = " + App.BDD + ".alumno.id_centro " +
                "where " + App.BDD + ".alumno.dni = '" + LoginController.alumnoLogueado.getDni() + "';";
        String infoCentro = "";

        try {
            stat = App.dbCon.createStatement();
            ResultSet rs = stat.executeQuery(query);
            if (rs.next()) {

                infoCentro = "\nCENTRO:\n" +
                        "\nNombre: " + rs.getString("nombre") +
                        "\nTeléfono: " + rs.getString("telefono") +
                        "\nDirección: " + rs.getString("direccion");

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
        datosGlobales.add(infoCentro);
    }

    private static void datosCurso(){
        Statement stat = null;
        String query = "select " + App.BDD + ".curso.* from " + App.BDD + ".curso " +
                "inner join " +
                App.BDD + ".alumno " +
                "on "+ App.BDD+".curso.id = " + App.BDD + ".alumno.id_curso " +
                "where " + App.BDD + ".alumno.dni = '" + LoginController.alumnoLogueado.getDni() + "';";
        String dniTutorCurso = "";
        String infoCurso = "";

        try {
            stat = App.dbCon.createStatement();
            ResultSet rs = stat.executeQuery(query);
            if (rs.next()) {

                dniTutorCurso = rs.getString("dni_tutor");
                infoCurso = "\nCURSO:\n" +
                        "\nEtapa: " + rs.getString("nombre") +
                        "\nTutor: " + CursoController.obtenerTutorCurso(dniTutorCurso);

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
        datosGlobales.add(infoCurso);
    }

    public static void datosAsignaturas(){

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
            datosGlobales.add("\nASIGNATURAS: ");

            while (rs.next()){

                    datosGlobales.add("\nNombre: " + rs.getString("nombre") +
                            " Horas: " + Integer.parseInt(rs.getString("horas")) +
                            " Profesor: " + AsignaturasController.obtenerProfesorAsignatura(rs.getString
                            ("dni_profesor")));

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

    private static void datosTutor(){

        Statement stat = null;
        String query = "select " + App.BDD + ".profesor.* from " + App.BDD + ".profesor " +
                "inner join " +
                App.BDD + ".curso " +
                "on " + App.BDD + ".profesor.dni = " + App.BDD + ".curso.dni_tutor " +
                "inner join " +
                App.BDD + ".alumno " +
                "on " + App.BDD + ".alumno.id_curso = " + App.BDD + ".curso.id " +
                "where " + App.BDD + ".alumno.dni = '" + LoginController.alumnoLogueado.getDni() + "';";
        String dniTutorActual = "";
        String infoTutor = "";

        try{
            stat = App.dbCon.createStatement();
            ResultSet rs = stat.executeQuery(query);

            if (rs.next()){
                dniTutorActual = rs.getString("dni");
                infoTutor = "\nTUTOR:\n" +
                        "\nNombre: " + rs.getString("nombre") +
                        "\nApellido1: " + rs.getString("apellido1") +
                        "\nApellido2: " + rs.getString("apellido2") +
                        "\nDepartamento: " + ProfesoresController.obtenerDepProfesor(dniTutorActual);

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
        datosGlobales.add(infoTutor);
    }

}
