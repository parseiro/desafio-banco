package banco;

public enum TipoDaConta {
    CORRENTE("Corrente"), POUPANCA("Poupança");

    private String tipo;

    private TipoDaConta(String tipo) { this.tipo = tipo; }

    public String getTipoString() {
        return this.tipo;
    }
}
