package org.uem.dam.GestorFarmacia.view.submenus.data_view.data_panel;

import org.uem.dam.GestorFarmacia.model.DBItem;

public interface InspectorDataPanel<T extends DBItem> {

	public void refreshData(T dataItem);

}
