package org.uem.dam.GestorFarmacia.view.submenus;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import org.uem.dam.GestorFarmacia.view.ComponentView;

public abstract class DefaultSubmenu extends JPanel implements ComponentView {

	private static final long serialVersionUID = 1L;

	public DefaultSubmenu() {
		initComponents();
		initAttributes();
	}

	@Override
	public void initComponents() {
	}

	@Override
	public void initAttributes() {
	}

	@Override
	public void updateListeners(ActionListener controller) {
		System.err.println(String.format("%s is not implementing updateListeners() method", 
				this.getClass().toString()));
	}

}
