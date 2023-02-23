public class Id {
    private static Id instance = null;
    private static Integer currentId = 0;

    private Id() {}
    public static Id getInstance() {
        if (instance == null) {
            synchronized(Id.class) {
                if (instance == null) {
                    instance = new Id();
                }
            }
        }
        return instance;
    }

    public synchronized void setId(Producto product) {
        product.setId(currentId);
        currentId++;
    }
}