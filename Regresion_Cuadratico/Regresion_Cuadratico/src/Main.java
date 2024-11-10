public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        data_set dataSet = data_set.datos();
        Regresion_Cuadratica regresionCuadratica = new Regresion_Cuadratica(dataSet);
        System.out.println("Data spliting: ");
        regresionCuadratica.formula2();
        regresionCuadratica.formula3();
        regresionCuadratica.formula4();
        System.out.println("Mejor Modelo de los splitting");
        regresionCuadratica.MVP();
    }
}
