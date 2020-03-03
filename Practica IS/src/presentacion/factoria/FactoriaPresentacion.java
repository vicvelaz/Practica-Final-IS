package presentacion.factoria;

import javax.swing.JPanel;

import presentacion.*;

public abstract class FactoriaPresentacion {
	
	private static FactoriaPresentacion presentacion;
	
	public static synchronized  FactoriaPresentacion getInstancia() {
		if(presentacion == null) {
			presentacion = new FactoriaPresentacionImpl();
		}
		return presentacion;
	}
	
	public abstract MainGUI generarMainGUI();
	public abstract JPanel generarPath();
}