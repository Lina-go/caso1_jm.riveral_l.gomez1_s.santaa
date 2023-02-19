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
        Scanner in = new Scanner(System.in);
 
        // Pedimos Productos
        System.out.println("Numero de Productos:");
        numProductos  = in.nextInt();
        System.out.println("Numero de Productos: " + numProductos);
 
        // Pedimos No. de Procesos
        System.out.println("Numero de Procesos:");
        numProcedimientos = in.nextInt();
        System.out.println("Numero de Procesos: " + numProcedimientos);

        // Pedimos No. de Capacidad del Buzón
         System.out.println("Capacidad de Buzones:");
        capacidadB = in.nextInt();
        System.out.println("Capacidad: " + numProcedimientos);

        azules=numProcedimientos-1;
        naranjas=1;

        //Creamos Productos
       for (int i = 0; i < numProductos; i++) {
        Producto newProd = new Producto(i);
        productosCreados.add(newProd);
        
       }
        System.out.println("productos creados exitosamente");

        // Creamos Buzones:
        
        // Buzon Inicial
        BuzonInicial bInicio= new BuzonInicial(capacidadB);
        
        // Buzones Intermedios
        BuzonIntermedio bI1= new BuzonIntermedio(capacidadB);
        BuzonIntermedio bI2= new BuzonIntermedio(capacidadB);
        BuzonIntermedio bI3 = new BuzonIntermedio(capacidadB);
        
        // Buzon Final
        BuzonFinal bFinal = new BuzonFinal();
        
        // Lista de Buzones
        Buzon[] buzones= {bInicio,bI1,bI2,bI3};
        
        System.out.println("buzones creados exitosamente");
        // Creamos Proceso Inicial
        ProcesoInicial inicial= new ProcesoInicial(bI1,productosCreados, buzones);
        inicial.start();
        
        // Productos Obtenidos de la Etapa #1
        Queue<Producto> ProductosObtenidos = bInicio.listaDeProductosObtenidos();
        inicial.setProductosObtenidos(ProductosObtenidos);
        
        // Productos Obtenidos de la Etapa #2
        Queue<Producto> ProductosObtenidos1 = bI1.listaDeProductosObtenidos();
        Queue<Producto> ProductosObtenidos2 = bI2.listaDeProductosObtenidos();
        Queue<Producto> ProductosObtenidos3 = bI3.listaDeProductosObtenidos();
        // Productos Obtenidos de la Etapa #3
        Queue<Producto> ProductosObtenidos4 = bFinal.listaDeProductosObtenidos();
        

        
        
        // Creamos Proceso Final
        ProcesoFinal finalP = new ProcesoFinal(bI3, bFinal);
        finalP.start();
        
        System.out.println("Productos procesados creados exitosamente");

        



    }
    
}