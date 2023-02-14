/**
 * Buzon
 */
import java.util.LinkedList;
import java.util.Queue;

public abstract class Buzon {

    protected int tamano; //tamaño buzon
	protected Queue<String> cola = new LinkedList<String>();

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
	public abstract String sacaProducto();

	/**
	 * Retorna la capacidad actual del buzón.
	 */
	public synchronized int getCapacidad() {
		return tamano - cola.size();
	}

	/**
	 * Retorna la cantidad actual de mensajes en la cola
	 */
	public synchronized int getOcupacion() {
		return cola.size();
	}
	
}