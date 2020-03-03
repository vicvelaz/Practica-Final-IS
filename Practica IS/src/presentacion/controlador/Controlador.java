package presentacion.controlador;

import java.sql.SQLException;
import java.util.LinkedList;

import negocio.negocioEmpleado.SAEmpleado;
import negocio.negocioEmpleado.TEmpleado;
import negocio.factoriaSA.FactoriaSA;
import negocio.negocioCliente.SACliente;
import negocio.negocioCliente.TCliente;
import negocio.negocioFactura.SAFactura;
import negocio.negocioFactura.TFactura;
import negocio.negocioProducto.SAProducto;
import negocio.negocioProducto.TProducto;
import negocio.negocioTurno.SATurno;
import negocio.negocioTurno.TTurno;
import presentacion.MainGUI;
import presentacion.factoria.FactoriaPresentacion;

public class Controlador extends SingletonControlador{
	
	private FactoriaSA sa = FactoriaSA.getInstancia();
	private FactoriaPresentacion presentacion = FactoriaPresentacion.getInstancia();
	private MainGUI gui = presentacion.generarMainGUI();
	
	public void accion(int evento, Object objeto) {
		String mensaje = new String();
		switch(evento){
			case EventosTurno.ANADIR_TURNO:{ // Comienzo de las acciones que realiza el módulo TURNO
				TTurno turno = (TTurno) objeto;
				SATurno saTurno = sa.generaSATurno();
				try{
					Integer resultado = saTurno.anadirTurno(turno);
					if(resultado != -1){
						mensaje = "El turno se ha registrado con exito. Su id es: " + resultado.toString();
						gui.actualizar(EventosTurno.ANADIR_TURNO_OK, mensaje);
					}
					else{
						mensaje = "No pueden existir dos turnos con el mismo ID.";
						gui.actualizar(EventosTurno.ANADIR_TURNO_KO, mensaje);
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosTurno.ANADIR_TURNO_KO, mensaje);
				}
			}; break;
			case EventosTurno.EDITAR_TURNO:{
				TTurno turno = (TTurno) objeto;
				SATurno saTurno = sa.generaSATurno();
				try{
					Integer resultado = saTurno.editarTurno(turno);
					if(resultado == -1){
						mensaje = "No se ha encontrado ningún turno con el ID proporcionado.";
						gui.actualizar(EventosTurno.EDITAR_TURNO_KO, mensaje);
					}
					else if(resultado == -2){
						mensaje = "Los valores asignados al horario no son válidos.";
						gui.actualizar(EventosTurno.EDITAR_TURNO_KO, mensaje);
					}
					else{
						mensaje = "El turno seleccionado ha sido editado correctamente.";
						gui.actualizar(EventosTurno.EDITAR_TURNO_OK, mensaje);
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosTurno.EDITAR_TURNO_KO, mensaje);
				}
			}; break;
			case EventosTurno.ELIMINAR_TURNO:{
				Integer id = (Integer) objeto;
				SATurno saTurno = sa.generaSATurno();
				try{
					Integer resultado = saTurno.eliminarTurno(id);
					if(resultado == -1) {
						mensaje = "No se ha encontrado ningún turno con el ID proporcionado.";
						gui.actualizar(EventosTurno.ELIMINAR_TURNO_KO, mensaje);
					}
					else{
						mensaje = "El turno se ha eliminado con exito.";
						gui.actualizar(EventosTurno.ELIMINAR_TURNO_OK, mensaje);
					}
				}catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosTurno.ELIMINAR_TURNO_KO, mensaje);
				}
			}; break;
			case EventosTurno.MOSTRAR_TURNO:{
				Integer id = (Integer) objeto;
				SATurno saTurno = sa.generaSATurno();
				try{
					TTurno turno = saTurno.mostrarTurno(id);
					if(turno != null) {
						gui.actualizar(EventosTurno.MOSTRAR_TURNO_OK, turno);
					}
					else{
						mensaje = "No se ha encontrado ningún turno con el ID introducido.";
						gui.actualizar(EventosTurno.MOSTRAR_TURNO_KO, mensaje);
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosTurno.MOSTRAR_TURNO_KO, mensaje);
				}
			}; break;
			case EventosTurno.DEVOLVER_TURNO:{	//
				Integer idEmpleado = (Integer) objeto;
				SATurno saTurno = sa.generaSATurno();
				try{
					TTurno turno = saTurno.devolverTurno(idEmpleado);
					if(turno != null){
						gui.actualizar(EventosTurno.DEVOLVER_TURNO_OK, turno);
					}
					else{
						mensaje = "No se ha encontrado ningún turno asociado al empleado propuesto.";
						gui.actualizar(EventosTurno.DEVOLVER_TURNO_KO, mensaje);
					}
				}catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosTurno.DEVOLVER_TURNO_KO, mensaje);
				}
			}; break;
			case EventosTurno.LISTAR_TURNOS:{
				SATurno saTurno = sa.generaSATurno();
				try{
					LinkedList<TTurno> listaTurnos = saTurno.listarTurnos();
					if(listaTurnos != null){
						gui.actualizar(EventosTurno.LISTAR_TURNOS_OK, listaTurnos);
					}
					else{
						mensaje = "No hay turnos disponibles.";
						gui.actualizar(EventosTurno.LISTAR_TURNOS_KO, mensaje);
					}
				}catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosTurno.LISTAR_TURNOS_KO, mensaje);
				}
			}; break;
			case EventosTurno.LISTAR_EMPLEADOS:{
				Integer idTurno = (Integer) objeto;
				SATurno saTurno = sa.generaSATurno();
				try{
					LinkedList<TEmpleado> listaEmpleados = saTurno.listarEmpleados(idTurno);
					if(listaEmpleados != null){
						gui.actualizar(EventosTurno.LISTAR_EMPLEADOS_OK, listaEmpleados);
					}
					else{
						mensaje = "No hay empleados registrados en el turno seleccionado.";
						gui.actualizar(EventosTurno.LISTAR_EMPLEADOS_KO, mensaje);
					}
				}catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosTurno.LISTAR_EMPLEADOS_KO, mensaje);
				}
			}; break;
			case EventosCliente.REGISTRAR_CLIENTE: { // Comienzo de las acciones que realiza el módulo CLIENTE		
				TCliente cliente = (TCliente) objeto;
				
				SACliente saCliente = sa.generaSACliente();
				try{
					Integer resultado = saCliente.altaCliente(cliente);
					if(resultado != -1){
						mensaje = "El cliente ha sido añadido correctamente. Su id es: " + resultado.toString();
						gui.actualizar(EventosCliente.REGISTRAR_CLIENTE_OK, mensaje);
					}
					else{
						mensaje = "No pueden existir dos clientes con el mismo ID.";
						gui.actualizar(EventosCliente.REGISTRAR_CLIENTE_KO, mensaje);
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosCliente.REGISTRAR_CLIENTE_KO, mensaje);
				}
			}; break;
			case EventosCliente.MODIFICAR_CLIENTE: {
				TCliente cliente = (TCliente) objeto;
				
				SACliente saCliente = sa.generaSACliente();
				try{
					Integer resultado = saCliente.editarCliente(cliente);
					if(resultado == -1){
						mensaje = "No se ha encontrado ningún cliente con el ID introducido.";
						gui.actualizar(EventosCliente.MODIFICAR_CLIENTE_KO, mensaje);
					}
					else{
						mensaje = "El cliente seleccionado ha sido modificado correctamente.";
						gui.actualizar(EventosCliente.MODIFICAR_CLIENTE_KO, mensaje);
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosCliente.MODIFICAR_CLIENTE_KO, mensaje);
				}
			}; break;
			case EventosCliente.FACTURAS_PAGADAS_POR_CLIENTE: {
				Integer idCliente = (Integer) objeto;
				SACliente saCliente = sa.generaSACliente();
				try{
					LinkedList<TFactura> listaFacturasCliente = saCliente.listarFacturas(idCliente);
					if(listaFacturasCliente != null){
						gui.actualizar(EventosCliente.FACTURAS_CLIENTE_OK, listaFacturasCliente);
					}
					else{
						mensaje = "El cliente seleccionado no ha efectuado el pago de ninguna factura.";
						gui.actualizar(EventosCliente.FACTURAS_CLIENTE_KO, mensaje);
					}
				}catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosCliente.FACTURAS_CLIENTE_KO, mensaje);
				}
			}; break;
			case EventosCliente.LISTAR_CLIENTES: {
				SACliente saCliente = sa.generaSACliente();
				try{
					LinkedList<TCliente> listaClientes = saCliente.listarClientes();
					if(listaClientes != null){
						gui.actualizar(EventosCliente.LISTAR_CLIENTES_OK, listaClientes);
					}
					else{
						mensaje = "No hay clientes disponibles.";
						gui.actualizar(EventosCliente.LISTAR_CLIENTES_KO, mensaje);
					}
				}catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosCliente.LISTAR_CLIENTES_KO, mensaje);
				}
			}; break;
			case EventosCliente.BUSCAR_CLIENTE: {
				Integer id = (Integer) objeto;
				SACliente saCliente = sa.generaSACliente();
				try{
					TCliente cliente = saCliente.devolverCliente(id);
					if(cliente != null) {
						gui.actualizar(EventosCliente.BUSCAR_CLIENTE_OK, cliente);
					}
					else{
						mensaje = "No se ha encontrado ningún empleado con el ID introducido.";
						gui.actualizar(EventosCliente.BUSCAR_CLIENTE_KO, mensaje);;
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosCliente.BUSCAR_CLIENTE_KO, mensaje);
				}
			}; break;
			case EventosProducto.ANADIR_PRODUCTO:{ // Comienzo de las acciones que realiza el módulo PRODUCTO
				TProducto producto = (TProducto) objeto;
				SAProducto saProducto = sa.generaSAProducto();
				try{
					Integer resultado = saProducto.altaProducto(producto);
					if(resultado != -1){
						mensaje = "El producto se ha registrado con exito. Su id es:" + resultado.toString();
						gui.actualizar(EventosProducto.ANADIR_PRODUCTO_OK, mensaje);
					}
					else{
						mensaje = "No pueden existir dos productos con el mismo ID.";
						gui.actualizar(EventosProducto.ANADIR_PRODUCTO_KO, mensaje);
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosProducto.ANADIR_PRODUCTO_KO, mensaje);
				}
			}; break;
			case EventosProducto.EDITAR_PRODUCTO:{
				TProducto producto = (TProducto) objeto;
				SAProducto saProducto = sa.generaSAProducto();
				try{
					Integer resultado = saProducto.editarProducto(producto);
					if(resultado == -1){
						mensaje = "No se ha encontrado ningún producto con el ID proporcionado.";
						gui.actualizar(EventosProducto.EDITAR_PRODUCTO_KO, mensaje);
					}
					else if(resultado == -2){
						mensaje = "Los valores asignados a los campos no son validos.";
						gui.actualizar(EventosProducto.EDITAR_PRODUCTO_KO, mensaje);
					}
					else{
						mensaje = "El producto seleccionado ha sido editado correctamente.";
						gui.actualizar(EventosProducto.EDITAR_PRODUCTO_OK, mensaje);
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosProducto.EDITAR_PRODUCTO_KO, mensaje);
				}
			}; break;
			case EventosProducto.BORRAR_PRODUCTO:{
				Integer id = (Integer) objeto;
				SAProducto saProducto = sa.generaSAProducto();
				try{
					Integer resultado = saProducto.bajaProducto(id);
					if(resultado == -1) {
						mensaje = "No se ha encontrado ningún producto con el ID proporcionado.";
						gui.actualizar(EventosProducto.BORRAR_PRODUCTO_KO, mensaje);
					}
					else{
						mensaje = "El producto se ha eliminado con exito.";
						gui.actualizar(EventosProducto.BORRAR_PRODUCTO_OK, mensaje);
					}
				}catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosProducto.BORRAR_PRODUCTO_KO, mensaje);
				}
			}; break;
			case EventosProducto.LISTAR_PRODUCTOS:{
				SAProducto saProducto = sa.generaSAProducto();
				try{
					LinkedList<TProducto> listaProductos = saProducto.listarProductos();
					if(listaProductos != null){
						gui.actualizar(EventosProducto.LISTAR_PRODUCTOS_OK, listaProductos);
					}
					else{
						mensaje = "No hay productos disponibles.";
						gui.actualizar(EventosProducto.LISTAR_PRODUCTOS_KO, mensaje);
					}
				}catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosProducto.LISTAR_PRODUCTOS_KO, mensaje);
				}
			}; break;
			case EventosProducto.MOSTRAR_PRODUCTO:{
				Integer id = (Integer) objeto;
				SAProducto saProducto = sa.generaSAProducto();
				try{
					TProducto producto = saProducto.devolverProducto(id);
					if(producto != null) {
						gui.actualizar(EventosProducto.MOSTRAR_PRODUCTO_OK, producto);
					}
					else{
						mensaje = "No se ha encontrado ningún producto con el ID introducido.";
						gui.actualizar(EventosProducto.MOSTRAR_PRODUCTO_KO, mensaje);
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosProducto.MOSTRAR_PRODUCTO_KO, mensaje);
				}
			}; break;
			case EventosFactura.ANADIR_PRODUCTO_A_F:{ //Comienzo de las acciones que realiza el módulo FACTURA
				TProducto producto = (TProducto) objeto;
				SAFactura saFactura = sa.generaSAFactura();
				try{
					Integer resultado = saFactura.anadirProducto(producto);
					if(resultado != -1 && resultado != -2){
						mensaje = "El producto ha sido añadido correctamente a la factura.";
						gui.actualizar(EventosFactura.ANADIR_PRODUCTO_A_F_OK, mensaje);
					}
					else if(resultado == -1){
						mensaje = "El ID introducido no pertenece a ningun producto.";
						gui.actualizar(EventosFactura.ANADIR_PRODUCTO_A_F_KO, mensaje);
					}
					else if(resultado == -2){
						mensaje = "No hay suficiente stock que cubra la cantidad solicitada.";
						gui.actualizar(EventosFactura.ANADIR_PRODUCTO_A_F_KO, mensaje);
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosFactura.ANADIR_PRODUCTO_A_F_KO, mensaje);
				}
			}; break;
			case EventosFactura.ABRIR_FACTURA:{
				TFactura factuta = (TFactura) objeto;
				SAFactura saFactura = sa.generaSAFactura();
				try{
					Integer resultado = saFactura.abrirFactura(factuta);
					if(resultado != -1){
						mensaje = "La factura se ha abierto correctamente. Su id es: " + resultado.toString();
						gui.actualizar(EventosFactura.ABRIR_FACTURA_OK, resultado);
					}
					else{
						mensaje = "No pueden existir dos facturas con el mismo ID.";
						gui.actualizar(EventosFactura.ABRIR_FACTURA_KO, mensaje);
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosFactura.ABRIR_FACTURA_KO, mensaje);
				}
			}; break;
			case EventosFactura.CERRAR_FACTURA:{
				TFactura factuta = (TFactura) objeto;
				SAFactura saFactura = sa.generaSAFactura();
				try{
					Integer resultado = saFactura.abrirFactura(factuta);
					if(resultado != -1){
						mensaje = "La factura se ha cerrado correctamente.";
						gui.actualizar(EventosFactura.CERRAR_FACTURA_OK, mensaje);
					}
					else{
						mensaje = "No existe ninguna factura con el ID introducido.";
						gui.actualizar(EventosFactura.CERRAR_FACTURA_KO, mensaje);
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosFactura.CERRAR_FACTURA_KO, mensaje);
				}
			}; break;
			case EventosFactura.MOSTRAR_FACTURA:{
				Integer id = (Integer) objeto;
				SAFactura saFactura = sa.generaSAFactura();
				try{
					TFactura factura = saFactura.mostrarFactura(id);
					if(factura != null) {
						gui.actualizar(EventosFactura.MOSTRAR_FACTURA_KO, factura);
					}
					else{
						mensaje = "No se ha encontrado ninguna factura con el ID introducido.";
						gui.actualizar(EventosFactura.MOSTRAR_FACTURA_KO, mensaje);
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosFactura.MOSTRAR_FACTURA_KO, mensaje);
				}
			}; break;
			case EventosFactura.APLICAR_DESCUENTO:{
				TProducto producto = (TProducto) objeto;
				SAFactura saFactura = sa.generaSAFactura();
				try{
					Integer resultado = saFactura.devolucionProducto(producto);
					if(resultado != -1) {
						mensaje = "Descuento aplicado correctamente.";
						gui.actualizar(EventosFactura.APLICAR_DESCUENTO_OK, mensaje);
					}
					else{
						mensaje = "No se ha encontrado ninguna factura con el ID introducido.";
						gui.actualizar(EventosFactura.APLICAR_DESCUENTO_KO, mensaje);
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosFactura.APLICAR_DESCUENTO_KO, mensaje);
				}	
			}; break;
			case EventosFactura.DEVOLUCION_PRODUCTO:{
				TProducto p = (TProducto) objeto;
				SAFactura saFactura = sa.generaSAFactura();
				try{
					Integer resultado = saFactura.devolucionProducto(p);
					if(resultado != -1) {
						mensaje = "EL producto seleccionado ha sido eliminado de la factura";
						gui.actualizar(EventosFactura.BORRAR_PRODUCTO_OK, mensaje);
					}
					else{
						mensaje = "No se ha encontrado ningun producto con el ID introducido.";
						gui.actualizar(EventosFactura.BORRAR_PRODUCTO_KO, mensaje);
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosFactura.BORRAR_PRODUCTO_KO, mensaje);
				}				
			}; break;
			case EventosEmpleado.REGISTRAR_EMPLEADO:{ //Comienzo de las acciones que realiza el módulo EMPLEADO
				TEmpleado empleado = (TEmpleado) objeto;
				SAEmpleado saEmpleado = sa.generaSAEmpleado();
				try{
					Integer resultado = saEmpleado.altaEmpleado(empleado);
					if(resultado != -1){
						mensaje = "El empleado ha sido añadido correctamente. Su id es: " + resultado.toString();
						gui.actualizar(EventosEmpleado.REGISTRAR_EMPLEADO_OK, mensaje);
					}
					else{
						mensaje = "No pueden existir dos empleados con el mismo ID.";
						gui.actualizar(EventosEmpleado.REGISTRAR_EMPLEADO_KO, mensaje);
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosEmpleado.REGISTRAR_EMPLEADO_KO, mensaje);
				}
			}; break;
			case EventosEmpleado.MODIFICAR_EMPLEADO:{
				TEmpleado empleado = (TEmpleado) objeto;
				SAEmpleado saEmpleado = sa.generaSAEmpleado();
				try{
					Integer resultado = saEmpleado.editarEmpleado(empleado);
					if(resultado == -1){
						mensaje = "No se ha encontrado ningún empleado con el ID introducido.";
						gui.actualizar(EventosEmpleado.MODIFICAR_EMPLEADO_KO, mensaje);
					}
					else{
						mensaje = "El empleado seleccionado ha sido modificado correctamente.";
						gui.actualizar(EventosEmpleado.MODIFICAR_EMPLEADO_OK, mensaje);
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosEmpleado.MODIFICAR_EMPLEADO_KO, mensaje);
				}
			}; break;
			case EventosEmpleado.ELIMINAR_EMPLEADO:{
				Integer id = (Integer) objeto;
				SAEmpleado saEmpleado = sa.generaSAEmpleado();
				try{
					Integer resultado = saEmpleado.bajaEmpleado(id);
					if(resultado == -1) {
						mensaje = "No se ha encontrado ningún empleado con el ID introducido.";
						gui.actualizar(EventosEmpleado.ELIMINAR_EMPLEADO_KO, mensaje);
					}
					else{
						mensaje = "El empleado seleccionado ha sido eliminado correctamente.";
						gui.actualizar(EventosEmpleado.ELIMINAR_EMPLEADO_OK, mensaje);
					}
				}catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosEmpleado.ELIMINAR_EMPLEADO_KO, mensaje);
				}
			}; break;
			case EventosEmpleado.LISTAR_EMPLEADOS:{
				SAEmpleado saEmpleado = sa.generaSAEmpleado();
				try{
					LinkedList<TEmpleado> listaEmpleados = saEmpleado.listarEmpleados();
					if(listaEmpleados != null){
						gui.actualizar(EventosEmpleado.LISTAR_EMPLEADOS_OK, listaEmpleados);
					}
					else{
						mensaje = "No hay empleados disponibles.";
						gui.actualizar(EventosEmpleado.LISTAR_EMPLEADOS_KO, mensaje);
					}
				}catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosEmpleado.LISTAR_EMPLEADOS_KO, mensaje);
				}
			}; break;
			case EventosEmpleado.MOSTRAR_EMPLEADO:{
				Integer id = (Integer) objeto;
				SAEmpleado saEmpleado = sa.generaSAEmpleado();
				try{
					TEmpleado empleado = saEmpleado.devolverEmpleado(id);
					if(empleado != null) {
						gui.actualizar(EventosEmpleado.MOSTRAR_EMPLEADO_OK, empleado);
					}
					else{
						mensaje = "No se ha encontrado ningún empleado con el ID introducido.";
						gui.actualizar(EventosEmpleado.MOSTRAR_EMPLEADO_KO, mensaje);;
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosEmpleado.MOSTRAR_EMPLEADO_KO, mensaje);
				}
			}; break;
			case EventosTurno.EDITAR_BUSCAR_TURNO:{
				Integer id = (Integer) objeto;
				SATurno saTurno = sa.generaSATurno();
				try{
					TTurno turno = saTurno.mostrarTurno(id);
					if(turno != null) {
						gui.actualizar(EventosTurno.EDITAR_BUSCAR_TURNO_OK, turno);
					}
					else{
						mensaje = "No se ha encontrado ningún turno con el ID introducido.";
						gui.actualizar(EventosTurno.EDITAR_BUSCAR_TURNO_KO, mensaje);
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosTurno.EDITAR_BUSCAR_TURNO_KO, mensaje);
				}
			}; break;
			case EventosEmpleado.MODIFICAR_BUSCAR_EMPLEADO:{
				Integer id = (Integer) objeto;
				SAEmpleado saEmpleado = sa.generaSAEmpleado();
				try{
					TEmpleado empleado = saEmpleado.devolverEmpleado(id);
					if(empleado != null) {
						gui.actualizar(EventosEmpleado.MODIFICAR_BUSCAR_EMPLEADO_OK, empleado);
					}
					else{
						mensaje = "No se ha encontrado ningún empleado con el ID introducido.";
						gui.actualizar(EventosEmpleado.MODIFICAR_BUSCAR_EMPLEADO_KO, mensaje);;
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosEmpleado.MODIFICAR_BUSCAR_EMPLEADO_KO, mensaje);
				}
			}; break;
			case EventosCliente.MODIFICAR_BUSCAR_CLIENTE:{
				Integer id = (Integer) objeto;
				SACliente saCliente = sa.generaSACliente();
				try{
					TCliente cliente = saCliente.devolverCliente(id);
					if(cliente != null) {
						gui.actualizar(EventosCliente.MODIFICAR_BUSCAR_CLIENTE_OK, cliente);
					}
					else{
						mensaje = "No se ha encontrado ningún empleado con el ID introducido.";
						gui.actualizar(EventosCliente.MODIFICAR_BUSCAR_CLIENTE_KO, mensaje);;
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosCliente.MODIFICAR_BUSCAR_CLIENTE_KO, mensaje);
				}
			}; break;
			case EventosProducto.EDITAR_BUSCAR_PRODUCTO:{
				Integer id = (Integer) objeto;
				SAProducto saProducto = sa.generaSAProducto();
				try{
					TProducto producto = saProducto.devolverProducto(id);
					if(producto != null) {
						gui.actualizar(EventosProducto.EDITAR_BUSCAR_PRODUCTO_OK, producto);
					}
					else{
						mensaje = "No se ha encontrado ningún producto con el ID introducido.";
						gui.actualizar(EventosProducto.EDITAR_BUSCAR_PRODUCTO_KO, mensaje);
					}
				} catch(SQLException e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosProducto.EDITAR_BUSCAR_PRODUCTO_KO, mensaje);
				}
			}; break;
			case EventosMenu.MOSTRAR_TURNO_GUI:{
				gui.actualizar(EventosMenu.MOSTRAR_TURNO_GUI, null);
			}; break;
			case EventosMenu.MOSTRAR_EMPLEADO_GUI:{
				gui.actualizar(EventosMenu.MOSTRAR_EMPLEADO_GUI, null);
			}; break;
			case EventosMenu.MOSTRAR_FACTURA_GUI:{
				gui.actualizar(EventosMenu.MOSTRAR_FACTURA_GUI, null);
			}; break;
			case EventosMenu.MOSTRAR_CLIENTE_GUI:{
				gui.actualizar(EventosMenu.MOSTRAR_CLIENTE_GUI, null);
			}; break;
			case EventosMenu.MOSTRAR_PRODUCTO_GUI:{
				gui.actualizar(EventosMenu.MOSTRAR_PRODUCTO_GUI, null);
			}; break;
			case EventosMenu.MOSTRAR_HOME_GUI:{
				gui.actualizar(EventosMenu.MOSTRAR_HOME_GUI, null);
			}; break;
		}
	}
}