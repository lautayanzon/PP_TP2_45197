package modelo;

import modelo.actividades.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario implements Serializable{

    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos = 0;

    Sala sala;
    List<Actividad> actividades = new ArrayList<>();

    public EventoUniversitario(String id, String titulo, double costoBase) {
        this.id = id;
        this.titulo = titulo;
        if (costoBase > 0) {
            this.costoBase = costoBase;
            this.gratuito = false;
        }
        cantidadEventos++;
    }

    public EventoUniversitario(String id, String titulo, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.gratuito = gratuito;
        if (gratuito) {
            this.costoBase = 0;
        }
        cantidadEventos++;
    }

    public EventoUniversitario(EventoUniversitario otro) {
        this.id = otro.id;
        this.titulo = otro.titulo;
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
        cantidadEventos++;
    }

    double calcularCostoEstimado() {
        if (this.gratuito) {
            return 0;
        }
        return (this.costoBase + calcularCostoMateriales(this.actividades)) * 1.21;
    }

    public void asignarSala(Sala sala) {
        this.sala = sala;
    }

    public void crearActividad(int id, String titulo, int cupo, String tipo) {
        switch (tipo) {
            case "Taller":
                this.actividades.add(new Taller(id, titulo, cupo, false));
                break;
            case "Charla":
                this.actividades.add(new Charla(id, titulo, cupo, "Manuel"));
                break;
            case "Curso":
                this.actividades.add(new Curso(id, titulo, cupo, 1));
                break;
        }
    }

    public List<Actividad> getActividad() {
        return actividades;
    }

    public void mostrarDatos() {
        System.out.println("------------");
        System.out.println("Evento " + this.id);
        System.out.println("Titulo: " + this.titulo);
        System.out.println("Costo Base: " + this.costoBase);
        System.out.println("Es gratuito? " + this.gratuito);
        System.out.print("Que sala tiene asignada? ");
        this.sala.mostrarDatos();
        System.out.println("Costo estimado: " + this.calcularCostoEstimado());
        for (Actividad actividad : this.actividades) {
            actividad.mostrarInscripciones();
        }
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }

    public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo) {
        List<T> resultado = new ArrayList<>();
        for (Actividad actividad : this.actividades) {
            if (tipo.isInstance(actividad)) {
                resultado.add(tipo.cast(actividad));
            }
        }
        return resultado;
    }

    public double calcularCostoMateriales(List<? extends Actividad> actividades) {
        double total = 0;
        for (Actividad actividad : actividades) {
            total += actividad.calcularCostoMateriales();
        }
        return total;
    }

    public boolean persistirEvento() {
        try {
            FileOutputStream fos = new FileOutputStream(id + ".dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo.");
            return false;
        } catch (IOException e) {
            System.out.println("Se produjo un error al persistir el evento: " + e);
            return false;
        }
    }

    public static EventoUniversitario recuperarEvento(String id) {
        EventoUniversitario evento = null;
        try {
            FileInputStream fis = new FileInputStream(id + ".dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            evento = (EventoUniversitario) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo.");
        } catch (IOException e) {
            System.out.println("Se produjo un error de E/S.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error de conversion de clase.");
        }
        return evento;
    }
}
