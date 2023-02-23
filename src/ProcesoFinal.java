public class ProcesoFinal extends Thread {

    private Integer numProd;
    private Integer numProces;
    private Buzon buzonS;

    public ProcesoFinal(Integer nProcesos, Integer nProductos, Buzon buzonS) {
        this.numProd = nProductos;
        this.numProces = nProcesos;
        this.buzonS = buzonS;
    }

    public void run() {
        for (int i = 0; i < numProces * numProd; i++) {
            Producto prod = buzonS.darProd(i);
            while (prod == null) {
                prod = buzonS.darProd(i);
            }
            prod.transformar("/FINAL");
            System.out.println(prod.getMsg());
            Main.rep.report("Print final Secuencial-Producto" + prod.getId() + ":" + prod.getMsg());
        }

        Main.rep.report("Finalizó la ejecución del proceso final. Fin de la aplicación");

        Main.rep.close();

    }

}
