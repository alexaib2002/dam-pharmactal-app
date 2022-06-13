package org.uem.dam.gestor_farmacia.view.submenus.data_view.list_perspective.data_panel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;

import org.uem.dam.gestor_farmacia.model.Medicine;

import net.miginfocom.swing.MigLayout;

public class MedDataPanel extends UpdateDataDefaultPanel implements RefreshableDataPanel<Medicine> {

	public static final String ACTION_UPDATE = "Update Medicine";

	private static final long serialVersionUID = 1L;

	private JSpinner midSpn;
	private JSpinner massSpn;
	private JCheckBox chckbxNewCheckBox;
	private JComboBox<String> unitCmbx;
	private ArticleDataPanel articlePanel;

	@Override
	public void initComponents() {
		super.initComponents();

		setBorder(new TitledBorder(null, "Medicine", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		editPanel.setLayout(new MigLayout("", "[right][grow]", "[][][][]"));

		JLabel aidLbl = new JLabel("Medicine ID");
		editPanel.add(aidLbl, "flowy,cell 0 0,alignx right,aligny center");

		midSpn = new JSpinner();
		editPanel.add(midSpn, "cell 1 0");

		JLabel nameLbl = new JLabel("Mass");
		editPanel.add(nameLbl, "cell 0 1,alignx trailing");

		massSpn = new JSpinner();
		editPanel.add(massSpn, "flowx,cell 1 1,alignx center");

		unitCmbx = new JComboBox<String>();
		unitCmbx.setModel(
				new DefaultComboBoxModel<String>(
						new String[] {
								"g",
								"mg",
								"l",
								"ml" }
				)
		);
		editPanel.add(unitCmbx, "cell 2 1,growx");

		chckbxNewCheckBox = new JCheckBox("Requires prescription");
		editPanel.add(chckbxNewCheckBox, "cell 0 2 3 1,alignx center");

		articlePanel = new ArticleDataPanel();
		editPanel.add(articlePanel, "cell 0 3 3 1,grow");
	}

	@Override
	public void initAttributes() {
		updateBtn.setText(ACTION_UPDATE);
	}

	@Override
	public void refreshData(Medicine medicine) {
		midSpn.setValue(medicine.medId());
		massSpn.setValue(medicine.mass());
		chckbxNewCheckBox.setSelected(medicine.requiresPresc());
		unitCmbx.setSelectedItem(medicine.unit());
		articlePanel.refreshData(medicine.article());
	}

	@Override
	public Medicine getInputItem() {
		return new Medicine(
				null, // FIXME fetch article
				(int) midSpn.getValue(),
				(int) massSpn.getValue(),
				(String) unitCmbx.getSelectedItem(),
				chckbxNewCheckBox.isSelected()
		);
	}

	@Override
	public void setEditsEnabled(boolean enabled) {
		midSpn.setEnabled(enabled);
		massSpn.setEnabled(enabled);
		chckbxNewCheckBox.setEnabled(enabled);
		unitCmbx.setEnabled(enabled);
		articlePanel.setEditsEnabled(enabled);
		updateBtn.setEnabled(enabled);
	}

}
