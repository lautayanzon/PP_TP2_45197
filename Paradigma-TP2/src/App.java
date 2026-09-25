import excepciones.CupoExcedidoException;
import modelo.Estudiante;
import modelo.EventoUniversitario;
import modelo.Sala;
import modelo.actividades.Charla;
import modelo.actividades.Curso;
import modelo.actividades.Taller;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) throws CupoExcedidoException {

        List<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(new Estudiante("111", "Juan P."));
        estudiantes.add(new Estudiante("222", "Pedro M."));
        estudiantes.add(new Estudiante("333", "Miguel A."));

        EventoUniversitario evento1 = new EventoUniversitario("1", "Jornadas de Ingenieria", 25000);
        evento1.asignarSala(new Sala(1, "Sala Zoom"));
        evento1.crearActividad(1, "Salud", 10, "Charla");
        evento1.crearActividad(2, "Introduccion a Java", 20, "Charla");
        evento1.crearActividad(3, "Tecnologia", 15, "Taller");
        evento1.crearActividad(4, "Robotica", 12, "Taller");
        evento1.crearActividad(5, "Ciber Seguridad", 10, "Curso");

        EventoUniversitario evento2 = new EventoUniversitario("2", "Semana de la Ciencia", 18000);
        evento2.asignarSala(new Sala(2, "Aula Magna"));
        evento2.crearActividad(1, "IA aplicada", 30, "Charla");
        evento2.crearActividad(2, "Impresion 3D", 10, "Taller");
        evento2.crearActividad(3, "Python", 15, "Curso");
        evento2.crearActividad(4, "Bases de datos", 15, "Curso");

        try {
            evento1.getActividad().get(0).inscribir(estudiantes.get(0));
            evento1.getActividad().get(0).inscribir(estudiantes.get(1));
            evento1.getActividad().get(2).inscribir(estudiantes.get(1));
            evento1.getActividad().get(2).inscribir(estudiantes.get(2));
            evento1.getActividad().get(3).inscribir(estudiantes.get(0));
            evento1.getActividad().get(4).inscribir(estudiantes.get(1));
            evento1.getActividad().get(4).inscribir(estudiantes.get(2));

            evento2.getActividad().get(0).inscribir(estudiantes.get(0));
            evento2.getActividad().get(1).inscribir(estudiantes.get(2));
            evento2.getActividad().get(2).inscribir(estudiantes.get(1));
            evento2.getActividad().get(3).inscribir(estudiantes.get(0));
            evento2.getActividad().get(3).inscribir(estudiantes.get(2));
        } catch (CupoExcedidoException e) {
            System.out.println(e.getMessage());
        }

        mostrarFiltroYCostos("1 - Jornadas de Ingenieria", evento1);
        mostrarFiltroYCostos("2 - Semana de la Ciencia", evento2);

        boolean b = evento1.persistirEvento();
        EventoUniversitario eventoRecuperado = EventoUniversitario.recuperarEvento("1");
        eventoRecuperado.mostrarDatos();

        List<Taller> talleres = eventoRecuperado.filtrarActividadesPorTipo(Taller.class);
        System.out.println("Certificados Emitidos:");
        for (Taller taller : talleres) {
            System.out.println(taller.generarCertificado(estudiantes.get(1)));
        }

        System.out.println("Cantidad de Eventos: " + EventoUniversitario.getCantidadEventos());
        System.out.println("Evento persistido: " + b);
    }

    private static void mostrarFiltroYCostos(String nombreEvento, EventoUniversitario evento) {
        List<Charla> charlas = evento.filtrarActividadesPorTipo(Charla.class);
        List<Taller> talleres = evento.filtrarActividadesPorTipo(Taller.class);
        List<Curso> cursos = evento.filtrarActividadesPorTipo(Curso.class);

        System.out.println("============");
        System.out.println("Evento " + nombreEvento);
        System.out.println("Cantidad por tipo:");
        System.out.println("Charlas: " + charlas.size());
        System.out.println("Talleres: " + talleres.size());
        System.out.println("Cursos: " + cursos.size());

        System.out.println("Actividades tipadas:");
        for (Charla charla : charlas) {
            System.out.println("Charla - disertante: " + charla.getDisertante() + " | " + charla);
        }
        for (Taller taller : talleres) {
            System.out.println("Taller | " + taller);
        }
        for (Curso curso : cursos) {
            System.out.println("Curso - nivel: " + curso.getNivel() + " | " + curso);
        }

        System.out.println("Costo de materiales por tipo:");
        System.out.println("Charlas: " + evento.calcularCostoMateriales(charlas));
        System.out.println("Talleres: " + evento.calcularCostoMateriales(talleres));
        System.out.println("Cursos: " + evento.calcularCostoMateriales(cursos));
    }
}
