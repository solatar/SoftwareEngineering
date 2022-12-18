package ohtu.kivipaperisakset;

public class KPSTekoaly extends KiviPaperiSakset {

    @Override
    protected String toisenSiirto() {
        tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        tekoaly.asetaSiirto(ekanSiirto);
        return tokanSiirto;
    }   
}