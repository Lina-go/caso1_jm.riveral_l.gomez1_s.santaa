public class BuzonIntermedio extends Buzon {
    private int nivel;

    public BuzonIntermedio(int tamano, int level) {
        super(tamano);
        this.nivel = level;
    }

    /**
     * Añade un mensaje a la cola de mensajes del Buzón final.
     * Si el buzón no tiene espacio, hace Wait().
     * Cuando añade el mensaje hace Notify para para despertar
     * a consumidores en espera.
     */
    @Override
    public synchronized void recibeProductoA(Producto prod) {
        while (cola.size() == tamano) {
            Main.rep.rBuzonLleno(getId() + "", prod.getMsg());
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cola.add(prod);
        Main.rep.rprodAdded(getId(), prod.getMsg());
        notify();
    }

    private String getId() {
        return "Intermedio -" + nivel;
    }

    /**
     * Saca el producto en el tope de la cola y lo retorna
     * Si la cola no tiene mensajes hace wait().
     * Cuando saca el mensaje hace notify para despertar
     * a productores en espera.
     */
    @Override
    public synchronized Producto sacaProductoA() {
        while (cola.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Producto prod = cola.remove();
        notify();
        return prod;
    }

    @Override
    public synchronized void recibeProductoN(Producto prod) {
        // TODO Auto-generated method stub
        cola.add(prod);
        Main.rep.rprodAdded(getId(), prod.getMsg());
    }

    @Override
    public synchronized Producto sacaProductoN() {
        // TODO Auto-generated method stub
        Producto prod = cola.remove();
        return prod;

    }

}
