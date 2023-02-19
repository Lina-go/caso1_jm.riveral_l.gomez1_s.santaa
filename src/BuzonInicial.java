/**
 * BuzonInicial
 */
public class BuzonInicial extends Buzon {

    public BuzonInicial(int tamano){
        super(tamano);
    }

    @Override
    public synchronized void recibeProducto(Producto prod) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public synchronized String sacaProducto() {
        while(cola.size() ==0){
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        String prod = cola.remove();
        notify();
        return prod;

    }

    
    
    
}