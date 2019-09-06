package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Pessoa;
import br.edu.ifpb.infra.interfaces.Pessoas;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@ViewScoped
@Named
public class ControladorDePessoas implements Serializable {

    private Pessoa pessoa = new Pessoa();
    private List<Pessoa> todasAsPessoas;
    private String cpf;
    private List<Pessoa> buscadas;

    @Inject
    private Pessoas service;

    @PostConstruct
    private void init(){
        todasAsPessoas = service.todas();
        buscadas = todasAsPessoas;
    }

    public String salvar() {
        this.service.nova(pessoa);
        this.pessoa = new Pessoa();
        return null;
    }

    public String atualizar() {
        this.service.atualizar(pessoa);
        this.pessoa = new Pessoa();
        return null;
    }

    public String excluir(Pessoa pessoa) {
        this.service.excluir(pessoa);
        return null;
    }

    public String editar(Pessoa pessoa) {
        this.pessoa = pessoa;
        return null;
    }

    public String buscar(){
        this.buscadas = service.buscar(cpf);
        return null;
    }

    public List<Pessoa> getTodasAsPessoas() {
        return todasAsPessoas;
    }

    public void setTodasAsPessoas(List<Pessoa> todasAsPessoas) {
        this.todasAsPessoas = todasAsPessoas;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Pessoa> getBuscadas() {
        return buscadas;
    }

    public void setBuscadas(List<Pessoa> buscadas) {
        this.buscadas = buscadas;
    }
}
