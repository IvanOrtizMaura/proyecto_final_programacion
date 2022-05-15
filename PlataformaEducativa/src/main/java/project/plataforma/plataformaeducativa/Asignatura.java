/**
 * @author Rodrigo García Calvo & Ivan Ortiz Maura
 * @since 15/05/2022
 */

package project.plataforma.plataformaeducativa;

public class Asignatura {

    String nombre;
    int horas;
    String nombreProfesor;

    //Método constructor que permite crear un nuevo objeto Asignatura.
    public Asignatura(String nombre, int horas, String nombreProfesor) {
        this.nombre = nombre;
        this.horas = horas;
        this.nombreProfesor = nombreProfesor;
    }

    //Getters y setters.
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }
}
