package org.uem.dam.GestorFarmacia.view.submenus.data_view.data_panel;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.uem.dam.GestorFarmacia.model.Medicine;
import org.uem.dam.GestorFarmacia.view.DefaultComponent;

import net.miginfocom.swing.MigLayout;

public class MedDataPanel extends DefaultComponent implements InspectorDataPanel<Medicine> {

	private static final long serialVersionUID = 1L;

	@Override
	public void initComponents() {
		setBorder(
				new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Medicine", TitledBorder.LEADING,
						TitledBorder.TOP, null, new Color(51, 51, 51)));
		setLayout(new MigLayout("", "[right][grow,fill][]", "[][][]"));

		JLabel aidLbl = new JLabel("Medicine ID");
		this.add(aidLbl, "flowy,cell 0 0,alignx right,aligny center");

		JSpinner midSpn = new JSpinner();
		add(midSpn, "cell 1 0");

		JLabel nameLbl = new JLabel("Mass");
		this.add(nameLbl, "cell 0 1,alignx trailing");

		JSpinner massSpn = new JSpinner();
		add(massSpn, "flowx,cell 1 1,alignx center");

		JComboBox<String> unitCmbx = new JComboBox<String>();
		unitCmbx.setModel(
				new DefaultComboBoxModel<String>(new String[] {
						"g",
						"mg",
						"l",
						"ml" }));
		add(unitCmbx, "cell 2 1,growx");

		JCheckBox chckbxNewCheckBox = new JCheckBox("Requires prescription");
		add(chckbxNewCheckBox, "cell 0 2 3 1,alignx center");
	}

	@Override
	public void refreshData(Medicine medicine) {
		// TODO Auto-generated method stub

	}

}
