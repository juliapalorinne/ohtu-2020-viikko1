package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    Varasto varasto3;
    Varasto varasto4;
    Varasto varasto5;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(10, 2);
        varasto3 = new Varasto(-10, -2);
        varasto4 = new Varasto(-10);
        varasto5 = new Varasto(10, 12);
    }

    @Test
    public void konstruktori1LuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktori2AsettaaAlkusaldon() {
        assertEquals(2, varasto2.getSaldo(), vertailuTarkkuus);
        assertEquals(0, varasto3.getSaldo(), vertailuTarkkuus);
        assertEquals(10, varasto5.getSaldo(), vertailuTarkkuus);
    }

    
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    
    @Test
    public void uudellaVarastollaEiNegatiivinenTilavuus() {
        assertEquals(0, varasto4.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto3.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysEiYlitaVarastonKokoa() {
        varasto.lisaaVarastoon(12);
        // varasto tulee täyteen, muttei ylity
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    
    @Test
    public void negatiivinenLisaysEiOnnistu() {
        varasto.lisaaVarastoon(-2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttaminenEiOnnistu() {
        varasto2.otaVarastosta(-2);
        assertEquals(2, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisaaTilaa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenEiMuutaSaldoaNegatiiviseksi() {
        varasto2.otaVarastosta(3);

        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }

    
    @Test
    public void toStringPalauttaaOikeanMerkkijonon() {
        assertEquals("saldo = 2.0, vielä tilaa 8.0", varasto2.toString());
    }
}