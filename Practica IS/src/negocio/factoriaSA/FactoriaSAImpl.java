package negocio.factoriaSA;

import negocio.negocioCliente.SACliente;
import negocio.negocioCliente.SAClienteImpl;
import negocio.negocioEmpleado.SAEmpleado;
import negocio.negocioEmpleado.SAEmpleadoImpl;
import negocio.negocioFactura.SAFactura;
import negocio.negocioFactura.SAFacturaImpl;
import negocio.negocioProducto.SAProducto;
import negocio.negocioProducto.SAProductoImpl;
import negocio.negocioTurno.SATurno;
import negocio.negocioTurno.SATurnoImpl;

public class FactoriaSAImpl extends FactoriaSA{
	
	public SATurno generaSATurno(){
	 	return new SATurnoImpl();
	}
	
	public SACliente generaSACliente(){
		return new SAClienteImpl();
	}
	
	public SAEmpleado generaSAEmpleado(){
		return new SAEmpleadoImpl();
	}
	
	public SAFactura generaSAFactura(){
		return new SAFacturaImpl();
	}
	
	public SAProducto generaSAProducto(){
		return new SAProductoImpl();
	}
}