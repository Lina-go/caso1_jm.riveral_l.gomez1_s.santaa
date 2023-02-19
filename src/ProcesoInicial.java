import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * ProcesoInicial
 */
public class ProcesoInicial extends Thread {

    private static Buzon BuzonS;
    private static Queue<Producto> productosIni = new LinkedList<Producto>();
	private Buzon[] buzones;
	private Queue<Producto> productosObtenidos;
    
    public ProcesoInicial(Buzon BuzonSalida, Queue<Producto> productosInicio, Buzon[] buzones){
        this.BuzonS=BuzonSalida;
        this.productosIni=productosInicio;
        this.buzones = buzones;
        

    }

    public synchronized void cargaInicial(){
    	System.out.println("========================== ETAPA 1 ==========================");
    	for (Producto producto : productosIni) {
        	productosObtenidos.add(producto);
        }
        while(productosIni.size()!=0){
            if(BuzonS.getCapacidad()==0){
                try {
                	
                    wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
            System.out.println("ET1: Se comienzan a procesar "+ productosIni.size() + " Productos" );
            asignarProcesos();
            BuzonS.recibeProducto(productosIni.remove());
            
            
            
            
            
            notify();

        }
    }
    
    public synchronized void asignarProcesos() {
        ProcesoVerde hilo1 = new ProcesoVerde();
        ProcesoAzul hilo2 = new ProcesoAzul();
        
        hilo1.start();
        hilo2.start();
    }


    public void run(){
        
        cargaInicial();
        IniciarProcesosIntermedios(3);
        }
        
    public void IniciarProcesosIntermedios(int azules) {
		for(int j=0;j<3;j++){
			
            for(int x=2;x<=azules;x++){
                ProcesoIntermedio Etapa1Azul = new ProcesoIntermedio(buzones[j],buzones[j+1],"AZUL",j+1, productosObtenidos);
                Etapa1Azul.start();
            }
            ProcesoIntermedio Etapa1Naranja = new ProcesoIntermedio(buzones[j],buzones[j+1],"NARANJA",j+1, productosObtenidos);
            Etapa1Naranja.start();
            
		}
          
    }

		  public void setProductosObtenidos (Queue<Producto> productosObtenidos) {
      		this.productosObtenidos = productosObtenidos;
      	}
            
    class ProcesoVerde extends Thread {
        public void run() {
        	for(Producto Producto : productosIni) {
            	int time = ThreadLocalRandom.current().nextInt(500, 5001);
            	System.out.println("ET1: Se ha asignado el proceso naranja al producto " + Producto.getId());
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
        		int time = ThreadLocalRandom.current().nextInt(500, 5001);
	        	System.out.println("ET1: Se ha asignado el proceso azul al producto " + Producto.getId());
				try {
					sleep(time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        	
	        }
        }
    }
    
    
}

	