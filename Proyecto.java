import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Proyecto {
    private int id;
    private String nombre;
    private double presupuesto;
    private Map<Integer, Tarea> tareas;

    public Proyecto(int id, String nombre, double presupuesto) {
        this.id = id;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.tareas = new HashMap<>();
    }

    public void agregarTarea(Tarea tarea) {
        tareas.put(tarea.getId(), tarea);
    }

    public void eliminarTarea(int idTarea) {
        tareas.remove(idTarea);
    }

    public Tarea buscarTarea(int idTarea) {
        return tareas.get(idTarea);
    }

    public Collection<Tarea> getTareas() {
        return tareas.values();
    }

    @Override
    public String toString() {
        return "Proyecto: " + nombre + ", Presupuesto: " + presupuesto + ", Tareas: " + tareas.size();
    }
}

