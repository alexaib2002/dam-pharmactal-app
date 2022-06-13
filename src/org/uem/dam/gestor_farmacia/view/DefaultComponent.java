package org.uem.dam.gestor_farmacia.view;

import javax.swing.JPanel;

public abstract class DefaultComponent extends JPanel implements BuildableView {

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
