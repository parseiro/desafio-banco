package banco;

import java.util.HashSet;
import java.util.Set;

public class Banco {
    private String nome;
    private Set<Conta> contas = new HashSet<>();

    public Banco(String nome) {
        this.nome = nome;

    }

    public ContaCorrente criarContaCorrente() {
        var conta = new ContaCorrente();
        this.contas.add(conta);
        return conta;
    }

    public ContaPoupanca criarContaPoupanca() {
        var conta = new ContaPoupanca();
        this.contas.add(conta);
        return conta;
    }

    public String getNome() {
        return nome;
    }

    public Set<Conta> getContas() {
        return contas;
    }
}
