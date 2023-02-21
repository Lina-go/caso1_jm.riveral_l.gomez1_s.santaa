/**
 * BuzonInicial
 */
public class BuzonInicial extends Buzon {

    public BuzonInicial(int tamano){
        super(tamano);
    }

    @Override
    public synchronized void recibeProductoA(Producto prod) {
        if (cola.size() == tamano) {
            System.out.println("Se intentó agregar un producto cuando el buzon inicial estaba lleno");
        }
        cola.add(prod);
        Main.rep.rprodAdded("inicial", prod.getMsg());
        notify();
        }

    @Override
    public synchronized Producto sacaProductoA() {
        while(cola.size() == 0) {
            Main.rep.rBuzonVacio("inicial");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Producto x = cola.remove();
        Main.rep.rProdRemoved("inicial", x.getMsg());
        return x;
    }

    @Override
    public void recibeProductoN(Producto prod) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recibeProductoN'");
    }

    @Override
    public Producto sacaProductoN() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sacaProductoN'");
    }
    

    
    
    
}