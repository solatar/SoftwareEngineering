package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KiviPaperiSakset {
    Scanner scanner = new Scanner(System.in);
    String ekanSiirto;
    String tokanSiirto;
    static Tuomari tuomari;
    static Tekoaly tekoaly;
    static TekoalyParannettu parempiTekoaly;
      
    // tämä on ns template metodi
    public void pelaa() {
        tuomari = new Tuomari();
        
        ekanSiirto = ensimmaisenSiirto();
        System.out.print("Toisen pelaajan siirto: ");
        tokanSiirto = toisenSiirto();
        
        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();
            ekanSiirto = ensimmaisenSiirto();
            tokanSiirto = toisenSiirto();           

        }
        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }
    
    protected String ensimmaisenSiirto() {
        System.out.print("Ensimmäisen pelaajan siirto: ");
        return scanner.nextLine();
    }
    // tämä on abstrakti metodi sillä sen toteutus vaihtelee eri pelityypeissä
    abstract protected String toisenSiirto();

    public static KiviPaperiSakset luoKPSPelaajaVsPelaaja()  {
        tuomari = new  Tuomari();
        return new KPSPelaajaVsPelaaja();
    }  
    
    public static KiviPaperiSakset luoKPSTekoaly()  {
        tuomari = new  Tuomari();
        tekoaly = new Tekoaly();
        return new KPSTekoaly();
    }  
        
    public static KiviPaperiSakset luoKPSParempiTekoaly()  {
        tuomari = new  Tuomari();
        parempiTekoaly = new TekoalyParannettu(20);
        return new KPSParempiTekoaly();
    }  

    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}
