package br.edu.ifpb.infra.memory;

import br.edu.ifpb.domain.*;
import br.edu.ifpb.infra.interfaces.Pessoas;

import java.util.ArrayList;
import java.util.List;


public class PessoasEmMemoria implements Pessoas {

    private final List<Pessoa> pessoas = new ArrayList<>();

    private static PessoasEmMemoria instance = null;

    public static PessoasEmMemoria getInstance(){
        if (instance == null) {
            synchronized (PessoasEmMemoria.class) {
                if (instance == null) {
                    instance = new PessoasEmMemoria();
                }
            }
        }
        return instance;
    }


    public void nova(Pessoa pessoa) {
        pessoas.add(pessoa);

    }

    public List<Pessoa> todas() {
        return pessoas;
    }

    public void excluir(Pessoa pessoa) {
        pessoas.remove(pessoa);
    }

    public void atualizar(Pessoa pessoa) {
        pessoas.remove(pessoa);
        pessoas.add(pessoa);
    }

    @Override
    public Pessoa buscar(CPF cpf) {
        return pessoas.stream().filter(pessoa -> pessoa.getCpf().equals(cpf)).findFirst().get();
    }

    @Override
    public List<Pessoa> buscar(String cpf) {
        return null;
    }


}
