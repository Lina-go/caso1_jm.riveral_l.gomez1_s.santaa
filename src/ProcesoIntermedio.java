public class ProcesoIntermedio extends Thread{

private Buzon salida;
private  Buzon entrada;
private int nivel;
private String tipo;
public ProcesoIntermedio(Buzon buzonE,Buzon buzonS,String ntipo,int pnivel){

    this.salida=buzonS;
    this.entrada=buzonE;
    this.tipo=ntipo;
    this.nivel=pnivel;
}








}