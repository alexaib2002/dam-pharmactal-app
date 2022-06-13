package org.uem.dam.gestor_farmacia.view.submenus.data_view.list_perspective.data_panel;

import org.uem.dam.gestor_farmacia.model.DBItem;

public interface RefreshableDataPanel<T extends DBItem> {

	public void refreshData(T dataItem);

	public void setEditsEnabled(boolean value);

}
