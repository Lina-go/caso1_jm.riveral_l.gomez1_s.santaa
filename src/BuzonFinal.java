import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BuzonFinal
 */
public class BuzonFinal extends Buzon{
	private static Queue<Producto> productosCreados = new LinkedList<Producto>();
	
private ArrayList<Producto> listaFin;
    public BuzonFinal(){
        super(tamano);
        listaFin= new ArrayList<Producto>();
    }
    @Override
    public synchronized void recibeProducto(Producto prod) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public synchronized void sacaProducto(Producto prod) {
        // TODO Auto-generated method stub
        productosCreados.add(prod);
    }
	public Queue<Producto> listaDeProductosObtenidos() {
		return productosCreados;
	}
}

