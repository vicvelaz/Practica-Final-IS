package negocio.negocioProducto;

public class TProducto {
	private Integer id;
	private String nombre;
	private double precio;
	private Integer calorias;
	private Integer cantidad;
	private boolean activo;
	private double CantidadComida;
	private double volumen;
	
	public TProducto(Integer id, String nombre, double precio, Integer calorias, Integer cantidad, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.calorias = calorias;
		this.cantidad = cantidad;
		this.activo = activo;
	}
	
	public Integer getId(){
		return id;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public double getPrecio(){
		return precio;
	}
	
	public Integer getCalorias(){
		return calorias;
	}
	
	public Integer getCantidad(){
		return cantidad;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public void setHoraInicio(double precio){
		this.precio = precio;
	}
	
	public void setCalorias(Integer calorias){
		this.calorias = calorias;
	}
	
	public void setCantidad(Integer cantidad){
		this.cantidad = cantidad;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;

	}

	public double getCantidadComida() {
		return CantidadComida;
	}

	public double getVolumen() {
		return volumen;
	}
}