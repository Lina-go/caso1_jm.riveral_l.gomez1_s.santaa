
/**
 * Buzon
 */
import java.util.LinkedList;
import java.util.Queue;

public abstract class Buzon {

	protected int tamano; // tamaño buzon
	protected Queue<Producto> cola = new LinkedList<Producto>();
	protected int contador;

	public Buzon(int tamano) {
		this.tamano = tamano;
		this.contador = 0;
	}

	/**
	 * Añade un mensaje a la cola de mensajes del Buzón
	 */
	public abstract void recibeProductoA(Producto prod);

	/**
	 * Saca el mensaje en el tope de la cola y lo retorna
	 */
	public abstract Producto sacaProductoA();

	public abstract void recibeProductoN(Producto prod);

	public abstract Producto sacaProductoN();

	/**
	 * Retorna la capacidad actual del buzón.
	 */
	public synchronized int getCapacidad() {
		return tamano - cola.size();
	}

	public synchronized int getContador() {
		return contador;
	}

	public synchronized void setContador() {
		contador += 1;
	}

	/**
	 * Retorna la cantidad actual de mensajes en la cola
	 */
	public synchronized int getOcupacion() {
		return cola.size();
	}

}