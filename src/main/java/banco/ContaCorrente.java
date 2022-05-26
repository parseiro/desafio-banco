package banco;

import banco.Conta;

public class ContaCorrente extends Conta {
    protected ContaCorrente() {
        super(TipoDaConta.CORRENTE);
    }

}
