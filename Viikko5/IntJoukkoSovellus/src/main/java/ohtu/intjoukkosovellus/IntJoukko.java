
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int kapasiteetti = 5; // aloitustalukon koko
    public final static int oletusKasvatus = 5;  // luotava uusi taulukko on näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 
    private int luvunIndeksi = 0; 

    public IntJoukko() {
        lukujono = new int[kapasiteetti];
        alusta(lukujono);
        alkioidenLkm = 0;
        this.kasvatuskoko = oletusKasvatus;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        lukujono = new int[kapasiteetti];
        alusta(lukujono);
        alkioidenLkm = 0;
        this.kasvatuskoko = oletusKasvatus;
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Parametrien on oltava positiivisia");
        }
        lukujono = new int[kapasiteetti];
        alusta(lukujono);
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public void alusta(int[] taulukko) {
        for (int i = 0; i < taulukko.length; i++) {
            taulukko[i] = 0;
        }
    }

    public boolean lisaa(int luku) {
        if (onkoLukuJoukossa(luku)) {
            return false;
        } else {
            if (alkioidenLkm <= lukujono.length+1) {
                lukujono[alkioidenLkm+1] = luku;
            } else {
                lukujono = kasvataTaulukkoa(lukujono);
                lukujono[alkioidenLkm+1] = luku;
            }
        }
        alkioidenLkm++;
        return true;
    }

    public boolean onkoLukuJoukossa(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                luvunIndeksi = i;
                return true;
            }
        }
        return false;
    }    

    public int[] kasvataTaulukkoa(int[] taulukko) {
        int koko = taulukko.length + oletusKasvatus;
        int[] uusi = new int[koko];
        for (int i = 0; i < taulukko.length; i++) {
            uusi[i] = taulukko[i];
        }
        return uusi;
    }

    public boolean poista(int luku) {
        if (onkoLukuJoukossa(luku) == false) {
            return false;
        } else {
            for (int i = luvunIndeksi; i < lukujono.length-1; i++) {
                int siirrettävä = lukujono[i+1];
                lukujono[i] = siirrettävä;
            }
        }
        alkioidenLkm--;
        return true;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + lukujono[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += lukujono[i];
                tuotos += ", ";
            }
            tuotos += lukujono[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        } 
        return z;
    }        
}
