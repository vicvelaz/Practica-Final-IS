package presentacion;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import negocio.negocioEmpleado.TEmpleado;
import presentacion.controlador.EventosEmpleado;
import presentacion.controlador.SingletonControlador;
import presentacion.factoria.FactoriaPresentacion;

public class EmpleadoGUI extends JPanel implements InterfazGUI {
	private static final long serialVersionUID = 1L;

	private String name = "EMPLEADOS";
	
	private CardLayout _localCL;
	private JPanel _homePanel;
	private JPanel _currentPanel = _homePanel;
	
	private static FactoriaPresentacion presentacion = FactoriaPresentacion.getInstancia();
	//private static SingletonControlador controlador = SingletonControlador.getInstancia();

	private JPanel pathPanel = presentacion.generarPath();
	List<Component> _lastPathComponents = new LinkedList<Component>();
	
	//--- COMPONENTES ---//
	
	private JPanel anadirOutputArea;
	private JLabel anadirOutputLabel;
	
	private JPanel borrarOutputArea;
	private JLabel borrarOutputLabel;
	
	private JPanel mostrarEmpleadoPanel;
	private CardLayout mostrarEmpleadoPanelCL;
	private JPanel mostrarErrorArea;
	private JLabel mostrarErrorLabel;
	private JLabel mostrarIDText;
	private JLabel mostrarNombreText;
	private JLabel mostrarDNIText;
	private JLabel mostrarIDTurnoText;
	
	private JPanel editarEmpleadoPanel;
	private CardLayout editarEmpleadoPanelCL;
	private JPanel editarBuscarErrorArea;
	private JLabel editarBuscarErrorLabel;
	private JPanel editarOutputArea;
	private JLabel editarOutputLabel;
	private JTextField editarNombreField;
	private JTextField editarDNIField;
	private JTextField editarIDTurnoField;
	
	private DefaultTableModel mostrarModel;
	
	public EmpleadoGUI() {
		initialize();
	}
	
	public void initialize() {
		_localCL = new CardLayout();
		setLayout(_localCL);
		setBackground(new Color(235, 237, 241));
		setMaximumSize(new Dimension(1024, 460));
		
		_homePanel = new JPanel(new GridBagLayout());
		_homePanel.setBackground(new Color(235, 237, 241));
		_homePanel.setMaximumSize(new Dimension(1024, 460));
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10,10,10,10);
		
