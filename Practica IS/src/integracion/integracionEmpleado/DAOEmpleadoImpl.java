package integracion.integracionEmpleado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import negocio.negocioEmpleado.TEmpleado;

public class DAOEmpleadoImpl implements DAOEmpleado {
	
	Connection connection;
	PreparedStatement pStatement;
	ResultSet rs;

	public Integer altaEmpleado(TEmpleado empleado) throws SQLException {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("insert into empleados values (null,?,?,?,?)",
					   PreparedStatement.RETURN_GENERATED_KEYS);
			
			pStatement.setString(1, empleado.getDni());
			pStatement.setString(2, empleado.getNombre());
			pStatement.setBoolean(3, empleado.getActivo());
			pStatement.setInt(5, empleado.getIdTurno());
			
			
			pStatement.executeUpdate();
			rs = pStatement.getGeneratedKeys();
			if(rs.next()) {
				empleado.setId(rs.getInt(1));
				return rs.getInt(1);
			}else {
				return -1;
			}
			
			
		} catch (SQLException e) {
			//return -1;
			throw new SQLException();
		}finally {
			closeAll();
		}
		
	}
	

	public Integer editarEmpleado(TEmpleado empleado) throws SQLException {
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select ID from empleado WHERE ID = ?");
			pStatement.setInt(1, empleado.getId());
			rs = pStatement.executeQuery();	// te faltaba poner esta función, como lo hiciste en turno
			if(rs.next()) {
				
				pStatement = connection.prepareStatement("update empleado set activo = true, nombre = ?, dni = ?, id_turno = ? where id = " + empleado.getId());		
				pStatement.setString(2, empleado.getDni());
				pStatement.setString(1, empleado.getNombre());
				pStatement.setInt(3, empleado.getIdTurno());
				pStatement.executeUpdate();	
				return 0;
			}else {
				return -1;
			}
			
		} catch (SQLException e) {
			//return -1;
			throw new SQLException();
		}finally {
			closeAll();
		}
	}

	public Integer bajaEmpleado(Integer id) throws SQLException {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("update empleado set activo = false where id = " + id.toString());
			//pStatement.setInt(1, id);		// no hace falta y produce un error.
			pStatement.executeUpdate();
			//rs = pStatement.executeQuery();	// no hace falta y produce un error.
			return 0;
		} catch (SQLException e) {
			//return -1;
			throw new SQLException();
		}finally {
			closeAll();
		}
	}

	public TEmpleado devolverEmpleado(Integer id) throws SQLException {
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from empleado WHERE activo = true and ID = ?");
			pStatement.setInt(1, id);
			rs = pStatement.executeQuery();
			if(rs.next()) {
				TEmpleado empleado = new TEmpleado(rs.getInt(1), rs.getString(3), rs.getString(2), rs.getBoolean(4), rs.getInt(5));
				return empleado;
			}else {
				return null;
			}
			
		} catch (SQLException e) {
			//return null;
			throw new SQLException();
		}finally {
			closeAll();
		}
	}
		

	public LinkedList<TEmpleado> listarEmpleados() throws SQLException {
		
		LinkedList<TEmpleado> listaEmpleados = new LinkedList<>();
		try {
			int i = 0;
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from empleado WHERE activo = true ");
			rs = pStatement.executeQuery();
			
		
			while (rs.next()) {
				TEmpleado empleado = new TEmpleado(rs.getInt(1), rs.getString(3), rs.getString(2), rs.getBoolean(4), rs.getInt(5));
				listaEmpleados.add(empleado);
				i++;
			}
			if(i == 0) {
				return null;
			}else {
				return listaEmpleados;
			}
			
		} catch (SQLException e) {
			//return null;
			throw new SQLException();
		}finally {
			closeAll();
		}
	}
	
	private void closeAll() {
		if(rs != null) {
			try {
				rs.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(pStatement != null) {
			try {
				pStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
