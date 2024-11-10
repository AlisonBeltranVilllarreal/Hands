import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Comparator;

public class Regresion_Cuadratica {
    private data_set dataset;
    ArrayList<data_set> Modelo = new ArrayList<>();
    private double B0 = 0.0;
    private double B1 = 0.0;
    private double B2 = 0.0;

    public Regresion_Cuadratica(data_set dataset) {
        this.dataset = dataset;
        this.formula();  // Primera ejecución de la fórmula completa con el dataset original.
    }

    // Método para calcular la regresión cuadrática
    public void formula() {
        List<Double> x = this.dataset.getX();
        List<Double> y = this.dataset.getY();
        // Realizar la regresión cuadrática usando todo el dataset
        calcularRegresion(x, y);
    }

    // Método de uso general para calcular la regresión cuadrática dado X e Y
    private void calcularRegresion(List<Double> x, List<Double> y) {
        int n = x.size();
        double SumaX = Mate_Discreta.Suma_X(x);
        double SumaX2 = Mate_Discreta.Suma_Xdos(x);
        double SumaX3 = Mate_Discreta.Suma_X3(x);
        double SumaX4 = Mate_Discreta.Suma_X4(x);
        double SumaY = Mate_Discreta.Suma_Y(y);
        double SumaXY = Mate_Discreta.Suma_XY(x, y);
        double SumaX2Y = Mate_Discreta.Suma_X2Y(x, y);

        // Resolver el sistema de ecuaciones para B0, B1, B2
        double[][] A = {
                {n, SumaX, SumaX2},
                {SumaX, SumaX2, SumaX3},
                {SumaX2, SumaX3, SumaX4}
        };
        double[] B = {SumaY, SumaXY, SumaX2Y};

        // Solucionar el sistema lineal
        double[] coeficientes = resolverSistemaEcuaciones(A, B);
        this.B0 = coeficientes[0];
        this.B1 = coeficientes[1];
        this.B2 = coeficientes[2];
        this.MostrarModelo();

        // Calcular el error y agregar el modelo a la lista
        double R2 = Error.R2(x, y, this.B0, this.B1, this.B2);
        System.out.println("Error cuadrática = " + R2);
        System.out.println("\n");

        // Crear un objeto data_set con los coeficientes y el error y agregarlo a la lista de modelos
        data_set modeloGenerado = new data_set(this.B0, this.B1, this.B2, R2);
        Modelo.add(modeloGenerado);
    }

    // Mostrar el modelo
    public void MostrarModelo() {
        System.out.println("Modelo de Regresión Cuadrática = " + this.B0 + " + " + this.B1 + "X + " + this.B2 + "X^2");
    }

    // Método para resolver un sistema de ecuaciones lineales
    public double[] resolverSistemaEcuaciones(double[][] A, double[] B) {
        int n = B.length;
        double[] result = new double[n];
        for (int p = 0; p < n; p++) {
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }
            double[] temp = A[p];
            A[p] = A[max];
            A[max] = temp;
            double t = B[p];
            B[p] = B[max];
            B[max] = t;
            for (int i = p + 1; i < n; i++) {
                double alpha = A[i][p] / A[p][p];
                B[i] -= alpha * B[p];
                for (int j = p; j < n; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * result[j];
            }
            result[i] = (B[i] - sum) / A[i][i];
        }
        return result;
    }

    // Método para dividir el dataset en subconjuntos y aplicar la regresión
    public void formula2() {
        List<Double> x = new ArrayList<>(this.dataset.getX());
        List<Double> y = new ArrayList<>(this.dataset.getY());
        // Dividir el conjunto en 70% entrenamiento, 30% prueba
        int splitIndex = (int) (x.size() * 0.7);
        List<Double> xTrain = x.subList(0, splitIndex);
        List<Double> yTrain = y.subList(0, splitIndex);
        List<Double> xTest = x.subList(splitIndex, x.size());
        List<Double> yTest = y.subList(splitIndex, y.size());
        System.out.println("\nRegresión con 70% entrenamiento y 30% prueba");
        calcularRegresion(xTrain, yTrain);
        evaluarModelo(xTest, yTest);
    }

    public void formula3() {
        List<Double> x = new ArrayList<>(this.dataset.getX());
        List<Double> y = new ArrayList<>(this.dataset.getY());
        // Dividir el conjunto en 70% entrenamiento desde el final, 30% prueba al inicio
        int splitIndex = (int) (x.size() * 0.3); // Obtén el índice de inicio de la parte de entrenamiento
        List<Double> xTest = x.subList(0, splitIndex);
        List<Double> yTest = y.subList(0, splitIndex);
        List<Double> xTrain = x.subList(splitIndex, x.size()); // 70% de entrenamiento desde el final
        List<Double> yTrain = y.subList(splitIndex, y.size());
        System.out.println("\nRegresión con 70% entrenamiento (desde el final) y 30% prueba");
        calcularRegresion(xTrain, yTrain);
        evaluarModelo(xTest, yTest);
    }

    public void formula4() {
        List<Double> x = new ArrayList<>(this.dataset.getX());
        List<Double> y = new ArrayList<>(this.dataset.getY());
        // Crear listas para entrenamiento y prueba
        List<Double> xTrain = new ArrayList<>();
        List<Double> yTrain = new ArrayList<>();
        List<Double> xTest = new ArrayList<>();
        List<Double> yTest = new ArrayList<>();
        // Selección aleatoria de 70% entrenamiento, 30% prueba
        Random random = new Random();
        for (int i = 0; i < x.size(); i++) {
            if (random.nextDouble() < 0.7) {
                xTrain.add(x.get(i));
                yTrain.add(y.get(i));
            } else {
                xTest.add(x.get(i));
                yTest.add(y.get(i));
            }
        }
        System.out.println("\nRegresión con 70% entrenamiento y 30% prueba aleatoria");
        calcularRegresion(xTrain, yTrain);
        evaluarModelo(xTest, yTest);
    }

    // Método para evaluar el modelo en el conjunto de prueba
    public void evaluarModelo(List<Double> xTest, List<Double> yTest) {
        System.out.println("Evaluación del modelo con datos de prueba:");
        for (int i = 0; i < xTest.size(); i++) {
            double pred = predicciones(xTest.get(i));
            System.out.println("Predicción: " + pred + " | Valor real: " + yTest.get(i));
        }
    }

    // Método para seleccionar el mejor modelo basado en el error R2
    public void MVP() {
        if (this.Modelo.isEmpty()) {
            System.out.println("No se encontraron modelos para evaluar.");
            return;
        }
// Encontrar el modelo con el menor error (mejor R2) usando una clase anónima en lugar de lambda
        data_set mejorModelo = Collections.min(this.Modelo, new Comparator<data_set>() {
            @Override
            public int compare(data_set m1, data_set m2) {
                return Double.compare(m1.getError(), m2.getError());
            }
        });
        System.out.println("El mejor modelo tiene los coeficientes: B0=" + mejorModelo.getB0() +
                ", B1=" + mejorModelo.getB1() + ", B2=" + mejorModelo.getB2() +
                " con un error de " + mejorModelo.getError());
    }
    
    // Predicción con el modelo cuadrático
    public double predicciones(double x) {
        return this.B0 + this.B1 * x + this.B2 * x * x;
    }

}
    