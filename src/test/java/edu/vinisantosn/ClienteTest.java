package edu.vinisantosn;

import edu.vinisantosn.domain.Cliente;
import edu.vinisantosn.generic.jdbc.dao.ClienteDAO;
import edu.vinisantosn.generic.jdbc.dao.IClienteDAO;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ClienteTest {
    private IClienteDAO clienteDAO;

    @Test
    public void cadastrarTest() throws Exception {
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Vinicius Santos");
        Integer countCad = clienteDAO.cadastrar(cliente);
        assertTrue(countCad == 1);

        Cliente clienteBD = clienteDAO.buscar("10");
        assertNotNull(clienteBD);
        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        Integer countDel = clienteDAO.excluir(clienteBD);
        assertTrue(countDel >= 1);

    }

    @Test
    public void buscarTest() throws Exception {
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Vinicius Santos");
        Integer countCad = clienteDAO.cadastrar(cliente);
        assertTrue(countCad == 1);

        Cliente clienteBD = clienteDAO.buscar("10");
        assertNotNull(clienteBD);
        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        Integer countDel = clienteDAO.excluir(clienteBD);
        assertTrue(countDel == 1);

    }

    @Test
    public void excluirTest() throws Exception {
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Vinicius Santos");
        Integer countCad = clienteDAO.cadastrar(cliente);
        assertTrue(countCad == 1);

        Cliente clienteBD = clienteDAO.buscar("10");
        assertNotNull(clienteBD);
        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        Integer countDel = clienteDAO.excluir(clienteBD);
        assertTrue(countDel == 1);

    }

    @Test
    public void buscarTodosTest() throws Exception {
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Vinicius Santos");
        Integer countCad = clienteDAO.cadastrar(cliente);
        assertTrue(countCad == 1);

        Cliente clienteUm = new Cliente();
        clienteUm.setCodigo("20");
        clienteUm.setNome("João Silva");
        Integer countCad2 = clienteDAO.cadastrar(clienteUm);
        assertTrue(countCad2 == 1);

        List<Cliente> clienteList = clienteDAO.buscarTodos();
        assertNotNull(clienteList);
        assertEquals(2, clienteList.size());

        int countDel = 0;
        for (Cliente clienteDoLaco : clienteList
        ) {
            clienteDAO.excluir(clienteDoLaco);
            countDel++;
        }
        assertEquals(clienteList.size(), countDel);
        clienteList = clienteDAO.buscarTodos();
        assertEquals(clienteList.size(), 0);

    }

    @Test
    public void atualizarTest() throws Exception {
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Vinicius Santos");
        Integer countCad = clienteDAO.cadastrar(cliente);
        assertTrue(countCad == 1);

        Cliente clienteBD = clienteDAO.buscar("10");
        assertNotNull(clienteBD);
        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        clienteBD.setCodigo("20");
        clienteBD.setNome("VINISANTOSN");
        Integer countUpdate = clienteDAO.atualizar(clienteBD);
        assertTrue(countUpdate == 1);

        Cliente clienteBD1 = clienteDAO.buscar("10");
        assertNull(clienteBD1);

        Cliente clienteBD2 = clienteDAO.buscar("20");
        assertNotNull(clienteBD2);
        assertEquals(clienteBD.getCodigo(), clienteBD2.getCodigo());
        assertEquals(clienteBD.getNome(), clienteBD2.getNome());


        List<Cliente> clienteList = clienteDAO.buscarTodos();


        for (Cliente cli : clienteList
        ) {
            clienteDAO.excluir(cli);

        }



    }


}
