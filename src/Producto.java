public class Producto {

    private int id;
    private String msg;
    private boolean color;

    public Producto(int pId,boolean col) {
        this.id=pId;
        this.msg=pId+"";
        this.color=col;
    }
    
    public int getId() {
        return this.id;
        
    }
    public String getMsg() {
        return this.msg;
        
    }
    public Boolean getTipo() {
        return this.color;
        
    }

    public synchronized void transformar(String nStr) {
        msg+=nStr;
        
    }
}
