public class Point {
    private double[] coordinates; // Array para almacenar las coordenadas del punto en un espacio n-dimensional.
    
    // Constructor que recibe un array de coordenadas para inicializar el punto
    public Point(double[] coordinates) {
        this.coordinates = coordinates; // Asigna las coordenadas pasadas al atributo coordinates.
    }

    // Método para obtener las coordenadas del punto
    public double[] getCoordinates() {
        return coordinates;
    }

    // Método para calcular la distancia euclidiana entre este punto y otro punto dado
    public double distance(Point other) {
        double sum = 0; // Variable para acumular la suma de las diferencias al cuadrado entre coordenadas.
        for (int i = 0; i < coordinates.length; i++) { // Recorre cada coordenada del punto.
            sum += Math.pow(coordinates[i] - other.coordinates[i], 2); 
            // Calcula la diferencia entre las coordenadas correspondientes, eleva al cuadrado y suma al total.
        }
        return Math.sqrt(sum); // Devuelve la raíz cuadrada de la suma, que es la distancia euclidiana.
    }
}

