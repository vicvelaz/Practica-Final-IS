package negocio.negocioTurno;


public class TTurno {
	private Integer id;
	private String nombre;
	private Integer horaInicio;
	private Integer horaFin;
	private boolean activo;
	
	public TTurno(String nombre, Integer horaInicio, Integer horaFin) {
		this.nombre = nombre;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
	
	public TTurno(Integer id, String nombre, Integer horaInicio, Integer horaFin, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.activo = activo;
	}
	
	public TTurno(String nombre, Integer horaInicio, Integer horaFin, boolean activo) {
		this.nombre = nombre;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.activo = activo;
	}
	
	public Integer getId(){
		return id;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public Integer getHoraInicio(){
		return horaInicio;
	}
	
	public Integer getHoraFin(){
		return horaFin;
	}
	
	public boolean getActivo(){
		return activo;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public void setHoraInicio(Integer horaInicio){
		this.horaInicio = horaInicio;
	}
	
	public void setHoraFin(Integer horaFin){
		this.horaFin = horaFin;
	}
	
	public void setActivo(boolean activo){
		this.activo = activo;
	}
}