package org.uem.dam.GestorFarmacia.control.listener;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.uem.dam.GestorFarmacia.model.DBItem;
import org.uem.dam.GestorFarmacia.persist.DBItemMap;
import org.uem.dam.GestorFarmacia.view.submenus.data_view.data_panel.InspectorDataPanel;

public class ItemListContnListener<T extends InspectorDataPanel<DBItem>> implements ListSelectionListener {

	private final String tableName;
	private final JList<String> list;
	private final T inspectorPanel;

	private final DBItemMap itemMap;

	public ItemListContnListener(String tableName,
			DBItemMap itemMap,
			JList<String> list,
			T panel) {
		this.tableName = tableName;
		this.list = list;
		this.itemMap = itemMap;
		this.inspectorPanel = panel;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting() && list.getSelectedIndex() > -1) { // don't want input echoes
			DBItem itemSelected = itemMap.get(tableName).get(list.getSelectedIndex());
			inspectorPanel.refreshData(itemSelected);
		}
	}

}
