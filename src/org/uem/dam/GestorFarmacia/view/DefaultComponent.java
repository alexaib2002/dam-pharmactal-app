package org.uem.dam.GestorFarmacia.view;

import javax.swing.JPanel;

public abstract class DefaultComponent extends JPanel implements ComponentView {

	private static final long serialVersionUID = 1L;

	public DefaultComponent() {
		initComponents();
		initAttributes();
	}

	@Override
	public abstract void initComponents();

	@Override
	public void initAttributes() {
	}

}
