import java.util.List;

public class Mate_Discreta { // Define una clase pública llamada Mate_Discreta

    // Método estático para calcular la suma de los valores en una lista de números de punto flotante
    public static double Suma_X(List<Double> listaX) {
        double sumaX = 0.0; // Inicializa la suma de X a 0
        for (double R : listaX) { // Itera sobre cada valor R en la lista listaX
            sumaX += R; // Suma el valor R a sumaX
        }
        return sumaX; // Devuelve la suma total de X
    }

    // Método estático para calcular la suma de los valores en una lista de números de punto flotante
    public static double Suma_Y(List<Double> listaY) {
        double sumaY = 0.0; // Inicializa la suma de Y a 0
        for (double R : listaY) { // Itera sobre cada valor R en la lista listaY
            sumaY += R; // Suma el valor R a sumaY
        }
        return sumaY; // Devuelve la suma total de Y
    }

    // Método estático para calcular la suma de los productos de dos listas de números de punto flotante
    public static double Suma_XY(List<Double> listaX, List<Double> listaY) {
        double sumaXY = 0.0; // Inicializa la suma de XY a 0
        for (int i = 0; i < listaX.size(); i++) { // Itera sobre los índices de la lista listaX
            sumaXY += listaX.get(i) * listaY.get(i); // Suma el producto de los valores correspondientes en listaX y listaY a sumaXY
        }
        return sumaXY; // Devuelve la suma total de XY
    }

    // Método estático para calcular la suma de los cuadrados de los valores en una lista de números de punto flotante
    public static double Suma_Xdos(List<Double> listaX) {
        double sumaXdos = 0.0; // Inicializa la suma de los cuadrados de X a 0
        for (double R : listaX) { // Itera sobre cada valor R en la lista listaX
            sumaXdos += R * R; // Suma el cuadrado del valor R a sumaXdos
        }
        return sumaXdos; // Devuelve la suma total de los cuadrados de X
    }

    // Método estático para calcular la suma de los cubos de los valores en una lista de números de punto flotante
    public static double Suma_X3(List<Double> listaX) {
        double sumaX3 = 0.0; // Inicializa la suma de los cubos de X a 0
        for (double R : listaX) { // Itera sobre cada valor R en la lista listaX
            sumaX3 += R * R * R; // Suma el cubo del valor R a sumaX3
        }
        return sumaX3; // Devuelve la suma total de los cubos de X
    }

    // Método estático para calcular la suma de las cuartas potencias de los valores en una lista de números de punto flotante
    public static double Suma_X4(List<Double> listaX) {
        double sumaX4 = 0.0; // Inicializa la suma de las cuartas potencias de X a 0
        for (double R : listaX) { // Itera sobre cada valor R en la lista listaX
            sumaX4 += Math.pow(R, 4); // Suma la cuarta potencia del valor R a sumaX4
        }
        return sumaX4; // Devuelve la suma total de las cuartas potencias de X
    }

    // Método estático para calcular la suma de los productos de los cuadrados de una lista de números de punto flotante con los valores de otra lista
    public static double Suma_X2Y(List<Double> listaX, List<Double> listaY) {
        double sumaX2Y = 0.0; // Inicializa la suma de X^2Y a 0
        for (int i = 0; i < listaX.size(); i++) { // Itera sobre los índices de la lista listaX
            sumaX2Y += Math.pow(listaX.get(i), 2) * listaY.get(i); // Suma el producto del cuadrado del valor en listaX y el valor correspondiente en listaY a suma
        }
        return sumaX2Y;
    }
}
