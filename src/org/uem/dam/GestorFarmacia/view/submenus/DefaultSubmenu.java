package org.uem.dam.GestorFarmacia.view.submenus;

import java.awt.event.ActionListener;
import java.util.EventListener;

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
	public abstract void updateListeners(ActionListener controller);

}
