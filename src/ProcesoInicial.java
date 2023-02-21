import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ProcesoInicial
 */
public class ProcesoInicial extends Thread {

    private Buzon buzonS;
    private Queue<Producto> productosIni = new LinkedList<Producto>();

    public ProcesoInicial(Buzon BuzonSalida, Queue<Producto> productosInicio) {
        this.buzonS = BuzonSalida;
        this.productosIni = productosInicio;

    }

    public void run() {
        for (int i = 0; i < productosIni.size(); i++) {
            Producto x = productosIni.remove();

            while (buzonS.getCapacidad() == 0) {

                Main.rep.rBuzonLleno("inicial", x.getMsg());
                ProcesoInicial.yield();
            }
            buzonS.recibeProductoA(x);
            buzonS.setContador();

        }
        Main.rep.report("El proceo inicial envió todos los mensajes. Finaliza su ejecución");
    }
}