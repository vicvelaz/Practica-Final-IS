package negocio.negocioEmpleado;


public class TEmpleado {
	private Integer id;
	private String nombre;
	private String dni;
	private boolean activo;
	private Integer idTurno;
	
	public TEmpleado(Integer id, String nombre, String dni, boolean activo, Integer idTurno){
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
		this.activo = activo;
		this.idTurno = idTurno;
	}
	
	public Integer getId(){
		return id;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public String getDni(){
		return dni;
	}
	
	public boolean getActivo(){
		return activo;
	}
	
	public Integer getIdTurno(){
		return idTurno;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public void setHoraInicio(String dni){
		this.dni = dni;
	}
	
	public void setHoraFin(boolean activo){
		this.activo = activo;
	}
	
	public void setIdTurno(Integer idTurno){
		this.idTurno = idTurno;
	}
	
	public void setActivo(boolean activo){
		this.activo = activo;
	}
}