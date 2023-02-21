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
    private int numProductos;
    
public ProcesoFinal(Buzon buzonE,Buzon buzonS){
    this.salida=buzonS;
    this.entrada=buzonE;
}

public void run(){
    Producto prod = entrada.sacaProducto();
}
public static CyclicBarrier getBarrier() {
    return barrera;
}
public static void setBarrier(CyclicBarrier barrera) {
    ProcesoFinal.barrera = barrera;
}
}