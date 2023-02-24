import java.time.LocalDateTime;
import java.util.Scanner;
import Utils.Reporter;

public class Main {

    private static int capacidadB;
    private static int numProductos;
    private static int numProcedimientos;
    public static final Reporter rep = new Reporter("ExecReport " + LocalDateTime.now().toString());

    public static void main(String[] args) {

        try (// Using Scanner for Getting Input from User
                Scanner in = new Scanner(System.in)) {
            // Consola: Se le piden al usuario params(productos,procesos,capacidad)
            System.out.println("Numero de Productos:");
            numProductos = in.nextInt();
            Main.rep.report("Numero de Productos: " + numProductos);
            System.out.println("Numero de Productos: " + numProductos);

            System.out.println("Numero de Procesos:");
            numProcedimientos = in.nextInt();
            Main.rep.report("Numero de Procesos: " + numProcedimientos);
            System.out.println("Numero de Procesos: " + numProcedimientos);

            System.out.println("Capacidad de Buzones:");
            capacidadB = in.nextInt();
            Main.rep.report("Capacidad: " + capacidadB);
            if (capacidadB < 1) {
                System.out.println(
                        "La capacidad dada no es valida, esta debe ser mayor o igual a 1.Se va a terminar la ejecucion, intente nuevamente");
                System.exit(1);
            }

        }
        // Se crean los Buzones
        BuzonInicial bI1 = new BuzonInicial(1, capacidadB);
        BuzonIntermedio bI2 = new BuzonIntermedio(2, capacidadB);
        BuzonFinal bF = new BuzonFinal(3);
        int azules = numProcedimientos - 1;
        Main.rep.report("\nHay " + azules + " procesos azules y 1 proceso naranja por nivel\n");

        /*
         * ETAPA 1: Se inicia el proceso Inicial quien: "En la primera etapa se crea el
         * producto y se le
         * asigna un número consecutivo para su identificación".
         */
        Main.rep.report("Se inician procesos");

        for (int i = 0; i < azules; i++) {
            ProcesoInicial pIA = new ProcesoInicial(1, "AZUL", numProductos, bI1);
            pIA.start();
        }
        ProcesoInicial pIN = new ProcesoInicial(1, "NARANJA", numProductos, bI1);
        pIN.start();

        /*
         * ETAPA 2 y 3: En la segunda y tercera etapa se transforman para lograr el
         * producto final y se termina
         * la producción. Si bien en el paso de etapa en etapa no es necesario respetar
         * el orden
         */
        Buzon[] buzones = { bI1, bI2, bF };
        for (int j = 0; j < 2; j++) {
            for (int x = 0; x < azules; x++) {
                ProcesoIntermedio pAzul = new ProcesoIntermedio(j + 2, "AZUL", numProductos, buzones[j],
                        buzones[j + 1]);
                pAzul.start();
            }
            ProcesoIntermedio pNaranja = new ProcesoIntermedio(j + 2, "NARANJA", numProductos, buzones[j],
                    buzones[j + 1]);
            pNaranja.start();
        }

        /*
         * ETAPA FINAL: El proceso final (rojo) debe imprimir en estricto orden los
         * productos generados.
         *
         */
        ProcesoFinal finalProcess = new ProcesoFinal(numProcedimientos, numProductos, bF);
        finalProcess.start();
    }

}