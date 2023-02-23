public class ProcesoFinal extends Thread{
    private  Buzon entrada;
    private int numProd;
    private int numProceso;
    
public ProcesoFinal(Buzon buzonE, int numProductos, int numProcedimientos){
    this.entrada=buzonE;
    this.numProd=numProductos;
    this.numProceso=numProcedimientos;
}


@Override
public void run(){
    Integer i = 0;
    while (i < numProd* numProceso) {
        Producto producto = entrada.giveProduct(i);
        while (producto == null) {
            producto = entrada.giveProduct(i);
        }
        producto.transformar("FIN");
        System.out.println(producto.getMsg());
        i++;
    }
}
}