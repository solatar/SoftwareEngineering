package ohtu;

import java.util.List;
import java.util.ArrayList;

public class Ostoskori {
    int tavaroita;
    int hinta;
    ArrayList<Ostos> tuotteet;    

    public Ostoskori() {
        this.tavaroita = 0;
        this.hinta = 0;
        this.tuotteet = new ArrayList<>();
    }

    public int tavaroitaKorissa() {
        return this.tavaroita;
    }
 
    public int hinta() {
        return this.hinta;
    }
 
    public void lisaaTuote(Tuote lisattava) {
        int ostettu = 0;
        for (Ostos ostos : tuotteet) {
            if (ostos.tuotteenNimi().equals(lisattava.getNimi())) {
                ostos.muutaLukumaaraa(1);
                ostettu = 1;
            } 
        }
        if (ostettu == 0) {
            tuotteet.add(new Ostos(lisattava));
        } 
        tavaroita++;
        hinta += lisattava.getHinta();
    }
 
    public void poista(Tuote poistettava) {
        for (int i = 0; i < tuotteet.size(); i++) {
            Ostos ostos = tuotteet.get(i);
            if (ostos.tuotteenNimi().equals(poistettava.getNimi())) {
                ostos.muutaLukumaaraa(-1);
                if (ostos.lukumaara() <= 0) {
                    tuotteet.remove(i);
                }
            }
        }  
        tavaroita--;
        hinta -= poistettava.getHinta();      
    }
 
    public List<Ostos> ostokset() {
        return this.tuotteet;
    }
 
    public void tyhjenna() {
        this.tuotteet.clear();
        tavaroita = 0;
        hinta = 0;
    }    
}
