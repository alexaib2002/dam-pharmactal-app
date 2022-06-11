package org.uem.dam.GestorFarmacia.view.submenus.data_view.table_perspective;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.uem.dam.GestorFarmacia.model.DBItem;
import org.uem.dam.GestorFarmacia.view.DefaultComponent;
import org.uem.dam.GestorFarmacia.view.submenus.data_view.UpdatableDataContainer;

import net.miginfocom.swing.MigLayout;

public class TableTabContainer extends DefaultComponent implements UpdatableDataContainer {

	private static final long serialVersionUID = 1L;

	private DefaultTableModel tableModel;

	public TableTabContainer(String[] cols) {
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
	public void updateContent(ArrayList<DBItem> data) {
		tableModel.setRowCount(0);
		for (DBItem dbItem : data) {
			tableModel.addRow(dbItem.getAttributes());
		}
	}

}
