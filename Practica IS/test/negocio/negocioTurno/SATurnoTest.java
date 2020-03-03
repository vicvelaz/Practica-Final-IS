package negocio.negocioTurno;


	import static org.junit.Assert.*;

	import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.Test;

import negocio.negocioEmpleado.SAEmpleado;
import negocio.negocioEmpleado.SAEmpleadoImpl;
import negocio.negocioEmpleado.TEmpleado;
import negocio.negocioTurno.SATurno;
import negocio.negocioTurno.SATurnoImpl;
import negocio.negocioTurno.TTurno;

	public class SATurnoTest {
		
		@Test
		public void altaTurnoNoExistenteExito()
		{
			TTurno turno=new TTurno (0001,"turno1",1,2,true);
			SATurno saTurno=new SATurnoImpl();
			Integer retorno=null;
			try {
				retorno = saTurno.altaTurno(turno);
				 assertTrue(retorno == 0); //Si lo ha añadido correctamente
			}
			catch(SQLException e)
			{
				fail(e.getMessage());
			}
		}

		@Test
		public void altaTurnoExistenteFallo()
		{
			TTurno turno=new TTurno (0002,"turno2",1,2,true);
			TTurno turno1=new TTurno (0002,"turno3",1,2,true);
			SATurno saTurno=new SATurnoImpl();
			Integer retorno=null;
			try {
				retorno = saTurno.altaTurno(turno);
				retorno=saTurno.altaTurno(turno1);
				 assertTrue(retorno != 0); //Si lo ha añadido correctamente devuelve el error
			}
			catch(SQLException e)
			{
				fail(e.getMessage());
			}
		}
		
		@Test
		public void editarTurnoNoExistenteFallo()
		{
			TTurno turno=new TTurno (0003,"turno4",1,2,true);
			SATurno saTurno=new SATurnoImpl();
			Integer retorno=null;
			try {
				retorno=saTurno.editarTurno(turno);
				assertTrue(retorno!=0);
			}
			catch(SQLException e)
			{
				fail(e.getMessage());
			}
		}
		
		@Test
		public void editarTurnoExistenteExito() //noooooo
		{
			TTurno turno=new TTurno (0005,"turno5",1,2,true);
			TTurno turno1=new TTurno (0005,"turno6",6,9,true);
			SATurno saTurno=new SATurnoImpl();
			Integer retorno=null;
			
			try {
				retorno=saTurno.altaTurno(turno);
				retorno=saTurno.editarTurno(turno1);
				assertTrue(retorno==0);
			}
			catch(SQLException e)
			{
				fail(e.getMessage());
			}
		}
		
		@Test
		public void listarEmpleadosDeTurnoExistenteExito()
		{
			TTurno turno=new TTurno (0007,"turno7",1,2,true);
			TEmpleado empleado= new TEmpleado(00001,"empleado1","222165A",true,0007);
			SATurno saTurno=new SATurnoImpl();
			SAEmpleado saEmpleado=new SAEmpleadoImpl();
			//Integer retorno=null;
			LinkedList<TEmpleado> listaEmpleados=null;
			try {
				saTurno.altaTurno(turno);
				saEmpleado.altaEmpleado(empleado);
				listaEmpleados=saTurno.listarEmpleados(turno.getId());
				assertNotNull(listaEmpleados); //Devuelve true si no esta vacia la lista
			}
			catch(SQLException e)
			{
				fail(e.getMessage());
			}
		}
		
		@Test
		public void listarEmpleadosDeTurnoNoExistenteFallo()
		{
			TTurno turno=new TTurno (0010,"turno10",1,2,true);
			SATurno saTurno=new SATurnoImpl();
			LinkedList<TEmpleado> listaEmpleados=null;
			try {
				saTurno.altaTurno(turno);
				listaEmpleados=saTurno.listarEmpleados(turno.getId());
				assertNull(listaEmpleados); //Devuelve true si esta vacia la lista
			}
			catch(SQLException e)
			{
				fail(e.getMessage());
			}
		}
		
		@Test
		public void listarTurnosExistentesExito()
		{
			TTurno turno=new TTurno (0011,"turno11",1,2,true);
			SATurno saTurno=new SATurnoImpl();
			LinkedList<TTurno> listaTurnos=null;
			try {
				saTurno.altaTurno(turno);
				listaTurnos=saTurno.listarTurnos();
				assertNotNull(listaTurnos); //Devuelve true si no esta vacia la lista
			}
			catch(SQLException e)
			{
				fail(e.getMessage());
			}
		}
		
		@Test
		public void listarTurnosNoExistentesFallo()
		{
			SATurno saTurno=new SATurnoImpl();
			LinkedList<TTurno> listaTurnos=null;
			try {
				listaTurnos=saTurno.listarTurnos();
				assertNull(listaTurnos); //Devuelve true si esta vacia la lista
			}
			catch(SQLException e)
			{
				fail(e.getMessage());
			}
		}
		
		@Test
		public void bajaTurnoExistenteExito()
		{
			TTurno turno=new TTurno (00012,"turno12",1,2,true);
			SATurno saTurno=new SATurnoImpl();
			Integer retorno=null;
			try {
				retorno =saTurno.altaTurno(turno);
				retorno=saTurno.bajaTurno(turno.getId());
				assertTrue(retorno==0);
			}
			catch(SQLException e)
			{
				fail(e.getMessage());
			}
		}
		
		@Test
		public void bajaTurnoNoExistente()
		{
			TTurno turno=new TTurno (00013,"turno13",1,2,true);
			SATurno saTurno=new SATurnoImpl();
			Integer retorno=null;
			try {   //No se añade el turno
				retorno=saTurno.bajaTurno(turno.getId());
				assertTrue(retorno!=0);
			}
			catch(SQLException e)
			{
				fail(e.getMessage());
			}
		}
		
		@Test
		public void devolverTurnoDeEmpleadoExistente ()
		{
			TTurno turno=new TTurno (00014,"turno14",1,2,true);
			SATurno saTurno=new SATurnoImpl();
			TEmpleado empleado=new TEmpleado(00002,"empleado2","222100A",true,00014);
			SAEmpleado saEmpleado= new SAEmpleadoImpl();
			TTurno retorno=null;
			try {
				saTurno.altaTurno(turno);
				saEmpleado.altaEmpleado(empleado);
				retorno=saTurno.devolverTurnoDeEmpleado(empleado.getId());
				assertEquals(turno,retorno); 
			}
			catch(SQLException e)
			{
				fail(e.getMessage());
			}
		}
		
		@Test
		public void devolverTurnoDeEmpleadoNoExistente()
		{
			TTurno turno=new TTurno (00015,"turno15",1,2,true);
			SATurno saTurno=new SATurnoImpl();
			TEmpleado empleado=new TEmpleado(00003,"empleado3","222100A",true,00015);
			TTurno retorno=null;
			try {//No hace el alta del empleado
				saTurno.altaTurno(turno);
				retorno=saTurno.devolverTurnoDeEmpleado(empleado.getId());
				assertNotEquals(turno,retorno); 
			}
			catch(SQLException e)
			{
				fail(e.getMessage());
			}
		}
		
		@Test
		public void devolverTurnoExistenteTrue()
		{
			TTurno turno=new TTurno (00016,"turno16",1,2,true);
			SATurno saTurno=new SATurnoImpl();
			TTurno retorno=null;
			try {
				saTurno.altaTurno(turno);
				retorno=saTurno.devolverTurno(turno.getId());
				assertEquals(turno,retorno); 
			}
			catch(SQLException e)
			{
				fail(e.getMessage());
			}
		}
		
		@Test
		public void devolverTurnoNoExistenteFallo()
		{
			
			TTurno turno=new TTurno (00017,"turno17",1,2,true);
			SATurno saTurno=new SATurnoImpl();
			TTurno retorno=null;
			try {
				//No da de alta el turno
				retorno=saTurno.devolverTurno(turno.getId());
				assertNotEquals(turno,retorno);
			}
			catch(SQLException e)
			{
				fail(e.getMessage());
			}
		}
	}

