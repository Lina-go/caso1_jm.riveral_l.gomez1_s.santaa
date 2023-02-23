import java.util.ArrayList;

/**
 * BuzonFinal
 */
public class BuzonFinal extends Buzon {
    private int nivel;
    private ArrayList<Producto> listaFin;

    public BuzonFinal(int i) {
        super(i);
        nivel = i;
        listaFin = new ArrayList<Producto>();
    }

    public int contieneProductoId(int pId) {
        int b = -1;
        for (int index = 0; index < listaFin.size(); index++) {
            if (listaFin.get(index).getId() == pId) {
                b = index;
            }
        }
        return b;
    }

    public Producto getproductoId(int n) {
        return listaFin.get(n);
    }

    @Override
    public synchronized void recibeProductoA(Producto prod) {
        cola.add(prod);
        notify();
    }

    @Override
    public synchronized Producto sacaProductoA() {
        throw new UnsupportedOperationException("No se puede sacar productos del buzón final!!");

    }

    @Override
    public synchronized void recibeProductoN(Producto prod) {
        cola.add(prod);
        notify();
    }

    @Override
    public synchronized Producto sacaProductoN() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sacaProductoN'");
    }
}