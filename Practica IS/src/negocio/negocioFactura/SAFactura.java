package negocio.negocioFactura;

import java.sql.SQLException;

import negocio.negocioProducto.TProducto;

public interface SAFactura {
	public abstract Integer anadirProducto(TProducto p) throws SQLException;
	public abstract Integer aplicarDescuento(TFactura factura) throws SQLException;
	public abstract Integer devolucionProducto(TProducto p) throws SQLException;
	public abstract TFactura mostrarFactura(Integer id) throws SQLException;
	public abstract Integer abrirFactura(TFactura factura) throws SQLException;
	public abstract Integer cerrarFactura(TFactura factura) throws SQLException;
}