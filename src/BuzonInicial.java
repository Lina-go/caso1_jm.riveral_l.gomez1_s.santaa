/**
 * BuzonInicial
 */
public class BuzonInicial extends Buzon {

    public BuzonInicial(int tam){
        super.tamano = tam;
        super.isFinal = false;
    }

    @Override
    public synchronized void recibeProducto(Producto prod) {
        while (cola.size() == tamano) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Buzon Inicial recibe producto: "+prod.getId()+"\n");
        cola.add(prod);
        notifyAll();
    }

    @Override
    public synchronized Producto sacaProducto() {
        while(cola.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
        Producto prod = cola.remove();
        notifyAll();
        return prod;

    }

    
    
    
}