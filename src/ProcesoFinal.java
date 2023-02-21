import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CyclicBarrier;

public class ProcesoFinal extends Thread{
    private Buzon salida;
    private  Buzon entrada;
    private int nivel;
    private int tipo;
    private Queue<Producto> productosIni = new LinkedList<Producto>();
    private static CyclicBarrier barrera;
    public static int contador;
    private int numProductos;
    
public ProcesoFinal(Buzon buzonE,Buzon buzonS){
    this.salida=buzonS;
    this.entrada=buzonE;
}

public void run(){
    
    while (true) {
        if (entrada.getOcupacion() == 0) {
            System.out.println("Se acabaron los productos");
            break;
        }
        Producto prod = entrada.sacaProducto();
        System.out.println("Proceso " + prod.getMsg() + " recibio producto: "+prod.getMsg());
        salida.recibeProducto(prod);
        System.out.println("Proceso Final: " + prod.getId());
    }
}
public static CyclicBarrier getBarrier() {
    return barrera;
}
public static void setBarrier(CyclicBarrier barre) {
    ProcesoFinal.barrera = barre;
}
}