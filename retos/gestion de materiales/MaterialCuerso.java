public abstract class MaterialCuerso {
    protected String titulo;
    protected String autor;

    public MaterialCuerso(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public abstract void mostrarDetalle();
}
