import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TanqueTest {

    private Tanque tanque;

    @Before
    public void setUp() {
        EVeiculo tipoVeiculo = EVeiculo.CARRO;
        ECombustivel tipoCombustivel = ECombustivel.GASOLINA;

        tanque = new Tanque(tipoVeiculo, tipoCombustivel);
    }

    @Test
    public void testGastarCombustivel() {
        tanque.abastecer(50);
        double litrosGastos = tanque.gastarCombustivel(20);
        assertEquals(20, litrosGastos, 0.01);
        assertEquals(30, tanque.getCapacidadeAtual(), 0.01);
    }

    @Test
    public void testAbastecerTanqueVazio() {

        double litrosAbastecidos = tanque.abastecer(30);
        assertEquals(30, litrosAbastecidos, 0.01);
        assertEquals(30, tanque.getCapacidadeAtual(), 0.01);
        assertEquals(30, tanque.getTotalAbastecido(), 0.01);
    }

    @Test
    public void testAbastecerTanqueCheio() {
        tanque.abastecer(50);
        double litrosAbastecidos = tanque.abastecer(30);
        assertEquals(-1, litrosAbastecidos, 0.01);
        assertEquals(50, tanque.getCapacidadeAtual(), 0.01);
        assertEquals(50, tanque.getTotalAbastecido(), 0.01);
    }

    @Test
    public void testAbastecerTanqueParcialmenteCheio() {
        tanque.abastecer(20);
        double litrosAbastecidos = tanque.abastecer(30);
        assertEquals(30, litrosAbastecidos, 0.01);
        assertEquals(50, tanque.getCapacidadeAtual(), 0.01);
        assertEquals(50, tanque.getTotalAbastecido(), 0.01);
    }

    @Test
    public void testValorGastoCombustivel() {
        tanque = new Tanque(EVeiculo.CARRO, ECombustivel.GASOLINA);
        tanque.abastecer(40);
        double valorGasto = tanque.valorGastoCombustivel();
        assertEquals(207.60, valorGasto, 0.01);
    }

    @Test
    public void testAutonomiaMaxima() {
        assertEquals(500, tanque.getAutonomiaMaxima(), 0.01);
    }

    @Test
    public void testAutonomiaAtual() {
        tanque.abastecer(30);
        assertEquals(300, tanque.getAutonomiaAtual(), 0.01);
    }
}
