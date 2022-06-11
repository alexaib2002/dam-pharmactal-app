package org.uem.dam.GestorFarmacia.view.submenus.data_view.list_perspective;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.uem.dam.GestorFarmacia.control.subcontrol.ItemListContnListener;
import org.uem.dam.GestorFarmacia.model.DBItem;
import org.uem.dam.GestorFarmacia.model.RootItem;
import org.uem.dam.GestorFarmacia.view.submenus.DefaultInteractableSubmenu;
import org.uem.dam.GestorFarmacia.view.submenus.data_view.UpdatableDataContainer;

import net.miginfocom.swing.MigLayout;

public class ItemListContainer extends DefaultInteractableSubmenu<ItemListContnListener> implements UpdatableDataContainer {

	private static final long serialVersionUID = 1L;

	private JList<String> list;
	private DefaultListModel<String> listModel;

	@Override
	public void initComponents() {
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0,grow");

		list = new JList<String>();
		listModel = new DefaultListModel<String>();
		list.setModel(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);

	}

	@Override
	public void updateListeners(ItemListContnListener controller) {
		list.addListSelectionListener(controller);
	}

	@Override
	public void updateContent(ArrayList<DBItem> data) {
		list.clearSelection();
		listModel.setSize(0);
		for (DBItem dbItem : data) {
			if (dbItem instanceof RootItem) {
				listModel.addElement(((RootItem) dbItem).name());
			}
		}
		if (data.size() > 0) {
			list.setSelectedIndex(0);
		}
	}

	public JList<String> getList() {
		return list;
	}

	public DefaultListModel<String> getListModel() {
		return listModel;
	}
}
