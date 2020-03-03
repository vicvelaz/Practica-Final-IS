
import static org.junit.Assert.*;

import org.junit.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import negocio.negocioEmpleado.SAEmpleado;
import negocio.negocioEmpleado.SAEmpleadoImp;
import negocio.negocioEmpleado.TEmpleado;


class SAEmpleadoTest {
	@Test
	public void listarEmpleadosVacioFallo() 
	{
		SAEmpleado saEmpleado = new SAEmpleadoImpl();
		try
		{
			LinkedList<TEmpleado> empleados = saEmpleado.listarEmpleados();
			assertTrue(empleados.isEmpty());
		}
		catch(SQLException e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void listarEmpleadosNoVacioExito() 
	{
		TEmpleado empleado = new TEmpleado(3422345,"EmpleadoEjemplo1", "DNIPrueabaEmpl1", true, 73737382);
		TEmpleado empleado2 = new TEmpleado(8883993294,"EmleadoEjemplo2", "DNIPruebaEmpl2",true, 0898435);
		
		SAEmpleado saEmpleado = new SAEmpleadoImpl();
		Integer id1, id2; 
		try
		{
			id1 = saEmpleado.anadirEmpleado(empleado); 
			id2 = saEmpleado.anadirEmpleado(empleado2); //Si falla devuelve -1
			
			LinkedList<TEmpleado> empleados = saEmpleado.listarEmpleados();
			assertTrue(empleados.contains(empleado) && empleados.contains(empleado2));
		}
		catch(SQLException e)
		{
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void altaEmpleadoNoExistenteDatosBienExito() 
	{
		TEmpleado empleado = new TEmpleado(3420945,"EmpleadoEjemplo3", "DNIPrueabaEmpl3", true, 73007382);
		SAEmpleado saEmpleado = new SAEmpleadoImpl();
		Integer retorno = null;
		try 
		{
			 retorno = saEmpleado.anadirEmpleado(empleado);
			 assertTrue(retorno == 0); //Si lo ha añadido correctamente
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void altaEmpleadoExistenteActivoFallo() 
	{
		TEmpleado empleado = new TEmpleado(342094009,"EmpleadoEjemplo4", "DNIPrueabaEmpl4", true, 74407382);
		TEmpleado empleado2 = new TEmpleado(342094009,"EmpleadoEjemplo4", "DNIPrueabaEmpl4", true, 74407382);
		SAEmpleado saEmpleado = new SAEmpleadoImpl();
		Integer retorno = null;
		try 
		{
			 saEmpleado.anadirEmpleado(empleado);
			 retorno = saEmpleado.anadirEmpleado(empleado2);
			 assertTrue(retorno == -1); //Si ya existe el empleado a añadir y esta activo
		} catch (SQLException e) 
		{
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void altaEmpleadoExistenteNoActivoExito() 
	{
		TEmpleado empleado = new TEmpleado(342294009,"EmpleadoEjemplo5", "DNIPrueabaEmpl5", true, 74407355);
		TEmpleado empleado2 = new TEmpleado(342294009,"EmpleadoEjemplo5", "DNIPrueabaEmpl5", true, 74407355);
		SAEmpleado saEmpleado = new SAEmpleadoImpl();
		Integer retorno = null;
		Integer id = null; 
		try 
		{
			 retorno = saEmpleado.anadirEmpleado(empleado);
			 id=empleado.getId();
			 saEmpleado.eliminarEmpleado(id); 
			 retorno = saEmpleado.anadirEmpleado(empleado2);
			 assertTrue( empleado2.getActivo()); 
		} catch (SQLException e) 
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void bajaEmpleadoExistenteExito() 
	{
		TEmpleado empleado = new TEmpleado(387294009,"EmpleadoEjemplo6", "DNIPrueabaEmpl6", true, 766657355);
		SAEmpleado saEmpleado = new SAEmpleadoImpl();
		Integer retorno = null;
		Integer id = null; 
		try 
		{
			 retorno = saEmpleado.anadirEmpleado(empleado);
			 id=empleado.getId();
			 retorno = saEmpleado.eliminarEmpleado(id); 
			assertTrue(retorno >= 0); 
		} catch (SQLException e) 
		{
			fail(e.getMessage());
		}
	}
	

	
	@Test
	public void mostrarEmpleadoExistenteExito() 
	{
		TEmpleado empleado = new TEmpleado(6544294009,"EmpleadoEjemplo7", "DNIPrueabaEmpl7", true, 7667777715);
		SAEmpleado saEmpleado = new SAEmpleadoImpl();
		TEmpleado retorno = null;
		Integer id = null; 
		try 
		{
			 retorno = saEmpleado.anadirEmpleado(empleado);
			 retorno = saEmpleado.mostrarEmpleado(empleado.getId()); 
			 assertTrue(retorno != null); 
		} catch (SQLException e) 
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void mostrarEmpleadoNoExistenteFallo() 
	{
		SAEmpleado saEmpleado = new SAEmpleadoImpl();
		TEmpleado retorno = null;
		try 
		{
			 retorno = saEmpleado.mostrarEmpleado(28454515131318); 
			 assertTrue(retorno == null); 
		} catch (SQLException e) 
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void modificarEmpleadoExistenteDatosBien()
	{
		TEmpleado empleado = new TEmpleado(65445432144009,"EmpleadoEjemplo8", "DNIPrueabaEmpl8", true, 7667707151);
		SAEmpleado saEmpleado = new SAEmpleadoImpl();
		Integer retorno = null;
		Integer id = null; 
		try 
		{
			 id = saEmpleado.anadirEmpleado(empleado);
			 TEmpleado empleadoMod = new TEmpleado1(65445432144009,"EmpleadoEjemplo9", "DNIPrueabaEmpl9", true, 7667707151); 
			 retorno = saEmpleado.editarEmpleado(empleadoMod); 
			 assertTrue(retorno >= 0 ); 
			 
		} 
		catch (SQLException e) 
		{
			fail(e.getMessage());
		}
	}
	

	
}
