package cliente;

import banco.Banco;
import banco.ContaCorrente;
import banco.ContaPoupanca;

import java.math.BigDecimal;

public class Main {
    public static void main(String... args) {
        final Banco banco = new Banco("Bradesco");

        final ContaCorrente cc = banco.criarContaCorrente();
        cc.depositar(BigDecimal.valueOf(35.3));
        cc.imprimirSaldo();


        final ContaPoupanca cp = banco.criarContaPoupanca();
        cp.depositar(BigDecimal.valueOf(10));
        System.out.println("Saldo antes de render");
        cp.imprimirSaldo();
        cp.renderMensal();
        System.out.println("Saldo após render");
        cp.imprimirSaldo();

        cc.transferir(BigDecimal.valueOf(1.50), cp);
        cp.transferir(BigDecimal.valueOf(2.50), cc);
        cc.imprimirExtrato();



    }
}
