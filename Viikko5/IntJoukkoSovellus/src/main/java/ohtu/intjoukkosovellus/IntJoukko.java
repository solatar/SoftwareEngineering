
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
        if (kuuluu(luku)) {
            return false;
        } else {
            if (alkioidenLkm <= lukujono.length-1) {
                lukujono[alkioidenLkm] = luku;
            } else {
                lukujono = kasvataTaulukkoa(lukujono);
                lukujono[alkioidenLkm] = luku;
            }
        }
        this.alkioidenLkm++;
        return true;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < this.lukujono.length; i++) {
            if (lukujono[i] == luku) {
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
        if (kuuluu(luku) == false) {
            return false;
        } else {
            for (int i = luvunIndeksi; i < alkioidenLkm-1; i++) {
                int siirrettävä = lukujono[i+1];
                lukujono[i] = siirrettävä;
                System.out.println("siirrettiin luku " +siirrettävä+ " indeksiin " +(i));
            }
            lukujono[alkioidenLkm-1] = 0;
            alkioidenLkm--;
        }
        return true;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String merkkijonoEsitys = "";
        if (this.alkioidenLkm == 0) {
            merkkijonoEsitys = "{}";
        } else {
            merkkijonoEsitys += "{";
            for (int i=0; i<alkioidenLkm-1; i++) {
                merkkijonoEsitys += lukujono[i];
                merkkijonoEsitys += ", ";
            }
            merkkijonoEsitys += lukujono[alkioidenLkm-1];
            merkkijonoEsitys += "}";
        }
        return merkkijonoEsitys;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        int koko = a.alkioidenLkm + b.alkioidenLkm;
        IntJoukko yhdiste = new IntJoukko(koko);
        for (int i=0; i<a.alkioidenLkm; i++) {
            yhdiste.lukujono[i] = a.lukujono[i];
        }
        for (int i=0; i<b.alkioidenLkm; i++) {
            yhdiste.lukujono[i+a.alkioidenLkm] = b.lukujono[i];
        }
        yhdiste.alkioidenLkm = koko;
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkaus.lisaa(bTaulu[j]);
                }
            }
        }        
        return leikkaus;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            erotus.lisaa(aTaulu[i]);
        }
        System.out.println(erotus);
        for (int i = 0; i < bTaulu.length; i++) {
            erotus.poista(bTaulu[i]);
        } 
        System.out.println(erotus);
        return erotus;
    }     
}

