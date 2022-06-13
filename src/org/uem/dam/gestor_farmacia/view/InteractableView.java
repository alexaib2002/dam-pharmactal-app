package org.uem.dam.gestor_farmacia.view;

import java.util.EventListener;

public interface InteractableView<T extends EventListener> extends BuildableView {

	public void updateListeners(T controller);

}
