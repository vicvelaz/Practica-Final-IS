package integracion.factoriaDAO;

import integracion.integracionCliente.DAOCliente;
import integracion.integracionCliente.DAOClienteImpl;
import integracion.integracionEmpleado.DAOEmpleado;
import integracion.integracionEmpleado.DAOEmpleadoImpl;
import integracion.integracionFactura.DAOFactura;
import integracion.integracionFactura.DAOFacturaImpl;
import integracion.integracionProducto.DAOProducto;
import integracion.integracionProducto.DAOProductoImpl;
import integracion.integracionTurno.DAOTurno;
import integracion.integracionTurno.DAOTurnoImpl;

public class FactoriaDAOImpl extends FactoriaDAO{
	
	public DAOTurno generaDAOTurno(){
	 	return new DAOTurnoImpl();
	}
	
	public DAOCliente generaDAOCliente(){
		return new DAOClienteImpl();
	}
	
	public DAOEmpleado generaDAOEmpleado(){
		return new DAOEmpleadoImpl();
	}
	
	public DAOFactura generaDAOFactura(){
		return new DAOFacturaImpl();
	}
	
	public DAOProducto generaDAOProducto(){
		return new DAOProductoImpl();
	}
}