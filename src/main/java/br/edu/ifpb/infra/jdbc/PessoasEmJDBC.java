package br.edu.ifpb.infra.jdbc;

import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Pessoa;
import br.edu.ifpb.infra.interfaces.Pessoas;
import com.sun.corba.se.impl.orb.PrefixParserAction;
import sun.rmi.runtime.Log;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class PessoasEmJDBC implements Pessoas {

    @Resource(name = "java:app/jdbc/atividade_dac")
    private DataSource dataSource;
    private Connection connection;

    private Logger log = Logger.getLogger(this.getClass().getName());

    @PostConstruct
    private void init(){
        try{
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Deu erro na conexão");
            e.printStackTrace();
        }
    }

    @Override
    public void nova(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (cpf, nome, id_dependente) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pessoa.getCpf().valor());
            ps.setString(2, pessoa.getNome());
            ps.setString(3, pessoa.getDependente().getUuid());
            ps.execute();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Deu erro ao salvar");
            e.printStackTrace();
        }
    }

    @Override
    public List<Pessoa> todas() {
        String sql = "SELECT * FROM pessoa";
        List<Pessoa> pessoas = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                pessoas.add(construirPessoa(resultSet));
            }
            return pessoas;
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Deu erro ao listar");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void excluir(Pessoa pessoa) {
        String sql = "DELETE FROM pessoa WHERE cpf = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pessoa.getCpf().valor());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Deu erro ao excluir");
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Pessoa pessoa) {
        String sql = "UPDATE pessoa SET nome = ?, id_dependente = ? WHERE cpf = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getDependente().getUuid());
            ps.setString(3, pessoa.getCpf().valor());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Deu erro ao atualizar");
            e.printStackTrace();
        }
    }

    @Override
    public Pessoa buscar(CPF cpf) {
        String sql = "SELECT * FROM pessoa WHERE cpf = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cpf.valor());
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return construirPessoa(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Pessoa construirPessoa(ResultSet resultSet) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(new CPF(resultSet.getString("cpf")));
        pessoa.setNome(resultSet.getString("nome"));
        pessoa.setDependente(buscarDep(resultSet.getString("id_dependente")));
        return pessoa;
    }

    private Dependente buscarDep(String uuid) throws SQLException {
        String sql = "SELECT * FROM dependente WHERE uuid = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, uuid);
        ResultSet resultSet = ps.executeQuery();
        if(resultSet.next()){
            return new Dependente(
                resultSet.getString("uuid"),
                resultSet.getString("nome"),
                resultSet.getDate("dataNascimento").toLocalDate()
            );
        }
        return null;
    }
}
