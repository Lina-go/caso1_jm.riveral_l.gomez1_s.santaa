import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CyclicBarrier;

public class ProcesoFinal extends Thread {
    private BuzonFinal entrada;

    public static int contador;
    private int idBuscado;

    public ProcesoFinal(BuzonFinal buzonE, int numeroProcesos) {
        this.entrada = buzonE;

        this.idBuscado = 1;

    }

    public void run() {

        boolean terminar = false;
        while (!terminar) {
            Main.rep.rRetriveAttempt("final");
            if (entrada.getOcupacion() == 0) {
                Main.rep.rBuzonFinalVacio();
                terminar = true;
            }
            while (entrada.contieneProductoId(idBuscado) == -1) {
                Main.rep.rFinalPrintFail(idBuscado);
                try {
                    wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            Main.rep.rFinalPrintFail(idBuscado);

            idBuscado++;

        }
    }

}