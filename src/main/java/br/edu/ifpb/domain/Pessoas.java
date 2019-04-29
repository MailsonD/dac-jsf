package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 25/04/2019, 09:57:57
 */
public interface Pessoas extends Serializable {
 
    void nova(Pessoa pessoa);

    List<Pessoa> todas() ;

    void excluir(Pessoa pessoa);

    void atualizar(Pessoa pessoa);

    Pessoa buscar(CPF cpf);
    
    List<Dependente> todosOsDepentendes();

    Dependente localizarDependenteComId(String uuid);
}
