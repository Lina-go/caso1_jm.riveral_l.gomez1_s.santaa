/**
 * Buzon
 */
import java.util.LinkedList;
import java.util.Queue;

public abstract class Buzon {

    protected static int tamano; //tamaño buzon
	protected Queue<Producto> cola = new LinkedList<Producto>();
	protected int contador; 

    public Buzon(int tamano) {
		this.tamano = tamano;
	}


    /**
	 * Añade un mensaje a la cola de mensajes del Buzón 
	 */
	public abstract void recibeProducto(Producto prod);
	
	/**
	 * Saca el mensaje en el tope de la cola y lo retorna
	 */
	public abstract Producto sacaProducto();

	/**
	 * Retorna la capacidad actual del buzón.
	 */
	public synchronized int getCapacidad() {
		return tamano - cola.size();
	}
	public synchronized int getContador() {
		return contador;
	}

	/**
	 * Retorna la cantidad actual de mensajes en la cola
	 */
	public synchronized int getOcupacion() {
		return cola.size();
	}
	public void setContador(int i) {
		ProcesoFinal.contador+=i;
	}
	
}