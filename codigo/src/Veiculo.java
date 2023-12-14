import java.util.List;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A classe abstrata `Veiculo` serve como base para outros tipos específicos de veículos, como Carro, Van, Furgão e Caminhão.
 */
abstract class Veiculo {

    private final int MAX_ROTAS = 30;
    private EVeiculo tipoVeiculo;
    private String placa;
    private List<Rota> rotasPercoridas;
    Tanque tanque;
    private double totalManutencoes;

    /**
     * Construtor da classe `Veiculo`.
     * @param tipoVeiculo O tipo de veículo.
     * @param placa A placa do veículo.
     * @param combustivel O tipo de combustível utilizado pelo veículo.
     */
    public Veiculo(String placa, EVeiculo tipoVeiculo, ECombustivel combustivel) {

        this.placa = placa;
        this.tipoVeiculo = tipoVeiculo;
        rotasPercoridas = new ArrayList<>();
        tanque = new Tanque(tipoVeiculo, combustivel);
        totalManutencoes = 0;

    }

    public double totalGasto(){
        return tanque.valorGastoCombustivel() + totalManutencoes;
    }

    public void SomarManutencoes(double manutencoes){
        totalManutencoes += manutencoes;
    }

    private double autonomiaAtual() {
        return tanque.getAutonomiaAtual();
    }
    private double autonomiaMaxima() {
        return tanque.getAutonomiaMaxima();
    }

    /**
     * Percorre uma rota com base na quilometragem fornecida e adiciona na lista de rotas. 
     * Caso não consiga percorrer por não ter autonomia abastece sozinho.
     * @param kmRota A quilometragem da rota.
     * @return `true` se a rota puder ser percorrida, `false` caso contrário.
     */
    public boolean percorrerRota(Rota rota) {
        double kmRota = rota.getQuilometragem();
        double litros = kmRota / tanque.getConsumo();
        if (kmRota <= autonomiaMaxima()) {
            if (autonomiaAtual() >= kmRota) {
                double litrosGastos = tanque.gastarCombustivel(litros);

                if (litrosGastos >= 0 && qtdRotasPercorridas() < MAX_ROTAS) {   
                        rotasPercoridas.add(rota);
                        return true;              
                } 
            }
            double litrosAbastecidos = tanque.abastecer(litros);
            if (litrosAbastecidos > 0) {
                return percorrerRota(rota);
            }
        }
        return false;
    }

    public int qtdRotasPercorridas(){
        return rotasPercoridas.size();
    }

    /**
     * Calcula a quilometragem percorrida no mês atual.
     * @return A quilometragem percorrida no mês atual.
     */
    public double kmNoMes() {
        Calendar hoje = Calendar.getInstance();
        int mesAtual = hoje.get(Calendar.MONTH);

        return rotasPercoridas.stream()
                .filter(rota -> {
                    Calendar dataRota = Calendar.getInstance();

                    dataRota.setTime(rota.getData());
                    return dataRota.get(Calendar.MONTH) == mesAtual;
                })
                .mapToDouble(rota -> rota.getQuilometragem())
                .sum();
    }

    /**
     * Calcula a quilometragem total percorrida.
     * @return A quilometragem total percorrida.
     */
    public double kmTotal() {
        return rotasPercoridas.stream()
            .mapToDouble(rota -> rota.getQuilometragem())
            .sum();
    }

    /**
     * Verifica se a placa do veículo corresponde à placa fornecida.
     * @param placa A placa a ser verificada.
     * @return `true` se a placa corresponder, `false` caso contrário.
     */
    public boolean placaCorresponde(String placa){
        return this.placa.equals(placa);
    }

    /**
     * Apaga todas as rotas percorridas pelo veículo.
     */
    public void apagarRotas() {
        rotasPercoridas.clear();
    }

    @Override
    public String toString(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        DecimalFormat formatarDouble = new DecimalFormat("#.##");
        StringBuilder aux = new StringBuilder();
        aux.append("\n=============== VEÍCULO ===============");
        aux.append("\nPlaca: "+ placa);
        aux.append("\nTanque Maxímo: "+ formatarDouble.format(tipoVeiculo.capacidadeMaximaTanque) + " | Tanque Atual: "+ formatarDouble.format(tanque.getCapacidadeAtual()));
        aux.append("\nTotal já abastecido: "+ formatarDouble.format(tanque.getTotalAbastecido()));
        aux.append("\nRotas percorridas: "+ rotasPercoridas.size());
        aux.append("\nQuilometragem total: "+ formatarDouble.format(kmTotal()));
        aux.append("\nQuilometragem do mês: "+ formatarDouble.format(kmNoMes()));
        aux.append("\nProxima manutenção periódica daqui a "+ (tipoVeiculo.manutencaoPeriodica - kmTotal())+ " km");
        aux.append("\nProxima troca de peças daqui a "+ (tipoVeiculo.manutencaoTrocaPecas - kmTotal()) + " km");
        aux.append("\nDespeza total: "+ moeda.format(totalGasto()));
        aux.append("\n");

        return aux.toString();
    }
}
