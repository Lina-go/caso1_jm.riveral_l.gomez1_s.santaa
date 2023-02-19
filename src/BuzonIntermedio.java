import java.util.LinkedList;
import java.util.Queue;

public class BuzonIntermedio extends Buzon {
	private static Queue<Producto> ListaDeEspera = new LinkedList<Producto>();
	private static Queue<Producto> ProductosDespachados = new LinkedList<Producto>();
    public BuzonIntermedio(int tamano) {
        super(tamano);
        //TODO Auto-generated constructor stub
    }

    



    @Override
    public void recibeProducto(Producto prod) {
    	ListaDeEspera.add(prod);
    	System.out.println("Se ha recibido un nuevo producto al Buzon I1");
        
    }

    @Override
    public void sacaProducto(Producto prod) {
        ListaDeEspera.remove(prod);

    }
    
    public synchronized Queue<Producto> listaDeProductosObtenidos() {
    return ProductosDespachados;

    }
}