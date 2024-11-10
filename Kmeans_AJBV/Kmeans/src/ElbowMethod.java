import java.util.ArrayList;
import java.util.List;
public class ElbowMethod {
    
    // Método que encuentra el número óptimo de clústeres utilizando el método del codo
    public static int findOptimalClusters(List<Point> points, int maxClusters) {
        List<Double> wcss = new ArrayList<>(); // Lista para almacenar la suma de las distancias al cuadrado dentro de los clústeres (WCSS) para cada cantidad de clústeres.
        
        for (int k = 1; k <= maxClusters; k++) { // Itera desde 1 hasta el número máximo de clústeres.
            KMeans kmeans = new KMeans(k, points, false); // Crea una instancia de KMeans con k clústeres y sin imprimir resultados.
            kmeans.run(); // Ejecuta el algoritmo KMeans.
            wcss.add(computeWCSS(kmeans)); // Calcula y almacena el WCSS para el valor actual de k.
        }
        
        return determineElbowPoint(wcss); // Determina y devuelve el punto del "codo" en la lista de WCSS.
    }

    // Método para calcular la suma de distancias al cuadrado (WCSS) de los puntos al centroide de su clúster
    private static double computeWCSS(KMeans kmeans) {
        double sum = 0; // Variable para acumular el WCSS.
        for (Cluster cluster : kmeans.getClusters()) { // Recorre cada clúster del modelo KMeans.
            for (Point point : cluster.getPoints()) { // Recorre cada punto en el clúster actual.
                sum += Math.pow(point.distance(cluster.getCentroid()), 2); // Suma la distancia al cuadrado del punto al centroide del clúster.
            }
        }
        return sum; // Devuelve el WCSS total para el modelo KMeans actual.
    }

    // Método para determinar el "punto del codo" en la lista de WCSS, donde la disminución en WCSS se estabiliza
    private static int determineElbowPoint(List<Double> wcss) {
        for (int i = 1; i < wcss.size() - 1; i++) { // Itera desde el segundo hasta el penúltimo elemento de WCSS.
            double diff1 = wcss.get(i - 1) - wcss.get(i); // Diferencia de WCSS entre el clúster actual y el anterior.
            double diff2 = wcss.get(i) - wcss.get(i + 1); // Diferencia de WCSS entre el clúster actual y el siguiente.
            if (Math.abs(diff1 - diff2) < 0.1) { // Si las diferencias consecutivas son aproximadamente iguales, indica el "codo".
                return i + 1; // Retorna el número óptimo de clústeres (k óptimo) como el índice + 1.
            }
        }
        return wcss.size(); // Retorna el número máximo de clústeres si no se encuentra el "codo".
    }
}
