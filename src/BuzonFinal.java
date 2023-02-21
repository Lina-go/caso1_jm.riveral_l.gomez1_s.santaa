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
    public void recibeProducto(Producto prod) {
        cola.add(prod);
    }
    @Override
    public Producto sacaProducto() {
        throw new UnsupportedOperationException("No se puede sacar productos del buzón final!!");
        
    }
}