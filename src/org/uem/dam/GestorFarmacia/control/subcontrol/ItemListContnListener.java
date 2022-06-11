package org.uem.dam.GestorFarmacia.control.subcontrol;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.model.DBItem;
import org.uem.dam.GestorFarmacia.persist.DBItemMap;
import org.uem.dam.GestorFarmacia.view.submenus.data_view.list_perspective.data_panel.RefreshableDataPanel;

public class ItemListContnListener<T extends RefreshableDataPanel<DBItem>> extends InteractableControl
		implements ListSelectionListener {

	private final String tableName;
	private final JList<String> list;
	private final T inspectorPanel;

	public ItemListContnListener(MainController mainController,
			String tableName,
			DBItemMap dbItemMap,
			JList<String> list,
			T panel) {
		super(mainController);
		this.tableName = tableName;
		this.list = list;
		this.inspectorPanel = panel;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting() && list.getSelectedIndex() > -1) { // don't want input echoes
			DBItem itemSelected = dbItemMap.get(tableName).get(list.getSelectedIndex());
			inspectorPanel.refreshData(itemSelected);
		}
	}

}
