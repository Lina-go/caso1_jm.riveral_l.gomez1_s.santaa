public class BuzonIntermedio extends Buzon {
    public BuzonIntermedio(int number, int capacity) {
        super.tamano = capacity;
        super.name = "Buzón Intermedio " + number;
        super.esFinal = false;
    }
}
