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
import negocio.negocioTurno.TTurno;
import presentacion.controlador.EventosTurno;
import presentacion.controlador.SingletonControlador;
import presentacion.factoria.FactoriaPresentacion;

public class TurnoGUI extends JPanel implements InterfazGUI {
	private static final long serialVersionUID = 1L;

	private String name = "TURNOS";
	
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
	
	private JPanel mostrarTurnoPanel;
	private CardLayout mostrarTurnoPanelCL;
	private JPanel mostrarErrorArea;
	private JLabel mostrarErrorLabel;
	private JLabel mostrarIDText;
	private JLabel mostrarNombreText;
	private JLabel mostrarHoraInText;
	private JLabel mostrarHoraFinText;
	private JLabel mostrarActivoText;
	
	private JPanel editarTurnoPanel;
	private CardLayout editarTurnoPanelCL;
	private JPanel editarBuscarErrorArea;
	private JLabel editarBuscarErrorLabel;
	private JPanel editarOutputArea;
	private JLabel editarOutputLabel;
	private JTextField editarNombreField;
	private JTextField editarHoraInicioField;
	private JTextField editarHoraFinField;
	
	private DefaultTableModel mostrarModel;
	
	private JPanel empleadosMainPanel;
	private CardLayout empleadosMainPanelCL;
	private DefaultTableModel empleadosModel;
	private JPanel empleadosErrorArea;
	private JLabel empleadosErrorLabel;
	
	private JPanel turnoEmpleadoPanel;
	private CardLayout turnoEmpleadoPanelCL;
	private JPanel turnoEmpleadoErrorArea;
	private JLabel turnoEmpleadoErrorLabel;
	private JLabel turnoEmpleadoIDText;
	private JLabel turnoEmpleadoNombreText;
	private JLabel turnoEmpleadoHoraInText;
	private JLabel turnoEmpleadoHoraFinText;
	private JLabel turnoEmpleadoActivoText;
	
