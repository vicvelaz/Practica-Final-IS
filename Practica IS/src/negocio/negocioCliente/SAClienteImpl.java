package negocio.negocioCliente;

import java.sql.SQLException;
import java.util.LinkedList;

import integracion.factoriaDAO.FactoriaDAO;
import integracion.integracionCliente.DAOCliente;
import negocio.negocioFactura.TFactura;

public class SAClienteImpl implements SACliente {
	
	public Integer altaCliente(TCliente cliente) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOCliente clienteDAO = dao.generaDAOCliente();
		TCliente aux = null;
		
		aux = clienteDAO.devolverCliente(cliente.getId());
		if(aux == null){
			return clienteDAO.altaCliente(cliente);
		}
		else{
			return -1;
		}
	}
	
	public Integer editarCliente(TCliente cliente) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOCliente clienteDAO = dao.generaDAOCliente();
		TCliente aux = null;
		
		aux = clienteDAO.devolverCliente(cliente.getId());
		if(aux == null){
			return -1;
		}
		else{
			return clienteDAO.editarCliente(cliente);
		}
	}
	
	public TCliente devolverCliente(Integer id) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOCliente clienteDAO = dao.generaDAOCliente();
		TCliente aux = null;
		
		aux = clienteDAO.devolverCliente(id);
		if(aux != null){
			return aux;
		}
		return null;
	}
	
	public LinkedList<TCliente> listarClientes() throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOCliente clienteDAO = dao.generaDAOCliente();
		LinkedList<TCliente> aux = null;
		
		aux = clienteDAO.listarClientes();
		if(aux != null){
			return aux;
		}
		else{
			return null;
		}
	}
	
	public LinkedList<TFactura> listarFacturas(Integer idCliente) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOCliente clienteDAO = dao.generaDAOCliente();
		LinkedList<TFactura> aux = null;
		
		aux = clienteDAO.listarFacturas(idCliente);
		if(aux != null){
			return aux;
		}
		else{
			return null;
		}
	}
}