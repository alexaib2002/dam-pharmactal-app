package org.uem.dam.GestorFarmacia.view.submenus.data_view.list_perspective.data_panel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;

import org.uem.dam.GestorFarmacia.model.Medicine;
import org.uem.dam.GestorFarmacia.view.DefaultComponent;

import net.miginfocom.swing.MigLayout;

public class MedDataPanel extends DefaultComponent implements RefreshableDataPanel<Medicine> {

	private static final long serialVersionUID = 1L;

	private JSpinner midSpn;
	private JSpinner massSpn;
	private JCheckBox chckbxNewCheckBox;
	private JComboBox<String> unitCmbx;
	private ArticleDataPanel articlePanel;

	@Override
	public void initComponents() {
		setBorder(new TitledBorder(null, "Medicine", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[right][grow,fill][]", "[][][][]"));

		JLabel aidLbl = new JLabel("Medicine ID");
		this.add(aidLbl, "flowy,cell 0 0,alignx right,aligny center");

		midSpn = new JSpinner();
		add(midSpn, "cell 1 0");

		JLabel nameLbl = new JLabel("Mass");
		this.add(nameLbl, "cell 0 1,alignx trailing");

		massSpn = new JSpinner();
		add(massSpn, "flowx,cell 1 1,alignx center");

		unitCmbx = new JComboBox<String>();
		unitCmbx.setModel(
				new DefaultComboBoxModel<String>(new String[] {
						"g",
						"mg",
						"l",
						"ml" }));
		add(unitCmbx, "cell 2 1,growx");

		chckbxNewCheckBox = new JCheckBox("Requires prescription");
		add(chckbxNewCheckBox, "cell 0 2 3 1,alignx center");

		articlePanel = new ArticleDataPanel();
		add(articlePanel, "cell 0 3 3 1,grow");
	}

	@Override
	public void refreshData(Medicine medicine) {
		midSpn.setValue(medicine.medId());
		massSpn.setValue(medicine.mass());
		chckbxNewCheckBox.setSelected(medicine.requiresPresc());
		unitCmbx.setSelectedItem(medicine.unit());

	}

}
