import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

// Clase que representa un recurso médico crítico
class RecursoMedico {
    private final String nombre;
    private final ReentrantLock lock = new ReentrantLock();

    public RecursoMedico(String nombre) {
        this.nombre = nombre;
    }

    public void usar(String profesional) {
        lock.lock();
        try {
            System.out.println("👩‍⚕️ " + profesional + " ha ingresado a " + nombre);
            Thread.sleep(1000); // Simula uso del recurso
            System.out.println("✅ " + profesional + " ha salido de " + nombre);
        } catch (InterruptedException e) {
            System.out.println("⚠️ " + profesional + " fue interrumpido.");
        } finally {
            lock.unlock();
        }
    }
}

// Clase principal que ejecuta la simulación
public class AccesoRecursoMedico {
    public static void main(String[] args) {
        System.out.println("🏥 Iniciando acceso a la Sala de cirugía...");

        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");

        // Crear tareas para profesionales médicos usando Runnable
        Runnable drSanchez = () -> salaCirugia.usar("Dra. Sánchez");
        Runnable drGomez = () -> salaCirugia.usar("Dr. Gómez");
        Runnable enfermeraLopez = () -> salaCirugia.usar("Enfermera López");
        Runnable drRamirez = () -> salaCirugia.usar("Dr. Ramírez");

        // ExecutorService para manejar hilos concurrentes
        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.execute(drSanchez);
        executor.execute(drGomez);
        executor.execute(enfermeraLopez);
        executor.execute(drRamirez);

        executor.shutdown(); // No se aceptan nuevas tareas
    }
}

