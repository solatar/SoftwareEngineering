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
        this.tuotteet.add(new Ostos(lisattava));
        tavaroita++;
        hinta += lisattava.getHinta();
    }
 
    public void poista(Tuote poistettava) {
        // poistaa tuotteen
    }
 
    public List<Ostos> ostokset() {
        // palauttaa listan jossa on korissa olevat ostokset
 
        return null;
    }
 
    public void tyhjenna() {
        // tyhjentää korin
    }    
}
