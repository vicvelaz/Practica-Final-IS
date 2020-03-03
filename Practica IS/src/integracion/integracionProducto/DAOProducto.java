package integracion.integracionProducto;

import java.sql.SQLException;
import java.util.LinkedList;

import negocio.negocioProducto.TProducto;

public interface DAOProducto {
	public abstract Integer altaProducto(TProducto producto) throws SQLException;
	public abstract Integer editarProducto(TProducto producto) throws SQLException;
	public abstract Integer bajaProducto(Integer id) throws SQLException;
	public abstract TProducto devolverProducto(Integer id) throws SQLException;
	public abstract LinkedList<TProducto> listarProductos() throws SQLException;
}