public class BuzonIntermedio extends Buzon {
    
    public BuzonIntermedio(int tamano) {
        super(tamano);
        //TODO Auto-generated constructor stub
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
        cola.add(prod);
        notify();
    }

    @Override
    public synchronized Producto sacaProducto() {
        while (cola.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Producto prod = cola.remove();
        notify();
        return prod;
    }
}
