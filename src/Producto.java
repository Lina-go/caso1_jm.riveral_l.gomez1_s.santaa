public class Producto {
    private int id;
    private String msg;

    public Producto() {
        this.msg = "";
    }

    public Integer getId() {
        return id;
    }

    public int setId(int id) {
        return this.id = id;
    }

    public String transformar(String mensaje) {
        return this.msg += mensaje;
    }

    public String getMsg() {
        return msg;
    }

}