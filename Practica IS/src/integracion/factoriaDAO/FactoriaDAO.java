package integracion.factoriaDAO;

import integracion.integracionCliente.DAOCliente;
import integracion.integracionEmpleado.DAOEmpleado;
import integracion.integracionFactura.DAOFactura;
import integracion.integracionProducto.DAOProducto;
import integracion.integracionTurno.DAOTurno;

public abstract class FactoriaDAO {
	private static FactoriaDAO dao;
	
	public static FactoriaDAO getInstancia() {
		if(dao == null) {
			dao = new FactoriaDAOImpl();
		}
		return dao;
	}
	
	public abstract DAOTurno generaDAOTurno();
	public abstract DAOCliente generaDAOCliente();
	public abstract DAOFactura generaDAOFactura();
	public abstract DAOProducto generaDAOProducto();
	public abstract DAOEmpleado generaDAOEmpleado();
}