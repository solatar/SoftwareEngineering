package ohtu;

public class Main {
    public static void main(String[] args) {
        Ostoskori kori = new Ostoskori();
        Tuote salaatti = new Tuote("salaatti", 5);
        kori.lisaaTuote(salaatti);
        kori.poista(salaatti);
    }
}