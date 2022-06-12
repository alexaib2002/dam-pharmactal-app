package org.uem.dam.GestorFarmacia.control.subcontrol;

import org.uem.dam.GestorFarmacia.model.DBItem;

public interface ItemGenerator<T extends DBItem> {

	public void insertItem(T item);

}
