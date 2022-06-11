package org.uem.dam.GestorFarmacia.view;

import java.util.EventListener;

public interface InteractableView<T extends EventListener> extends BuildableView {

	public void updateListeners(T controller);

}
