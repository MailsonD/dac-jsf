package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.infra.interfaces.Dependentes;

import br.edu.ifpb.infra.memory.DependentesEmMemoria;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class ControladorDeDependentes  implements Serializable {

    private Dependente dependente = new Dependente();

    @Inject
    private Dependentes service;

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

    public List<Dependente> listarTodosOsDependentes(){
        return service.todosOsDepentendes();
    }

    public Dependente getDependente() {
        return dependente;
    }

    public void setDependente(Dependente dependente) {
        this.dependente = dependente;
    }
}
