import java.util.ArrayList; 
import java.util.List; 

public class Cluster {
    private Point centroid; // Define un objeto Point que representa el centroide del clúster.
    private List<Point> points; // Declara una lista de puntos asociados al clúster.

    // Constructor que recibe un centroide inicial para el clúster
    public Cluster(Point centroid) {
        this.centroid = centroid; // Asigna el centroide inicial al atributo centroid.
        this.points = new ArrayList<>(); // Inicializa la lista de puntos como un ArrayList vacío.
    }

    // Método que devuelve el centroide actual del clúster
    public Point getCentroid() {
        return centroid;
    }

    // Método que devuelve la lista de puntos del clúster
    public List<Point> getPoints() {
        return points;
    }

    // Método que agrega un punto a la lista de puntos del clúster
    public void addPoint(Point point) {
        points.add(point); // Añade el punto dado a la lista de puntos.
    }

    // Método que elimina todos los puntos de la lista, útil para reiniciar el clúster
    public void clearPoints() {
        points.clear(); // Borra todos los elementos de la lista de puntos.
    }

    // Método que actualiza el centroide basado en el promedio de los puntos actuales
    public void updateCentroid() {
        int dimension = centroid.getCoordinates().length; // Obtiene la cantidad de dimensiones de las coordenadas del centroide.
        double[] newCoords = new double[dimension]; // Crea un array para almacenar las nuevas coordenadas del centroide.

        // Recorre cada punto del clúster y acumula las coordenadas en newCoords
        for (Point point : points) {
            for (int i = 0; i < dimension; i++) {
                newCoords[i] += point.getCoordinates()[i]; // Suma cada coordenada del punto actual al array newCoords.
            }
        }
        
        // Calcula el promedio de las coordenadas acumuladas dividiendo por el número de puntos
        for (int i = 0; i < dimension; i++) {
            newCoords[i] /= points.size(); // Divide cada valor en newCoords por la cantidad de puntos para obtener el promedio.
        }
        
        centroid = new Point(newCoords); // Crea un nuevo objeto Point con las coordenadas promedio y lo asigna como nuevo centroide.
    }
}
