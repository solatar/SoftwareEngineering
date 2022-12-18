package ohtu.kivipaperisakset;

public class KPSPelaajaVsPelaaja extends KiviPaperiSakset {

    @Override
    protected String toisenSiirto() {
        tokanSiirto = scanner.nextLine();
        return tokanSiirto;
    }
}