package org.uem.dam.GestorFarmacia.view;

import java.util.EventListener;

public interface InteractableView<T extends EventListener> extends ComponentView {

	public void updateListeners(T controller);

}
