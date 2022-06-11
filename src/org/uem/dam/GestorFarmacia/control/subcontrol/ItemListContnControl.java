package org.uem.dam.GestorFarmacia.control.subcontrol;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.uem.dam.GestorFarmacia.model.DBItem;
import org.uem.dam.GestorFarmacia.persist.DBItemMap;
import org.uem.dam.GestorFarmacia.view.submenus.data_view.ItemInspectorContainer;
import org.uem.dam.GestorFarmacia.view.submenus.data_view.ItemListContainer;

public class ItemListContnControl implements ListSelectionListener {

	private final String tableName;
	private final JList<String> list;
	private final DefaultListModel<String> listModel;

	private final DBItemMap itemMap;

	// TODO implement default on class to extend this structure
	public ItemListContnControl(String tableName,
			DBItemMap itemMap,
			ItemListContainer container,
			ItemInspectorContainer dataViewSubmenu) {
		this.tableName = tableName;
		this.list = container.getList();
		this.listModel = container.getListModel();
		this.itemMap = itemMap;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting() && list.getSelectedIndex() > -1) { // don't want input echoes
			DBItem itemSelected = itemMap.get(tableName).get(list.getSelectedIndex());
			// TODO dump data into view
		}
	}

}
