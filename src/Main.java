
/**
 * Main
 */
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import Utils.Reporter;

public class Main {

    private static int numProductos = 0;
    private static int numProcedimientos = 0;
    private static int capacidadB = 0;
    private static ArrayList<Producto> productosCreados = new ArrayList<Producto>();
    private static int azules = 0;
    public static final Reporter rep = new Reporter("ExecReport " + LocalDateTime.now().toString());

    public static void main(String args[]) {
        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);

        // Consola: Se le piden al usuario params(productos,procesos,capacidad)
        System.out.println("Numero de Productos:");
        numProductos = in.nextInt();
        Main.rep.report("Numero de Productos: " + numProductos);

        System.out.println("Numero de Procesos:");
        numProcedimientos = in.nextInt();
        Main.rep.report("Numero de Procesos: " + numProcedimientos);

        System.out.println("Capacidad de Buzones:");
        capacidadB = in.nextInt();
        Main.rep.report("Capacidad: " + capacidadB);

        azules = numProcedimientos - 1;
        System.out.println("\nHay " + azules + " procesos azules y 1 proceso naranja por nivel\n");
        Main.rep.report("\nHay " + azules + " procesos azules y 1 proceso naranja por nivel\n");

        // crea productos
        for (int i = 0; i < numProductos; i++) {
            Producto newProd = new Producto(i + 1);
            productosCreados.add(newProd);
        }
        System.out.println("productos creados exitosamente");

        BuzonInicial bInicio = new BuzonInicial(capacidadB);
        BuzonIntermedio bI1 = new BuzonIntermedio(capacidadB, 1);
        BuzonIntermedio bI2 = new BuzonIntermedio(capacidadB, 2);
        BuzonFinal bFinalInf = new BuzonFinal(Integer.MAX_VALUE);
        Buzon[] buzones = { bInicio, bI1, bI2, bFinalInf };

        System.out.println("buzones creados exitosamente");

        ProcesoInicial inicial = new ProcesoInicial(bInicio, productosCreados);
        System.out.print("\nSe inicia el proceso Incial...");
        inicial.start();

        for (int j = 0; j < 3; j++) {
            for (int x = 0; x < azules; x++) {
                ProcesoIntermedio pAzul = new ProcesoIntermedio(buzones[j], buzones[j + 1], "AZUL", j + 1,
                        numProductos);
                pAzul.start();
                // System.out.println("Proceso " + " AZUL " + " iniciado en nivel: "+(j+1));

            }
            ProcesoIntermedio pNaranja = new ProcesoIntermedio(buzones[j], buzones[j + 1], "NARANJA", j + 1,
                    numProductos);
            pNaranja.start();

            // System.out.println("Proceso " + " Naranja " + " iniciado en nivel: "+(j+1));
        }

        ProcesoFinal finalP = new ProcesoFinal(bFinalInf, numProcedimientos);
        finalP.start();
        rep.report("Finalizó la ejecución del proceso final. Fin de la aplicación");
        rep.close();
    }

}