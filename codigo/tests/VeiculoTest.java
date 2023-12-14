import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


public class VeiculoTest {

    private Carro carro;
    private Rota rota1;
    private Rota rota2;

    @Before
    public void setUp() {
        Frota frota = new Frota();
        carro = new Carro("ABC1234", ECombustivel.GASOLINA);
        frota.adicionarVeiculo(carro);
        rota1 = new Rota(20.0, new Date());
        rota2 = new Rota(40.0, new Date());
    }

    @Test
    public void testPercorrerRotaAbastecimentoAutomatico() {
        // Autonomia atual insuficiente para a rota
        assertTrue(carro.percorrerRota(rota1));
        // Autonomia é abastecida automaticamente
        assertEquals(1, carro.qtdRotasPercorridas());
    }

    @Test
    public void testPercorrerRota(){
        Frota frota = new Frota();
        carro = new Carro("ABC1234", ECombustivel.GASOLINA);
        frota.adicionarVeiculo(carro);
        rota1 = new Rota(20.0, new Date());
        rota2 = new Rota(40.0, new Date());

        carro.percorrerRota(rota1);
        assertEquals(0, carro.tanque.getCapacidadeAtual(),0.01);
    }

    @Test
    public void testKmNoMes() throws ParseException {
        
        carro.percorrerRota(rota1);
        assertEquals(20.0, carro.kmNoMes(), 0.01);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Rota rotaForaDoMes = new Rota(30.0, cal.getTime());
        carro.percorrerRota(rotaForaDoMes);

        assertEquals(20.0, carro.kmNoMes(), 0.01);
    }

    @Test
    public void testKmTotal() throws ParseException {
        carro.percorrerRota(rota1);
        carro.percorrerRota(rota2);
        assertEquals(60.0, carro.kmTotal(), 0.01);
    }

    @Test
    public void testPlacaCorresponde() {
        assertTrue(carro.placaCorresponde("ABC1234"));
        assertFalse(carro.placaCorresponde("XYZ5678"));
    }


    @Test
    public void testToString() throws ParseException {
        // Configuração do cenário

        Veiculo veiculo3 = new Carro("GFS1234", ECombustivel.GASOLINA);

        Rota rota3 = new Rota(250.0, new Date());
        Rota rota4 = new Rota(300.0, new Date());

        veiculo3.percorrerRota(rota3);
        veiculo3.percorrerRota(rota4);

        // Teste do método toString
        String expectedToString = "\n=============== VEÍCULO ==============="
                + "\nPlaca: GFS1234"
                + "\nTanque Maxímo: 50 | Tanque Atual: 0"
                + "\nTotal já abastecido: 55"
                + "\nRotas percorridas: 2"
                + "\nQuilometragem total: 550"
                + "\nQuilometragem do mês: 550"
                + "\nProxima manutenção periódica daqui a 9450.0 km"
                + "\nProxima troca de peças daqui a 9450.0 km"
                + "\nDespeza total: R$ 285,45"
                + "\n";
        assertEquals(expectedToString, veiculo3.toString());
    }
}
