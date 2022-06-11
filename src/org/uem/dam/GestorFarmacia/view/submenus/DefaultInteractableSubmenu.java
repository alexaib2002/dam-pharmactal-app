package org.uem.dam.GestorFarmacia.view.submenus;

import java.util.EventListener;

import javax.swing.JPanel;

import org.uem.dam.GestorFarmacia.view.InteractableView;

public abstract class DefaultInteractableSubmenu<T extends EventListener> extends JPanel
		implements InteractableView<T> {

	private static final long serialVersionUID = 1L;

	public DefaultInteractableSubmenu() {
		initComponents();
		initAttributes();
	}

	@Override
	public abstract void initComponents();

	@Override
	public void initAttributes() {
	}

	@Override
	public abstract void updateListeners(T listener);

}
