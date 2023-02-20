import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ProcesoInicial
 */
public class ProcesoInicial extends Thread {

    private Buzon BuzonS;
    private Queue<Producto> productosIni = new LinkedList<Producto>();
    
    public ProcesoInicial(Buzon BuzonSalida, Queue<Producto> productosInicio){
        this.BuzonS=BuzonSalida;
        this.productosIni=productosInicio;

    }
    
    public synchronized void cargaInicial(){

        while(productosIni.size()!=0){
            if(BuzonS.getCapacidad()==0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
            BuzonS.recibeProducto(productosIni.remove());
            notify();
        }
    }

    public void run(){
        System.out.println("Proceso Inicial Iniciado");
        cargaInicial();
        System.out.println("Proceso Inicial terminado");

    }
}