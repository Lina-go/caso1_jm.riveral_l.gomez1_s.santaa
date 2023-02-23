public class Producto {
    private Integer id;
    private String msg;

    public Producto() {
        this.msg = "";
    }

    public Integer getId() {
        return id;
    }

    public int setId(Integer id) {
        return this.id = id;
    }

    public String transformar(String message) {
        return this.msg += message;
    }

    public String getMsg() {
        return msg;
    }

}