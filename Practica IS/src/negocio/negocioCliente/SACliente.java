package negocio.negocioCliente;

import java.sql.SQLException;
import java.util.LinkedList;

import negocio.negocioFactura.TFactura;

public interface SACliente {
	public abstract Integer altaCliente(TCliente cliente) throws SQLException;
	public abstract Integer editarCliente(TCliente cliente) throws SQLException;
	public abstract TCliente devolverCliente(Integer id) throws SQLException;
	public abstract LinkedList<TCliente> listarClientes() throws SQLException;
	public abstract LinkedList<TFactura> listarFacturas(Integer idCliente) throws SQLException;
}