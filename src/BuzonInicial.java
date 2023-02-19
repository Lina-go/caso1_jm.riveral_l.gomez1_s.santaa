import java.util.LinkedList;
import java.util.Queue;

/**
 * BuzonInicial
 */
public class BuzonInicial extends Buzon {
	private static Queue<Producto> productosCreados = new LinkedList<Producto>();
    public BuzonInicial(int tamano){
        super(tamano);
    }

    @Override
    public synchronized void recibeProducto(Producto prod) {
    	productosCreados.add(prod);
        
    }

    @Override
    public synchronized void sacaProducto(Producto prod) {
    	productosCreados.remove(prod);
        
    }

    public synchronized Queue<Producto> listaDeProductosObtenidos() {
    return productosCreados;

    }
}