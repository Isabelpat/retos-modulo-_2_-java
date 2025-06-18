import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MeridianPrime {

    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger semaforoRojoConsecutivo = new AtomicInteger(0);

        Flux<String> sensoresTrafico = Flux.interval(Duration.ofMillis(500))
                .map(i -> random.nextInt(101)) // 0-100%
                .filter(congestion -> congestion > 70)
                .map(congestion -> "Alerta: Congestión del " + congestion + "% en Avenida Solar")
                .doOnNext(System.out::println)
                .onBackpressureBuffer() // Simula contrapresión
                .subscribeOn(Schedulers.parallel());

        Flux<String> contaminacionAire = Flux.interval(Duration.ofMillis(600))
                .map(i -> 10 + random.nextInt(91)) // 10-100 ug/m3
                .filter(pm -> pm > 50)
                .map(pm -> "Alerta: Contaminación alta (PM2.5: " + pm + " ug/m3)")
                .doOnNext(System.out::println)
                .subscribeOn(Schedulers.parallel());

        Flux<String> accidentesViales = Flux.interval(Duration.ofMillis(800))
                .map(i -> {
                    String[] prioridades = {"Baja", "Media", "Alta"};
                    return prioridades[random.nextInt(3)];
                })
                .filter(p -> p.equals("Alta"))
                .map(p -> "Emergencia vial: Accidente con prioridad " + p)
                .doOnNext(System.out::println)
                .subscribeOn(Schedulers.parallel());

        Flux<String> trenesMaglev = Flux.interval(Duration.ofMillis(700))
                .map(i -> random.nextInt(11)) // 0-10 minutos
                .filter(min -> min > 5)
                .map(min -> "Tren maglev con retraso crítico: " + min + " minutos")
                .delayElements(Duration.ofMillis(300)) // Simula contrapresión
                .doOnNext(System.out::println)
                .subscribeOn(Schedulers.parallel());

        Flux<String> semaforos = Flux.interval(Duration.ofMillis(400))
                .map(i -> {
                    String[] estados = {"Verde", "Amarillo", "Rojo"};
                    return estados[random.nextInt(3)];
                })
                .map(estado -> {
                    if (estado.equals("Rojo")) {
                        int count = semaforoRojoConsecutivo.incrementAndGet();
                        if (count >= 3) {
                            semaforoRojoConsecutivo.set(0); // reinicia
                            return "Semáforo en Rojo detectado 3 veces seguidas en cruce Norte";
                        }
                        return null;
                    } else {
                        semaforoRojoConsecutivo.set(0);
                        return null;
                    }
                })
                .filter(msg -> msg != null)
                .doOnNext(System.out::println)
                .subscribeOn(Schedulers.parallel());

        // Mezcla todos los flujos en uno
        Flux<String> eventosCriticos = Flux.merge(
                sensoresTrafico,
                contaminacionAire,
                accidentesViales,
                trenesMaglev,
                semaforos
        );

        // Ventana de 2 segundos para detectar alertas simultáneas
        eventosCriticos.buffer(Duration.ofSeconds(2))
                .filter(lista -> lista.size() >= 3)
                .map(lista -> "Alerta global: Múltiples eventos críticos detectados en Meridian Prime")
                .doOnNext(System.out::println)
                .subscribe();

        // Mantiene la app activa 20 segundos
        Thread.sleep(20000);
    }
}
