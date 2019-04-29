package br.edu.ifpb.infra.memory;

import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Dependentes;

import java.util.ArrayList;
import java.util.List;

public class DependentesEmMemoria implements Dependentes {

    private final List<Dependente> dependentes = new ArrayList<>();

    @Override
    public void salvar(Dependente dependente) {
        dependente.setUuid("asduasdhuas");
        dependentes.add(dependente);
    }

    @Override
    public void excluir(Dependente dependente) {

    }

    @Override
    public void atualizar(Dependente dependente) {

    }

    @Override
    public List<Dependente> todosOsDepentendes() {
        return dependentes;
    }

    @Override
    public Dependente localizarDependenteComId(String uuid) {
        return null;
    }
}
