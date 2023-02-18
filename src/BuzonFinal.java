import java.util.ArrayList;

/**
 * BuzonFinal
 */
public class BuzonFinal extends Buzon{

private ArrayList<Producto> listaFin;
    public BuzonFinal(){
        super(tamano);
        listaFin= new ArrayList<Producto>();
    }
    @Override
    public void recibeProducto(Producto prod) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public String sacaProducto() {
        // TODO Auto-generated method stub
        return null;
    }
}