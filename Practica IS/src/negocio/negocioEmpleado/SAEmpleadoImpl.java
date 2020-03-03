package negocio.negocioEmpleado;

import java.sql.SQLException;
import java.util.LinkedList;

import integracion.factoriaDAO.FactoriaDAO;
import integracion.integracionEmpleado.DAOEmpleado;

public class SAEmpleadoImpl implements SAEmpleado {
	
	public Integer altaEmpleado(TEmpleado empleado) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOEmpleado empleadoDAO = dao.generaDAOEmpleado();
		TEmpleado aux = null;
		
		aux = empleadoDAO.devolverEmpleado(empleado.getId());
		if(aux == null){
			return empleadoDAO.altaEmpleado(empleado);
		}
		else{
			if(aux.getActivo() == true){
				return -1;
			}
			else{
				empleado.setId(aux.getId());
				empleado.setActivo(true);
				return empleadoDAO.editarEmpleado(empleado);
			}
		}
	}
	
	public Integer editarEmpleado(TEmpleado empleado) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOEmpleado empleadoDAO = dao.generaDAOEmpleado();
		TEmpleado aux = null;
		
		aux = empleadoDAO.devolverEmpleado(empleado.getId());
		if(aux == null || aux.getActivo() == false){
			return -1;
		}
		else{
			return empleadoDAO.editarEmpleado(empleado);
		}
	}
	
	public Integer bajaEmpleado(Integer id) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOEmpleado empleadoDAO = dao.generaDAOEmpleado();
		TEmpleado aux = null;
		
		aux = empleadoDAO.devolverEmpleado(id);
		if(aux == null || aux.getActivo() == false){
			return -1;
		}
		else{
			empleadoDAO.bajaEmpleado(id);
			return id;
		}
	}
	
	public TEmpleado devolverEmpleado(Integer id) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOEmpleado empleadoDAO = dao.generaDAOEmpleado();
		TEmpleado aux = null;
		
		aux = empleadoDAO.devolverEmpleado(id);
		if(aux != null && aux.getActivo()){
			return aux;
		}
		return null;
	}
	
	public LinkedList<TEmpleado> listarEmpleados() throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOEmpleado empleadoDAO = dao.generaDAOEmpleado();
		LinkedList<TEmpleado> aux = null;
		
		aux = empleadoDAO.listarEmpleados();
		if(aux != null){
			return aux;
		}
		else{
			return null;
		}
	}
}