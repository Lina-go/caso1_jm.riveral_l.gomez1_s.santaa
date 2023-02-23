/**
 * Buzon
 */
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Buzon {

    protected int tamano; //tamaño buzon
	protected boolean isFinal; //Ultimo buzon

	protected Queue<Producto> cola = new LinkedList<Producto>(); //colaBuzón
	protected Queue<Producto> blueProducts = new LinkedList<Producto>();
    protected Queue<Producto> orangeProducts = new LinkedList<Producto>();
    protected LinkedList<Producto> finalProducts = new LinkedList<Producto>();





    /**
	 * Añade un mensaje a la cola de mensajes del Buzón 
     * @throws InterruptedException
	 */

	protected synchronized void recibeProducto(Producto prod) {
		while (cola.size() == tamano) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cola.add(prod);
        notifyAll();
	}
    //get tamaño del buzon
    public synchronized int getTamano(){
        return tamano;
    }
	
	/**
	 * Saca el mensaje en el tope de la cola y lo retorna
	 * @throws InterruptedException
	 */
	protected synchronized Producto sacaProducto() {
		while (cola.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Producto prod = cola.remove();
        notifyAll();
        return prod;
	}

	public synchronized int getCapacidad() {
		return tamano - cola.size();
	}

	/**
	 * Retorna la cantidad actual de mensajes en la cola
	 */
	public synchronized int getOcupacion() {
		return cola.size();
	}
	protected synchronized boolean colaIsFull() {
        if (isFinal)
            return false;
        return this.cola.size() == this.tamano;
    }

	protected synchronized boolean colaIsEmpty() {
        return this.cola.isEmpty();
    }
	//////////////////////////// PRODUCTO AZUL /////////////////////////////////
	protected synchronized boolean blueIsFull() {
        if (isFinal)
            return false;
        return this.blueProducts.size() == this.tamano;
    }

	protected synchronized boolean blueIsEmpty() {
        return this.blueProducts.isEmpty();
    }

	protected synchronized void recibeProductoA(Producto prod) {
		while (blueIsFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!isFinal)
            blueProducts.add(prod);
        else{
            finalProducts.add(prod);}

        notifyAll();
	}
	
	/**
	 * Saca el mensaje en el tope de la cola y lo retorna
	 */
	protected synchronized Producto sacaProductoA(){
		while (blueIsEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Producto sentProduct = blueProducts.remove();

        notifyAll();

        return sentProduct;
	}

	//////////////////////////// PRODUCTO NARANJA /////////////////////////////////
	protected synchronized boolean orangeIsFull() {
        if (isFinal)
            return false;
        return this.orangeProducts.size() == this.tamano;
    }

    protected synchronized boolean orangeIsEmpty() {
        return this.orangeProducts.isEmpty();
    }
	protected synchronized void recibeProductoN(Producto prod){
		if (!isFinal)
        {System.out.println("PAPITASSS");
            orangeProducts.add(prod);}
        else{
            System.out.println("Añadiendo producto final");
            finalProducts.add(prod);}
	}
    
    // get lista de finalProducts
    public synchronized List<Producto> getFinalProducts() {
        return finalProducts;
    }
	
	/**
	 * Saca el mensaje en el tope de la cola y lo retorna
	 */
	protected synchronized Producto sacaProductoN(){
		return orangeProducts.remove();
    }

    public synchronized Producto giveProduct(Integer id) {
        for (Producto product : finalProducts) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
    public synchronized void setFinal() {
        isFinal = true;
    }
    // get final
    public synchronized boolean getFinal() {
        return isFinal;
    }
}