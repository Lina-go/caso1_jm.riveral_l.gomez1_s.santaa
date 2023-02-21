import java.util.ArrayList;

/**
 * BuzonFinal
 */
public class BuzonFinal extends Buzon{
private ArrayList<Producto> listaFin;
    public BuzonFinal(){
        super(tamano);
        listaFin= new ArrayList<Producto>();
    }
    @Override
    public void recibeProductoA(Producto prod) {
        cola.add(prod);
    }
    @Override
    public Producto sacaProductoA() {
        throw new UnsupportedOperationException("No se puede sacar productos del buzón final!!");
        
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