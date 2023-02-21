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
    
    while(salida.getContador()<numProductos){
        
        Producto prod = entrada.sacaProducto();
        if(tipo.equals("NARANJA")){
            System.out.println("Proceso " + tipo + " iniciado en nivel: "+nivel);
            while(entrada.getOcupacion() == 0) {
                Thread.yield();
            }
            
            try {
                sleep(ThreadLocalRandom.current().nextInt(50, 501));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(salida.getCapacidad()==0){
                Thread.yield();
            }
            prod.transformar("T"+tipo+nivel);
            salida.recibeProducto(prod);
            notifyAll(); // se vuelve a llamar a todos los hilos
            
            System.out.println("Proceso " + tipo + " terminado en nivel: "+nivel);
        }
        else if (tipo.equals("AZUL")) {
            
            try {
                sleep(ThreadLocalRandom.current().nextInt(50, 501));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            //salida.recibeProducto(); pasar producto con transformacion()
            prod.transformar("T"+tipo+nivel);
            salida.recibeProducto(prod);
            System.out.println("Proceso " + tipo + " terminado en nivel: "+nivel);
        }
        salida.setContador(1);
    }


}



}
