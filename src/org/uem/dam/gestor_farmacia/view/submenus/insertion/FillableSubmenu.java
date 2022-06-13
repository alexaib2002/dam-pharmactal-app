package org.uem.dam.gestor_farmacia.view.submenus.insertion;

import org.uem.dam.gestor_farmacia.model.DBItem;
import org.uem.dam.gestor_farmacia.view.submenus.FetchableSubmenu;

public interface FillableSubmenu<T extends DBItem> extends FetchableSubmenu<DBItem> {

	@Override
	public T getInputItem();

	public void clearFields();

}
