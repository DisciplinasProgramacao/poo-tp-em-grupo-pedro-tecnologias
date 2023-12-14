/**
 * A classe `Tanque` representa o tanque de um veículo, mantendo informações sobre sua capacidade máxima, capacidade atual e consumo.
 */
public class Tanque {

    ECombustivel tipoCombustivel;
    private double consumo;
    private double capacidadeMaxima; 
    private double capacidadeAtual;
    private double totalAbastecido;

    /**
     * Construtor da classe `Tanque`.
     * @param capacidadeMaxima A capacidade máxima do tanque.
     * @param consumo O consumo do veículo em (litros por quilômetro).
     * @param precoCombustivel O preço médio do combustível do veículo para calculo.
     */
    public Tanque(EVeiculo tipoVeiculo, ECombustivel tipoCombustivel){

        this.tipoCombustivel = tipoCombustivel;
        consumo = tipoCombustivel.consumo;
        capacidadeMaxima = tipoVeiculo.capacidadeMaximaTanque;
        capacidadeAtual = 0;
        totalAbastecido = 0;
    }

    /**
     * Abastece o tanque com a quantidade especificada de litros.
     * @param litros A quantidade de litros a ser abastecida.
     * @return A quantidade de litros realmente abastecida (retorna -1 caso litros não seja um número inteiro).
     */
    public double abastecer(double litros){
        if (litros > 0) {        
            double tanqueDisponivel = (capacidadeMaxima - capacidadeAtual);

            if (litros <= tanqueDisponivel) {
                
                capacidadeAtual += litros;
                totalAbastecido += litros;
                return litros;

            }else if(tanqueDisponivel != 0){
                capacidadeAtual = capacidadeMaxima;
                totalAbastecido += tanqueDisponivel;
                return tanqueDisponivel;
            }
        }
        return -1; 
    }

    /**
     * O tanque é  gasto de acordo com a quantidade especificada de litros.
     * @param litros A quantidade de litros a ser gasta.
     * @return A quantidade de litros realmente gasta (retorna -1 caso litros não seja um número inteiro).
     */
    public double gastarCombustivel(double litros){

        if(litros > 0){
            if (litros <= capacidadeAtual) {
                capacidadeAtual -=  litros;
                return litros;
            }else{
                double litrosGastos = capacidadeAtual;
                capacidadeAtual = 0;
                return litrosGastos;
            }
        }
         return -1;
    }

    public double valorGastoCombustivel(){
        return totalAbastecido * tipoCombustivel.preco; 
    }

    /**
     * Calcula a autonomia máxima do veículo com base na capacidade máxima do tanque e no consumo.
     * @return A autonomia máxima em quilômetros.
     */
    private double autonomiaMaxima(){
        return capacidadeMaxima * consumo;

    }

     /**
     * Calcula a autonomia atual do veículo com base na capacidade atual do tanque e no consumo.
     * @return A autonomia atual em quilômetros.
     */
    private double autonomiaAtual(){
        return capacidadeAtual * consumo;

    }

    /**
     * Percorre uma rota, consumindo combustível com base na quilometragem da rota.
     * @param rota A rota a ser percorrida.
     * @return `true` se a rota puder ser percorrida com a autonomia atual, `false` caso contrário.
     */
    public double getCapacidadeAtual(){
        return capacidadeAtual;
    }

    public double getConsumo(){
        return consumo;
    }

    public double getTotalAbastecido(){
        return totalAbastecido;
    }

    /**
     * Obtém a autonomia máxima do tanque.
     * @return autonomia máxima.
     */
    public double getAutonomiaMaxima(){
        return autonomiaMaxima();
    }

    /**
     * Obtém a autonomia atual do tanque.
     * @return A autonomia atual do tanque.
     */
    public double getAutonomiaAtual(){
        return autonomiaAtual();
    }

}
