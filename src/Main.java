/**
 * Main
 */
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import Utils.Reporter;

public class Main {

    private static int numProductos=0;
    private static int numProcedimientos=0;
    private static int capacidadB =0;
    private static Queue<Producto> productosCreados = new LinkedList<Producto>();
    private static int azules=0;
    private static int naranjas=0;


    public static void main(String args[])
    {
        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);
 
        System.out.println("Numero de Productos:");
        numProductos  = in.nextInt();
        System.out.println("Numero de Productos: " + numProductos);
 
        System.out.println("Numero de Procesos:");
        numProcedimientos = in.nextInt();
        System.out.println("Numero de Procesos: " + numProcedimientos);

         System.out.println("Capacidad de Buzonez:");
        capacidadB = in.nextInt();
        System.out.println("Capacidad: " + numProcedimientos);

        azules=numProcedimientos-1;
        naranjas=1;

        //crea productos
       for (int i = 0; i < numProductos; i++) {
        Producto newProd = new Producto(i);
        productosCreados.add(newProd);
        
       }
        System.out.println("productos creados exitosamente");


        BuzonInicial bInicio= new BuzonInicial(capacidadB);
        BuzonFinal bFinal = new BuzonFinal();
        BuzonIntermedio bI1= new BuzonIntermedio(capacidadB);
        BuzonIntermedio bI2= new BuzonIntermedio(capacidadB);
        BuzonIntermedio bI3 = new BuzonIntermedio(capacidadB);
        Buzon[] buzones= {bInicio,bI1,bI2,bI3};
        System.out.println("buzones creados exitosamente");

        ProcesoInicial inicial= new ProcesoInicial(bI1,productosCreados);

        for(int j=0;j<3;j++){
            for(int x=1;x<=azules;x++){
                ProcesoIntermedio pInter= new ProcesoIntermedio(buzones[j],buzones[j+1],"AZUL",j+1);
            }
            ProcesoIntermedio pNaranja = new ProcesoIntermedio(buzones[j],buzones[j+1],"NARANJA",j+1);
        }
        ProcesoFinal finalP = new ProcesoFinal(bI3, bFinal);
        

        System.out.println("procesos creados exitosamente");



    }
    
}