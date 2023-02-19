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
        cola.add(prod.getMsg());
        notify();
    }

    @Override
    public synchronized String sacaProducto() {
        while (cola.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String prod = cola.remove();
        notify();
        return prod;
    }
}
