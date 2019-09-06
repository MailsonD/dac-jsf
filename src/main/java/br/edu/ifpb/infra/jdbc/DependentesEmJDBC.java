package br.edu.ifpb.infra.jdbc;

import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.infra.interfaces.Dependentes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class DependentesEmJDBC implements Dependentes {

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
    public void salvar(Dependente dependente) {
        String sql = "INSERT INTO dependente (uuid, nome, dataNascimento) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, gerarUUID());
            ps.setString(2, dependente.getNome());
            ps.setDate(3, Date.valueOf(dependente.getDataDeNascimento()));
            ps.execute();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Deu erro no salvar");
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(Dependente dependente) {
        String sql = "DELETE FROM dependente WHERE uuid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, dependente.getUuid());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Deu erro no excluir");
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Dependente dependente) {
        String sql = "UPDATE dependente SET nome = ?, dataNascimento = ? WHERE uuid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, dependente.getNome());
            ps.setDate(2, Date.valueOf(dependente.getDataDeNascimento()));
            ps.setString(3, dependente.getUuid());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Deu erro no atualizar");
            e.printStackTrace();
        }
    }

    @Override
    public List<Dependente> todosOsDepentendes() {
        String sql = "SELECT * FROM dependente";
        List<Dependente> dependentes = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                dependentes.add(construirDependente(resultSet));
            }
            return dependentes;
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Deu erro no listar");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Dependente localizarDependenteComId(String uuid) {
        String sql = "SELECT * FROM dependente WHERE uuid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, uuid);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return construirDependente(resultSet);
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Deu erro no buscar");
            e.printStackTrace();
        }
        return null;
    }

    private Dependente construirDependente(ResultSet resultSet) throws SQLException {
        Dependente dependente = new Dependente();
        dependente.setNome(resultSet.getString("nome"));
        dependente.setUuid(resultSet.getString("uuid"));
        dependente.setDataDeNascimento(resultSet.getDate("dataNascimento").toLocalDate());
        return dependente;
    }

    private String gerarUUID() {
        return UUID.randomUUID().toString();
    }
}
