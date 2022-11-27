package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa kauppa;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        kauppa = new Kauppa(varasto, pankki, viite);  
    }    


    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        kauppa.tilimaksu("pekka", "12345");
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }    
    
    @Test
    public void pankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
        when(viite.uusi()).thenReturn(50);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "juusto", 10));    
        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1); 
        kauppa.lisaaKoriin(2);  
        kauppa.tilimaksu("mikael", "88888");

        verify(pankki).tilisiirto(eq("mikael"), eq("50"), eq("88888"), anyInt(), eq("15"));   
    }    

    @Test
    public void pankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla2() {
        when(viite.uusi()).thenReturn(51);
        when(varasto.saldo(3)).thenReturn(10); 
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "kurkku", 3));  
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(3); 
        when(varasto.saldo(3)).thenReturn(9); 
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "kurkku", 3));
        kauppa.lisaaKoriin(3);  
        kauppa.tilimaksu("linus", "88889");
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("linus"), eq("51"), eq("88888"), eq("33333-44455"), eq("6"));   
    }   

    @Test
    public void pankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla3() {
        when(viite.uusi()).thenReturn(52);
        when(varasto.saldo(4)).thenReturn(15); 
        when(varasto.haeTuote(4)).thenReturn(new Tuote(4, "omena", 2));  
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(4); 
        when(varasto.saldo(5)).thenReturn(0); 
        kauppa.lisaaKoriin(5);  
        kauppa.tilimaksu("timotei", "88890");
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("timotei"), eq("52"), eq("88890"), eq("33333-44455"), eq("2"));      
    }    
}
