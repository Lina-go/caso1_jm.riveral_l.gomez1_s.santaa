/**
 * Main
 */
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;
import Utils.Reporter;

public class Main {

    private static int numProductos=0;
    private static int numProcedimientos=0;
    private static int capacidadB =0;
    private static Queue<Producto> productosCreados = new LinkedList<Producto>();
    private static Queue<Producto> productosTransformados = new LinkedList<Producto>();
    private static int azules=0;
    private static int naranjas=0;


    public static void main(String args[])
    {
        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);
        
        //Consola: Se le piden al usuario params(productos,procesos,capacidad)
        System.out.println("Numero de Productos:");
        numProductos  = in.nextInt();
        System.out.println("Numero de Productos: " + numProductos);
 
        System.out.println("Numero de Procesos:");
        numProcedimientos = in.nextInt();
        System.out.println("Numero de Procesos: " + numProcedimientos);

        System.out.println("Capacidad de Buzones:");
        capacidadB = in.nextInt();
        System.out.println("Capacidad: " + capacidadB);

        azules=numProcedimientos-1;
        naranjas=1;
        System.out.println("\nHay "+ azules+ " procesos azules y 1 proceso naranja\n");

        //crea productos
        for (int i = 0; i < numProductos; i++) {
            if (i%2==0){
                Producto newProd = new Producto(i,true);
                productosCreados.add(newProd);
            }
            else{
                
                Producto newProd = new Producto(i,false);
                productosCreados.add(newProd);
            }
       }
        System.out.println("productos creados exitosamente");


        BuzonInicial bInicio= new BuzonInicial(capacidadB);
        BuzonIntermedio bI1= new BuzonIntermedio(capacidadB);
        BuzonIntermedio bI2= new BuzonIntermedio(capacidadB);
        BuzonIntermedio bI3 = new BuzonIntermedio(capacidadB);
        BuzonFinal bFinal = new BuzonFinal();
        Buzon[] buzones= {bInicio,bI1,bI2,bI3};

        ProcesoFinal.setBarrier(new CyclicBarrier(numProcedimientos+1));

        System.out.println("buzones creados exitosamente");


        ProcesoInicial inicial= new ProcesoInicial(bI1,productosCreados);
        System.out.print("\nSe inicia el proceso Incial...");
        inicial.start();
        
        for(int j=0;j<3;j++){
            for(int x=0;x<azules;x++){
                ProcesoIntermedio pAzul= new ProcesoIntermedio(buzones[j],buzones[j+1],"AZUL",j+1,numProductos); 
                pAzul.start();
            }
            ProcesoIntermedio pNaranja = new ProcesoIntermedio(buzones[j],buzones[j+1],"NARANJA",j+1,numProductos);
            pNaranja.start();
            
        }
        try {
            ProcesoFinal.getBarrier().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ProcesoFinal finalP = new ProcesoFinal(bI3, bFinal);
        finalP.start();
        System.out.println("procesos finalizados exitosamente");

    }
    
}