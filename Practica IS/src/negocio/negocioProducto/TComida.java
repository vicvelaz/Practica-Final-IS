package negocio.negocioProducto;

public class TComida extends TProducto {
	private double gramos;

	public TComida(Integer id, String nombre, double precio, Integer calorias, Integer cantidad, boolean activo, double gramos) {
		super(id, nombre, precio, calorias, cantidad, activo);
		this.gramos = gramos;
	}

	public double getGramos() {
		return gramos;
	}

	public void setGramos(double gramos) {
		this.gramos = gramos;
	}
}