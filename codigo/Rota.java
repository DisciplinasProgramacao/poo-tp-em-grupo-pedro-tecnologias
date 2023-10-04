import java.util.Date;

public class Rota {
    private double quilometragem;
    private Date data;

    public Rota(double quilometragem, Date data) {
        this.quilometragem = quilometragem;
        this.data = data;
    }

    public double getQuilometragem() {
        return quilometragem;
    }

    public Date getData() {
        return data;
    }

    public String relatorio() {
        // Implemente a geração do relatório da rota
        // Pode incluir informações como quilometragem e data
        return "Quilometragem: " + quilometragem + ", Data: " + data;
    }
}
