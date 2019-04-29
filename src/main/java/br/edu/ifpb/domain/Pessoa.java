package br.edu.ifpb.domain;

import java.util.Objects;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 25/04/2019, 09:55:19
 */
public class Pessoa {

    private int id;
    private String nome;
    private CPF cpf;
    private Dependente dependente;

    public Pessoa() {
    }

    public Pessoa(String nome,String cpf,Dependente dependente) {
        this(nome,new CPF(cpf),dependente);
    }

    public Pessoa(String nome,CPF cpf,Dependente dependente) {
        this.cpf = cpf;
        this.nome = nome;
        this.dependente = dependente;
    }

    public CPF getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setCpf(CPF cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Dependente getDependente() {
        return dependente;
    }

    public void setDependente(Dependente dependente) {
        this.dependente = dependente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return id == pessoa.id &&
                Objects.equals(nome, pessoa.nome) &&
                Objects.equals(cpf, pessoa.cpf) &&
                Objects.equals(dependente, pessoa.dependente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf, dependente);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf=" + cpf +
                ", dependente=" + dependente +
                '}';
    }
}
