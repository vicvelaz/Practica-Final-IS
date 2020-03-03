package negocio.negocioFactura;


import java.sql.Date;


public class TFactura {
	private Integer id;
	private double descuento;
	private double precio;
	private Date fecha;
	private Integer idEmpleado;
	private Integer idCliente;


	public TFactura(Integer id, Double descuento, double precio, Date fecha, Integer idEmpleado, Integer idCliente) {

		this.id = id;
		this.descuento = descuento;
		this.precio = precio;
		this.fecha = fecha;
		this.idEmpleado = idEmpleado;
		this.idCliente = idCliente;
	}

	public Integer getId() {
		return id;
	}

	public double getDescuento() {
		return descuento;
	}

	public double getPrecio() {
		return precio;
	}

	public Date getFecha() {
		return fecha;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDescuento(Integer descuento) {
		this.descuento = descuento;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public void setIdCleinte(Integer idCliente) {
		this.idCliente = idCliente;
	}
}