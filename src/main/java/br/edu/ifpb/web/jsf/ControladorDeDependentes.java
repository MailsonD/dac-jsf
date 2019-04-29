package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Dependentes;

import br.edu.ifpb.infra.memory.DependentesEmMemoria;
import br.edu.ifpb.infra.memory.PessoasEmMemoria;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class ControladorDeDependentes  implements Serializable {

    private Dependente dependente = new Dependente();

    private Dependentes service = new DependentesEmMemoria();

    public String salvar(){
        service.salvar(dependente);
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
