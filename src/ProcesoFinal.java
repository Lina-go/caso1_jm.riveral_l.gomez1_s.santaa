import java.util.concurrent.CyclicBarrier;

public class ProcesoFinal extends Thread{
    private Buzon salida;
    private  Buzon entrada;
    private int nivel;
    private int tipo;
    private static CyclicBarrier barrier;
public ProcesoFinal(Buzon buzonE,Buzon buzonS,CyclicBarrier barrera){

    this.salida=buzonS;
    this.entrada=buzonE;
    this.barrier=barrera;
   
}
public synchronized void esperaProductos(){
    try {
        barrier.await();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public void run(){

}
}