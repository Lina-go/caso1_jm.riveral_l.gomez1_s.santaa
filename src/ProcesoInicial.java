import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ProcesoInicial
 */
public class ProcesoInicial extends Thread {

    private Buzon buzonS;
    private ArrayList<Producto> productosIni = new ArrayList<Producto>();
    private int index = 0;

    public ProcesoInicial(Buzon BuzonSalida, ArrayList<Producto> productosInicio) {
        this.buzonS = BuzonSalida;
        this.productosIni = productosInicio;

    }

    public void run() {
        while (index < productosIni.size()) {
            Producto x = productosIni.get(index);
            while (buzonS.getCapacidad() == 0) {

                Main.rep.rBuzonLleno("inicial", x.getMsg());
                ProcesoInicial.yield();
            }
            buzonS.recibeProductoA(x);
            buzonS.setContador();
            index++;

        }
        Main.rep.report("El proceo inicial envió todos los mensajes. Finaliza su ejecución");
    }
}