	public TurnoGUI() {
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
		
		JButton anadirBtn = createMenuButton("resources/icons/turnos/anadir-turno.png", new Color(77, 198, 51));
		anadirBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("ANADIR TURNO");
				anadirPanel();
			}
		});
		_homePanel.add(anadirBtn, c);
		
		c.gridx++;
		JButton editarBtn = createMenuButton("resources/icons/turnos/editar-turno.png", new Color(240, 178, 44));
		editarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("EDITAR TURNO");
				editarPanel();;
			}
		});
		_homePanel.add(editarBtn, c);
		
		c.gridx++;
		JButton borrarBtn = createMenuButton("resources/icons/turnos/eliminar-turno.png", new Color(218, 41, 41));
		borrarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("ELIMINAR TURNO");
				borrarPanel();;
			}
		});
		_homePanel.add(borrarBtn, c);
		
		c.gridx++;
		JButton listarBtn = createMenuButton("resources/icons/turnos/mostrar-turnos-turno.png", new Color(56, 176, 225));
		listarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				SingletonControlador.getInstancia().accion(EventosTurno.LISTAR_TURNOS, null);
			}
		});
		_homePanel.add(listarBtn, c);
		
		c.gridx = 0;
		c.gridy++;
		JButton buscarBtn = createMenuButton("resources/icons/turnos/buscar-turno.png", new Color(47, 101, 175));
		buscarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("BUSCAR TURNO");
				buscarPanel();
			}
		});
		_homePanel.add(buscarBtn, c);
		
		c.gridx++;
		JButton turnoEmpleadoBtn = createMenuButton("resources/icons/turnos/empleado-turno.png", new Color(38, 120, 69));
		turnoEmpleadoBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("TURNO EMPLEADO");
				turnoEmpleadoPanel();
			}
		});
		_homePanel.add(turnoEmpleadoBtn, c);
		
		c.gridx++;
		JButton empleadosTurnoBtn = createMenuButton("resources/icons/turnos/mostrar-empleados-turno.png", new Color(127, 74, 162));
		empleadosTurnoBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("EMPLEADOS TURNO");
				empleadosTurnoPanel();
			}
		});
		_homePanel.add(empleadosTurnoBtn, c);

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
		button.setPreferredSize(new Dimension(180,112));
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
		JLabel horaInicioLabel = new JLabel("Hora Inicio: ");
		horaInicioLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(horaInicioLabel, c);
		
		c.gridy++;
		JLabel horaFinLabel = new JLabel("Hora Fin: ");
		horaFinLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(horaFinLabel, c);
		
		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField nombreField = new JTextField(15);
		formPanel.add(nombreField, c);
		
		c.gridy++;
		JTextField horaInicioField = new JTextField(15);
		formPanel.add(horaInicioField, c);
		
		c.gridy++;
		JTextField horaFinField = new JTextField(15);
		formPanel.add(horaFinField, c);
		
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
				String horaInText = horaInicioField.getText();	
				String horaFinText = horaFinField.getText();
				Integer horaIn = Integer.valueOf(horaInText);
				Integer horaFin = Integer.valueOf(horaFinText);
				if ((nombre.length() > 0 && !nombre.equals(" ")) && (horaInText.length() > 0 && !horaInText.equals(" ")) && (horaFinText.length() > 0 && !horaFinText.equals(" "))) {
					TTurno turno = new TTurno(nombre, horaIn, horaFin);
					SingletonControlador.getInstancia().accion(EventosTurno.ANADIR_TURNO, turno);
				} else {
					showOutputMsg(anadirOutputArea, anadirOutputLabel, "ERROR: Los datos introducidos no son validos.", false);
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
		editarTurnoPanel = new JPanel();
		editarTurnoPanelCL = new CardLayout();
		editarTurnoPanel.setLayout(editarTurnoPanelCL);
		editarTurnoPanel.setBackground(new Color(235, 237, 241));
		editarTurnoPanel.setMaximumSize(new Dimension(1024, 460));
		
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
		
		editarBuscarErrorLabel = new JLabel("ERROR: El ID introducido no es valido.");
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
		JLabel IDLabel = new JLabel("ID Turno: ");
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
		JLabel horaInicioLabel = new JLabel("Hora Inicio: ");
		horaInicioLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel2.add(horaInicioLabel, c2);
		
		c2.gridy++;
		JLabel horaFinLabel = new JLabel("Hora Fin: ");
		horaFinLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel2.add(horaFinLabel, c2);
		
		c2.gridx++;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_START;
		editarNombreField = new JTextField(15);
		formPanel2.add(editarNombreField, c2);
		
		c2.gridy++;
		editarHoraInicioField = new JTextField(15);
		formPanel2.add(editarHoraInicioField, c2);
		
		c2.gridy++;
		editarHoraFinField = new JTextField(15);
		formPanel2.add(editarHoraFinField, c2);
		
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
					SingletonControlador.getInstancia().accion(EventosTurno.EDITAR_BUSCAR_TURNO, Integer.valueOf(ID));
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
				String horaIn = editarHoraInicioField.getText();
				String horaFin = editarHoraFinField.getText();
				if ((nombre.length() > 0 && !nombre.equals(" ")) && (horaIn.length() > 0 && !horaIn.equals(" ")) && (horaFin.length() > 0 && !horaFin.equals(" "))) {
					TTurno turno = new TTurno(ID, nombre, Integer.valueOf(horaIn), Integer.valueOf(horaFin), true);
					SingletonControlador.getInstancia().accion(EventosTurno.EDITAR_TURNO, turno);
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
		
		editarTurnoPanel.add(buscarPanel, "BUSCAR");
		editarTurnoPanel.add(editarPanel, "SECOND");
		add(editarTurnoPanel, "EDITAR");
		
		editarTurnoPanelCL.show(editarTurnoPanel, "BUSCAR");
		_localCL.show(this, "EDITAR");
		_currentPanel = editarTurnoPanel;
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
		JLabel IDLabel = new JLabel("ID Turno: ");
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
					
					SingletonControlador.getInstancia().accion(EventosTurno.ELIMINAR_TURNO, Integer.valueOf(ID));
				} else {
					showOutputMsg(borrarOutputArea, borrarOutputLabel, "ERROR: El ID introducido no es valido.", false);
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
		
		JLabel tableTitle = new JLabel("TURNOS");
		tableTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tableTitle.setFont(new Font("Arial", Font.BOLD, 18));
		
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		tablePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tablePanel.setBackground(new Color(235, 237, 241));
		tablePanel.setMaximumSize(new Dimension(800, 320));
		
		String[] columns = {"ID Turno", "Nombre", "Hora Inicio", "Hora Fin", "Activo"};
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
		mostrarTurnoPanel = new JPanel();
		mostrarTurnoPanelCL = new CardLayout();
		mostrarTurnoPanel.setLayout(mostrarTurnoPanelCL);
		mostrarTurnoPanel.setBackground(new Color(235, 237, 241));
		mostrarTurnoPanel.setMaximumSize(new Dimension(1024, 460));
		
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
		JLabel IDLabel = new JLabel("ID Turno: ");
		IDLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(IDLabel, c);
		
		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField IDField = new JTextField(15);
		formPanel.add(IDField, c);
		
		//--
		
		JPanel turnoPanel = new JPanel();
		turnoPanel.setLayout(new BoxLayout(turnoPanel, BoxLayout.Y_AXIS));
		turnoPanel.setBackground(new Color(235, 237, 241));
		turnoPanel.setMaximumSize(new Dimension(1024, 460));
			
		//--
				
		JPanel dataPanel = new JPanel(new GridBagLayout());
		dataPanel.setBackground(new Color(235, 237, 241));
		dataPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		dataPanel.setMaximumSize(new Dimension(1024, 150));
					
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_END;
		c2.insets = new Insets(5,5,0,0);
		JLabel IDLabel2 = new JLabel("ID Turno: ");
		IDLabel2.setFont(new Font("Arial", Font.PLAIN, 20));
		dataPanel.add(IDLabel2, c2);
				
		c2.gridy++;
		JLabel nombreLabel = new JLabel("Nombre: ");
		nombreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		dataPanel.add(nombreLabel, c2);
		
		c2.gridy++;
		JLabel horaInLabel = new JLabel("Hora Inicio: ");
		horaInLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		dataPanel.add(horaInLabel, c2);
		
		c2.gridy++;
		JLabel horaFinLabel = new JLabel("Hora Fin: ");
		horaFinLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		dataPanel.add(horaFinLabel, c2);
		
		c2.gridy++;
		JLabel activoLabel = new JLabel("Activo: ");
		activoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		dataPanel.add(activoLabel, c2);
		
		c2.gridx++;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_START;
		mostrarIDText = new JLabel("2");
		mostrarIDText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarIDText, c2);
		
		c2.gridy++;
		mostrarNombreText = new JLabel("Turno");
		mostrarNombreText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarNombreText, c2);
		
		c2.gridy++;
		mostrarHoraInText = new JLabel("0");
		mostrarHoraInText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarHoraInText, c2);
		
		c2.gridy++;
		mostrarHoraFinText = new JLabel("9");
		mostrarHoraFinText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarHoraFinText, c2);
		
		c2.gridy++;
		mostrarActivoText = new JLabel("true");
		mostrarActivoText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarActivoText, c2);
				
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
					SingletonControlador.getInstancia().accion(EventosTurno.MOSTRAR_TURNO, Integer.valueOf(ID));
				} else {
					showOutputMsg(mostrarErrorArea, mostrarErrorLabel, "ERROR: El ID introducido no es valido.", false);
				}
			}
		});
		
		//--
		
		buscarPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		buscarPanel.add(errorPanel);
		buscarPanel.add(Box.createRigidArea(new Dimension(0,60)));
		buscarPanel.add(formPanel);
		buscarPanel.add(buscarBtn);
		
		//--
		
		turnoPanel.add(Box.createRigidArea(new Dimension(0, 130)));
		turnoPanel.add(dataPanel);
		
		//--
		
		mostrarTurnoPanel.add(buscarPanel, "BUSCAR");
		mostrarTurnoPanel.add(turnoPanel, "TURNO");
		add(mostrarTurnoPanel, "BUSCAR");
		
		mostrarTurnoPanelCL.show(mostrarTurnoPanel, "BUSCAR");
		_localCL.show(this, "BUSCAR");
		_currentPanel = mostrarTurnoPanel;
	}
	
	private void turnoEmpleadoPanel() {
		turnoEmpleadoPanel = new JPanel();
		turnoEmpleadoPanelCL = new CardLayout();
		turnoEmpleadoPanel.setLayout(turnoEmpleadoPanelCL);
		turnoEmpleadoPanel.setBackground(new Color(235, 237, 241));
		turnoEmpleadoPanel.setMaximumSize(new Dimension(1024, 460));
		
		JPanel buscarPanel = new JPanel();
		buscarPanel.setLayout(new BoxLayout(buscarPanel, BoxLayout.Y_AXIS));
		buscarPanel.setBackground(new Color(235, 237, 241));
		buscarPanel.setMaximumSize(new Dimension(1024, 460));
	
		//--
		
		JPanel errorPanel = new JPanel();
		errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.Y_AXIS));
		errorPanel.setBackground(new Color(235, 237, 241));
		errorPanel.setMaximumSize(new Dimension(1024, 50));
		
		turnoEmpleadoErrorArea = new JPanel();
		turnoEmpleadoErrorArea.setLayout(new BoxLayout(turnoEmpleadoErrorArea, BoxLayout.X_AXIS));
		turnoEmpleadoErrorArea.setBackground(new Color(172, 40, 40));
		turnoEmpleadoErrorArea.setMaximumSize(new Dimension(800, 50));
		
		turnoEmpleadoErrorLabel = new JLabel("ERROR: El ID introducido no es valido.");
		turnoEmpleadoErrorLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		turnoEmpleadoErrorLabel.setForeground(new Color(230,230,230));
		
		turnoEmpleadoErrorArea.add(Box.createRigidArea(new Dimension(40, 0)));
		turnoEmpleadoErrorArea.add(turnoEmpleadoErrorLabel);
		turnoEmpleadoErrorArea.setVisible(false);
		errorPanel.add(turnoEmpleadoErrorArea);
		
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
		
		JPanel turnoPanel = new JPanel();
		turnoPanel.setLayout(new BoxLayout(turnoPanel, BoxLayout.Y_AXIS));
		turnoPanel.setBackground(new Color(235, 237, 241));
		turnoPanel.setMaximumSize(new Dimension(1024, 460));
			
		//--
				
		JPanel dataPanel = new JPanel(new GridBagLayout());
		dataPanel.setBackground(new Color(235, 237, 241));
		dataPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		dataPanel.setMaximumSize(new Dimension(1024, 150));
					
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_END;
		c2.insets = new Insets(5,5,0,0);
		JLabel IDLabel2 = new JLabel("ID Turno: ");
		IDLabel2.setFont(new Font("Arial", Font.PLAIN, 20));
		dataPanel.add(IDLabel2, c2);
				
		c2.gridy++;
		JLabel nombreLabel = new JLabel("Nombre: ");
		nombreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		dataPanel.add(nombreLabel, c2);
		
		c2.gridy++;
		JLabel horaInLabel = new JLabel("Hora Inicio: ");
		horaInLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		dataPanel.add(horaInLabel, c2);
		
		c2.gridy++;
		JLabel horaFinLabel = new JLabel("Hora Fin: ");
		horaFinLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		dataPanel.add(horaFinLabel, c2);
		
		c2.gridy++;
		JLabel activoLabel = new JLabel("Activo: ");
		activoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		dataPanel.add(activoLabel, c2);
		
		c2.gridx++;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_START;
		turnoEmpleadoIDText = new JLabel("2");
		turnoEmpleadoIDText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(turnoEmpleadoIDText, c2);
		
		c2.gridy++;
		turnoEmpleadoNombreText = new JLabel("Turno");	////////
		turnoEmpleadoNombreText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(turnoEmpleadoNombreText, c2);
		
		c2.gridy++;
		turnoEmpleadoHoraInText = new JLabel("0");
		turnoEmpleadoHoraInText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(turnoEmpleadoHoraInText, c2);
		
		c2.gridy++;
		turnoEmpleadoHoraFinText = new JLabel("9");
		turnoEmpleadoHoraFinText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(turnoEmpleadoHoraFinText, c2);
		
		c2.gridy++;
		turnoEmpleadoActivoText = new JLabel("true");
		turnoEmpleadoActivoText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(turnoEmpleadoActivoText, c2);
				
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
					SingletonControlador.getInstancia().accion(EventosTurno.DEVOLVER_TURNO, Integer.valueOf(ID));
				} else {
					showOutputMsg(turnoEmpleadoErrorArea, turnoEmpleadoErrorLabel, "ERROR: El ID introducido no es valido.", false);
				}
			}
		});
		
		//--
		
		buscarPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		buscarPanel.add(errorPanel);
		buscarPanel.add(Box.createRigidArea(new Dimension(0,60)));
		buscarPanel.add(formPanel);
		buscarPanel.add(buscarBtn);
		
		//--
		
		turnoPanel.add(Box.createRigidArea(new Dimension(0, 130)));
		turnoPanel.add(dataPanel);
		
		//--
		
		turnoEmpleadoPanel.add(buscarPanel, "BUSCAR");
		turnoEmpleadoPanel.add(turnoPanel, "TURNO");
		add(turnoEmpleadoPanel, "TURNO EMPLEADO");
		
		turnoEmpleadoPanelCL.show(turnoEmpleadoPanel, "BUSCAR");
		_localCL.show(this, "TURNO EMPLEADO");
		_currentPanel = turnoEmpleadoPanel;
	}
	
	private void empleadosTurnoPanel() {
		empleadosMainPanel = new JPanel();
		empleadosMainPanelCL = new CardLayout();
		empleadosMainPanel.setLayout(empleadosMainPanelCL);
		empleadosMainPanel.setBackground(new Color(235, 237, 241));
		empleadosMainPanel.setMaximumSize(new Dimension(1024, 460));
		
		JPanel buscarPanel = new JPanel();
		buscarPanel.setLayout(new BoxLayout(buscarPanel, BoxLayout.Y_AXIS));
		buscarPanel.setBackground(new Color(235, 237, 241));
		buscarPanel.setMaximumSize(new Dimension(1024, 460));
	
		//--
		
		JPanel errorPanel = new JPanel();
		errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.Y_AXIS));
		errorPanel.setBackground(new Color(235, 237, 241));
		errorPanel.setMaximumSize(new Dimension(1024, 50));
		
		empleadosErrorArea = new JPanel();
		empleadosErrorArea.setLayout(new BoxLayout(empleadosErrorArea, BoxLayout.X_AXIS));
		empleadosErrorArea.setBackground(new Color(172, 40, 40));
		empleadosErrorArea.setMaximumSize(new Dimension(800, 50));
		
		empleadosErrorLabel = new JLabel("ERROR: El ID introducido no es valido.");
		empleadosErrorLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		empleadosErrorLabel.setForeground(new Color(230,230,230));
		
		empleadosErrorArea.add(Box.createRigidArea(new Dimension(40, 0)));
		empleadosErrorArea.add(empleadosErrorLabel);
		empleadosErrorArea.setVisible(false);
		errorPanel.add(empleadosErrorArea);
		
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
		JLabel IDLabel = new JLabel("ID Turno: ");
		IDLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(IDLabel, c);
		
		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField IDField = new JTextField(15);
		formPanel.add(IDField, c);
		
		//--
		
		JPanel empleadosPanel = new JPanel();
		empleadosPanel.setLayout(new BoxLayout(empleadosPanel, BoxLayout.Y_AXIS));
		empleadosPanel.setBackground(new Color(235, 237, 241));
		empleadosPanel.setMaximumSize(new Dimension(1024, 460));
		
		JLabel tableTitle = new JLabel("EMPLEADOS");
		tableTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tableTitle.setFont(new Font("Arial", Font.BOLD, 18));
		
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		tablePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tablePanel.setBackground(new Color(235, 237, 241));
		tablePanel.setMaximumSize(new Dimension(800, 320));
		
		String[] columns = {"ID Empleado", "Nombre", "DNI", "ID Turno", "Activo"};

		empleadosModel = new DefaultTableModel(); 
        for (String column : columns) {
        	empleadosModel.addColumn(column);
        }
		JTable dataTable = new JTable(empleadosModel);
		dataTable.setEnabled(false);
		dataTable.getTableHeader().setReorderingAllowed(false);
		dataTable.setPreferredScrollableViewportSize(new Dimension(450, 63));
		dataTable.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(dataTable);
		tablePanel.add(scrollPane);
		
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
					SingletonControlador.getInstancia().accion(EventosTurno.LISTAR_EMPLEADOS, Integer.valueOf(ID));
				} else {
					showOutputMsg(empleadosErrorArea, empleadosErrorLabel, "ERROR: El ID introducido no es valido.", false);
				}
			}
		});
		
		//--
		
		buscarPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		buscarPanel.add(errorPanel);
		buscarPanel.add(Box.createRigidArea(new Dimension(0,60)));
		buscarPanel.add(formPanel);
		buscarPanel.add(buscarBtn);
		
		//--
		
		empleadosPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		empleadosPanel.add(tableTitle);
		empleadosPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		empleadosPanel.add(tablePanel);
		
		//--
		
		empleadosMainPanel.add(buscarPanel, "BUSCAR");
		empleadosMainPanel.add(empleadosPanel, "EMPLEADOS");
		add(empleadosMainPanel, "EMPLEADOS TURNO");
		
		empleadosMainPanelCL.show(empleadosMainPanel, "BUSCAR");
		_localCL.show(this, "EMPLEADOS TURNO");
		_currentPanel = empleadosMainPanel;
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
		TTurno turno;
		switch (evento){
			case EventosTurno.ANADIR_TURNO_OK:
				mensaje = (String) datos;
				showOutputMsg(anadirOutputArea, anadirOutputLabel, mensaje, true);
				System.out.println("Anadir Turno OK");
				break;
			case EventosTurno.ANADIR_TURNO_KO: {
				mensaje = (String) datos;
				showOutputMsg(anadirOutputArea, anadirOutputLabel, mensaje, false);
				System.out.println("Anadir Turno KO");
				}
				break;
			case EventosTurno.ELIMINAR_TURNO_OK:
				mensaje = (String) datos;
				showOutputMsg(borrarOutputArea, borrarOutputLabel, mensaje, true);
				System.out.println("Eliminar Turno OK");
				break;
			case EventosTurno.ELIMINAR_TURNO_KO:
				mensaje = (String) datos;
				showOutputMsg(borrarOutputArea, borrarOutputLabel, mensaje, false);
				System.out.println("Eliminar Turno KO");
				break;
			case EventosTurno.MOSTRAR_TURNO_OK:
				turno = (TTurno) datos;
				
				mostrarIDText.setText(turno.getId().toString());
				mostrarNombreText.setText(turno.getNombre());
				mostrarHoraInText.setText(turno.getHoraInicio().toString());
				mostrarHoraFinText.setText(turno.getHoraFin().toString());
				mostrarActivoText.setText(Boolean.valueOf(turno.getActivo()).toString());
				
				mostrarTurnoPanelCL.show(mostrarTurnoPanel, "TURNO");
				System.out.println("Mostrar Turno OK");
				break;
			case EventosTurno.MOSTRAR_TURNO_KO:
				mensaje = (String) datos;
				
				showOutputMsg(mostrarErrorArea, mostrarErrorLabel, mensaje, false);
				System.out.println("Mostrar Turno KO");
				break;
			case EventosTurno.DEVOLVER_TURNO_OK:
				turno = (TTurno) datos;
				
				turnoEmpleadoIDText.setText(turno.getId().toString());
				turnoEmpleadoNombreText.setText(turno.getNombre());
				turnoEmpleadoHoraInText.setText(turno.getHoraInicio().toString());
				turnoEmpleadoHoraFinText.setText(turno.getHoraFin().toString());
				turnoEmpleadoActivoText.setText(Boolean.valueOf(turno.getActivo()).toString());
				
				turnoEmpleadoPanelCL.show(turnoEmpleadoPanel, "TURNO");
				System.out.println("Devolver Turno OK");
				break;
			case EventosTurno.DEVOLVER_TURNO_KO:
				mensaje = (String) datos;
				
				showOutputMsg(turnoEmpleadoErrorArea, turnoEmpleadoErrorLabel, mensaje, false);
				System.out.println("Devolver Turno KO");
				break;
			case EventosTurno.LISTAR_TURNOS_OK:
				@SuppressWarnings("unchecked") List<TTurno> listaTurnos = (List<TTurno>) datos;
				
				addPathSeparator();
				createPathButton("MOSTRAR TURNOS");
				mostrarPanel();
				
				for (TTurno t : listaTurnos) {
					mostrarModel.addRow(new Object[]{t.getId().toString(), t.getNombre(), t.getHoraInicio().toString(), t.getHoraFin().toString(), Boolean.valueOf(t.getActivo())});
				}
				System.out.println("Listar Turnos OK");
				break;
			case EventosTurno.LISTAR_TURNOS_KO:
				mensaje = (String) datos;
				
				JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println("Listar Turnos KO");
				break;
			case EventosTurno.LISTAR_EMPLEADOS_OK:
				@SuppressWarnings("unchecked") List<TEmpleado> listaEmpleados = (List<TEmpleado>) datos;

				for (TEmpleado empleado : listaEmpleados) {
					empleadosModel.addRow(new Object[]{empleado.getId(), empleado.getNombre(), empleado.getDni(), empleado.getIdTurno().toString(), Boolean.valueOf(empleado.getActivo())});
				}
				
				empleadosMainPanelCL.show(empleadosMainPanel, "EMPLEADOS");
				System.out.println("Listar Empleados OK");
				break;
			case EventosTurno.LISTAR_EMPLEADOS_KO:
				mensaje = (String) datos;
				//{"ID Empleado", "Nombre", "DNI", "ID Turno", "Activo"}
				showOutputMsg(empleadosErrorArea, empleadosErrorLabel, mensaje, false);
				System.out.println("Listar Empleados KO");
				break;
			case EventosTurno.EDITAR_BUSCAR_TURNO_OK:
				turno = (TTurno) datos;
				
				editarNombreField.setText(turno.getNombre());
				editarHoraInicioField.setText(turno.getHoraInicio().toString());
				editarHoraFinField.setText(turno.getHoraFin().toString());

				editarTurnoPanelCL.show(editarTurnoPanel, "SECOND");
				System.out.println("Editar Buscar Turno OK");
				break;
			case EventosTurno.EDITAR_BUSCAR_TURNO_KO:
				mensaje = (String) datos;
				
				showOutputMsg(editarBuscarErrorArea, editarBuscarErrorLabel, mensaje, false);
				System.out.println("Editar Buscar Turno KO");
				break;
			case EventosTurno.EDITAR_TURNO_OK:
				mensaje = (String) datos;
				
				showOutputMsg(editarOutputArea, editarOutputLabel, mensaje, true);
				System.out.println("Editar Turno OK");
				break;
			case EventosTurno.EDITAR_TURNO_KO:
				mensaje = (String) datos;
				
				showOutputMsg(editarOutputArea, editarOutputLabel, mensaje, false);
				System.out.println("Editar Turno KO");
				break;
		}
	}
}
