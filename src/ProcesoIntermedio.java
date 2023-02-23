import java.util.concurrent.ThreadLocalRandom;

public class ProcesoIntermedio extends Thread {
    private Integer pNivel;
    private String tipo;
    private Integer numProductos;
    private Buzon buzonE;
    private Buzon buzonS;

    public ProcesoIntermedio(int nivel, String col, int numProd, Buzon buzonEntrada,Buzon buzonSalida) {
        this.buzonE = buzonEntrada;
        this.buzonS = buzonSalida;
        this.pNivel = nivel;
        this.tipo = col;
        this.numProductos = numProd;
    }

@Override
public void run(){
    if (tipo.equals("AZUL")){
        for (int i = 0; i < numProductos; i++) {
            Producto product = buzonE.sacaA();
            try {
                Integer time = ThreadLocalRandom.current().nextInt(50, 501);
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            product.transformar("A"+"nivel: "+pNivel+"tipo: "+tipo+" /");

            buzonS.recibeA(product);
        }
    }
    else{
        for (int i = 0; i < numProductos; i++) {
            while (buzonE.vacioN()){
                Thread.yield();}
            Producto prod = buzonE.sacaN();
            synchronized (buzonE) {
                buzonE.notifyAll();
            }

            try {
                Integer time = ThreadLocalRandom.current().nextInt(50, 500 + 1);
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            prod.transformar("N"+"nivel: "+pNivel+"tipo: "+tipo);

            while (buzonS.llenoN()){
                Thread.yield();}
            buzonS.recibeN(prod);
            synchronized (buzonS) {
                buzonS.notifyAll();
            }
        }

    }
}

}