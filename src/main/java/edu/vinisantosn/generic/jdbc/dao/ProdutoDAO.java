package edu.vinisantosn.generic.jdbc.dao;

import edu.vinisantosn.domain.Cliente;
import edu.vinisantosn.domain.Produto;
import edu.vinisantosn.generic.jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements IProdutoDAO{
    @Override
    public Integer cadastrar(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSqlInsert();
            stm = connection.prepareStatement(sql);
            adicionarParametrosInsert(stm,produto);
            return stm.executeUpdate();
        } catch (Exception e){
            throw e;
        } finally {
            closeConnection(connection,stm, null);
        }
    }

    @Override
    public Integer atualizar(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSqlUpdate();
            stm = connection.prepareStatement(sql);
            adicionarParametrosUpdate(stm,produto);
            return stm.executeUpdate();
        } catch (Exception e){
            throw e;
        } finally {
            closeConnection(connection,stm, null);
        }
    }



    @Override
    public Produto buscar(String nome) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Produto produto = null;
        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSqlSelect();
            stm = connection.prepareStatement(sql);
            adicionarParametrosSelect(stm, nome);
            rs = stm.executeQuery();

            if(rs.next()){
                produto = new Produto();
                int id = rs.getInt("ID");
                String nomeProd = rs.getString("NOME");
                Double preco = rs.getDouble("PRECO");
                int qtdEstoque = rs.getInt("QUANTIDADE_ESTOQUE");
                produto.setId(id);
                produto.setNome(nomeProd);
                produto.setPreco(preco);
                produto.setQuantidadeEstoque(qtdEstoque);
            }

        } catch (Exception e){
            throw e;
        } finally {
            closeConnection(connection,stm, rs);
        }
        return produto;
    }

    @Override
    public List<Produto> buscarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Produto> list = new ArrayList<>();
        Produto produto = null;
        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSqlSelectAll();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()){
                produto = new Produto();
                int id = rs.getInt("ID");
                String nomeProd = rs.getString("NOME");
                Double preco = rs.getDouble("PRECO");
                int qtdEstoque = rs.getInt("QUANTIDADE_ESTOQUE");
                produto.setId(id);
                produto.setNome(nomeProd);
                produto.setPreco(preco);
                produto.setQuantidadeEstoque(qtdEstoque);
                list.add(produto);
            }

        } catch (Exception e){
            throw e;
        } finally {
            closeConnection(connection,stm, rs);
        }
        return list;
    }

    @Override
    public Integer excluir(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSqlDelete();
            stm = connection.prepareStatement(sql);
            adicionarParametrosDelete(stm,produto);
            return stm.executeUpdate();
        } catch (Exception e){
            throw e;
        } finally {
            closeConnection(connection,stm, null);
        }
    }
    private String getSqlInsert() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO tbl_produto (id, nome, preco, quantidade_estoque) ");
        sb.append("VALUES (nextval('sq_produto'),?,?,?)");
        return sb.toString();
    }
    private void adicionarParametrosInsert(PreparedStatement stm, Produto produto) throws SQLException {
        stm.setString(1,produto.getNome());
        stm.setDouble(2,produto.getPreco());
        stm.setInt(3,produto.getQuantidadeEstoque());
    }

    private String getSqlUpdate(){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE tbl_produto ");
        sb.append("SET NOME=?, PRECO=?, QUANTIDADE_ESTOQUE=? ");
        sb.append("WHERE ID=?");
        return sb.toString();
    }
    private void adicionarParametrosUpdate(PreparedStatement stm, Produto produto) throws SQLException {
        stm.setString(1, produto.getNome());
        stm.setDouble(2,produto.getPreco());
        stm.setInt(3,produto.getQuantidadeEstoque());
        stm.setInt(4,produto.getId());
    }
    private String getSqlSelect() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tbl_produto ");
        sb.append("WHERE NOME=?");
        return sb.toString();
    }
    private void adicionarParametrosSelect(PreparedStatement stm, String nome) throws SQLException {
        stm.setString(1,nome);
    }

    private String getSqlSelectAll(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT  * FROM tbl_produto");
        return sb.toString();
    }
    private String getSqlDelete(){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM tbl_produto ");
        sb.append("WHERE NOME=?");
        return sb.toString();
    }
    private void adicionarParametrosDelete(PreparedStatement stm, Produto produto) throws SQLException {
        stm.setString(1,produto.getNome());
    }

    private void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs){
        try {
            if (rs != null && !rs.isClosed()){
                rs.close();
            }
            if (stm != null && !stm.isClosed()){
                stm.close();
            }
            if (connection!= null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
