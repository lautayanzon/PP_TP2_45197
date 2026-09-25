package modelo;

import java.io.Serializable;

public class Sala implements Serializable {

    private int id;
    private String nombre;

    public Sala(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "modelo.Sala{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    void mostrarDatos() {
        System.out.println(this.nombre);
    }
}
