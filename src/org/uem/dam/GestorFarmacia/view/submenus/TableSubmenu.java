package org.uem.dam.GestorFarmacia.view.submenus;

import javax.swing.JTable;

import org.uem.dam.GestorFarmacia.model.ComponentView;

import net.miginfocom.swing.MigLayout;

public class TableSubmenu extends DefaultSubmenu implements ComponentView {
	private static final long serialVersionUID = 1L;
	private JTable table;

	@Override
	public void initComponents() {
		setLayout(new MigLayout("", "[grow]", "[top][grow,center]"));

		table = new JTable();
		add(table, "cell 0 1,grow");
	}

	@Override
	public void initAttributes() {
		// TODO Auto-generated method stub

	}

}
