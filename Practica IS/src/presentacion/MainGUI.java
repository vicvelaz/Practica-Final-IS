package presentacion;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import presentacion.controlador.EventosCliente;
import presentacion.controlador.EventosEmpleado;
import presentacion.controlador.EventosFactura;
import presentacion.controlador.EventosMenu;
import presentacion.controlador.EventosProducto;
import presentacion.controlador.EventosTurno;
import presentacion.controlador.SingletonControlador;
import presentacion.factoria.FactoriaPresentacion;

public class MainGUI extends JFrame implements InterfazGUI {

	private static final long serialVersionUID = 1L;

	private String name = "HOME";
	
	private CardLayout cl;
	private JPanel mainPanel, homePanel;
	
	private ClienteGUI clientesPanel;
	private EmpleadoGUI empleadosPanel;
	private TurnoGUI turnosPanel;
	private ProductoGUI productosPanel;
	private FacturaGUI facturasPanel;
	
	private static FactoriaPresentacion presentacion;
	private static SingletonControlador controlador;

	private JPanel pathPanel = presentacion.generarPath();
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {			
					presentacion = FactoriaPresentacion.getInstancia();
					controlador =  SingletonControlador.getInstancia();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainGUI() {
		initialize();
	}

	private void initialize() {
		setBackground(new Color(255, 255, 255));
		setUndecorated(true);
		setBounds(100, 100, 1024, 620);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel topBar = new JPanel();
		topBar.setLayout(new BoxLayout(topBar, BoxLayout.X_AXIS));
		topBar.setBackground(new Color(41, 48, 64));
		topBar.setMaximumSize(new Dimension(1024, 50));
		
		topBar.add(Box.createRigidArea(new Dimension(20,0)));
		
		JLabel groupLogo = new JLabel();
		groupLogo.setIcon(new ImageIcon("resources/icons/home/G202-logo-small.png"));
		topBar.add(groupLogo);
		
		topBar.add(Box.createRigidArea(new Dimension(8,0)));
		
		JLabel appName = new JLabel("APPLICATION");
		appName.setFont(new Font("Arial", Font.BOLD, 14));
		appName.setForeground(new Color(215,215,215));
		topBar.add(appName);
		
		topBar.add(Box.createRigidArea(new Dimension(820,0)));
		
		JButton exitBtn = new JButton("X");
		exitBtn.setFocusPainted(false);
		exitBtn.setBorder(null);
		exitBtn.setContentAreaFilled(false);
		exitBtn.setBorderPainted(false);
		exitBtn.setPreferredSize(new Dimension(30, 30));
		exitBtn.setFont(new Font("Corbel", Font.BOLD, 20));
		exitBtn.setForeground(new Color(110,120,140));
		exitBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea cerrar el programa?", "Salir", JOptionPane.YES_NO_OPTION);
				
				if(confirmacion == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		topBar.add(exitBtn);
		

		pathPanel.add(Box.createRigidArea(new Dimension(35,0)));		
		JButton homePathBtn = createPathButton(name);
		homePathBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controlador.accion(EventosMenu.MOSTRAR_HOME_GUI, null);
				//mostrar();
			}
		});
		pathPanel.add(homePathBtn);

		cl = new CardLayout();
		mainPanel = new JPanel(cl);
		mainPanel.setBackground(new Color(235, 237, 241));
		mainPanel.setMaximumSize(new Dimension(1024, 460));
		
		clientesPanel = new ClienteGUI();
		empleadosPanel = new EmpleadoGUI();
		turnosPanel = new TurnoGUI();
		productosPanel = new ProductoGUI();
		facturasPanel = new FacturaGUI();
		
