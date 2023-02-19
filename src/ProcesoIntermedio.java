import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class ProcesoIntermedio extends Thread{

private Buzon salida;
private  Buzon entrada;
private int nivel;
private String tipo;
private Queue<Producto> productosIni;
private Object BuzonS;

public ProcesoIntermedio(Buzon buzonE,Buzon buzonS,String ntipo,int pnivel, Queue<Producto> productosInicio){
    this.BuzonS=buzonS;
    this.entrada=buzonE;
    this.tipo=ntipo;
    this.nivel=pnivel;
    this.productosIni=productosInicio;
    
}
public synchronized void cargaInicial(int etapa){

    while(productosIni.size()!=0){
        if(entrada.getCapacidad()==0){
            try {
            	 
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
            	System.err.println("El buzón de salida está vacio o no existe");
            }
        }
        
    	System.out.println("========================== ETAPA "+ etapa + " ==========================");
    	System.out.println("ET2: Se comienzan a procesar "+ productosIni.size() + " Productos" );
        asignarProcesos();
        ((Buzon) BuzonS).recibeProducto(productosIni.remove());
        
        notify();

    }
    
    
}


public synchronized void asignarProcesos() {
	System.out.println("Se empiezan a asignar procesos de la Etapa 2");
    ProcesoVerde hilo1 = new ProcesoVerde();
    ProcesoAzul hilo2 = new ProcesoAzul();
    
    hilo1.start();
    hilo2.start();
}


class ProcesoVerde extends Thread {
    public void run() {
    	for(Producto Producto : productosIni) {
        	int time = ThreadLocalRandom.current().nextInt(50, 501);
        	System.out.println("ET2: Se ha asignado el proceso naranja al producto " + Producto.getId());
			try {
				sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	
        }
    }
}
class ProcesoAzul extends Thread {
    public void run() {
    	for(Producto Producto : productosIni) {
        	int time = ThreadLocalRandom.current().nextInt(50, 501);
        	System.out.println("ET2: Se ha asignado el proceso azul al producto " + Producto.getId());
			try {
				sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	
        }
    }
}
public void run(){
    
    cargaInicial(2);


}


}