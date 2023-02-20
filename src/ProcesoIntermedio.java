import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class ProcesoIntermedio extends Thread{

private Buzon salida;
private  Buzon entrada;
private int nivel;
private String tipo;
private Queue<Producto> productosTransformados = new LinkedList<Producto>();
public ProcesoIntermedio(Buzon buzonE,Buzon buzonS,String ntipo,int pnivel){

    this.salida=buzonS;
    this.entrada=buzonE;
    this.tipo=ntipo;
    this.nivel=pnivel;
}




public void run(){
    System.out.println("Proceso " + tipo + " iniciado en nivel " + nivel);
    System.out.println(productosTransformados.size());
    while(productosTransformados.size()!=0){
    
        if(tipo.equals("NARANJA")){
            System.out.println("Proceso " + tipo + " iniciado en nivelooos: "+nivel);
            while(entrada.getOcupacion() == 0) {
                Thread.yield();
            }
            String prod = entrada.sacaProducto();
            try {
                sleep(ThreadLocalRandom.current().nextInt(50, 501));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(salida.getCapacidad()==0){
                Thread.yield();
            }
            salida.recibeProducto(productosTransformados.remove());
            System.out.println("Proceso " + tipo + " terminado en nivel: "+nivel);
        }
        else if (tipo.equals("AZUL")) {
            String prod = entrada.sacaProducto();
            try {
                sleep(ThreadLocalRandom.current().nextInt(50, 501));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            //salida.recibeProducto(); pasar producto con transformacion()
            salida.recibeProducto(productosTransformados.remove());
            System.out.println("Proceso " + tipo + " terminado en nivel: "+nivel);
        }
    }

}



}

