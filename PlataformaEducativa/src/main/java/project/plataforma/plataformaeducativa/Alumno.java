/**
 * @author Rodrigo García Calvo & Ivan Ortiz Maura
 * @since 15/05/2022
 */

package project.plataforma.plataformaeducativa;

public class Alumno {

    String dni;
    String nombre;
    String apellido1;
    String apellido2;
    String nacimiento;
    String telefono;
    int idCurso;
    int idCentro;

    //Método constructor que permite crear un nuevo objeto alumno.
    public Alumno(String dni, String nombre, String apellido1, String apellido2, String nacimiento, String telefono,
                  int idCurso, int idCentro) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nacimiento = nacimiento;
        this.telefono = telefono;
        this.idCurso = idCurso;
        this.idCentro = idCentro;
    }

    //Getters y setters.
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
    }
}
