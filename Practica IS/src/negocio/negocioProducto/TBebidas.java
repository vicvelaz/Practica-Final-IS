package negocio.negocioProducto;

public class TBebidas extends TProducto {
	private double volumen;

	public TBebidas(Integer id, String nombre, double precio, Integer calorias, Integer cantidad, boolean activo, double volumen) {
		super(id, nombre, precio, calorias, cantidad, activo);
		this.volumen = volumen;
	}

	public double getVolumen() {
		return volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}
}