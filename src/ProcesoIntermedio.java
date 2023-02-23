import java.util.concurrent.ThreadLocalRandom;

public class ProcesoIntermedio extends Thread{

private Buzon salida;
private  Buzon entrada;
private int nivel;
private int numProceso;
private String tipo;
private int numProductos;

public ProcesoIntermedio(Buzon buzonE,Buzon buzonS,String ntipo,int pnivel,int numProducto, int numProc){
    this.numProductos = numProducto;
    this.salida=buzonS;
    this.entrada=buzonE;
    this.tipo=ntipo;
    this.nivel=pnivel;
    this.numProceso = numProc;
}
private void procesoA(Producto prod){
    try {
        sleep(ThreadLocalRandom.current().nextInt(50, 501));
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    
    prod.transformar("T.tipo:"+tipo+" nivel:"+nivel+"numProceso:"+numProceso+"/");
    salida.recibeProductoA(prod);
    System.out.println("Soy el producto "+prod.getMsg());
}

private void procesoN(Producto prod){
    synchronized (entrada) {
        entrada.notifyAll();
    }
    try {
        sleep(ThreadLocalRandom.current().nextInt(50, 501));
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    prod.transformar("T.tipo:"+tipo+" nivel:"+nivel+"numProceso:"+numProceso+"/");
    while (salida.orangeIsFull()){
                Thread.yield();
    }
    salida.recibeProductoN(prod);
    synchronized (salida) {
        salida.notifyAll();
    }
    System.out.println("Soy el producto "+prod.getMsg());
}

@Override
public void run(){
    
    for (int i=0;i<numProductos;i++){
        
        if(tipo.equals("NARANJA")){

            if (nivel==1){
                while (entrada.colaIsEmpty()){
                    Thread.yield();}
                    Producto prod = entrada.sacaProducto();
                
                procesoN(prod);
            }
            
            else if (nivel==2 || nivel==3){
                while (entrada.orangeIsEmpty()){
                    Thread.yield();}
                Producto prod = entrada.sacaProductoN();
                procesoN(prod);
            }
            else{
                throw new UnsupportedOperationException("Solo hay 3 niveles!!");
            }
        }
        else if (tipo.equals("AZUL")) {

            //Si la etapa (nivel) es 1 
            if (nivel==1){
                Producto prod = entrada.sacaProducto();
                procesoA(prod);
            }
            // Si la entrada es 2 o 3
            else if (nivel==2 || nivel==3){
                Producto prod = entrada.sacaProductoA();
                procesoA(prod);
            }
            else{
                throw new UnsupportedOperationException("Solo hay 3 niveles!!");
            }
        }
    }
    System.out.println("-----------------------------ETAPA 1,2,3 finalizada -------------------------------------\n");
}
}



