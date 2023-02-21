import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class ProcesoIntermedio extends Thread{

private Buzon salida;
private  Buzon entrada;
private int nivel;
private String tipo;
private int numProductos;
private Queue<Producto> productosTransformados = new LinkedList<Producto>();
private static int h=0;

public ProcesoIntermedio(Buzon buzonE,Buzon buzonS,String ntipo,int pnivel,int numProducto){
    this.numProductos = numProducto;
    this.salida=buzonS;
    this.entrada=buzonE;
    this.tipo=ntipo;
    this.nivel=pnivel;
}


public void run(){
    System.out.println("Proceso " + tipo + " iniciado en nivel " + nivel);
    //System.out.println(productosTransformados.size());
    
    
    while(h<numProductos){
        
        Producto prod = entrada.sacaProducto();
        if(tipo.equals("NARANJA") && prod.getTipo()==true){
            System.out.println("Proceso " + tipo + " iniciado en nivel: "+nivel);
            while(entrada.getOcupacion() == 0) {
                //Thread.yield();
            }
            
            productosTransformados.add(prod);
            try {
                productosTransformados.wait(); //se pausa el hilo azul
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                sleep(ThreadLocalRandom.current().nextInt(50, 501));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(salida.getCapacidad()==0){
                //Thread.yield();
            }
            salida.recibeProducto(productosTransformados.remove());
            notify(); // se vuelve a llamar a todos los hilos
            
            System.out.println("Proceso " + tipo + " terminado en nivel: "+nivel);
        }
        else if (tipo.equals("AZUL")&& prod.getTipo()==false) {
            
            productosTransformados.add(prod);
            try {
                sleep(ThreadLocalRandom.current().nextInt(50, 501));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            //salida.recibeProducto(); pasar producto con transformacion()
            salida.recibeProducto(productosTransformados.remove());
            System.out.println("Proceso " + tipo + " terminado en nivel: "+nivel);
        }
        h++;
    }


}



}

