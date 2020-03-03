import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.Test;

import negocio.negocioProducto.*;

public class SAProductoTest {

	@Test
	public void listarProductosVacioFallo() 
	{
		SAProducto saProducto = new SAProductoImpl();
		try
		{
			LinkedList<TProducto> productos = saProducto.listarProductos();
			assertTrue(productos.isEmpty());
		}
		catch(SQLException e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void listarEmpleadosNoVacioExito() 
	{
		TProducto producto = new TProducto(55555,"producto1",7.2,120,3,true);
		TProducto producto2 = new TProducto(55455,"producto2",9.2,890,30,true);
		
		SAProducto saProducto = new SAProductoImpl();
		Integer id1, id2; 
		try
		{
			id1 = saProducto.anadirProducto(producto); 
			id2 = saProducto.anadirProducto(producto2); //Si falla devuelve -1
			
			LinkedList<TProducto> productos = saProducto.listarProductos();
			assertTrue(productos.contains(producto) && productos.contains(producto2));
		}
		catch(SQLException e)
		{
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void anadirProductoNoExistenteDatosBienExito() 
	{
		TProducto producto = new TProducto(009555,"producto3",7.2,180,3,true);
		SAProducto saProducto = new SAProductoImpl();
		Integer retorno = null;
		try 
		{
			 retorno = saProducto.anadirProducto(producto);
			 assertTrue(retorno == 0); //Si lo ha añadido correctamente
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void anadirProductoExistenteActivoFallo() 
	{
		TProducto producto = new TProducto(50965675,"producto4",7.9,120,3,true);
		TProducto producto2 = new TProducto(50965675,"producto4",7.9,120,3,true);
		SAProducto saProducto = new SAProductoImpl();
		Integer retorno = null;
		try 
		{
			saProducto.anadirProducto(producto);
			 retorno = saProducto.anadirProducto(producto2);
			 assertTrue(retorno == -1); //Si ya existe el empleado a añadir y esta activo
		} catch (SQLException e) 
		{
			fail(e.getMessage());
		}
	}
	

	@Test
	public void borrarProductoExistenteExito() 
	{
		TProducto producto = new TProducto(50999985,"producto5",7.9,123,3,true);
		SAProducto saProducto = new SAProductoImpl();
		Integer retorno = null;
		Integer id = null; 
		try 
		{
			 retorno = saProducto.anadirProducto(producto);
			 
			 retorno = saProducto.eliminarProducto(producto.getActivo()); 
			assertTrue(retorno >= 0); 
		} catch (SQLException e) 
		{
			fail(e.getMessage());
		}
	}
	

	
	@Test
	public void mostrarProductoExistenteExito() 
	{
		TProducto producto = new TProducto(111129985,"producto6",4.9,123,3,true);
		SAProducto saProducto = new SAProductoImpl();
		TEmpleado retorno = null;
		Integer id = null; 
		try 
		{
			 retorno = saProducto.anadirProducto(producto);
			 retorno = saProducto.mostrarProducto(producto.getId()); 
			 assertTrue(retorno != null); 
		} catch (SQLException e) 
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void mostrarProductoNoExistenteFallo() 
	{
		SAProducto saProducto = new SAProductoImpl();
		TProducto retorno = null;
		try 
		{
			 retorno = saProducto.mostrarProducto(01101010101); 
			 assertTrue(retorno == null); 
		} catch (SQLException e) 
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void editarProductoExistenteDatosBien()
	{
		TProducto producto = new TProducto(111129985,"producto7",9.9,663,3,true);
		SAProducto saProducto = new SAProductoImpl();
		Integer retorno = null;
		Integer id = null; 
		try 
		{
			 id = saProducto.anadirProducto(producto);
			 TProducto productoMod = new TProducto1(111129985,"producto8",10.9,663,3,true); 
			 retorno = saProducto.editarProducto(productoMod); 
			 assertTrue(retorno >= 0 ); 
			 
		} 
		catch (SQLException e) 
		{
			fail(e.getMessage());
		}
	}

}
