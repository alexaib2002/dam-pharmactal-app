package org.uem.dam.GestorFarmacia.view.submenus;

import java.util.EventListener;

import org.uem.dam.GestorFarmacia.view.DefaultComponent;
import org.uem.dam.GestorFarmacia.view.InteractableView;

public abstract class DefaultInteractableSubmenu<T extends EventListener> extends DefaultComponent
		implements InteractableView<T> {

	private static final long serialVersionUID = 1L;

	@Override
	public abstract void initComponents();

	@Override
	public void initAttributes() {
	}

	@Override
	public abstract void updateListeners(T listener);

}
