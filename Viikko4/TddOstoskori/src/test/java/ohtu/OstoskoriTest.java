package ohtu;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OstoskoriTest {

    Ostoskori kori;

    @Before
    public void setUp() {
        kori = new Ostoskori();
    }

    @Test
    public void ostoskorinHintaJaTavaroidenMaaraAlussa() { 
        assertEquals(0, kori.hinta());
        assertEquals(0, kori.tavaroitaKorissa());
    }

    @Test
    public void yhdenTuotteenLisaamisenJalkeenKorissaYksiTuote() {
        Tuote maito = new Tuote("maito", 3); 
        kori.lisaaTuote(maito);
        assertEquals(1, kori.tavaroitaKorissa());
    }    

    @Test
    public void kahdenEriTuotteenLisaamisenJalkeenKorissaKaksiTuotetta() {
        Tuote maito = new Tuote("maito", 3); 
        kori.lisaaTuote(maito);
        Tuote munat = new Tuote("munat", 4);
        kori.lisaaTuote(munat);
        assertEquals(2, kori.tavaroitaKorissa());
    }    

    @Test
    public void ostoskorinHintaOnTuotteidenHintojenSumma() {
        Tuote maito = new Tuote("maito", 3); 
        kori.lisaaTuote(maito);
        Tuote munat = new Tuote("munat", 4);
        kori.lisaaTuote(munat);
        assertEquals(7, kori.hinta);
    }    

    @Test
    public void kahdenSamanTuotteenLisaamisenJalkeenKorissaKaksiTuotetta() {
        Tuote leipa = new Tuote("leipä", 2); 
        kori.lisaaTuote(leipa);
        kori.lisaaTuote(leipa);
        assertEquals(2, kori.tavaroitaKorissa());
    }  

    @Test
    public void ostoskorinHintaOnTuotteidenHintojenSumma2() {
        Tuote leipa = new Tuote("leipä", 2); 
        kori.lisaaTuote(leipa);
        kori.lisaaTuote(leipa);
        assertEquals(4, kori.hinta);
    }  

    @Test
    public void yhdenTuotteenLisaamisenJalkeenKorissaYksiOstosOlio() {
        Tuote munat = new Tuote("munat", 4);
        kori.lisaaTuote(munat); 
        List<Ostos> ostokset = kori.ostokset();
        assertEquals(1, ostokset.size());
    }  

    @Test
    public void yhdenTuotteenLisaamisenKorissaYksiOstosOlioJollaOikeaTuotteenNimiJaMaara() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito); 
        Ostos ostos = kori.ostokset().get(0);
        assertEquals(maito.getNimi(), ostos.tuotteenNimi());
        assertEquals(maito.getHinta(), ostos.hinta());
    } 

    @Test
    public void kahdenEriTuotteenLisaamisenJalkeenKorissaKaksiOstosta() {
        Tuote maito = new Tuote("maito", 3); 
        kori.lisaaTuote(maito);
        Tuote munat = new Tuote("munat", 4);
        kori.lisaaTuote(munat);
        List<Ostos> ostokset = kori.ostokset();
        assertEquals(2, ostokset.size());
    }  
    
    @Test
    public void kahdenSamanTuotteenLisaamisenJalkeenKorissaYksiOstos() {
        Tuote leipa = new Tuote("leipä", 2); 
        kori.lisaaTuote(leipa);
        kori.lisaaTuote(leipa);
        List<Ostos> ostokset = kori.ostokset();
        Ostos ostos = ostokset.get(0);
        assertEquals(1, ostokset.size());
        assertEquals(leipa.getNimi(), ostos.tuotteenNimi());
        assertEquals(2, ostos.lukumaara());
    } 
    
    @Test 
    public void ostostenMaaraEiMuutuJosSamaaTuotettaJaaKoriin() {
        Tuote suklaa = new Tuote("suklaa", 7); 
        kori.lisaaTuote(suklaa);
        kori.lisaaTuote(suklaa);
        kori.poista(suklaa);
        List<Ostos> ostokset = kori.ostokset();
        Ostos ostos = ostokset.get(0);
        assertEquals(1, ostokset.size());
        assertEquals(1, ostos.lukumaara());
    }  

    @Test 
    public void ainoanTuotteenPoistoTyhjentääKorin() {
        Tuote salaatti = new Tuote("salaatti", 5);
        kori.lisaaTuote(salaatti);
        kori.poista(salaatti);
        List<Ostos> ostokset = kori.ostokset();
        assertEquals(0, ostokset.size());
        assertEquals(0, kori.tavaroitaKorissa());
        assertEquals(0, kori.hinta());
    }

    @Test
    public void tyhjennettyKoriTyhja() {
        Tuote salaatti = new Tuote("salaatti", 5);
        kori.lisaaTuote(salaatti);    
        Tuote suklaa = new Tuote("suklaa", 7); 
        kori.lisaaTuote(suklaa);
        kori.tyhjenna();         
        List<Ostos> ostokset = kori.ostokset();
        assertEquals(0, ostokset.size());
        assertEquals(0, kori.tavaroitaKorissa());
        assertEquals(0, kori.hinta());          
    }
}
