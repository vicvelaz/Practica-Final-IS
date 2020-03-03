package negocio.negocioTurno;

import java.sql.SQLException;
import java.util.LinkedList;
import negocio.negocioEmpleado.TEmpleado;
import integracion.factoriaDAO.FactoriaDAO;
import integracion.integracionTurno.DAOTurno;

public class SATurnoImpl implements SATurno {
	
	public Integer anadirTurno(TTurno turno) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOTurno turnoDAO = dao.generaDAOTurno();
		
		return turnoDAO.anadirTurno(turno);
		/*TTurno aux = null;
		
		aux = turnoDAO.mostrarTurno(turno.getId());
		if(aux == null){
			return turnoDAO.anadirTurno(turno);
		}
		else{
			if(aux.getActivo() == true){
				return -1;
			}
			else{
				turno.setId(aux.getId());
				turno.setActivo(true);
				return turnoDAO.editarTurno(turno);
			}
		}*/
	}
	
	public Integer editarTurno(TTurno turno) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOTurno turnoDAO = dao.generaDAOTurno();
		TTurno aux = null;
		
		aux = turnoDAO.mostrarTurno(turno.getId());
		if(aux == null || aux.getActivo() == false){
			return -1;
		}
		else if(turno.getHoraFin() < 0 || turno.getHoraFin() > 23 || turno.getHoraInicio() < 0 || turno.getHoraInicio() > 23){
			return -2;
		}
		else{
			return turnoDAO.editarTurno(turno);
		}
	}
	
	public Integer eliminarTurno(Integer id) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOTurno turnoDAO = dao.generaDAOTurno();
		TTurno aux = null;
		
		aux = turnoDAO.mostrarTurno(id);
		if(aux == null || aux.getActivo() == false){
			return -1;
		}
		else{
			return turnoDAO.eliminarTurno(id);	// devuelve 0
		}
	}
	
	public TTurno mostrarTurno(Integer id) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOTurno turnoDAO = dao.generaDAOTurno();
		TTurno aux = null;
		
		aux = turnoDAO.mostrarTurno(id);
		if(aux != null && aux.getActivo() == true){
			return aux;
		}
		return null;
	}

	public TTurno devolverTurno(Integer idEmpleado) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOTurno turnoDAO = dao.generaDAOTurno();
		TTurno aux = null;
		
		aux = turnoDAO.devolverTurno(idEmpleado);
		if(aux == null){
			return null;
		}
		else{
			return aux;
		}
	}

	public LinkedList<TTurno> listarTurnos() throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOTurno turnoDAO = dao.generaDAOTurno();
		LinkedList<TTurno> aux = null;
		
		aux = turnoDAO.listarTurnos();
		if(aux != null){
			return aux;
		}
		else{
			return null;
		}
	}
	
	public LinkedList<TEmpleado> listarEmpleados(Integer id) throws SQLException {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOTurno turnoDAO = dao.generaDAOTurno();
		LinkedList<TEmpleado> aux = null;
		
		aux = turnoDAO.listarEmpleados(id);
		if(aux != null){
			return aux;
		}
		else{
			return null;
		}
	}
}