import java.util.List;

public class Error { 

    // Método estático para calcular el coeficiente de determinación R²
    public static double R2(List<Double> x, List<Double> y, double B0, double B1, double B2) {
        double ssTotal = 0.0; // Inicializa la suma total de cuadrados a 0
        double ssRes = 0.0; // Inicializa la suma de los cuadrados residuales a 0
        double yMean = Mate_Discreta.Suma_Y(y) / y.size(); // Calcula la media de los valores y
        
        for (int i = 0; i < x.size(); i++) { // Itera sobre los índices de la lista x
            double yi = y.get(i); // Obtiene el valor y correspondiente al índice i
            double yPred = B0 + B1 * x.get(i) + B2 * x.get(i) * x.get(i); // Calcula el valor predicho usando el modelo cuadrático
            ssTotal += Math.pow(yi - yMean, 2); // Calcula y acumula la suma total de cuadrados
            ssRes += Math.pow(yi - yPred, 2); // Calcula y acumula la suma de los cuadrados residuales
        }
        return 1 - (ssRes / ssTotal); // Calcula y devuelve el coeficiente de determinación R²
    }

    // Método estático para calcular la correlación entre dos listas de valores
    public static double correlacion(List<Double> x, List<Double> y) {
        // Implementar cálculo de correlación si es necesario
        return 0.0; // Retorna 0.0 por defecto, ya que no se ha implementado el cálculo
    }
}

