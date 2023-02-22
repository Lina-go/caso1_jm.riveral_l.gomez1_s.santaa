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
        return "intermedio-" + nivel + ",Tipo-" + tipo;
    }

    public void run() {
        // System.out.println(productosTransformados.size());
        if (tipo.equals("AZUL")) {

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

        } else {
            boolean terminar = false;
            while (!terminar) {
                Main.rep.rRetriveAttempt(getIdentifier());

                while (entrada.getOcupacion() == 0) {
                    Main.rep.rBuzonVacio(getIdentifier());
                    ProcesoIntermedio.yield();
                }

                Producto prod = entrada.sacaProductoN();
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
                while (salida.getCapacidad() == 0) {
                    Main.rep.rBuzonLleno(getIdentifier(), prod.getMsg());
                    ProcesoIntermedio.yield();
                }
                salida.recibeProductoN(prod);
                Main.rep.rProdRemoved(getIdentifier(), prod.getMsg());
                salida.setContador();
                if (salida.getContador() == numProductos) {
                    Main.rep.rEndOfExecution(getIdentifier());
                }
            }
        }
    }
}
