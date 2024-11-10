import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataSet {
    private static List<String> headers = new ArrayList<>(); // Lista estática para almacenar los encabezados del archivo CSV.

    // Método que carga los datos de un archivo CSV en una lista de objetos DataPoint
    public static List<DataPoint> loadData(String filePath) {
        List<DataPoint> dataPoints = new ArrayList<>(); // Lista para almacenar los puntos de datos que se cargarán.
    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { // Abre el archivo CSV para lectura.
            String line; // Variable para almacenar cada línea del archivo.
    
            // Leer y almacenar los encabezados
            if ((line = br.readLine()) != null) { // Lee la primera línea (encabezados) del archivo.
                String[] headerValues = line.split(";"); // Divide los encabezados por el delimitador ';'.
                for (String header : headerValues) {
                    headers.add(header.trim()); // Elimina espacios en blanco y añade cada encabezado a la lista.
                }
            }
    
            // Leer cada línea de datos
            while ((line = br.readLine()) != null) { // Lee cada línea de datos del archivo.
                // Verificar si la línea está vacía o contiene solo espacios
                if (line.trim().isEmpty()) {
                    continue; // Salta las líneas vacías.
                }
    
                String[] values = line.split(";"); // Divide la línea de datos por ';' para obtener los valores.
    
                // Asume que la primera columna es la etiqueta y el resto son detalles
                double[] detalles = new double[values.length - 1]; // Array para almacenar los detalles numéricos.
    
                // Leer los detalles y manejar cadenas vacías
                for (int i = 1; i < values.length; i++) { // Empieza en 1 para omitir la primera columna (etiqueta).
                    String value = values[i].trim(); // Quita espacios en blanco del valor.
                    if (!value.isEmpty()) {
                        detalles[i - 1] = Double.parseDouble(value); // Convierte el valor a double y lo almacena.
                    } else {
                        // Maneja el caso donde el valor está vacío.
                        detalles[i - 1] = 0.0; // Asigna 0.0 como valor predeterminado en caso de valor vacío.
                    }
                }
    
                String label = values[0].trim(); // La primera columna es la etiqueta; quita espacios en blanco.
    
                DataPoint dataPoint = new DataPoint(detalles, label); // Crea un objeto DataPoint con los detalles y la etiqueta.
                dataPoints.add(dataPoint); // Añade el DataPoint a la lista de puntos de datos.
            }
        } catch (IOException e) {
            e.printStackTrace(); // Muestra un error si ocurre un problema al leer el archivo.
        }
    
        return dataPoints; // Devuelve la lista de puntos de datos.
    }
    
    // Método para imprimir los datos cargados, incluyendo encabezados
    public static void printData(List<DataPoint> dataPoints) {
        if (dataPoints.isEmpty()) { // Verifica si no se han cargado datos.
            System.out.println("No se han cargado datos.");
            return; // Termina el método si la lista está vacía.
        }

        // Imprimir encabezado
        for (String header : headers) {
            System.out.print(header + "\t"); // Imprime cada encabezado separado por tabulaciones.
        }
        System.out.println(); // Salta a la siguiente línea después de los encabezados.

        // Imprimir los valores de cada DataPoint
        for (DataPoint point : dataPoints) {
            System.out.print(point.getLabel() + "\t"); // Imprime la etiqueta del punto de datos.
            for (double detalle : point.getDetalles()) {
                System.out.print(detalle + "\t"); // Imprime cada detalle numérico separado por tabulaciones.
            }
            System.out.println(); // Salta a la siguiente línea después de imprimir todos los detalles del punto.
        }
    }
}
