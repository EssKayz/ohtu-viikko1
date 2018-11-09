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

    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);

    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void adiu1() {
        varasto = new Varasto(-1, -1);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void adiu2() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void diu1() {
        varasto = new Varasto(-1);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void diu2() {
        varasto = new Varasto(10, 0);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void diu3() {
        varasto = new Varasto(10, 10);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void diu4() {
        varasto = new Varasto(10, 12);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void diu5() {
        varasto = new Varasto(10, 0);
        assertEquals(0, varasto.otaVarastosta(-5), vertailuTarkkuus);
    }

    @Test
    public void diu6() {
        String correct = "saldo = 0.0, tilaa " + varasto.paljonkoMahtuu();
        boolean diu = true;
        
        if (!varasto.toString().contains("saldo = " + varasto.getSaldo())) {
            diu = false;
        }
        
        if (!varasto.toString().contains("tilaa " + varasto.paljonkoMahtuu())) {
            diu = false;
        }
        
        assertEquals(true, diu);
        
    }

    @Test
    public void haistapaskavitunbuginennetbeans() {
        double max = varasto.getTilavuus();
        double diu = max - varasto.getSaldo();
        assertEquals(diu, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void liikaaTavaraa() {
        double tilavuus = varasto.getTilavuus();
        varasto.lisaaVarastoon(tilavuus + 10);
        assertEquals(tilavuus, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void otaLiikaaTavaraa() {
        double saldo = varasto.getSaldo();
        varasto.otaVarastosta(saldo + 2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
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
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}
