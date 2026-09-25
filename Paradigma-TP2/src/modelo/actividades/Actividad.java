package modelo.actividades;

import excepciones.CupoExcedidoException;
import modelo.Estudiante;
import modelo.Inscripcion;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Actividad implements Serializable {

    private int id;
    private String titulo;
    private int cupoMaximo;
    public final int CUPO_MINIMO = 1;
    private List<Inscripcion> inscripciones = new ArrayList<>();

    public Actividad(int id, String titulo, int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
        this.titulo = titulo;
        this.id = id;
    }

    public Inscripcion inscribir(Estudiante estudiante) throws CupoExcedidoException {

        if (inscripciones.size() >= cupoMaximo) {
            throw new CupoExcedidoException("No se puede inscribir. Cupo maximo: " + cupoMaximo);
        }
        String estado = "Regular";
        LocalDate date = LocalDate.now();
        Inscripcion inscripcion = new Inscripcion(date, estado, estudiante, this);
        inscripciones.add(inscripcion);
        return inscripcion;
    }

    public void mostrarInscripciones() {
        System.out.println("Lista de Inscripciones");
        for (Inscripcion inscripcion : this.inscripciones) {
            inscripcion.mostrarDatos();
        }
        System.out.println("-------");
    }

    public final void mostrarIdentificacion() {
        System.out.println("Tipo:" + this.getTipo());
    }

    public abstract double calcularCostoMateriales();

    public abstract String getTipo();

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    @Override
    public String toString() {
        return "modelo.actividades.Actividad{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", cupoMaximo=" + cupoMaximo +
                '}';
    }
}
