package presentacion.factoria;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import presentacion.*;

public class FactoriaPresentacionImpl extends FactoriaPresentacion {
	
	JPanel pathPanel;

	public MainGUI generarMainGUI() {
		return new MainGUI();
	}
	
	public JPanel generarPath() {
		if (pathPanel == null) {
			pathPanel = new JPanel();
			pathPanel.setLayout(new BoxLayout(pathPanel, BoxLayout.X_AXIS));
			pathPanel.setBackground(new Color(255, 255, 255));
			pathPanel.setMaximumSize(new Dimension(1024, 110));
		}
		return pathPanel;
	}

}