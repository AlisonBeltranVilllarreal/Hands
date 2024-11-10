import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Regresion_Lineal {
    private data_set dataset;

    ArrayList<data_set> Modelo = new ArrayList<>();
    private double Pendiente = 0;
    private double interc = 0;

    public Regresion_Lineal(data_set dataset) {
        this.dataset = dataset;
        formula();
    }

    public void formula() {
        List<Double> x= dataset.getX();
        List<Double> y= dataset.getY();

        double SumaX= Mate_Discreta.Suma_X(dataset.getX());
        double SumaY= Mate_Discreta.Suma_Y(dataset.getY());
        double SumaXY= Mate_Discreta.Suma_XY(dataset.getX(), dataset.getY());
        double SumaXdos= Mate_Discreta.Suma_Xdos(dataset.getX());

        int n = dataset.getX().size();

        Pendiente = (n * SumaXY - SumaX * SumaY) / (n * SumaXdos - SumaX * SumaX);
        interc = (SumaY - Pendiente * SumaX) / n;

        MostrarModelo();

        double correlacion = Error.correlacion(n, SumaX, SumaXY, SumaY, SumaXdos, Mate_Discreta.Suma_Ydos(dataset.getY()));
        System.out.println("Error de Correlacion en base al data set de entrenamiento: " + correlacion);

        double R2 = Error.R2( x ,y , interc, Pendiente );
        System.out.println("Error cuadratica = " + R2 );

        double[] datosSimulados = {60, 65, 70, 75, 80};
        System.out.println("Predicciones ");
        for (double simulacion : datosSimulados) {
            double Resultado = predicciones(simulacion);
            System.out.println("Cuando x = " + simulacion + ", y = " + Resultado);
        }
        System.out.println("\n");
    }

    public void formula2() {
        List<Double> x = dataset.getX();
        List<Double> y = dataset.getY();

        // Dividir los datos en 70% entrenamiento y 30% prueba
        int n = x.size();
        int trainSize = (int) (n * 0.7);

        List<Double> xTrain = new ArrayList<>(x.subList(0, trainSize));
        List<Double> yTrain = new ArrayList<>(y.subList(0, trainSize));
        List<Double> xTest = new ArrayList<>(x.subList(trainSize, n));
        List<Double> yTest = new ArrayList<>(y.subList(trainSize, n));

        double SumaX = Mate_Discreta.Suma_X(xTrain);
        double SumaY = Mate_Discreta.Suma_Y(yTrain);
        double SumaXY = Mate_Discreta.Suma_XY(xTrain, yTrain);
        double SumaXdos = Mate_Discreta.Suma_Xdos(xTrain);

        Pendiente = (trainSize * SumaXY - SumaX * SumaY) / (trainSize * SumaXdos - SumaX * SumaX);
        interc = (SumaY - Pendiente * SumaX) / trainSize;

        MostrarModelo();

        double correlacion = Error.correlacion(yTrain.size(), SumaX, SumaXY, SumaY, SumaXdos, Mate_Discreta.Suma_Ydos(yTrain));
        System.out.println("Error de Correlacion en base al data set de entrenamiento: " + correlacion);

        double R2 = Error.R2(xTest, yTest, interc, Pendiente);
        System.out.println("Error cuadratica = " + R2);

        Modelo.add(new data_set(interc, Pendiente, R2));

        System.out.println("Predicciones del 30% restante");
        for (double simulacion : xTest) {
            double Resultado = predicciones(simulacion);
            System.out.println("Cuando x = " + simulacion + ", y = " + Resultado);
        }
        System.out.println("\n");
    }

    public void formula3() {
        List<Double> x = dataset.getX();
        List<Double> y = dataset.getY();

        // Dividir los datos en 70% entrenamiento y 30% prueba desde el final hacia el principio
        int n = x.size();
        int trainSize = (int) (n * 0.7);

        List<Double> xTrain = new ArrayList<>(x.subList(n - trainSize, n));
        List<Double> yTrain = new ArrayList<>(y.subList(n - trainSize, n));
        List<Double> xTest = new ArrayList<>(x.subList(0, n - trainSize));
        List<Double> yTest = new ArrayList<>(y.subList(0, n - trainSize));

        double SumaX = Mate_Discreta.Suma_X(xTrain);
        double SumaY = Mate_Discreta.Suma_Y(yTrain);
        double SumaXY = Mate_Discreta.Suma_XY(xTrain, yTrain);
        double SumaXdos = Mate_Discreta.Suma_Xdos(xTrain);

        Pendiente = (trainSize * SumaXY - SumaX * SumaY) / (trainSize * SumaXdos - SumaX * SumaX);
        interc = (SumaY - Pendiente * SumaX) / trainSize;

        MostrarModelo();

        double correlacion = Error.correlacion(yTrain.size(), SumaX, SumaXY, SumaY, SumaXdos, Mate_Discreta.Suma_Ydos(yTrain));
        System.out.println("Error de Correlacion en base al data set de entrenamiento: " + correlacion);

        double R2 = Error.R2(xTest, yTest, interc, Pendiente);
        System.out.println("Error cuadratica = " + R2);

        Modelo.add(new data_set(interc, Pendiente, R2));



        System.out.println("Predicciones del 30% restante");
        for (double simulacion : xTest) {
            double Resultado = predicciones(simulacion);
            System.out.println("Cuando x = " + simulacion + ", y = " + Resultado);
        }
        System.out.println("\n");
    }

    public void formula4() {
        List<Double> x = dataset.getX();
        List<Double> y = dataset.getY();

        // Dividir los datos en 70% entrenamiento y 30% prueba desde el final hacia el principio
        int n = x.size();

        // Barajar los datos para una distribución aleatoria
        Collections.shuffle(x);
        Collections.shuffle(y);
        int trainSize = (int) (n * 0.7);

        List<Double> xTrain = new ArrayList<>(x.subList(n - trainSize, n));
        List<Double> yTrain = new ArrayList<>(y.subList(n - trainSize, n));
        List<Double> xTest = new ArrayList<>(x.subList(0, n - trainSize));
        List<Double> yTest = new ArrayList<>(y.subList(0, n - trainSize));

        double SumaX = Mate_Discreta.Suma_X(xTrain);
        double SumaY = Mate_Discreta.Suma_Y(yTrain);
        double SumaXY = Mate_Discreta.Suma_XY(xTrain, yTrain);
        double SumaXdos = Mate_Discreta.Suma_Xdos(xTrain);

        Pendiente = (trainSize * SumaXY - SumaX * SumaY) / (trainSize * SumaXdos - SumaX * SumaX);
        interc = (SumaY - Pendiente * SumaX) / trainSize;

        MostrarModelo();

        double correlacion = Error.correlacion(yTrain.size(), SumaX, SumaXY, SumaY, SumaXdos, Mate_Discreta.Suma_Ydos(yTrain));
        System.out.println("Error de Correlacion en base al data set de entrenamiento: " + correlacion);

        double R2 = Error.R2(xTest, yTest, interc, Pendiente);
        System.out.println("Error cuadratica = " + (correlacion*correlacion)*100+"%");

        Modelo.add(new data_set(interc, Pendiente, R2));



        System.out.println("Predicciones del 30% restante");
        for (double simulacion : xTest) {
            double Resultado = predicciones(simulacion);
            System.out.println("Cuando x = " + simulacion + ", y = " + Resultado);
        }
        System.out.println("\n");
    }

    public void MVP(){
        if(Modelo == null || Modelo.size() == 0){
            System.out.println("No tiene modelos");
            return;
        }

        data_set mejorModelo = Modelo.get(0);

        for (data_set dataset : Modelo) {
            if(dataset.getError()> mejorModelo.getError()){
                mejorModelo = dataset;
            }
        }
        System.out.println("Y= "+ mejorModelo.getB0()+ " + "+ mejorModelo.getB1() + " X ");
        System.out.println("Con el error de: "+ mejorModelo.getError());
    }


    public void MostrarModelo(){
        System.out.println("Modelo de Regresion Lineal = " + interc + " + " + Pendiente +" X " );
    }

    public double predicciones(double x) {
        return Pendiente * x + interc;
    }

}