package edu.vinisantosn;


import edu.vinisantosn.domain.Produto;
import edu.vinisantosn.generic.jdbc.dao.IProdutoDAO;
import edu.vinisantosn.generic.jdbc.dao.ProdutoDAO;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
public class ProdutoTest {
    private IProdutoDAO produtoDAO;

    @Test
    public void cadastrarTest() throws Exception {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setNome("Geladeira");
        produto.setPreco(1999.99);
        produto.setQuantidadeEstoque(54);
        Integer countCad = produtoDAO.cadastrar(produto);
        assertTrue(countCad == 1);

        Produto produtoBD = produtoDAO.buscar("Geladeira");
        assertNotNull(produtoBD);
        assertEquals(produto.getNome(), produtoBD.getNome());
        assertEquals(produto.getQuantidadeEstoque(), produtoBD.getQuantidadeEstoque());

        Integer countDel = produtoDAO.excluir(produtoBD);
        assertTrue(countDel >= 1);

    }

    @Test
    public void buscarTodosTest() throws Exception {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setNome("Geladeira");
        produto.setPreco(1999.99);
        produto.setQuantidadeEstoque(54);
        Integer countCad = produtoDAO.cadastrar(produto);
        assertTrue(countCad == 1);

        Produto produto2 = new Produto();
        produto2.setNome("Fogao");
        produto2.setPreco(899.75);
        produto2.setQuantidadeEstoque(14);
        Integer countCad2 = produtoDAO.cadastrar(produto);
        assertTrue(countCad2 == 1);

        List<Produto> produtoList = produtoDAO.buscarTodos();
        assertNotNull(produtoList);
        assertEquals(2, produtoList.size());

        int countDel = 0;
        for (Produto prod : produtoList
        ) {
            produtoDAO.excluir(prod);
            countDel++;
        }
        assertEquals(produtoList.size(), countDel);
        produtoList = produtoDAO.buscarTodos();
        assertEquals(produtoList.size(), 0);
    }
    @Test
    public void atualizarTest() throws Exception {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setNome("Geladeira");
        produto.setPreco(1999.99);
        produto.setQuantidadeEstoque(54);
        Integer countCad = produtoDAO.cadastrar(produto);
        assertTrue(countCad == 1);

        Produto produtoDB = produtoDAO.buscar("Geladeira");
        assertNotNull(produtoDB);
        assertEquals(produto.getQuantidadeEstoque(), produtoDB.getQuantidadeEstoque());
        assertEquals(produto.getNome(), produtoDB.getNome());

        produtoDB.setNome("TV");
        produtoDB.setPreco(1897.53);
        produtoDB.setQuantidadeEstoque(11);
        Integer countUpdate = produtoDAO.atualizar(produtoDB);
        assertTrue(countUpdate == 1);

        Produto produtoDB1 = produtoDAO.buscar("Geladeira");
        assertNull(produtoDB1);

        Produto produtoDB2 = produtoDAO.buscar("TV");
        assertNotNull(produtoDB2);
        assertEquals(produtoDB.getQuantidadeEstoque(), produtoDB2.getQuantidadeEstoque());
        assertEquals(produtoDB.getNome(), produtoDB2.getNome());


        List<Produto> produtoList = produtoDAO.buscarTodos();


        for (Produto prod : produtoList
        ) {
            produtoDAO.excluir(prod);

        }



    }
}
