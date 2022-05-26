package banco;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta {

    private static final int CONTA_PADRAO = 1;
    private static int PROXIMA_CONTA = 1;

    @Getter
    private List<Transacao> transacaoList = new ArrayList<>();

    @Getter
    private TipoDaConta tipo = TipoDaConta.CORRENTE;

    @Getter
    private int agencia;

    @Getter
    private int numero;

    @Getter
    public BigDecimal saldo = BigDecimal.ZERO;

    protected Conta(TipoDaConta tipo) {
        this(tipo, CONTA_PADRAO, PROXIMA_CONTA);
        PROXIMA_CONTA++;
    }

    protected Conta(TipoDaConta tipo, int agencia, int numero) {
        this.tipo = tipo;
        this.agencia = agencia;
        this.numero = numero;
    }

    private void sacarSemRegistrar(BigDecimal valor) {
        this.saldo = this.saldo.subtract(valor);
    }

    public final void sacar(BigDecimal valor) {
        if (valor == null) {
            throw new IllegalArgumentException("O valor precisa estar presente");
        }
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor precisa ser maior do que zero");
        }

        var transacao = new Transacao(TipoDaTransacao.SAQUE, valor);
        this.transacaoList.add(transacao);
        this.sacarSemRegistrar(valor);
    }

    private void depositarSemRegistrar(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }

    public final void depositar(BigDecimal valor) {
        if (valor == null) {
            throw new IllegalArgumentException("O valor precisa estar presente");
        }
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor precisa ser maior do que zero");
        }
        var transacao = new Transacao(TipoDaTransacao.DEPOSITO, valor);
        this.transacaoList.add(transacao);
        this.depositarSemRegistrar(valor);
    }

    public final void transferir(BigDecimal valor, Conta destino) {
        if (valor == null || destino == null) {
            throw new IllegalArgumentException("O valor e a conta de destino precisam estar presentes");
        }
        if (this == destino) {
            throw new IllegalArgumentException("As contas de fonte e destino precisam ser distintas");
        }
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor precisa ser maior do que zero");
        }

        this.sacarSemRegistrar(valor);
        destino.depositarSemRegistrar(valor);
        this.transacaoList.add(new Transacao(TipoDaTransacao.TRANSFERENCIA_SAIDA, valor,
                "Para: " + destino.getTipo()
                        + " Ag: " + destino.getAgencia()
                        + " Número: " + destino.getNumero()));
        destino.transacaoList.add(new Transacao(TipoDaTransacao.TRANSFERENCIA_ENTRADA, valor,
                "De: " + destino.getTipo()
                        + " Ag: " + destino.getAgencia()
                        + " Número: " + destino.getNumero()));
    }

    public void imprimirSaldo() {
        System.out.printf("Conta do tipo *%s*  / Agência: %d Número: %d%n",
                this.tipo.getTipoString(),
                this.agencia,
                this.numero);
        System.out.printf("Saldo: %.2f%n", this.saldo);
    }

    public void imprimirExtrato() {
        System.out.printf("Conta do tipo *%s*  / Agência: %d Número: %d%n",
                this.tipo.getTipoString(),
                this.agencia,
                this.numero);

        for (var t : this.transacaoList) {
            System.out.printf("%s R$%.2f / Obs.:%s%n", t.getTipo(), t.getValor(), t.getComentario());
        }

        System.out.printf("Saldo: %.2f%n", this.saldo);
    }
}
