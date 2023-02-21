/**
 * BuzonInicial
 */
public class BuzonInicial extends Buzon {

    public BuzonInicial(int tamano){
        super(tamano);
    }

    @Override
    public synchronized void recibeProducto(Producto prod) {
        throw new UnsupportedOperationException("No se puede poner producto en el buzon inicial");
        
    }

    @Override
    public synchronized Producto sacaProducto() {
        while(cola.size() ==0){
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        Producto prod = cola.remove();
        notify();
        return prod;

    }

    
    
    
}