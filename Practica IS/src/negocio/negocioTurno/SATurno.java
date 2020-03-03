package negocio.negocioTurno;

import java.sql.SQLException;
import java.util.LinkedList;

import negocio.negocioEmpleado.TEmpleado;

public interface SATurno {
	public abstract Integer anadirTurno(TTurno turno) throws SQLException;
	public abstract Integer editarTurno(TTurno turno) throws SQLException;
	public abstract Integer eliminarTurno(Integer id) throws SQLException;
	public abstract TTurno mostrarTurno(Integer id) throws SQLException;
	public abstract TTurno devolverTurno(Integer n) throws SQLException;
	public abstract LinkedList<TTurno> listarTurnos() throws SQLException;
	public abstract LinkedList<TEmpleado> listarEmpleados(Integer idTurno) throws SQLException;
}