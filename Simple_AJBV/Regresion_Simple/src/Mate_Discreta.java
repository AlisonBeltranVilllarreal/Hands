import java.util.List;

public class Mate_Discreta {

    public static double Suma_X(List<Double> listaX){
        double sumaX = 0;
        for(double R: listaX){
            sumaX=sumaX+R;
        }
        return sumaX;
    }

    public static double Suma_Y(List<Double> listaY){
        double sumaY= 0;
        for(double R: listaY){
            sumaY=sumaY+R;
        }
        return sumaY;
    }

    public static double Suma_XY(List<Double> listaX,List<Double> listaY ){

        double sumaXY= 0;
        for(int i = 0; i < listaX.size(); i++){
            sumaXY += listaX.get(i) * listaY.get(i);
        }
        return sumaXY;
    }

    public static double Suma_Xdos(List<Double> listaX){
        double sumaXdos = 0;
        for(double R: listaX){
            sumaXdos +=R*R ;
        }
        return sumaXdos;
    }

    public static double Suma_Ydos(List<Double> listaY){
        double sumaYdos = 0;
        for(double R: listaY){
            sumaYdos +=R*R ;
        }
        return sumaYdos;
    }


}

