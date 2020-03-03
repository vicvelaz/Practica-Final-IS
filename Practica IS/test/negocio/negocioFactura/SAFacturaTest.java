import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import negocio.negocioFactura.*;


public class SAFacturaTest {

	@Test
	public void abrirFacturaNoExistenteDatosBienExito() 
	{
		
		TFactura factura = new TFactura (5555,15,20.5,1500,342087945,009087);
		SAFactura saFactura = new SAFacturaImpl();
		Integer retorno = null;
		try 
		{
			 retorno = saFactura.abrirFactura(factura);
			 assertTrue(retorno == 0); //Si lo ha añadido correctamente
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void aplicarDescuentoExito() 
	{
		TFactura factura = new TFactura (55545,15,20.6,1500,3423087945,0092087);
		SAFactura saFactura = new SAFacturaImpl();
		Integer retorno = null;
		try 
		{
			retorno= saFactura.abrirFactura(factura);
			retorno=saFactura.aplicarDescuento(factura.getDescuento());
			assertTrue(factura.getPrecio()==20.6-15);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
		
		@Test
	public void cerrarFacturaExistenteExito() 
		{
	
			TFactura factura = new TFactura (555655,15,20.5,1500,087945,0096087);
			SAFactura saFactura = new SAFacturaImpl();
			Integer retorno = null;
			try 
			{
				 retorno = saFactura.cerrarFactura(factura);
				 assertTrue(retorno == 0); //Si lo ha añadido correctamente
			} catch (SQLException e) {
				fail(e.getMessage());
			}
		}
			
	
		
		@Test
		public void anadirProductoExistenteExito() 
		{
			TFactura factura = new TFactura (555655,15,20.5,1500,087945,0096087);
			SAFactura saFactura = new SAFacturaImpl();
			TProducto producto = new TProducto (999932,"Donut",2.4,200,7,true);
			Integer retorno = null;
			try 
			{
				 retorno = saFactura.abrirFactura(factura);
				 retorno = saFactura.anadirProducto(producto);
				 assertTrue(retorno >= 0); //Si lo ha añadido correctamente
			} catch (SQLException e) {
				fail(e.getMessage());
			}
		}
		
		@Test
		public void mostrarFacturaExistenteExito() 
		{
			TFactura factura = new TFactura (555655,15,20.5,1500,087945,0096087);
			SAFactura saFactura = new SAFacturaImpl();
			Integer retorno = null;
			try 
			{
				 retorno = saFactura.abrirFactura(factura);
				TFactura facturaRetorno = saFactura.mostrarFactura(factura.getId());
				 assertEquals(factura,facturaRetorno);
			} catch (SQLException e) {
				fail(e.getMessage());
			}
		}
		
		
}
