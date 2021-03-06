package org.uem.dam.gestor_farmacia.view.submenus.data_view.list_perspective.data_panel;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.uem.dam.gestor_farmacia.model.Provider;

import net.miginfocom.swing.MigLayout;

public class ProvidersDataPanel extends UpdateDataDefaultPanel implements RefreshableDataPanel<Provider> {

	private static final long serialVersionUID = 1L;

	private JTextField nameTxt;
	private JSpinner pidSpn;
	private JTextField phoneTxt;
	private JTextField addrTxt;

	public ProvidersDataPanel() {
		super(false);
	}

	public ProvidersDataPanel(boolean nested) {
		super(nested);
	}

	@Override
	public void initComponents() {
		super.initComponents();
		setBorder(new TitledBorder(null, "Provider", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		editPanel.setLayout(new MigLayout("", "[right][grow]", "[][][][]"));

		JLabel pidLbl = new JLabel("Provider ID");
		editPanel.add(pidLbl, "flowy,cell 0 0,alignx right,aligny center");

		pidSpn = new JSpinner();
		editPanel.add(pidSpn, "cell 1 0");

		JLabel nameLbl = new JLabel("Name");
		editPanel.add(nameLbl, "cell 0 1");

		nameTxt = new JTextField();
		editPanel.add(nameTxt, "cell 1 1,growx");
		nameTxt.setColumns(10);

		JLabel priceLbl = new JLabel("Phone");
		editPanel.add(priceLbl, "cell 0 2,alignx trailing");

		phoneTxt = new JTextField();
		editPanel.add(phoneTxt, "cell 1 2,growx");
		phoneTxt.setColumns(10);

		JLabel stockLbl = new JLabel("Address");
		editPanel.add(stockLbl, "cell 0 3,alignx trailing");

		addrTxt = new JTextField();
		editPanel.add(addrTxt, "cell 1 3,growx");
		addrTxt.setColumns(10);
	}

	@Override
	public void refreshData(Provider provider) {
		nameTxt.setText(provider.name());
		pidSpn.setValue(provider.providerId());
		phoneTxt.setText(provider.phone());
		addrTxt.setText(provider.address());
	}

	@Override
	public Provider getInputItem() {
		return new Provider((int) pidSpn.getValue(), nameTxt.getText(), phoneTxt.getText(), addrTxt.getText());
	}

	@Override
	public void setEditsEnabled(boolean enabled) {
		pidSpn.setEnabled(enabled);
		removeBtn.setEnabled(enabled);
	}

}
