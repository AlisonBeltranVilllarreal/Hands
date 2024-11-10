import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class KNN {

    private int k;  // Número de vecinos más cercanos que se utilizarán en la predicción.

    // Constructor que recibe el número de vecinos más cercanos k.
    public KNN(int k) {
        this.k = k; // Asigna el valor de k al atributo de la clase.
    }

    // Método para calcular la distancia euclidiana entre dos puntos de datos.
    private double calcularDistancia(double[] detalles1, double[] detalles2) {
        double suma = 0.0; // Variable para acumular la suma de las diferencias al cuadrado.
        for (int i = 0; i < detalles1.length; i++) { // Itera sobre cada dimensión de los detalles.
            suma += Math.pow(detalles1[i] - detalles2[i], 2); // Suma el cuadrado de la diferencia de cada dimensión.
        }
        return Math.sqrt(suma); // Devuelve la raíz cuadrada de la suma para obtener la distancia euclidiana.
    }

    // Método para obtener los k vecinos más cercanos al punto dado.
    private List<DataPoint> obtenerVecinosMasCercanos(List<DataPoint> dataPoints, DataPoint nuevoPunto) {
        List<DataPoint> vecinos = new ArrayList<>(dataPoints); // Copia la lista de puntos de datos.

        // Ordena la lista de puntos por la distancia al nuevo punto.
        Collections.sort(vecinos, Comparator.comparingDouble(dp -> calcularDistancia(dp.getDetalles(), nuevoPunto.getDetalles())));

        // Retorna los k vecinos más cercanos después de ordenar.
        return vecinos.subList(0, k);
    }

    // Método para predecir la clase del nuevo punto de datos.
    public String predecirClase(List<DataPoint> dataPoints, DataPoint nuevoPunto) {
        List<DataPoint> vecinosCercanos = obtenerVecinosMasCercanos(dataPoints, nuevoPunto); // Obtiene los k vecinos más cercanos.

        // Contadores para las etiquetas de los vecinos.
        int countTrue = 0;
        int countFalse = 0;
        for (DataPoint vecino : vecinosCercanos) { // Recorre cada vecino cercano.
            if (vecino.getLabel().equals("True")) { // Si la etiqueta del vecino es "True".
                countTrue++; // Incrementa el contador de "True".
            } else {
                countFalse++; // Incrementa el contador de "False".
            }
        }

        // Retorna la clase con la mayor cantidad de vecinos (True o False).
        return (countTrue > countFalse) ? "True" : "False";
    }
}
