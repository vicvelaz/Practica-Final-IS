package integracion.integracionFactura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import negocio.negocioFactura.TFactura;
import negocio.negocioProducto.TProducto;

public class DAOFacturaImpl implements DAOFactura {


	Connection connection;
	PreparedStatement pStatement;
	ResultSet rs;
	
	
	public Integer anadirProducto(TProducto p) throws SQLException {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			Statement statement = connection.createStatement();
			String sql = "select cantidad from productos where id =" + p.getId();
			rs = statement.executeQuery(sql);
			int cantidad = rs.getInt(1);
			if(cantidad > p.getCantidad()) {
				cantidad -= p.getCantidad();
				sql = "update cantidad =" + cantidad + "from productos where id ="+p.getId();
				statement.executeQuery(sql);
				return 0;
			}else {
				return -1;
			}
		
		} catch (SQLException e) {
			return -1;
		}finally {
			closeAll();
		}
	}

	public Integer aplicarDescuento(TFactura factura) throws SQLException {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("update descuento = ? from productos where id = ?");
			pStatement.setDouble(1, factura.getDescuento());
			pStatement.setInt(2, factura.getId());
			pStatement.executeQuery();
			return 0;
	
		
		} catch (SQLException e) {
			return -1;
		}finally {
			closeAll();
		}
	}

	public Integer eliminar(Integer id) throws SQLException {
		return null;
	}

	public Integer devolucionProducto(TProducto p) throws SQLException {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select cantidad from productos where id = ?");
			pStatement.setInt(1, p.getId()); 
			rs = pStatement.executeQuery();
			int cantidad = rs.getInt(1);
			cantidad += p.getCantidad();
			pStatement = connection.prepareStatement("update cantidad = ? from productos where id = ?");
			pStatement.setInt(1, cantidad);
			pStatement.setInt(2, p.getId());
			pStatement.executeQuery();
			return 0;
			
		
		} catch (SQLException e) {
			return -1;
		}finally {
			closeAll();
		}
	}


	public TFactura mostrarFactura(Integer id) throws SQLException {
		try {
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from productos WHERE activo = true and ID = ?");
			pStatement.setInt(1, id);
			rs = pStatement.executeQuery();
			if(rs.next()) {
				TFactura factura = new TFactura(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getDate(4), rs.getInt(6), rs.getInt(7));
				return factura;
			}else {
				return null;
			}
			
		} catch (SQLException e) {
			return null;
		}finally {
			closeAll();
		}
	}

	public Integer abrirFactura(TFactura factura) throws SQLException {

		Date date = new Date();
		java.sql.Date date2 = (java.sql.Date) date;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("insert into factura values (null,0,0,?,true,?,?)",
					   PreparedStatement.RETURN_GENERATED_KEYS);
			
			pStatement.setDate(1,date2);
			pStatement.setInt(2, factura.getIdCliente());
			pStatement.setInt(3, factura.getIdEmpleado());
			
			pStatement.executeUpdate();
			rs = pStatement.getGeneratedKeys();
			factura.setId(rs.getInt(1));
			factura.setIdCleinte(factura.getIdCliente());
			factura.setIdEmpleado(factura.getIdEmpleado());
			factura.setFecha(date2);
			return 0;
			
		} catch (SQLException e) {
			return -1;
		}finally {
			closeAll();
		}
		
	}
	public Integer cerrarFactura(TFactura factura) throws SQLException {
		
		try {
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("update precio = ? from factura where id = ?");
			pStatement.setDouble(1, factura.getPrecio());
			pStatement.setInt(2,  factura.getId());
			pStatement.executeUpdate();
			
			return 0;
		} catch (SQLException e) {
			return null;
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