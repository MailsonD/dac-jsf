package br.edu.ifpb.infra.interfaces;

import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Pessoa;

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

}
