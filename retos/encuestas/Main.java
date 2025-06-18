import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear datos de ejemplo
        Encuesta e1 = new Encuesta("Juan", "El servicio fue lento", 2);
        Encuesta e2 = new Encuesta("Ana", null, 3);
        Encuesta e3 = new Encuesta("Luis", "Falta limpieza en las salas", 1);
        Encuesta e4 = new Encuesta("Marta", "Muy buen trato", 5);
        Encuesta e5 = new Encuesta("Pedro", null, 4);

        Sucursal s1 = new Sucursal("Sucursal Norte", Arrays.asList(e1, e2));
        Sucursal s2 = new Sucursal("Sucursal Sur", Arrays.asList(e3, e4, e5));

        List<Sucursal> sucursales = Arrays.asList(s1, s2);

        // Procesar encuestas
        sucursales.stream()
            .flatMap(sucursal -> 
                sucursal.getEncuestas().stream()
                    .filter(encuesta -> encuesta.getCalificacion() <= 3)
                    .flatMap(encuesta -> 
                        encuesta.getComentario()
                            .map(comentario -> 
                                // crear mensaje con el nombre de la sucursal y comentario
                                "Sucursal " + sucursal.getNombre() + ": Seguimiento a paciente con comentario: \"" + comentario + "\""
                            )
                            .stream() // convertir Optional a Stream para flatMap
                    )
            )
            .forEach(System.out::println);
    }
}

