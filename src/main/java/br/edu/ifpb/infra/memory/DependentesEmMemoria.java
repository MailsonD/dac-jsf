package br.edu.ifpb.infra.memory;

import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.infra.interfaces.Dependentes;

import java.util.ArrayList;
import java.util.List;

public class DependentesEmMemoria implements Dependentes {

    private final List<Dependente> dependentes = new ArrayList<>();

    private static DependentesEmMemoria instance = null;

    public static DependentesEmMemoria getInstance(){
        if (instance == null) {
            synchronized (DependentesEmMemoria.class) {
                if (instance == null) {
                    instance = new DependentesEmMemoria();
                }
            }
        }
        return instance;
    }

    @Override
    public void salvar(Dependente dependente) {
        dependentes.add(dependente);
    }

    @Override
    public void excluir(Dependente dependente) {
        dependentes.remove(dependente);
    }

    @Override
    public void atualizar(Dependente dependente) {
        dependentes.remove(dependente);
        dependentes.add(dependente);
    }

    @Override
    public List<Dependente> todosOsDepentendes() {
        return dependentes;
    }

    @Override
    public Dependente localizarDependenteComId(String uuid) {
        return dependentes.stream().
                filter(dependente -> dependente.getUuid().equals(uuid))
                .findFirst().get();
    }
}
