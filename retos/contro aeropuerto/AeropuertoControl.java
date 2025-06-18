import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class AeropuertoControl {

    public CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(2, 4));
                boolean disponible = ThreadLocalRandom.current().nextInt(100) < 80; // 80% de probabilidad
                System.out.println("Pista disponible: " + disponible);
                return disponible;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar la pista");
            }
        });
    }

    public CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(2, 4));
                boolean favorable = ThreadLocalRandom.current().nextInt(100) < 85; // 85% de probabilidad
                System.out.println("Clima favorable: " + favorable);
                return favorable;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar el clima");
            }
        });
    }

    public CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(2, 4));
                boolean despejado = ThreadLocalRandom.current().nextInt(100) < 90; // 90% de probabilidad
                System.out.println("Tráfico aéreo despejado: " + despejado);
                return despejado;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar el tráfico aéreo");
            }
        });
    }

    public CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(2, 4));
                boolean disponible = ThreadLocalRandom.current().nextInt(100) < 95; // 95% de probabilidad
                System.out.println("Personal en tierra disponible: " + disponible);
                return disponible;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar el personal en tierra");
            }
        });
    }

    public void procesarAterrizaje() {
        System.out.println("Verificando condiciones para aterrizaje...");

        CompletableFuture<Boolean> pista = verificarPista().exceptionally(ex -> {
            System.out.println("Error en verificación de pista: " + ex.getMessage());
            return false;
        });

        CompletableFuture<Boolean> clima = verificarClima().exceptionally(ex -> {
            System.out.println("Error en verificación de clima: " + ex.getMessage());
            return false;
        });

        CompletableFuture<Boolean> trafico = verificarTraficoAereo().exceptionally(ex -> {
            System.out.println("Error en verificación de tráfico aéreo: " + ex.getMessage());
            return false;
        });

        CompletableFuture<Boolean> personal = verificarPersonalTierra().exceptionally(ex -> {
            System.out.println("Error en verificación de personal en tierra: " + ex.getMessage());
            return false;
        });

        CompletableFuture.allOf(pista, clima, trafico, personal).thenRun(() -> {
            try {
                boolean condicionesOptimas =
                    pista.get() && clima.get() && trafico.get() && personal.get();

                if (condicionesOptimas) {
                    System.out.println("Aterrizaje autorizado: todas las condiciones óptimas.");
                } else {
                    System.out.println("Aterrizaje denegado: condiciones no óptimas.");
                }
            } catch (Exception e) {
                System.out.println("Error al procesar aterrizaje: " + e.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        AeropuertoControl control = new AeropuertoControl();
        control.procesarAterrizaje();

        // Esperar para que los procesos asincrónicos terminen
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
