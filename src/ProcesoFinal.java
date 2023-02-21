import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CyclicBarrier;

public class ProcesoFinal extends Thread{
    private Buzon salida;
    private  Buzon entrada;
    
    private static CyclicBarrier barrera;
    public static int contador;
    private int finalizados;

    
public ProcesoFinal(Buzon buzonE, int numeroProcesos){
    this.entrada=buzonE;
    this.finalizados=numeroProcesos;

}

public void run(){
    
        boolean terminar = false;
        while(!terminar) {
            Main.rep.rRetriveAttempt("final");
            while(entrada.getOcupacion() == 0) {
                Main.rep.rBuzonFinalVacio();
                this.yield();
            }
            Producto prod = entrada.sacaProductoA();
            finalizados--;
            System.out.println("El proceso final recibió el mensaje: " + prod.getMsg());
            if(finalizados == 0 ) {
                if(finalizados == 3) {
                    terminar = true;
                    Main.rep.report("El proceso final recibió el tercer mensaje 'FIN'. Finaliza su ejecución");;
                }
            }
        }
    }

public static CyclicBarrier getBarrier() {
    return barrera;
}
public static void setBarrier(CyclicBarrier barre) {
    ProcesoFinal.barrera = barre;
}
}