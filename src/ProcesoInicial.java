import java.util.concurrent.ThreadLocalRandom;

public class ProcesoInicial extends Thread {
    private int nivel;
    private String tipo;
    private int numProductos;
    private Buzon buzonS;

    public ProcesoInicial(int nStage, String string, int nProducts, Buzon buzonS) {    
        this.nivel = nStage;
        this.tipo = string;
        this.numProductos = nProducts;
        this.buzonS = buzonS;

    }
    @Override
    public void run(){
        if (tipo.equals("AZUL")){
            for (int i = 0; i < numProductos; i++) {
                Producto prod = new Producto();
                Id.getInstance().setId(prod);

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(50, 501));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            prod.transformar(" Producto "+ Integer.toString(prod.getId()+1) +" A"+"nivel: "+nivel+"tipo: "+tipo+" /");

            buzonS.recibeA(prod);
            }}
        else{
            for (int i = 0; i < numProductos; i++) {
                Producto prod = new Producto();
                Id.getInstance().setId(prod);
    
                try {
                    Integer time = ThreadLocalRandom.current().nextInt(50, 501);
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
    
                prod.transformar(prod.transformar(" Producto "+ Integer.toString(prod.getId()+1) +" N"+"nivel: "+nivel+"tipo: "+tipo+" /"));

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