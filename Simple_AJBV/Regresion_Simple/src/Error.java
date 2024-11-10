import java.util.List;


public class Error {

    public static double R2(List<Double> x, List<Double> y, double B0, double B1) {
        double sumaSST = 0; // Suma total de cuadrados
        double sumaCuadradosResiduales = 0;
        double mediaY = 0;

        // Calcular la media de Y
        for (int i = 0; i < y.size(); i++) {
            mediaY += y.get(i);
        }
        mediaY /= y.size();


        for (int i = 0; i < y.size(); i++) {
            // Suma total de cuadrados
            sumaSST += Math.pow(y.get(i) - mediaY, 2);
            // Suma de cuadrados residuales
            sumaCuadradosResiduales += Math.pow(y.get(i) - (B0 + B1 * x.get(i)), 2);
        }

        return (1 - (sumaCuadradosResiduales / sumaSST))*100;
    }

    public static double correlacion(double n, double sumX, double sumXY, double sumY, double sumX2, double sumY2){
        double Correlacion=(n * sumXY - sumX * sumY) / Math.sqrt((n * sumX2 - sumX * sumX) * (n * sumY2 - sumY * sumY));
        return Correlacion;
    }
}
