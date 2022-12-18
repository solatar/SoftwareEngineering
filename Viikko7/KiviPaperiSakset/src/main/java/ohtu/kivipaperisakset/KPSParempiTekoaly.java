package ohtu.kivipaperisakset;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KiviPaperiSakset {
  
    @Override
    protected String toisenSiirto() {
        tokanSiirto = parempiTekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        parempiTekoaly.asetaSiirto(ekanSiirto);
        return tokanSiirto;
    }    
}
