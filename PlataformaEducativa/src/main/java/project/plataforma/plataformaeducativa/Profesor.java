package project.plataforma.plataformaeducativa;

public class Profesor {
    String nombre;
    String apellido1;
    String apellido2;
    String centro;
    String departamento;
    public Profesor(String nombre, String apellido1, String apelliedo2, String centro, String departamento){
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apelliedo2;
        this.centro = centro;
        this.departamento = departamento;
    }
    //Getters
    public String getNombre(){
        return nombre;
    }
    public String getApellido1(){return apellido1;}
    public String getApellido2(){return apellido2;}
    public String getCentro(){return centro;}
    public String getDepartamento (){return departamento;}
    //Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido1(String apellido1) {this.apellido1 = apellido1;}
    public void setApellido2(String apellido2){this.apellido2 =apellido2;}
    public void setCentro(String centro){this.centro = centro;}
    public void setDepartamento(String departamento){this.nombre = departamento;}
}
