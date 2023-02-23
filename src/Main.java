import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    private static int numProductos=0;
    private static int numProcedimientos=0;
    private static int capacidadB =0;
    private static Queue<Producto> productosCreados = new LinkedList<Producto>();
    private static int azules=0;


    public static void main(String[] args)
    {
        try (// Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in)) {
            //Consola: Se le piden al usuario params(productos,procesos,capacidad)
            System.out.println("Numero de Productos:");
            numProductos  = in.nextInt();
            System.out.println("Numero de Productos: " + numProductos);
 
            System.out.println("Numero de Procesos:");
            numProcedimientos = in.nextInt();
            System.out.println("Numero de Procesos: " + numProcedimientos);

            System.out.println("Capacidad de Buzones:");
            capacidadB = in.nextInt();
        }
        
        System.out.println("Capacidad: " + capacidadB);

        azules=numProcedimientos-1;
        System.out.println("\nHay "+ azules+ " procesos azules y 1 proceso naranja\n");


        for (int i = 0; i < numProductos; i++) {
                Producto newProd = new Producto(i+1);
                productosCreados.add(newProd);
       }
        System.out.println("productos creados exitosamente");


        BuzonInicial bInicio= new BuzonInicial(capacidadB);
        BuzonIntermedio bI1= new BuzonIntermedio(capacidadB);
        BuzonIntermedio bI2= new BuzonIntermedio(capacidadB);
        BuzonIntermedio bI3 = new BuzonIntermedio(capacidadB);
        bI3.setFinal();

        Buzon[] buzones= {bInicio,bI1,bI2,bI3};


        System.out.println("buzones creados exitosamente");


        ProcesoInicial inicial= new ProcesoInicial(bInicio,productosCreados);
        System.out.println("-----------------------------CARGA iniciada -------------------------------------\n");
        inicial.start();
        
        System.out.println("-----------------------------ETAPA 1,2,3 iniciada -------------------------------------\n");
        for(int j=0;j<3;j++){
            for(int x=0;x<azules;x++){
                ProcesoIntermedio pAzul= new ProcesoIntermedio(buzones[j],buzones[j+1],"AZUL",j+1,numProductos,x); 
                pAzul.start();
            }
            ProcesoIntermedio pNaranja = new ProcesoIntermedio(buzones[j],buzones[j+1],"NARANJA",j+1,numProductos,azules+1);
            pNaranja.start();
            
        }
        System.out.println("Numero de Productos: " + numProductos);
        System.out.println("Numero de Procesos: " + numProcedimientos);

        ProcesoFinal finalP = new ProcesoFinal(bI3,numProductos,numProcedimientos);
        finalP.start();

    }
    
}