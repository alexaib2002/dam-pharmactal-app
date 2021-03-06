package org.uem.dam.gestor_farmacia.view.submenus;

import java.util.EventListener;

import org.uem.dam.gestor_farmacia.view.DefaultComponent;
import org.uem.dam.gestor_farmacia.view.InteractableView;

public abstract class DefaultInteractableSubmenu<T extends EventListener> extends DefaultComponent
		implements InteractableView<T> {

	private static final long serialVersionUID = 1L;

	@Override
	public abstract void initComponents();

	@Override
	public abstract void updateListeners(T listener);

}
