package negocio.factoriaSA;

import negocio.negocioCliente.SACliente;
import negocio.negocioEmpleado.SAEmpleado;
import negocio.negocioFactura.SAFactura;
import negocio.negocioProducto.SAProducto;
import negocio.negocioTurno.SATurno;

public abstract class FactoriaSA {
	private static FactoriaSA sa;
	
	public static FactoriaSA getInstancia() {
		if(sa == null) {
			sa = new FactoriaSAImpl();
		}
		return sa;
	}
	
	public abstract SATurno generaSATurno();
	public abstract SACliente generaSACliente();
	public abstract SAEmpleado generaSAEmpleado();
	public abstract SAFactura generaSAFactura();
	public abstract SAProducto generaSAProducto();
}