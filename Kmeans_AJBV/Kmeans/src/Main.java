import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList; 
import java.util.List; 

public class Main { 
    public static void main(String[] args) { 
        // Ruta del archivo de datos que se va a cargar
        String filename = "C:\\Users\\aliso\\OneDrive\\Documents\\7mo Semestre\\Codigos\\Kmeans_AJBV\\Kmeans\\dataset.csv";
        
        // Llama al método para cargar puntos desde el archivo y los guarda en la lista 'points'
        List<Point> points = loadPointsFromFile(filename);

        // Llama al método ElbowMethod para encontrar el número óptimo de clústers usando los puntos cargados
        int optimalClusters = ElbowMethod.findOptimalClusters(points, 5);
        System.out.println("Número óptimo de clústers sugerido: " + optimalClusters);
        
        // Crea una instancia de KMeans con el número óptimo de clústers y los puntos
        KMeans kmeans = new KMeans(optimalClusters, points, false); // No imprime
        // Ejecuta el algoritmo KMeans
        kmeans.run();
        // Imprime los resultados de la clasificación de puntos en clústers
        kmeans.printResults();
    }

    // Método para cargar puntos desde un archivo CSV
    public static List<Point> loadPointsFromFile(String filename) {
        List<Point> points = new ArrayList<>(); // Crea una lista vacía para almacenar los puntos

        // Bloque try-with-resources para abrir el archivo y asegurarse de que se cierre automáticamente
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line; // Variable para almacenar cada línea leída
            int count = 0; // Contador para controlar la cantidad de puntos a cargar
            
            // Lee cada línea del archivo mientras no sea null y el contador sea menor que 10
            while ((line = br.readLine()) != null && count < 100) {
                // Divide la línea en partes usando la coma como separador
                String[] values = line.split(",");
                // Crea un arreglo de double para almacenar las coordenadas del punto
                double[] coordinates = new double[values.length];
                
                // Convierte cada valor en la línea a double y lo almacena en el arreglo de coordenadas
                for (int i = 0; i < values.length; i++) {
                    coordinates[i] = Double.parseDouble(values[i].trim()); // Convierte y elimina espacios en blanco
                }
                
                // Crea un objeto Point con las coordenadas y lo agrega a la lista de puntos
                points.add(new Point(coordinates));
                count++; // Incrementa el contador para controlar que se lean solo 10 puntos
            }
        } catch (IOException e) { // Captura cualquier excepción que ocurra al leer el archivo
            // Imprime un mensaje de error si ocurre un problema al leer el archivo
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        
        return points; // Devuelve la lista de puntos cargados desde el archivo
    }
}