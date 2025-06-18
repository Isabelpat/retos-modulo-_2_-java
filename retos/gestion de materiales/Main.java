import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Video> videos = new ArrayList<>();
        videos.add(new Video("Intro a Java", "Profe Cam", 10));
        videos.add(new Video("Genéricos en Java", "Profe Cam", 15));

        List<Articulo> articulos = new ArrayList<>();
        articulos.add(new Articulo("Conceptos básicos de OOP", "Profe Cam", 800));
        articulos.add(new Articulo("Principios SOLID", "Profe Cam", 1200));

        List<Ejercicio> ejercicios = new ArrayList<>();
        ejercicios.add(new Ejercicio("Ejercicio 1: Clases", "Profe Cam"));
        ejercicios.add(new Ejercicio("Ejercicio 2: Herencia", "Profe Cam"));

        // Mostrar todo
        GestionMateriales.mostrarMateriales(videos);
        GestionMateriales.mostrarMateriales(articulos);
        GestionMateriales.mostrarMateriales(ejercicios);

        // Duración total de videos
        GestionMateriales.contarDuracionVideos(videos);

        // Marcar ejercicios como revisados
        GestionMateriales.marcarEjerciciosRevisados(ejercicios);

        // Verificación final
        System.out.println("\n Estado actualizado de los ejercicios:");
        GestionMateriales.mostrarMateriales(ejercicios);
    }
}
