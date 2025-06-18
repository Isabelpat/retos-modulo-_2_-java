import java.util.List;
import java.util.Optional;

public class ConfirmacionPedidos {
    public static void main(String[] args) {
        List<Pedido> pedidos = List.of(
            new Pedido("Ana", "domicilio", "555-1234"),
            new Pedido("Luis", "local", "555-0000"),
            new Pedido("Marta", "domicilio", null),
            new Pedido("Carlos", "domicilio", "555-5678"),
            new Pedido("Elena", "local", null)
        );

        pedidos.stream()
            .filter(p -> p.getTipoEntrega().equalsIgnoreCase("domicilio"))
            .map(Pedido::getTelefono)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(telefono -> " Confirmación enviada al número: " + telefono)
            .forEach(System.out::println);
    }
}
