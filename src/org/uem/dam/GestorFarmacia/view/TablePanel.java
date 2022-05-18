package org.uem.dam.GestorFarmacia.view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;

import org.uem.dam.GestorFarmacia.model.ComponentView;

public class TablePanel extends JPanel implements ComponentView {
	private JTable table;
	public TablePanel() {
		initComponents();
	}
	
	@Override
	public void initComponents() {
		setLayout(new MigLayout("", "[grow]", "[top][grow,center]"));
		
		table = new JTable();
		add(table, "cell 0 1,grow");
	}

}
