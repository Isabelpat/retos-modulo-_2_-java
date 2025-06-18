import java.util.concurrent.*;

// Sistema de Navegación
class SistemaNavegacion implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(1000);
        return "🛰️ Navegación: trayectoria corregida con éxito.";
    }
}

// Sistema de Soporte Vital
class SistemaSoporteVital implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(800);
        return "🧪 Soporte vital: presión y oxígeno dentro de parámetros normales.";
    }
}

// Sistema de Control Térmico
class SistemaControlTermico implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(600);
        return "Control térmico: temperatura estable (22°C).";
    }
}

// Sistema de Comunicaciones
class SistemaComunicaciones implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(500);
        return "📡 Comunicaciones: enlace con estación terrestre establecido.";
    }
}

// Clase principal
public class SimulacionMisionEspacial {
    public static void main(String[] args) {
        System.out.println(" Simulación de misión espacial iniciada...");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Future<String> nav = executor.submit(new SistemaNavegacion());
        Future<String> vital = executor.submit(new SistemaSoporteVital());
        Future<String> termico = executor.submit(new SistemaControlTermico());
        Future<String> comms = executor.submit(new SistemaComunicaciones());

        try {
            System.out.println(comms.get());
            System.out.println(vital.get());
            System.out.println(termico.get());
            System.out.println(nav.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        System.out.println(" Todos los sistemas reportan estado operativo.");
    }
}
