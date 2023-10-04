import java.util.Date;

public class Veiculo {
    private String placa;
    private Rota[] rotas;
    private int quantRotas;
    private double tanqueMax;
    private double totalReabastecido;

    public Veiculo(String placa, double tanqueMax) {
        this.placa = placa;
        this.tanqueMax = tanqueMax;
        this.rotas = new Rota[100]; // Você pode ajustar o tamanho do array conforme necessário
        this.quantRotas = 0;
        this.totalReabastecido = 0.0;
    }

    public boolean addRota(Rota rota) {
        if (quantRotas < rotas.length) {
            rotas[quantRotas++] = rota;
            return true;
        }
        return false; // Não há espaço para adicionar mais rotas
    }

    public double autonomiaMaxima() {
        // Implemente o cálculo da autonomia máxima com base no tanqueMax
        return tanqueMax * consumoPorLitro(); // Suponhamos que consumoPorLitro() seja um método que você implementou
    }

    public double autonomiaAtual() {
        // Implemente o cálculo da autonomia atual com base no tanque atual e consumoPorLitro()
        return (tanqueMax - totalReabastecido) * consumoPorLitro(); // Suponhamos que consumoPorLitro() seja um método que você implementou
    }

    public double abastecer(double litros) {
        if (litros > 0) {
            double litrosAbastecidos = Math.min(tanqueMax - totalReabastecido, litros);
            totalReabastecido += litrosAbastecidos;
            return litrosAbastecidos;
        }
        return 0.0; // Não foi abastecido nenhum litro
    }

    public double kmNoMes() {
        // Implemente o cálculo da quilometragem percorrida no mês
        // Suponhamos que você tenha um método para calcular a data atual e comparar com as datas das rotas
        return 0.0; // Valor de exemplo, você deve implementar essa lógica
    }

    public double kmTotal() {
        // Implemente o cálculo da quilometragem total percorrida somando todas as rotas
        double kmTotal = 0.0;
        for (int i = 0; i < quantRotas; i++) {
            kmTotal += rotas[i].getQuilometragem();
        }
        return kmTotal;
    }

    public void percorrerRota(Rota rota) {
        // Implemente a lógica para percorrer uma rota
        // Pode incluir a atualização da quilometragem, data, etc.
    }
}
