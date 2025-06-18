import java.util.List;

public class GestionMateriales {

    public static void mostrarMateriales(List<? extends MaterialCuerso> lista) {
        System.out.println(" Materiales del curso:");
        for (MaterialCuerso material : lista) {
            material.mostrarDetalle();
        }
    }

    public static void contarDuracionVideos(List<? extends Video> lista) {
        int total = 0;
        for (Video video : lista) {
            total += video.getDuracion();
        }
        System.out.println(" Duración total de videos: " + total + " minutos");
    }

    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista) {
        System.out.println(" Marcando ejercicios como revisados:");
        for (Object obj : lista) {
            if (obj instanceof Ejercicio) {
                Ejercicio e = (Ejercicio) obj;
                e.marcarRevisado();
                System.out.println(" Ejercicio '" + e.titulo + "' revisado.");
            }
        }
    }
}
