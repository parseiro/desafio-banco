package banco;

import lombok.Getter;

import java.math.BigDecimal;

public class Transacao {
    @Getter
    private TipoDaTransacao tipo;

    @Getter
    private BigDecimal valor;

    @Getter
    private String comentario = "";

    public Transacao(TipoDaTransacao tipo, BigDecimal valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public Transacao(TipoDaTransacao tipo, BigDecimal valor, String comentario) {
        this.tipo = tipo;
        this.valor = valor;
        this.comentario = comentario;
    }
}
