package org.uem.dam.GestorFarmacia.view.submenus.data_view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class ItemDataPanel extends JPanel {

	private JTextField aidTxt;
	private JTextField textField;
	private JLabel lblPrice;
	private JSpinner priceSpn;
	private JLabel lblStock;
	private JSpinner stockSpn;

	// FIXME placeholder
	public ItemDataPanel() {
		setLayout(new MigLayout("", "[right][grow]", "[][][][]"));

		JLabel aidLbl = new JLabel("Article ID");
		this.add(aidLbl, "flowy,cell 0 0,alignx left,aligny center");

		aidTxt = new JTextField();
		this.add(aidTxt, "cell 1 0,growx,aligny top");
		aidTxt.setColumns(10);

		JLabel nameLbl = new JLabel("Name");
		this.add(nameLbl, "cell 0 1");

		textField = new JTextField();
		this.add(textField, "cell 1 1,growx");
		textField.setColumns(10);

		lblPrice = new JLabel("Price");
		add(lblPrice, "cell 0 2,alignx trailing");

		priceSpn = new JSpinner();
		add(priceSpn, "cell 1 2");

		lblStock = new JLabel("Stock");
		add(lblStock, "cell 0 3");

		stockSpn = new JSpinner();
		add(stockSpn, "cell 1 3");
	}

}
