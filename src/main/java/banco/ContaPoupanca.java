package banco;

import banco.Conta;

import java.math.BigDecimal;

public class ContaPoupanca extends Conta {
    static private BigDecimal RENDIMENTO_MENSAL = BigDecimal.valueOf(0.0035);
    protected ContaPoupanca() {
        super(TipoDaConta.POUPANCA);
    }

    public void renderMensal() {
        BigDecimal saldo = this.saldo;

        BigDecimal rendimento = saldo.multiply(RENDIMENTO_MENSAL);
        this.depositar(rendimento);
    }
}
