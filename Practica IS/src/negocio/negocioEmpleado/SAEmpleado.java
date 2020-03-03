package negocio.negocioEmpleado;

import java.sql.SQLException;
import java.util.LinkedList;

public interface SAEmpleado {
	public abstract Integer altaEmpleado(TEmpleado empleado) throws SQLException;
	public abstract Integer editarEmpleado(TEmpleado empleado) throws SQLException;
	public abstract Integer bajaEmpleado(Integer id) throws SQLException;
	public abstract TEmpleado devolverEmpleado(Integer id) throws SQLException;
	public abstract LinkedList<TEmpleado> listarEmpleados() throws SQLException;
}