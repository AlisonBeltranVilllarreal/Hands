public class DataPoint {
    private double[] detalles; // Array para almacenar detalles numéricos del punto de datos.
    private String label; // Etiqueta para identificar o clasificar el punto (por ejemplo, una categoría o nombre).

    // Constructor que recibe un array de detalles y una etiqueta para inicializar el punto de datos
    public DataPoint(double[] detalles, String label) {
        this.detalles = detalles; // Asigna el array de detalles al atributo detalles.
        this.label = label; // Asigna la etiqueta al atributo label.
    }

    // Método para obtener los detalles numéricos del punto de datos
    public double[] getDetalles() {
        return detalles;
    }

    // Método para obtener la etiqueta del punto de datos
    public String getLabel() {
        return label;
    }
}
