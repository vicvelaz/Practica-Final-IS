package negocio.negocioCliente;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.junit.Test;
import static org.junit.Assert.*;
import negocio.negocioCliente.SACliente;
import negocio.negocioCliente.SAClienteImpl;
import negocio.negocioCliente.TCliente;
import negocio.negocioFactura.SAFactura;
import negocio.negocioFactura.SAFacturaImpl;
import negocio.negocioFactura.TFactura;

public class SAClienteTest {
	@Test
	public void listarClientesVacioFallo() 
	{
		SACliente saCliente = new SAClienteImpl();
		try
		{
			LinkedList<TCliente> clientes = saCliente.listarClientes();
			assertTrue(clientes.isEmpty());
		}
		catch(SQLException e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void listarClientesNoVacioExito() 
	{
		TCliente cliente = new TCliente(664899,"cliente1");
		TCliente cliente2 = new TCliente(60299,"cliente2");
		
		SACliente saCliente = new SAClienteImpl();
		Integer id1, id2; 
		try
		{
			id1 = saCliente.anadirCliente(cliente);
			id2 = saCliente.anadirCliente(cliente2); //Si falla devuelve -1
			
			LinkedList<TCliente> clientes = saCliente.listarClientes();
			assertTrue(clientes.contains(cliente) && clientes.contains(cliente2));
		}
		catch(SQLException e)
		{
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void anadirClienteNoExistenteDatosBienExito() 
	{
		TCliente cliente = new TCliente(60008990,"cliente3");
		SACliente saCliente = new SAClienteImpl();
		Integer retorno = null;
		try 
		{
			 retorno = saCliente.anadirCliente(cliente);
			 assertTrue(retorno == 0); //Si lo ha añadido correctamente
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	
	

	
	@Test
	public void devolverClientesExistenteExito() 
	{
		TCliente cliente = new TCliente(60408990,"cliente4");
		SACliente saCliente = new SAClienteImpl();
		TCliente retorno = null;
		Integer id = null; 
		
		try 
		{
			id = saCliente.anadirCliente(cliente);
			 retorno = saCliente.devolverCliente(cliente.getId()); 
			 assertTrue(retorno != null); 
		} catch (SQLException e) 
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void devolverClienteNoExistenteFallo() 
	{
		SACliente saCliente = new SAClienteImpl();
		TCliente cliente = null;
		try 
		{
			cliente = saCliente.devolverCliente(45454); 
			 assertTrue(cliente == null); 
		} catch (SQLException e) 
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void mostrarFActurasPorIdClienteExistentesExito()
	{
		TCliente cliente = new TCliente(61408990,"cliente5");
		SACliente saCliente = new SAClienteImpl();
		Integer retorno = null;
		Date date=new Date();
		
		TFactura factura=new TFactura(44342,10.4,56.2,date,97189494,61408990);
		SAFactura saFactura = new SAFacturaImpl();
		try {
			retorno = saCliente.anadirCliente(cliente);
			retorno=saFactura.abrirFactura(factura);
			LinkedList<TFactura> facturas=saCliente.listarFacturas(cliente.getId());
			assertTrue(facturas.contains(factura));
		}catch (SQLException e) 
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void editarClienteExistenteDatosBien()
	{
		TCliente cliente = new TCliente(61404990,"cliente6");	
		SACliente saCliente = new SAClienteImpl();
		Integer retorno = null;
		Integer id = null; 
		try 
		{
			 id = saCliente.anadirCliente(cliente);
			 TCliente clienteMod = new TCliente(61404990,"cliente7");
			 retorno=saCliente.editarCliente(clienteMod);
			
			 assertTrue(retorno >= 0 ); 
			 
		} 
		catch (SQLException e) 
		{
			fail(e.getMessage());
		}
	}

}
