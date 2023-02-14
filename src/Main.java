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
    private static Queue<Producto> productosCreados = new LinkedList<Producto>();



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
 
       for (int i = 0; i < numProductos; i++) {
        Producto newProd = new Producto(i);
        productosCreados.add(newProd);
        
       }
       System.out.println("productos creados exitosamente");

    }
    
}