		homePanel = new JPanel(new GridBagLayout());
		homePanel.setBackground(new Color(235, 237, 241));
		homePanel.setMaximumSize(new Dimension(1024, 460));
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10,10,10,10);
		JButton clientesBtn = createMenuButton("resources/icons/home/clientes.png", new Color(20,200,250));
		clientesBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controlador.accion(EventosMenu.MOSTRAR_CLIENTE_GUI, null);
			}
		});
		homePanel.add(clientesBtn, c);
		
		c.gridx++;
		JButton empleadosBtn = createMenuButton("resources/icons/home/empleados.png", new Color(71, 178, 37));
		empleadosBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controlador.accion(EventosMenu.MOSTRAR_EMPLEADO_GUI, null);
			}
		});
		homePanel.add(empleadosBtn, c);
		
		c.gridx++;
		JButton turnosBtn = createMenuButton("resources/icons/home/turnos.png", new Color(108, 84, 217));
		turnosBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controlador.accion(EventosMenu.MOSTRAR_TURNO_GUI, null);
			}
		});
		homePanel.add(turnosBtn, c);
		
		c.gridx = 0;
		c.gridy++;
		JButton productosBtn = createMenuButton("resources/icons/home/productos.png", new Color(232, 57, 54));
		productosBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controlador.accion(EventosMenu.MOSTRAR_PRODUCTO_GUI, null);
			}
		});
		homePanel.add(productosBtn, c);
		
		c.gridx++;
		JButton facturasBtn = createMenuButton("resources/icons/home/facturas.png", new Color(248, 155, 20));
		facturasBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controlador.accion(EventosMenu.MOSTRAR_FACTURA_GUI, null);
			}
		});
		homePanel.add(facturasBtn, c);
		
		mainPanel.add(homePanel, name);
		mainPanel.add(clientesPanel, clientesPanel.getName());
		mainPanel.add(empleadosPanel, empleadosPanel.getName());
		mainPanel.add(turnosPanel, turnosPanel.getName());
		mainPanel.add(productosPanel, productosPanel.getName());
		mainPanel.add(facturasPanel, facturasPanel.getName());
		cl.show(mainPanel, name);
		
		add(topBar);
		add(pathPanel);
		add(mainPanel);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private JButton createPathButton(String name) {		
		JButton pathBtn = new JButton(name);
		pathBtn.setFocusPainted(false);
		pathBtn.setBorderPainted(false);
		pathBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		pathBtn.setBackground(new Color(230,230,230));
		pathBtn.setMaximumSize(new Dimension(170, 50));
		return pathBtn;
	}
	
	private void addPathSeparator() {
		JLabel pathSeparator = new JLabel(">", SwingConstants.CENTER);
		pathSeparator.setOpaque(false);
		pathSeparator.setFont(new Font("Arial", Font.BOLD, 22));
		pathSeparator.setMaximumSize(new Dimension(50, 50));
		pathPanel.add(pathSeparator);
	}
	
	private void actualizarPath() {
		pathPanel.removeAll();
		
		pathPanel.add(Box.createRigidArea(new Dimension(35,0)));
		
		JButton homePathBtn = createPathButton(name);
		homePathBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mostrar();
			}
		});
		pathPanel.add(homePathBtn);
		
		pathPanel.revalidate();
		pathPanel.repaint();
	}
	
	private JButton createMenuButton(String iconPath, Color color) {
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(200,125));
		button.setBackground(color);
		button.setBorder(null);
		button.setFocusPainted(false);
		button.setIcon(new ImageIcon(iconPath));
		
		return button;
	}
	
	public void mostrar() {
		actualizarPath();
		cl.show(mainPanel, name);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento){
		
			//--- ACTUALIZAR MENU ---//
		
			case EventosMenu.MOSTRAR_HOME_GUI:
				mostrar();
				System.out.println("Retornando a MainGUI");
				break;
			case EventosMenu.MOSTRAR_TURNO_GUI:
				addPathSeparator();
				
				JButton turnosPathBtn = createPathButton(turnosPanel.getName());
				turnosPathBtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						turnosPanel.mostrar();
					}
				});
				pathPanel.add(turnosPathBtn);
				
				turnosPanel.mostrar();
				cl.show(mainPanel, turnosPanel.getName());
				System.out.println("TurnoGUI");
				break;
			case EventosMenu.MOSTRAR_CLIENTE_GUI:
				addPathSeparator();
				
				JButton clientesPathBtn = createPathButton(clientesPanel.getName());
				clientesPathBtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						clientesPanel.mostrar();
					}
				});
				pathPanel.add(clientesPathBtn);
				
				clientesPanel.mostrar();
				cl.show(mainPanel, clientesPanel.getName());
				System.out.println("ClienteGUI");
				break;
			case EventosMenu.MOSTRAR_EMPLEADO_GUI:
				addPathSeparator();
				
				JButton empleadosPathBtn = createPathButton(empleadosPanel.getName());
				empleadosPathBtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						empleadosPanel.mostrar();
					}
				});
				pathPanel.add(empleadosPathBtn);
				
				empleadosPanel.mostrar();
				cl.show(mainPanel, empleadosPanel.getName());
				System.out.println("EmpleadoGUI");
				break;
			case EventosMenu.MOSTRAR_PRODUCTO_GUI:
				addPathSeparator();
				
				JButton productosPathBtn = createPathButton(productosPanel.getName());
				productosPathBtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						productosPanel.mostrar();
					}
				});
				pathPanel.add(productosPathBtn);
				
				productosPanel.mostrar();
				cl.show(mainPanel, productosPanel.getName());
				System.out.println("ProductoGUI");
				break;
			case EventosMenu.MOSTRAR_FACTURA_GUI:
				addPathSeparator();
				
				JButton facturasPathBtn = createPathButton(facturasPanel.getName());
				facturasPathBtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						facturasPanel.mostrar();
					}
				});
				pathPanel.add(facturasPathBtn);
				
				facturasPanel.mostrar();
				cl.show(mainPanel, facturasPanel.getName());
				System.out.println("FacturaGUI");
				break;
				
			//--- ACTUALIZAR TURNO ---//
				
			case EventosTurno.ANADIR_TURNO_OK:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turnos");
				break;
			case EventosTurno.ANADIR_TURNO_KO:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turnos");
				break;
			case EventosTurno.ELIMINAR_TURNO_OK:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turnos");
				break;
			case EventosTurno.ELIMINAR_TURNO_KO:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turnos");
				break;
			case EventosTurno.MOSTRAR_TURNO_OK:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turnos");
				break;
			case EventosTurno.MOSTRAR_TURNO_KO:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turnos");
				break;
			case EventosTurno.DEVOLVER_TURNO_OK:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turnos");
				break;
			case EventosTurno.DEVOLVER_TURNO_KO:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turnos");
				break;
			case EventosTurno.LISTAR_TURNOS_OK:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turnos");
				break;
			case EventosTurno.LISTAR_TURNOS_KO:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turnos");
				break;
			case EventosTurno.LISTAR_EMPLEADOS_OK:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turnos");
				break;
			case EventosTurno.LISTAR_EMPLEADOS_KO:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turnos");
				break;
			case EventosTurno.EDITAR_BUSCAR_TURNO_OK:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turnos");
				break;
			case EventosTurno.EDITAR_BUSCAR_TURNO_KO:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turnos");
				break;
			case EventosTurno.EDITAR_TURNO_OK:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turnos");
				break;
			case EventosTurno.EDITAR_TURNO_KO:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turnos");
				break;
				
			//--- ACTUALIZAR CLIENTE ---//
				
			case EventosCliente.REGISTRAR_CLIENTE_OK:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.REGISTRAR_CLIENTE_KO:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.BUSCAR_CLIENTE_OK:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.BUSCAR_CLIENTE_KO:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.LISTAR_CLIENTES_OK:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.LISTAR_CLIENTES_KO:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.FACTURAS_CLIENTE_OK:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.FACTURAS_CLIENTE_KO:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.MODIFICAR_BUSCAR_CLIENTE_OK:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.MODIFICAR_BUSCAR_CLIENTE_KO:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.MODIFICAR_CLIENTE_OK:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.MODIFICAR_CLIENTE_KO:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
				
			//--- ACTUALIZAR EMPLEADO ---//
				
			case EventosEmpleado.REGISTRAR_EMPLEADO_OK:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleados");
				break;
			case EventosEmpleado.REGISTRAR_EMPLEADO_KO:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleados");
				break;
			case EventosEmpleado.MODIFICAR_BUSCAR_EMPLEADO_OK:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleados");
				break;
			case EventosEmpleado.MODIFICAR_BUSCAR_EMPLEADO_KO:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleados");
				break;
			case EventosEmpleado.MODIFICAR_EMPLEADO_OK:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleados");
				break;
			case EventosEmpleado.MODIFICAR_EMPLEADO_KO:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleados");
				break;
			case EventosEmpleado.ELIMINAR_EMPLEADO_OK:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleados");
				break;
			case EventosEmpleado.ELIMINAR_EMPLEADO_KO:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleados");
				break;
			case EventosEmpleado.MOSTRAR_EMPLEADO_OK:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleados");
				break;
			case EventosEmpleado.MOSTRAR_EMPLEADO_KO:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleados");
				break;
			case EventosEmpleado.LISTAR_EMPLEADOS_OK:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleados");
				break;
			case EventosEmpleado.LISTAR_EMPLEADOS_KO:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleados");
				break;
				
			//--- ACTUALIZAR PRODUCTO ---//
			
			case EventosProducto.ANADIR_PRODUCTO_OK:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.ANADIR_PRODUCTO_KO:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;	
			case EventosProducto.EDITAR_BUSCAR_PRODUCTO_OK:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.EDITAR_BUSCAR_PRODUCTO_KO:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.EDITAR_PRODUCTO_OK:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.EDITAR_PRODUCTO_KO:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.BORRAR_PRODUCTO_OK:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.BORRAR_PRODUCTO_KO:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.MOSTRAR_PRODUCTO_OK:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.MOSTRAR_PRODUCTO_KO:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.LISTAR_PRODUCTOS_OK:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.LISTAR_PRODUCTOS_KO:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
				
			//--- ACTUALIZAR FACTURA ---//
				
			case EventosFactura.MOSTRAR_FACTURA_OK:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.MOSTRAR_FACTURA_KO:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.ABRIR_FACTURA_OK:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.ABRIR_FACTURA_KO:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.CERRAR_FACTURA_OK:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.CERRAR_FACTURA_KO:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.ANADIR_PRODUCTO_A_F_OK:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.ANADIR_PRODUCTO_A_F_KO:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.BORRAR_PRODUCTO_OK:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.BORRAR_PRODUCTO_KO:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.APLICAR_DESCUENTO_OK:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.APLICAR_DESCUENTO_KO:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
		}
	}
}
