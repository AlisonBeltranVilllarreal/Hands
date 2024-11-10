import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class KMeans {
    private int numClusters; // Número de clústeres a crear.
    private List<Cluster> clusters; // Lista de clústeres, cada uno con su centroide y puntos asignados.
    private List<Point> points; // Lista de puntos a clasificar en los clústeres.
    private boolean shouldPrint; // Indica si se debe imprimir el estado durante la ejecución.

    // Constructor de la clase KMeans
    public KMeans(int numClusters, List<Point> points, boolean shouldPrint) {
        this.numClusters = numClusters; // Inicializa el número de clústeres.
        this.points = points; // Asigna la lista de puntos.
        this.shouldPrint = shouldPrint; // Asigna el valor para mostrar o no la salida.
        this.clusters = new ArrayList<>(numClusters); // Inicializa la lista de clústeres.
        initializeClusters(); // Llama al método para inicializar los clústeres.
    }

    // Método que devuelve la lista de clústeres
    public List<Cluster> getClusters() {
        return clusters;
    }

    // Método que asigna una nueva lista de clústeres
    public void setClusters(List<Cluster> clusters) {
        this.clusters = clusters;
    }

    // Método privado que inicializa los clústeres con centros aleatorios
    private void initializeClusters() {
        Random rand = new Random(); // Crea un objeto Random para seleccionar puntos al azar.
        for (int i = 0; i < numClusters; i++) {
            getClusters().add(new Cluster(points.get(rand.nextInt(points.size())))); 
            // Crea un nuevo clúster con un centroide aleatorio y lo añade a la lista.
        }
    }

    // Método principal para ejecutar el algoritmo KMeans
    public void run() {
        boolean centroidsChanged; // Indica si algún centroide ha cambiado.
        int iteration = 0; // Contador de iteraciones.
        int maxIterations = 10; // Límite de iteraciones para evitar bucles infinitos.

        do {
            if (shouldPrint) {
                System.out.println("Iteración " + iteration); // Imprime la iteración actual si shouldPrint es true.
            }
            assignPointsToClusters(); // Asigna cada punto al clúster más cercano.
            centroidsChanged = updateCentroids(); // Actualiza los centroides y verifica si cambiaron.
            if (shouldPrint) {
                printClusterState(); // Imprime el estado actual de los clústeres.
            }

            iteration++; // Incrementa el contador de iteraciones.
            if (iteration >= maxIterations) { // Verifica si se alcanzó el máximo de iteraciones.
                if (shouldPrint) {
                    System.out.println("Máximo de iteraciones alcanzado.");
                }
                break; // Finaliza el bucle si se alcanza el máximo de iteraciones.
            }
        } while (centroidsChanged); // Repite mientras algún centroide haya cambiado.

        if (shouldPrint) {
            System.out.println("Ejecución de KMeans finalizada."); // Imprime mensaje final.
        }
    }

    // Método para asignar cada punto al clúster más cercano
    private void assignPointsToClusters() {
        for (Cluster cluster : getClusters()) {
            cluster.clearPoints(); // Limpia los puntos en cada clúster para reasignarlos.
        }

        for (Point point : points) { // Recorre todos los puntos.
            Cluster closestCluster = getClusters().get(0); // Asume que el primer clúster es el más cercano.
            double minDistance = point.distance(closestCluster.getCentroid()); // Calcula la distancia al primer centroide.

            for (Cluster cluster : getClusters()) { // Compara el punto con cada clúster.
                double distance = point.distance(cluster.getCentroid()); // Calcula la distancia al centroide actual.
                if (distance < minDistance) { // Si la distancia es menor que la mínima encontrada.
                    minDistance = distance; // Actualiza la distancia mínima.
                    closestCluster = cluster; // Actualiza el clúster más cercano.
                }
            }

            closestCluster.addPoint(point); // Añade el punto al clúster más cercano.
        }
    }

    // Método para actualizar los centroides de los clústeres y verificar si cambiaron
    private boolean updateCentroids() {
        boolean changed = false; // Variable para verificar si algún centroide cambió.
        for (Cluster cluster : getClusters()) {
            Point oldCentroid = cluster.getCentroid(); // Guarda el centroide anterior.
            cluster.updateCentroid(); // Calcula el nuevo centroide.
            if (!equals(oldCentroid, cluster.getCentroid())) { // Compara el nuevo centroide con el anterior.
                changed = true; // Marca que hubo un cambio.
            }
        }
        return changed; // Retorna true si algún centroide cambió.
    }

    // Método auxiliar para comparar si dos puntos son iguales en sus coordenadas
    private boolean equals(Point p1, Point p2) {
        double[] coords1 = p1.getCoordinates(); // Obtiene las coordenadas del primer punto.
        double[] coords2 = p2.getCoordinates(); // Obtiene las coordenadas del segundo punto.
        for (int i = 0; i < coords1.length; i++) { // Recorre las coordenadas.
            if (coords1[i] != coords2[i]) return false; // Retorna false si alguna coordenada es diferente.
        }
        return true; // Retorna true si todas las coordenadas son iguales.
    }

    // Método para imprimir los resultados finales de los clústeres
    public void printResults() {
        System.out.println("Resultados de KMeans:");
        for (int i = 0; i < getClusters().size(); i++) { // Itera sobre los clústeres.
            System.out.println("Cluster " + (i + 1) + ":");
            System.out.println("  Centroide: " + java.util.Arrays.toString(getClusters().get(i).getCentroid().getCoordinates())); // Imprime el centroide.
            System.out.println("  Puntos:");
            for (Point point : getClusters().get(i).getPoints()) { // Itera sobre los puntos de cada clúster.
                System.out.println("    - " + java.util.Arrays.toString(point.getCoordinates())); // Imprime las coordenadas del punto.
            }
            System.out.println(); // Línea en blanco para separar los clústeres.
        }
    }

    // Método para imprimir el estado de los clústeres en cada iteración
    private void printClusterState() {
        for (int i = 0; i < getClusters().size(); i++) { // Itera sobre los clústeres.
            System.out.println("Centroide del Cluster " + i + ": " + 
                java.util.Arrays.toString(getClusters().get(i).getCentroid().getCoordinates())); // Imprime el centroide actual.
            System.out.println("Puntos en el Cluster " + i + ":");
            for (Point point : getClusters().get(i).getPoints()) { // Itera sobre los puntos en el clúster.
                System.out.println("\t" + java.util.Arrays.toString(point.getCoordinates())); // Imprime las coordenadas del punto.
            }
        }
    }
}
