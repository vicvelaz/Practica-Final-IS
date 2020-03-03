package negocio.negocioFactura;

import integracion.factoriaDAO.FactoriaDAO;
import integracion.integracionFactura.DAOFactura;
import integracion.integracionProducto.DAOProducto;

import java.sql.SQLException;

import negocio.negocioProducto.TProducto;

public class SAFacturaImpl implements SAFactura {
	public Integer anadirProducto(TProducto p) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOProducto productoDAO = dao.generaDAOProducto();
		DAOFactura facturaDAO = dao.generaDAOFactura();
		TProducto producto = null;
		
		producto = productoDAO.devolverProducto(p.getId());
		if(producto == null || producto.getActivo() == false){
			return -1;
		}
		else{
			if(producto.getCantidad() == 0 || p.getCantidad() > producto.getCantidad()){	
				return -2;
			}
			else{
				return facturaDAO.anadirProducto(p);
			}
		}
	}
	
	public Integer aplicarDescuento(TFactura factura) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOFactura facturaDAO = dao.generaDAOFactura();
		TFactura aux = null;
		
		aux = facturaDAO.mostrarFactura(factura.getId());
		if(aux == null){
			return -1;
		}
		else{
			return facturaDAO.aplicarDescuento(factura);
		}
	}
	
	public Integer devolucionProducto(TProducto p) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOFactura facturaDAO = dao.generaDAOFactura();
		
		if(p == null || p.getActivo() == false){
			return -1;
		}
		else{
			return facturaDAO.eliminar(p.getId());
		}
	}

	public TFactura mostrarFactura(Integer id) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOFactura facturaDAO = dao.generaDAOFactura();
		TFactura aux = null;
		
		aux = facturaDAO.mostrarFactura(id);
		if(aux != null){
			return aux;
		}
		return null;
	}

	public Integer abrirFactura(TFactura factura) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOFactura facturaDAO = dao.generaDAOFactura();
		TFactura aux = null;
		
		aux = facturaDAO.mostrarFactura(factura.getId());
		if(aux == null){
			return facturaDAO.abrirFactura(factura);
		}
		else{
			return -1;
		}
	}

	public Integer cerrarFactura(TFactura factura) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOFactura facturaDAO = dao.generaDAOFactura();
		TFactura aux = null;
		
		aux = facturaDAO.mostrarFactura(factura.getId());
		if(aux != null){
			return facturaDAO.cerrarFactura(factura);
		}
		else{
			return -1;
		}
	}
}