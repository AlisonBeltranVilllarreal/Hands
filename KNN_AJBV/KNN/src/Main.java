import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\aliso\\OneDrive\\Documents\\7mo Semestre\\Codigos\\KNN_AJBV\\KNN\\fake_bills.csv";
        List<DataPoint> dataPoints = DataSet.loadData(filePath);
        
        // Crear una instancia de KNN con el valor de k (por ejemplo, k = 3)
        KNN knn = new KNN(3);
        
        // Punto de prueba para predecir su clase
        double[] detallesPrueba = {171.50, 104.00, 104.50, 4.00, 3.00, 113.00};  // ejemplo de datos de entrada
        DataPoint puntoPrueba = new DataPoint(detallesPrueba, "");  // No se necesita etiqueta para el punto de prueba
        
        // Realizar la predicción
        String clasePredicha = knn.predecirClase(dataPoints, puntoPrueba);
        System.out.println("La clase predicha para el punto de prueba es: " + clasePredicha);
        
   
    }
}
