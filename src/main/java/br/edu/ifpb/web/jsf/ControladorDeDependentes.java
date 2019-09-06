package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.infra.interfaces.Dependentes;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named
public class ControladorDeDependentes  implements Serializable {

    private Dependente dependente = new Dependente();
    private List<Dependente> todosOsDependentes;

    @Inject
    private Dependentes service;

    @PostConstruct
    private void init(){
        todosOsDependentes = service.todosOsDepentendes();
    }

    public String salvar(){
        service.salvar(dependente);
        dependente = new Dependente();
        return "";
    }

    public String atualizar(){
        service.atualizar(dependente);
        return "";
    }

    public String editar(Dependente dependente){
        this.dependente = dependente;
        return "";

    }

    public String excluir(Dependente dependente){
        service.excluir(dependente);
        return "";
    }

    public List<Dependente> getTodosOsDependentes() {
        System.out.println("Tá pegando");
        return todosOsDependentes;
    }

    public void setTodosOsDependentes(List<Dependente> todosOsDependentes) {
        this.todosOsDependentes = todosOsDependentes;
    }

    public Dependente getDependente() {
        return dependente;
    }

    public void setDependente(Dependente dependente) {
        this.dependente = dependente;
    }
}
