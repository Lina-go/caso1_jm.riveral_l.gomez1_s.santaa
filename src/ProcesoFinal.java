import java.util.concurrent.CyclicBarrier;

public class ProcesoFinal extends Thread{
    private Buzon salida;
    private  Buzon entrada;
    private int nivel;
    private int tipo;
    private static CyclicBarrier barrera;
public ProcesoFinal(Buzon buzonE,Buzon buzonS){

    this.salida=buzonS;
    this.entrada=buzonE;
   
}

public void run(){

}
public static CyclicBarrier getBarrier() {
    return barrera;
}
public static void setBarrier(CyclicBarrier barrera) {
    ProcesoFinal.barrera = barrera;
}
}