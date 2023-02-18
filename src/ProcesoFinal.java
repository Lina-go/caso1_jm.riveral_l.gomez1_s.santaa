public class ProcesoFinal extends Thread{



private Buzon salida;
private  Buzon entrada;
private int nivel;
private int tipo;
public ProcesoFinal(Buzon buzonE,Buzon buzonS){

    this.salida=buzonS;
    this.entrada=buzonE;
   
}
}