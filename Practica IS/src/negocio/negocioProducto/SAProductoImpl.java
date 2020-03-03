package negocio.negocioProducto;

import java.sql.SQLException;
import java.util.LinkedList;

import integracion.factoriaDAO.FactoriaDAO;
import integracion.integracionProducto.DAOProducto;

public class SAProductoImpl implements SAProducto {

	public Integer altaProducto(TProducto producto) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOProducto productoDAO = dao.generaDAOProducto();
		TProducto aux = null;
		
		aux = productoDAO.devolverProducto(producto.getId());
		if(aux == null){
			return productoDAO.altaProducto(producto);
		}
		else{
			if(aux.getActivo() == true){
				return -1;
			}
			else{
				producto.setId(aux.getId());
				producto.setActivo(true);
				return productoDAO.editarProducto(producto);
			}
		}
	}
	
	public Integer editarProducto(TProducto producto) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOProducto productoDAO = dao.generaDAOProducto();
		TProducto aux = null;
		
		aux = productoDAO.devolverProducto(producto.getId());
		if(aux == null || aux.getActivo() == false){
			return -1;
		}
		else if(aux.getCantidad() < 0 || aux.getCalorias() < 0 || aux.getPrecio() < 0 || aux.getNombre().equalsIgnoreCase("")){
			return -2;
		}
		else{
			return productoDAO.editarProducto(producto);
		}
	}
	
	public Integer bajaProducto(Integer id) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOProducto productoDAO = dao.generaDAOProducto();
		TProducto aux = null;
		
		aux = productoDAO.devolverProducto(id);
		if(aux == null){
			return -1;
		}
		else{
			productoDAO.bajaProducto(id);
			return id;
		}
	}
	
	public TProducto devolverProducto(Integer id) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOProducto productoDAO = dao.generaDAOProducto();
		TProducto aux = null;
		
		aux = productoDAO.devolverProducto(id);
		if(aux != null){
			return aux;
		}
		return null;
	}
	
	public LinkedList<TProducto> listarProductos() throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOProducto productoDAO = dao.generaDAOProducto();
		LinkedList<TProducto> aux = null;
		
		aux = productoDAO.listarProductos();
		if(aux != null){
			return aux;
		}
		else{
			return null;
		}
	}
}