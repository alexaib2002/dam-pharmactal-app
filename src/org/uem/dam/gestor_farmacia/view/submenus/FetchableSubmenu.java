package org.uem.dam.gestor_farmacia.view.submenus;

import org.uem.dam.gestor_farmacia.model.DBItem;

public interface FetchableSubmenu<T extends DBItem> {

	public T getInputItem();
}
