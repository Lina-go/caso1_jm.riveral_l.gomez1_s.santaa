import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class ProcesoIntermedio extends Thread {

    private Buzon salida;
    private Buzon entrada;
    private int nivel;
    private String tipo;
    private int numProductos;

    public ProcesoIntermedio(Buzon buzonE, Buzon buzonS, String ntipo, int pnivel, int numProducto) {
        this.numProductos = numProducto;
        this.salida = buzonS;
        this.entrada = buzonE;
        this.tipo = ntipo;
        this.nivel = pnivel;
    }

    private String getIdentifier() {
        return "intermedio-" + nivel;
    }

    public void run() {
        // System.out.println(productosTransformados.size());
        boolean terminar = false;
        while (!terminar) {
            Main.rep.rRetriveAttempt(getIdentifier());
            Producto prod = entrada.sacaProductoA();
            if (salida.getContador() == numProductos) {
                terminar = true;
            } else {
                prod.transformar("T" + tipo + nivel);
            }
            int time = ThreadLocalRandom.current().nextInt(50, 501);
            try {
                sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Main.rep.rSendAttempt(getIdentifier(), prod.getMsg());
            salida.recibeProductoA(prod);
            salida.setContador();
            if (salida.getContador() == numProductos) {
                Main.rep.rEndOfExecution(getIdentifier());
            }
        }

    }
}
