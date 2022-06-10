package org.uem.dam.GestorFarmacia.view.submenus.data_view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.uem.dam.GestorFarmacia.model.DBItem;
import org.uem.dam.GestorFarmacia.view.ComponentView;
import org.uem.dam.GestorFarmacia.view.submenus.DefaultSubmenu;

import net.miginfocom.swing.MigLayout;

public class TableSubmenu extends DefaultSubmenu implements ComponentView {

	private static final long serialVersionUID = 1L;

	private DefaultTableModel tableModel;

	public TableSubmenu(String[] cols) {
		for (String col : cols) {
			tableModel.addColumn(col);
		}
	}

	@Override
	public void initComponents() {
		setLayout(new MigLayout("", "[grow]", "[grow,center]"));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0,grow");

		JTable table = new JTable();
		tableModel = new DefaultTableModel();
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
	}

	@Override
	public void updateListeners(ActionListener controller) {
		// TODO Auto-generated method stub

	}
	
	public void updateTable(ArrayList<DBItem> dbItemArray) {
		tableModel.setRowCount(0);
		for (DBItem dbItem : dbItemArray) {
			tableModel.addRow(dbItem.getAttributes());
		}
	}

}
