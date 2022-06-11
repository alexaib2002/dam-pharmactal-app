package org.uem.dam.GestorFarmacia.view.welcome;

import java.awt.event.ActionListener;
import org.uem.dam.GestorFarmacia.view.ComponentView;
import org.uem.dam.GestorFarmacia.view.submenus.DefaultSubmenu;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import net.miginfocom.swing.MigLayout;

public class WelcomePage extends DefaultSubmenu<ActionListener> implements ComponentView {
	public WelcomePage() {
		initComponents();
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void initComponents() {
		setLayout(new MigLayout("", "[163px,grow,center]", "[][grow]"));
		
		JLabel lblWelcome = new JLabel("¡Bienvenido Usuario!");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblWelcome, "cell 0 0");
		
		
		
	}

	@Override
	public void initAttributes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateListeners(ActionListener controller) {
		// TODO Auto-generated method stub
		
	}
}
