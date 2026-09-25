package modelo.certificacion;

import modelo.Estudiante;

public interface Certificable {

    public final String ENTIDAD_EMISORA = "Facultad";

    String generarCertificado(Estudiante estudiante);
}
