public class Compra {
    private String setor;
    private String produto;
    private double valor;

    public Compra(String setor, String produto, double valor) {
        this.setor = setor;
        this.produto = produto;
        this.valor = valor;
    }

    // Getters
    public String getSetor() {
        return setor;
    }

    public String getProduto() {
        return produto;
    }

    public double getValor() {
        return valor;
    }

    // Sobrescrevendo o método toString
    public String toString() {
        return "Setor: " + setor + "\n" +
                "Produto: " + produto + "\n" +
                "Valor: R$" + String.format("%.2f", valor) + "\n"; // String.format("%.2f", valor) Especificando que queremos exatamente 2 casas decimais após o ponto
        // O uso do String.format é importante para o contexto monetário pois ele garante que o valor seja exibido de maneira adequada e sem problemas de precisão. Isso evita exibições de valores muito extensos com imprecisões numéricas
    }
}