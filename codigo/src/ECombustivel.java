/**
 * Enumeração `ECombustivel` representa os tipos de combustíveis disponíveis, cada um com seu consumo e preço.
 */
enum ECombustivel {
    ALCOOL(7d, 3.29),
    GASOLINA(10d, 5.19),
    DIESEL(4d, 6.09);

    double consumo;
    double preco;

    /**
     * Construtor da enumeração `ECombustivel`.
     * @param consumo O consumo do combustível.
     * @param preco O preço do combustível.
     */
    ECombustivel(double consumo, double preco) {
        this.preco = preco;
        this.consumo = consumo;
    }
}
