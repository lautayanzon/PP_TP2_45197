package modelo;

import java.io.Serializable;

public class Estudiante implements Serializable {

    private String legajo;
    private String nombre;

    public Estudiante(String legajo, String nombre) {
        this.legajo = legajo;
        this.nombre = nombre;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "modelo.Estudiante{" +
                "legajo='" + legajo + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    void mostrarDatos() {
        System.out.println("modelo.Estudiante");
        System.out.println("[");
        System.out.println("Legajo: " + this.legajo);
        System.out.println("Nombre: " + this.nombre);
        System.out.println("]");
    }
}
