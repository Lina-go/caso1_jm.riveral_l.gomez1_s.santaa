public class Producto {

    private int id;
    private String msg;

    public Producto(int pId) {
        this.id=pId;
        this.msg=pId+"";
    }
    
    public int getId() {
        return this.id;
        
    }
    public String getMsg() {
        return this.msg;
        
    }

    public synchronized void transformar(String nStr) {
        msg+=nStr;
        
    }
}
