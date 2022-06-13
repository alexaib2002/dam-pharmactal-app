package org.uem.dam.gestor_farmacia.control.subcontrol;

import org.uem.dam.gestor_farmacia.model.DBItem;

public interface ItemGenerator<T extends DBItem> {

	public void insertItem(T item);

}
