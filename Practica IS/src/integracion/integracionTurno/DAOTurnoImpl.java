package integracion.integracionTurno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import negocio.negocioEmpleado.TEmpleado;
import negocio.negocioTurno.TTurno;

public class DAOTurnoImpl implements DAOTurno{
	
	Connection connection;
	PreparedStatement pStatement;
	ResultSet rs;

	public Integer anadirTurno(TTurno turno) throws SQLException {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("insert into Turno values (null,?,?,?,?)",
					   PreparedStatement.RETURN_GENERATED_KEYS);
			
			pStatement.setString(1, turno.getNombre());
			pStatement.setInt(2, turno.getHoraInicio());
			pStatement.setInt(3, turno.getHoraFin());
			pStatement.setBoolean(4, turno.getActivo());
			pStatement.executeUpdate();
			rs = pStatement.getGeneratedKeys();
			if(rs.next()) {
				turno.setId(rs.getInt(1));
				return rs.getInt(1);
			}else {
				return -1;
			}
				
		} catch (SQLException e) {
			//return null;
			throw new SQLException();
		}finally {
			closeAll();
		}
		
	}
	
	public Integer editarTurno(TTurno turno) throws SQLException{
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			
			pStatement = connection.prepareStatement("select ID from turno WHERE ID = ?");
			pStatement.setInt(1, turno.getId());
			rs = pStatement.executeQuery();
			if(rs.next()) {
				pStatement = connection.prepareStatement("update turno set activo = true, nombre = ?, horainicio = ?, horafin = ? where id = ?");	
				pStatement.setString(1, turno.getNombre());
				pStatement.setInt(2, turno.getHoraInicio());
				pStatement.setInt(3, turno.getHoraFin());
				pStatement.setInt(4, turno.getId());
				pStatement.executeUpdate();		
				return 0;
			}else {
				return -1;
			}	
			
		} catch (SQLException e) {
			//return null;
			throw new SQLException();
		}finally {
			closeAll();
		}
	}

	public Integer eliminarTurno(Integer id) throws SQLException {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			Statement statement = connection.createStatement();
			String sql = "update turno set activo = false where id =" + id;
			statement.executeUpdate(sql);
			
			return 0;
		} catch (SQLException e) {
			//return null;
			throw new SQLException();
		}finally {
			closeAll();
		}
		
	}
	
	public TTurno mostrarTurno(Integer id) throws SQLException{		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from turno WHERE activo = true and ID = ?");
			pStatement.setInt(1, id);
			rs = pStatement.executeQuery();
			if(rs.next()) {
				TTurno turno = new TTurno(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), true);
				return turno;
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
	
	public TTurno devolverTurno(Integer idEmpleado) throws SQLException {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select turno.id, turno.nombre, turno.horainicio, " + 
					"turno.horafin, turno.activo from turno join empleado " + 
					"on turno.id = empleado.id_turno where turno.activo=true and empleado.activo = true and empleado.id = ?");
			pStatement.setInt(1, idEmpleado);
			rs = pStatement.executeQuery();
			if(rs.next()) {
				TTurno turno = new TTurno(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), true);
				return turno;
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
	
	public LinkedList<TTurno> listarTurnos() throws SQLException{
		LinkedList<TTurno> listaTurnos = new LinkedList<>();
		try {
			int i = 0;
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from turno WHERE activo = true ");
			rs = pStatement.executeQuery();
			while (rs.next()) {
				TTurno turno = new TTurno(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), true);
				listaTurnos.add(turno);
				i++;
			}
			
			if(i == 0) {
				return null;
			}else {
				return listaTurnos;
			}
			
		} catch (SQLException e) {
			//return null;
			throw new SQLException();
		}finally {
			closeAll();
		}

}
	public LinkedList<TEmpleado> listarEmpleados(Integer idTurno) throws SQLException{
		LinkedList<TEmpleado> listaEmpleados = new LinkedList<>();
		try {
			int i = 0;
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from empleado WHERE activo = true and id_turno = " + idTurno.toString());
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