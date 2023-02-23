import java.util.Queue;
import java.util.LinkedList;
import java.util.Objects;

public abstract class Buzon {

    protected int tamano;
    protected String name;
    protected boolean esFinal;

    // Colas
    protected Queue<Producto> cola = new LinkedList<Producto>();
    protected Queue<Producto> productoAzul = new LinkedList<Producto>();
    protected Queue<Producto> productoNaranja = new LinkedList<Producto>();
    protected Queue<Producto> productoFinal = new LinkedList<Producto>();

    private synchronized boolean llenoA() {
        if (esFinal) {
            return false;
        }
        return productoAzul.size() == this.tamano;
    }

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

    private synchronized boolean vacioA() {
        return productoAzul.isEmpty();
    }

    public synchronized boolean llenoN() {
        if (esFinal){
            return false;
        }
        return productoNaranja.size() == this.tamano;
    }

    public synchronized boolean vacioN() {
        return productoNaranja.isEmpty();
    }

    public synchronized Producto sacaA() {

        while (vacioA()) {
            Main.rep.rBuzonVacio(getName());
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Producto sentProduct = productoAzul.remove();
        notifyAll();
        return sentProduct;
    }

    public synchronized void recibeA(Producto product) {
        while (llenoA()) {
            Main.rep.rBuzonLleno(name, product.getId() + "");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!esFinal){
            productoAzul.add(product);}
        else{
            productoFinal.add(product);}
        notifyAll();
    }

    public synchronized Producto sacaN() {

        return productoNaranja.remove();
    }

    public synchronized void recibeN(Producto product) {
        if (!esFinal) {
            productoNaranja.add(product);
        } else {
            productoFinal.add(product);
        }
    }

    public String getName() {
        return name;
    }

    public synchronized Producto darProd(Integer id) {
        for (Producto product : productoFinal) {
            if (Objects.equals(product.getId(), id)) {
                return product;
            }
        }
        return null;
    }

    public synchronized int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return tamano - cola.size();
    }

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
}