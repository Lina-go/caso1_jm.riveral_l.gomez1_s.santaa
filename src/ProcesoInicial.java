import java.util.LinkedList;
import java.util.Queue;

/**
 * ProcesoInicial
 */
public class ProcesoInicial extends Thread {

    private Buzon buzonS;
    private Queue<Producto> productosIni = new LinkedList<Producto>();
    
    public ProcesoInicial(Buzon BuzonSalida, Queue<Producto> productosInicio){
        this.buzonS=BuzonSalida;
        this.productosIni=productosInicio;

    }
    
    public synchronized void cargaInicial() {
        if (buzonS.getTamano()==0)
            System.out.println("No se puede iniciar si la capacidad es 0!!!");
    
        while(!productosIni.isEmpty()){
            if(buzonS.getCapacidad()==0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            buzonS.recibeProducto(productosIni.remove());
            notifyAll();
        }
    }

    @Override
    public void run(){
        System.out.println("Proceso Inicial Iniciado");
        
            try {
                cargaInicial();
            } catch (Exception e) {
                e.printStackTrace();
            }
        System.out.println("-----------------------------ETAPA 1 terminada -------------------------------------");

    }
}