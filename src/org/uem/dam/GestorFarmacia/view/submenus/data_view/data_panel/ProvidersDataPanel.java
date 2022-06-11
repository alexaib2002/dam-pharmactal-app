package org.uem.dam.GestorFarmacia.view.submenus.data_view.data_panel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.uem.dam.GestorFarmacia.model.Provider;
import org.uem.dam.GestorFarmacia.view.DefaultComponent;

import net.miginfocom.swing.MigLayout;

public class ProvidersDataPanel extends DefaultComponent implements InspectorDataPanel<Provider> {

	private static final long serialVersionUID = 1L;

	private JTextField nameTxt;
	private JLabel priceLbl;
	private JLabel stockLbl;
	private JSpinner pidSpn;
	private JTextField phoneTxt;
	private JTextField addrTxt;

	@Override
	public void initComponents() {
		setBorder(
				new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Providers", TitledBorder.LEADING,
						TitledBorder.TOP, null, new Color(51, 51, 51)));
		setLayout(new MigLayout("", "[right][grow]", "[][][][]"));

		JLabel pidLbl = new JLabel("Provider ID");
		this.add(pidLbl, "flowy,cell 0 0,alignx right,aligny center");

		pidSpn = new JSpinner();
		add(pidSpn, "cell 1 0");

		JLabel nameLbl = new JLabel("Name");
		this.add(nameLbl, "cell 0 1");

		nameTxt = new JTextField();
		this.add(nameTxt, "cell 1 1,growx");
		nameTxt.setColumns(10);

		priceLbl = new JLabel("Phone");
		add(priceLbl, "cell 0 2,alignx trailing");

		phoneTxt = new JTextField();
		add(phoneTxt, "cell 1 2,growx");
		phoneTxt.setColumns(10);

		stockLbl = new JLabel("Address");
		add(stockLbl, "cell 0 3,alignx trailing");

		addrTxt = new JTextField();
		add(addrTxt, "cell 1 3,growx");
		addrTxt.setColumns(10);
	}

	@Override
	public void refreshData(Provider dataItem) {
		// TODO
		nameTxt.setText(dataItem.name());
	}

}
