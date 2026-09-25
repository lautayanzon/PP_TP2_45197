package modelo.actividades;

import modelo.Estudiante;
import modelo.certificacion.Certificable;

public class Taller extends Actividad implements Certificable {

    private boolean requiereNotebook;

    public Taller(int id, String titulo, int cupoMaximo, boolean requiereNotebook) {
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    @Override
    public double calcularCostoMateriales() {
        if (requiereNotebook) {
            return 5000;
        } else {
            return 2000;
        }
    }

    @Override
    public String getTipo() {
        return "modelo.actividades.Taller";
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return "Certifico que el estudiante: " + estudiante.getNombre() + "curso correctamente la actividad: " + this.getTipo();
    }
}