		JButton anadirBtn = createMenuButton("resources/icons/empleados/anadir-empleado.png", new Color(77, 198, 51));
		anadirBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("ANADIR EMPLEADO");
				anadirPanel();
			}
		});
		_homePanel.add(anadirBtn, c);
		
		c.gridx++;
		JButton editarBtn = createMenuButton("resources/icons/empleados/editar-empleado.png", new Color(240, 178, 44));
		editarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("EDITAR EMPLEADO");
				editarPanel();;
			}
		});
		_homePanel.add(editarBtn, c);
		
		c.gridx++;
		JButton borrarBtn = createMenuButton("resources/icons/empleados/eliminar-empleado.png", new Color(218, 41, 41));
		borrarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("ELIMINAR EMPLEADO");
				borrarPanel();;
			}
		});
		_homePanel.add(borrarBtn, c);
		
		c.gridx = 0;
		c.gridy++;
		JButton listarBtn = createMenuButton("resources/icons/empleados/mostrar-empleados.png", new Color(56, 176, 225));
		listarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				SingletonControlador.getInstancia().accion(EventosEmpleado.LISTAR_EMPLEADOS, null);
			}
		});
		_homePanel.add(listarBtn, c);
		
		c.gridx++;
		JButton buscarBtn = createMenuButton("resources/icons/empleados/buscar-empleado.png", new Color(47, 101, 175));
		buscarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("BUSCAR EMPLEADO");
				buscarPanel();
			}
		});
		_homePanel.add(buscarBtn, c);

		add(_homePanel, name);
	}
	
	public String getName() {
		return name;
	}
	
	private void createPathButton(String name) {		
		JButton pathBtn = new JButton(name);
		pathBtn.setFocusPainted(false);
		pathBtn.setBorderPainted(false);
		pathBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		pathBtn.setBackground(new Color(230,230,230));
		pathBtn.setMaximumSize(new Dimension(250, 50));
		_lastPathComponents.add(pathBtn);
		pathPanel.add(pathBtn);
	}
	
	private void addPathSeparator() {
		JLabel pathSeparator = new JLabel(">", SwingConstants.CENTER);
		pathSeparator.setOpaque(false);
		pathSeparator.setFont(new Font("Arial", Font.BOLD, 22));
		pathSeparator.setMaximumSize(new Dimension(50, 50));
		_lastPathComponents.add(pathSeparator);
		pathPanel.add(pathSeparator);
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
	
	private void showOutputMsg(JPanel area, JLabel text, String msg, Boolean ok) {
		text.setText(msg);
		if (ok == true) {
			area.setBackground(new Color(37, 183, 50));
		} else {
			area.setBackground(new Color(172, 40, 40));
		}
		area.setVisible(true);

	}
	
	private void anadirPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(235, 237, 241));
		panel.setMaximumSize(new Dimension(1024, 460));
		
		//--
		
		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
		outputPanel.setBackground(new Color(235, 237, 241));
		outputPanel.setMaximumSize(new Dimension(1024, 50));
		
		anadirOutputArea = new JPanel();
		anadirOutputArea.setLayout(new BoxLayout(anadirOutputArea, BoxLayout.X_AXIS));
		anadirOutputArea.setBackground(new Color(172, 40, 40));
		anadirOutputArea.setMaximumSize(new Dimension(800, 50));
		
		anadirOutputLabel = new JLabel("ERROR: El nombre introducido no es valido.");
		anadirOutputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		anadirOutputLabel.setForeground(new Color(230,230,230));
		
		anadirOutputArea.add(Box.createRigidArea(new Dimension(40, 0)));
		anadirOutputArea.add(anadirOutputLabel);
		anadirOutputArea.setVisible(false);
		outputPanel.add(anadirOutputArea);
		
		//--
		
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(new Color(235, 237, 241));
		formPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		formPanel.setMaximumSize(new Dimension(1024, 120));
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5,5,0,0);
		JLabel nombreLabel = new JLabel("Nombre: ");
		nombreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(nombreLabel, c);
		
		c.gridy++;
		JLabel DNILabel = new JLabel("DNI: ");
		DNILabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(DNILabel, c);
		
		c.gridy++;
		JLabel IDTurno = new JLabel("ID Turno: ");
		IDTurno.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(IDTurno, c);
		
		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField nombreField = new JTextField(15);
		formPanel.add(nombreField, c);
		
		c.gridy++;
		JTextField DNIField = new JTextField(15);
		formPanel.add(DNIField, c);
		
		c.gridy++;
		JTextField IDTurnoField = new JTextField(15);
		formPanel.add(IDTurnoField, c);
		
		//--
		
		JButton enviarBtn = new JButton("ENVIAR");
		enviarBtn.setFocusPainted(false);
		enviarBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		enviarBtn.setBackground(new Color(230,230,230));
		enviarBtn.setMaximumSize(new Dimension(125, 30));
		enviarBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		enviarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String nombre = nombreField.getText();	
				String DNI = DNIField.getText();
				String IDTurno = IDTurnoField.getText();
				
				if ((nombre.length() > 0 && !nombre.equals(" ")) && (DNI.length() > 0 && !DNI.equals(" ")) && (IDTurno.length() > 0 && !IDTurno.equals(" "))) {
					TEmpleado empleado = new TEmpleado(0, nombre, DNI, false, Integer.valueOf(IDTurno));
					SingletonControlador.getInstancia().accion(EventosEmpleado.REGISTRAR_EMPLEADO, empleado);
				} else {
					showOutputMsg(anadirOutputArea, anadirOutputLabel, "ERROR: El nombre introducido no es valido.", false);
				}
			}
		});
		
		//--
		
		panel.add(Box.createRigidArea(new Dimension(0, 40)));
		panel.add(outputPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 30)));
		panel.add(formPanel);
		panel.add(enviarBtn);
		
		//--
		
		add(panel, "ANADIR");
		_localCL.show(this, "ANADIR");
		_currentPanel = panel;
	}
	
	private void editarPanel() {
		editarEmpleadoPanel = new JPanel();
		editarEmpleadoPanelCL = new CardLayout();
		editarEmpleadoPanel.setLayout(editarEmpleadoPanelCL);
		editarEmpleadoPanel.setBackground(new Color(235, 237, 241));
		editarEmpleadoPanel.setMaximumSize(new Dimension(1024, 460));
		
		JPanel buscarPanel = new JPanel();
		buscarPanel.setLayout(new BoxLayout(buscarPanel, BoxLayout.Y_AXIS));
		buscarPanel.setBackground(new Color(235, 237, 241));
		buscarPanel.setMaximumSize(new Dimension(1024, 460));
	
		//--
		
		JPanel errorPanel = new JPanel();
		errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.Y_AXIS));
		errorPanel.setBackground(new Color(235, 237, 241));
		errorPanel.setMaximumSize(new Dimension(1024, 50));
		
		editarBuscarErrorArea = new JPanel();
		editarBuscarErrorArea.setLayout(new BoxLayout(editarBuscarErrorArea, BoxLayout.X_AXIS));
		editarBuscarErrorArea.setBackground(new Color(172, 40, 40));
		editarBuscarErrorArea.setMaximumSize(new Dimension(800, 50));
		
		editarBuscarErrorLabel = new JLabel("ERROR: El nombre introducido no es valido.");
		editarBuscarErrorLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		editarBuscarErrorLabel.setForeground(new Color(230,230,230));
		
		editarBuscarErrorArea.add(Box.createRigidArea(new Dimension(40, 0)));
		editarBuscarErrorArea.add(editarBuscarErrorLabel);
		editarBuscarErrorArea.setVisible(false);
		errorPanel.add(editarBuscarErrorArea);
		
		//--
		
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(new Color(235, 237, 241));
		formPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		formPanel.setMaximumSize(new Dimension(1024, 70));
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5,5,0,0);
		JLabel IDLabel = new JLabel("ID Empleado: ");
		IDLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(IDLabel, c);
		
		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField IDField = new JTextField(15);
		formPanel.add(IDField, c);
		
		//--
		
		JPanel editarPanel = new JPanel();
		editarPanel.setLayout(new BoxLayout(editarPanel, BoxLayout.Y_AXIS));
		editarPanel.setBackground(new Color(235, 237, 241));
		editarPanel.setMaximumSize(new Dimension(1024, 460));
	
		//--
		
		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
		outputPanel.setBackground(new Color(235, 237, 241));
		outputPanel.setMaximumSize(new Dimension(1024, 50));
		
		editarOutputArea = new JPanel();
		editarOutputArea.setLayout(new BoxLayout(editarOutputArea, BoxLayout.X_AXIS));
		editarOutputArea.setBackground(new Color(172, 40, 40));
		editarOutputArea.setMaximumSize(new Dimension(800, 50));
		
		editarOutputLabel = new JLabel("ERROR: El nombre introducido no es valido.");
		editarOutputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		editarOutputLabel.setForeground(new Color(230,230,230));
		
		editarOutputArea.add(Box.createRigidArea(new Dimension(40, 0)));
		editarOutputArea.add(editarOutputLabel);
		editarOutputArea.setVisible(false);
		outputPanel.add(editarOutputArea);
		
		//--
		
		JPanel formPanel2 = new JPanel(new GridBagLayout());
		formPanel2.setBackground(new Color(235, 237, 241));
		formPanel2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		formPanel2.setMaximumSize(new Dimension(1024, 120));
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_END;
		c2.insets = new Insets(5,5,0,0);
		JLabel nombreLabel2 = new JLabel("Nombre: ");
		nombreLabel2.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel2.add(nombreLabel2, c2);
		
		c2.gridy++;
		JLabel DNILabel = new JLabel("DNI: ");
		DNILabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel2.add(DNILabel, c2);
		
		c2.gridy++;
		JLabel IDTurno = new JLabel("ID Turno: ");
		IDTurno.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel2.add(IDTurno, c2);
		
		c2.gridx++;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_START;
		editarNombreField = new JTextField(15);
		formPanel2.add(editarNombreField, c2);
		
		c2.gridy++;
		editarDNIField = new JTextField(15);
		formPanel2.add(editarDNIField, c2);
		
		c2.gridy++;
		editarIDTurnoField = new JTextField(15);
		formPanel2.add(editarIDTurnoField, c2);
		
		//--
		
		JButton buscarBtn = new JButton("BUSCAR");
		buscarBtn.setFocusPainted(false);
		buscarBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		buscarBtn.setBackground(new Color(230,230,230));
		buscarBtn.setMaximumSize(new Dimension(125, 30));
		buscarBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		buscarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String ID = IDField.getText();
				if ((ID.length() > 0 && !ID.equals(" "))) {
					SingletonControlador.getInstancia().accion(EventosEmpleado.MODIFICAR_BUSCAR_EMPLEADO, Integer.valueOf(ID));
				} else {
					showOutputMsg(editarBuscarErrorArea, editarBuscarErrorLabel, "ERROR: El ID introducido no es valido.", false);
				}
			}
		});
		
		//--
		
		JButton confirmBtn = new JButton("CONFIRMAR");
		confirmBtn.setFocusPainted(false);
		confirmBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		confirmBtn.setBackground(new Color(230,230,230));
		confirmBtn.setMaximumSize(new Dimension(150, 30));
		confirmBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		confirmBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Integer ID = Integer.valueOf(IDField.getText());
				String nombre = editarNombreField.getText();
				String DNI = editarDNIField.getText();
				String IDTurno = editarIDTurnoField.getText();
				
				if ((nombre.length() > 0 && !nombre.equals(" ")) && (DNI.length() > 0 && !DNI.equals(" ")) && (IDTurno.length() > 0 && !IDTurno.equals(" "))) {
					TEmpleado empleado = new TEmpleado(ID, nombre, DNI, true, Integer.valueOf(IDTurno));
					SingletonControlador.getInstancia().accion(EventosEmpleado.MODIFICAR_EMPLEADO, empleado);
				} else {
					showOutputMsg(editarBuscarErrorArea, editarBuscarErrorLabel, "ERROR: Los datos introducidos no son validos.", false);
				}
			}
		});
		
		//--
		
		buscarPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		buscarPanel.add(errorPanel);
		buscarPanel.add(Box.createRigidArea(new Dimension(0, 60)));
		buscarPanel.add(formPanel);
		buscarPanel.add(buscarBtn);
		
		//--
		
		editarPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		editarPanel.add(outputPanel);
		editarPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		editarPanel.add(formPanel2);
		editarPanel.add(confirmBtn);
		
		//-- 
		
		editarEmpleadoPanel.add(buscarPanel, "BUSCAR");
		editarEmpleadoPanel.add(editarPanel, "SECOND");
		add(editarEmpleadoPanel, "EDITAR");
		
		editarEmpleadoPanelCL.show(editarEmpleadoPanel, "BUSCAR");
		_localCL.show(this, "EDITAR");
		_currentPanel = editarEmpleadoPanel;
	}
	
	private void borrarPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(235, 237, 241));
		panel.setMaximumSize(new Dimension(1024, 460));
	
		//--
		
		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
		outputPanel.setBackground(new Color(235, 237, 241));
		outputPanel.setMaximumSize(new Dimension(1024, 50));
		
		borrarOutputArea = new JPanel();
		borrarOutputArea.setLayout(new BoxLayout(borrarOutputArea, BoxLayout.X_AXIS));
		borrarOutputArea.setBackground(new Color(172, 40, 40));
		borrarOutputArea.setMaximumSize(new Dimension(800, 50));
		
		borrarOutputLabel = new JLabel("ERROR: El nombre introducido no es valido.");
		borrarOutputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		borrarOutputLabel.setForeground(new Color(230,230,230));
		
		borrarOutputArea.add(Box.createRigidArea(new Dimension(40, 0)));
		borrarOutputArea.add(borrarOutputLabel);
		borrarOutputArea.setVisible(false);
		outputPanel.add(borrarOutputArea);
		
		//--
		
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(new Color(235, 237, 241));
		formPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		formPanel.setMaximumSize(new Dimension(1024, 70));
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5,5,0,0);
		JLabel IDLabel = new JLabel("ID Empleado: ");
		IDLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(IDLabel, c);
		
		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField IDField = new JTextField(15);
		formPanel.add(IDField, c);
		
		//--
		
		JButton borrarBtn = new JButton("ELIMINAR");
		borrarBtn.setFocusPainted(false);
		borrarBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		borrarBtn.setBackground(new Color(230,230,230));
		borrarBtn.setMaximumSize(new Dimension(125, 30));
		borrarBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		borrarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String ID = IDField.getText();	
				if (ID.length() > 0 && !ID.equals(" ")) {
					SingletonControlador.getInstancia().accion(EventosEmpleado.ELIMINAR_EMPLEADO, Integer.valueOf(ID));
				} else {
					showOutputMsg(borrarOutputArea, borrarOutputLabel, "ERROR: El nombre introducido no es valido.", false);
				}
			}
		});
		
		//--
		
		panel.add(Box.createRigidArea(new Dimension(0, 40)));
		panel.add(outputPanel);
		panel.add(Box.createRigidArea(new Dimension(0,60)));
		panel.add(formPanel);
		panel.add(borrarBtn);
		
		//--
		
		add(panel, "ELIMINAR");
		
		_localCL.show(this, "ELIMINAR");
		_currentPanel = panel;
	}
	
	private void mostrarPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(235, 237, 241));
		panel.setMaximumSize(new Dimension(1024, 460));
		
		JLabel tableTitle = new JLabel("EMPLEADOS");
		tableTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tableTitle.setFont(new Font("Arial", Font.BOLD, 18));
		
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		tablePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tablePanel.setBackground(new Color(235, 237, 241));
		tablePanel.setMaximumSize(new Dimension(800, 320));
		
		String[] columns = {"ID Empleado", "Nombre", "DNI", "ID Turno", "Activo"};

		mostrarModel = new DefaultTableModel(); 
        for (String column : columns) {
        	mostrarModel.addColumn(column);
        }
		JTable dataTable = new JTable(mostrarModel);
		dataTable.setEnabled(false);
		dataTable.getTableHeader().setReorderingAllowed(false);
		dataTable.setPreferredScrollableViewportSize(new Dimension(450, 63));
		dataTable.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(dataTable);
		tablePanel.add(scrollPane);
		
		panel.add(Box.createRigidArea(new Dimension(0, 40)));
		panel.add(tableTitle);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(tablePanel);
		
		add(panel, "MOSTRAR");
		_localCL.show(this, "MOSTRAR");
		_currentPanel = panel;
	}

	private void buscarPanel() {
		mostrarEmpleadoPanel = new JPanel();
		mostrarEmpleadoPanelCL = new CardLayout();
		mostrarEmpleadoPanel.setLayout(mostrarEmpleadoPanelCL);
		mostrarEmpleadoPanel.setBackground(new Color(235, 237, 241));
		mostrarEmpleadoPanel.setMaximumSize(new Dimension(1024, 460));
		
		JPanel buscarPanel = new JPanel();
		buscarPanel.setLayout(new BoxLayout(buscarPanel, BoxLayout.Y_AXIS));
		buscarPanel.setBackground(new Color(235, 237, 241));
		buscarPanel.setMaximumSize(new Dimension(1024, 460));
	
		//--
		
		JPanel errorPanel = new JPanel();
		errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.Y_AXIS));
		errorPanel.setBackground(new Color(235, 237, 241));
		errorPanel.setMaximumSize(new Dimension(1024, 50));
		
		mostrarErrorArea = new JPanel();
		mostrarErrorArea.setLayout(new BoxLayout(mostrarErrorArea, BoxLayout.X_AXIS));
		mostrarErrorArea.setBackground(new Color(172, 40, 40));
		mostrarErrorArea.setMaximumSize(new Dimension(800, 50));
		
		mostrarErrorLabel = new JLabel("ERROR: El nombre introducido no es valido.");
		mostrarErrorLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		mostrarErrorLabel.setForeground(new Color(230,230,230));
		
		mostrarErrorArea.add(Box.createRigidArea(new Dimension(40, 0)));
		mostrarErrorArea.add(mostrarErrorLabel);
		mostrarErrorArea.setVisible(false);
		errorPanel.add(mostrarErrorArea);
		
		//--
		
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(new Color(235, 237, 241));
		formPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		formPanel.setMaximumSize(new Dimension(1024, 70));
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5,5,0,0);
		JLabel IDLabel = new JLabel("ID Empleado: ");
		IDLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(IDLabel, c);
		
		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField IDField = new JTextField(15);
		formPanel.add(IDField, c);
		
		//--
		
		JPanel empleadoPanel = new JPanel();
		empleadoPanel.setLayout(new BoxLayout(empleadoPanel, BoxLayout.Y_AXIS));
		empleadoPanel.setBackground(new Color(235, 237, 241));
		empleadoPanel.setMaximumSize(new Dimension(1024, 460));
		
		//--
				
		JPanel dataPanel = new JPanel(new GridBagLayout());
		dataPanel.setBackground(new Color(235, 237, 241));
		dataPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		dataPanel.setMaximumSize(new Dimension(1024, 140));
				
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_END;
		c2.insets = new Insets(5,5,0,0);
		JLabel IDLabel2 = new JLabel("ID Empleado: ");
		IDLabel2.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(IDLabel2, c2);
				
		c2.gridy++;
		JLabel nombreLabel = new JLabel("Nombre: ");
		nombreLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(nombreLabel, c2);
		
		c2.gridy++;
		JLabel DNILabel = new JLabel("DNI: ");
		DNILabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(DNILabel, c2);
		
		c2.gridy++;
		JLabel IDTurnoLabel = new JLabel("ID Turno: ");
		IDTurnoLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(IDTurnoLabel, c2);
				
		c2.gridx++;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_START;
		mostrarIDText = new JLabel("0");
		mostrarIDText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarIDText, c2);
				
		c2.gridy++;
		mostrarNombreText = new JLabel("Nombre");
		mostrarNombreText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarNombreText, c2);
		
		c2.gridy++;
		mostrarDNIText = new JLabel("DNI");
		mostrarDNIText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarDNIText, c2);
		
		c2.gridy++;
		mostrarIDTurnoText = new JLabel("0");
		mostrarIDTurnoText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarIDTurnoText, c2);
			
		//--
		
		JButton buscarBtn = new JButton("BUSCAR");
		buscarBtn.setFocusPainted(false);
		buscarBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		buscarBtn.setBackground(new Color(230,230,230));
		buscarBtn.setMaximumSize(new Dimension(125, 30));
		buscarBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		buscarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String ID = IDField.getText();	
				if (ID.length() > 0 && !ID.equals(" ")) {
					SingletonControlador.getInstancia().accion(EventosEmpleado.MOSTRAR_EMPLEADO, Integer.valueOf(ID));
				} else {
					showOutputMsg(mostrarErrorArea, mostrarErrorLabel, "ERROR: El nombre introducido no es valido.", false);
				}
			}
		});
		
		//--
		
		buscarPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		buscarPanel.add(errorPanel);
		buscarPanel.add(Box.createRigidArea(new Dimension(0, 60)));
		buscarPanel.add(formPanel);
		buscarPanel.add(buscarBtn);
				
		//--
				
		empleadoPanel.add(Box.createRigidArea(new Dimension(0, 125)));
		empleadoPanel.add(dataPanel);
				
		//--
				
		mostrarEmpleadoPanel.add(buscarPanel, "BUSCAR");
		mostrarEmpleadoPanel.add(empleadoPanel, "EMPLEADO");
		add(mostrarEmpleadoPanel, "BUSCAR");
				
		mostrarEmpleadoPanelCL.show(mostrarEmpleadoPanel, "BUSCAR");
		_localCL.show(this, "BUSCAR");
		_currentPanel = mostrarEmpleadoPanel;
	}
	
	private void clear() {
		for (Component c : _lastPathComponents) {
			pathPanel.remove(c);
			pathPanel.revalidate();
			pathPanel.repaint();
		}
		
		this.remove(_currentPanel);
		this.revalidate();
		this.repaint();
	}
	
	public void mostrar() {
		_localCL.show(this, name);
		
		if (_currentPanel != null) {
			if (!_currentPanel.equals(_homePanel))
				clear();
		}
	}

	@Override
	public void actualizar(int evento, Object datos) {
		String mensaje;
		TEmpleado empleado;
		switch (evento){
			case EventosEmpleado.REGISTRAR_EMPLEADO_OK:
				mensaje = (String) datos;
				showOutputMsg(anadirOutputArea, anadirOutputLabel, mensaje, true);
				System.out.println("Anadir Empleado OK");
				break;
			case EventosEmpleado.REGISTRAR_EMPLEADO_KO: {
				mensaje = (String) datos;
				showOutputMsg(anadirOutputArea, anadirOutputLabel, mensaje, false);
				System.out.println("Anadir Empleado KO");
				}
				break;
			case EventosEmpleado.ELIMINAR_EMPLEADO_OK:
				mensaje = (String) datos;
				showOutputMsg(borrarOutputArea, borrarOutputLabel, mensaje, true);
				System.out.println("Eliminar Empleado OK");
				break;
			case EventosEmpleado.ELIMINAR_EMPLEADO_KO:
				mensaje = (String) datos;
				showOutputMsg(borrarOutputArea, borrarOutputLabel, mensaje, false);
				System.out.println("Eliminar Empleado KO");
				break;
			case EventosEmpleado.MOSTRAR_EMPLEADO_OK:
				empleado = (TEmpleado) datos;
				
				mostrarIDText.setText(empleado.getId().toString());
				mostrarNombreText.setText(empleado.getNombre());
				mostrarDNIText.setText(empleado.getDni());
				mostrarIDTurnoText.setText(empleado.getIdTurno().toString());
				
				mostrarEmpleadoPanelCL.show(mostrarEmpleadoPanel, "EMPLEADO");
				System.out.println("Mostrar Empleado OK");
				break;
			case EventosEmpleado.MOSTRAR_EMPLEADO_KO:
				mensaje = (String) datos;
				showOutputMsg(mostrarErrorArea, mostrarErrorLabel, mensaje, false);
				System.out.println("Mostrar Empleado KO");
				break;
			case EventosEmpleado.MODIFICAR_BUSCAR_EMPLEADO_OK:
				empleado = (TEmpleado) datos;
				
				editarNombreField.setText(empleado.getNombre());
				editarDNIField.setText(empleado.getDni());
				editarIDTurnoField.setText(Integer.valueOf(empleado.getIdTurno()).toString());

				editarEmpleadoPanelCL.show(editarEmpleadoPanel, "SECOND");
				System.out.println("Editar Buscar Empleado OK");
				break;
			case EventosEmpleado.MODIFICAR_BUSCAR_EMPLEADO_KO:
				mensaje = (String) datos;

				showOutputMsg(editarBuscarErrorArea, editarBuscarErrorLabel, mensaje, false);
				System.out.println("Editar Buscar Empleado KO");
				break;
			case EventosEmpleado.MODIFICAR_EMPLEADO_OK:
				mensaje = (String) datos;
				
				showOutputMsg(editarOutputArea, editarOutputLabel, mensaje, true);
				System.out.println("Editar Empleado OK");
				break;
			case EventosEmpleado.MODIFICAR_EMPLEADO_KO:
				mensaje = (String) datos;
				
				showOutputMsg(editarOutputArea, editarOutputLabel, mensaje, false);
				System.out.println("Editar Empleado KO");
				break;
			case EventosEmpleado.LISTAR_EMPLEADOS_OK:
				@SuppressWarnings("unchecked") List<TEmpleado> listaEmpleados = (List<TEmpleado>) datos;
				
				addPathSeparator();
				createPathButton("MOSTRAR EMPLEADOS");
				mostrarPanel();
				
				for (TEmpleado e : listaEmpleados) {
					mostrarModel.addRow(new Object[]{e.getId().toString(), e.getNombre(), e.getDni(), Integer.valueOf(e.getIdTurno()), Boolean.valueOf(e.getActivo())});
				}
				System.out.println("Listar Clientes OK");
				break;
			case EventosEmpleado.LISTAR_EMPLEADOS_KO:
				mensaje = (String) datos;
				
				JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println("Listar Clientes KO");
				break;
		}
	}
}
