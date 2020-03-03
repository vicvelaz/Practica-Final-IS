package integracion.integracionCliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import negocio.negocioCliente.TCliente;
import negocio.negocioFactura.TFactura;

public class DAOClienteImpl implements DAOCliente {
	
	Connection connection;
	PreparedStatement pStatement;
	ResultSet rs;
	
	public Integer altaCliente(TCliente cliente) throws SQLException {
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("insert into productos values (null,?)",
					   PreparedStatement.RETURN_GENERATED_KEYS);
			pStatement.setString(1,cliente.getNombre());
			pStatement.executeUpdate();
			rs = pStatement.getGeneratedKeys();
			if(rs.next()) {
				cliente.setId(rs.getInt(1));
				return rs.getInt(1);
			}else {
				return-1;
			}
				
		} catch (SQLException e) {
			//return -1;
			throw new SQLException();
		}finally {
			closeAll();
		}
		
	}

	@Override
	public Integer editarCliente(TCliente cliente) throws SQLException {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			
			pStatement = connection.prepareStatement("select ID from cliente WHERE ID = ?");
			pStatement.setInt(1, cliente.getId());
			rs = pStatement.executeQuery();
			if(rs.next()) {
				pStatement = connection.prepareStatement("update cliente set nombre = ? where id = ?");
				pStatement.setString(1, cliente.getNombre());
				pStatement.setInt(2, cliente.getId());				 
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

	@Override
	public TCliente devolverCliente(Integer id) throws SQLException {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from cliente where ID = ?");
			pStatement.setInt(1, id);
			rs = pStatement.executeQuery();
			if(rs.next()) {
				TCliente cliente = new TCliente(id, rs.getString(2));
				return cliente;
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

	@Override
	public LinkedList<TCliente> listarClientes() throws SQLException {
		LinkedList<TCliente> listaClientes = new LinkedList<>();
		try {
			int i = 0;
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from cliente");
			rs = pStatement.executeQuery();
			while (rs.next()) {
				TCliente cliente = new TCliente(rs.getInt(1), rs.getString(2));
				listaClientes.add(cliente);
				i++;
			}
			
			if(i == 0) {
				return null;
			}else {
				return listaClientes;
			}
			
		} catch (SQLException e) {
			//return null;
			throw new SQLException();
		}finally {
			closeAll();
		}

}

	@Override
	public LinkedList<TFactura> listarFacturas(Integer idCliente) throws SQLException {
		LinkedList<TFactura> listaFacturas = new LinkedList<>();
		try {
			int i = 0;
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from factura where cliente_id = " + idCliente.toString());
			rs = pStatement.executeQuery();
			while (rs.next()) {
				TFactura factura = new TFactura(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getDate(4), rs.getInt(6), rs.getInt(7));
				listaFacturas.add(factura);
				i++;
			}
			
			if(i == 0) {
				return null;
			}else {
				return listaFacturas;
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