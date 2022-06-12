package org.uem.dam.GestorFarmacia.view.submenus.insertion;

import org.uem.dam.GestorFarmacia.model.DBItem;

public interface FetchableSubmenu<T extends DBItem> {

	public T getInputItem();

	public void clearFields();
}
