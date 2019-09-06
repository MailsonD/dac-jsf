package br.edu.ifpb.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Dependente {

    private String uuid;
    private String nome;
    private LocalDate dataDeNascimento;

    public Dependente() {

    }


    public Dependente(String uuid, String nome, LocalDate dataDeNascimento) {
        this.uuid = uuid;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
    }

    private String gerarUUID() {
        return UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dependente)) return false;
        Dependente that = (Dependente) o;
        return Objects.equals(getUuid(), that.getUuid()) &&
                Objects.equals(getNome(), that.getNome()) &&
                Objects.equals(getDataDeNascimento(), that.getDataDeNascimento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getNome(), getDataDeNascimento());
    }

    @Override
    public String toString() {
        return "Dependente{" +
                "uuid='" + uuid + '\'' +
                ", nome='" + nome + '\'' +
                ", dataDeNascimento=" + dataDeNascimento +
                '}';
    }
}


