package br.edu.ifpb.infra.interfaces;

import br.edu.ifpb.domain.Dependente;

import java.util.List;

public interface Dependentes {

    void salvar(Dependente dependente);

    void excluir(Dependente dependente);

    void atualizar(Dependente dependente);

    List<Dependente> todosOsDepentendes();

    Dependente localizarDependenteComId(String uuid);
}
