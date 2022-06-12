package org.uem.dam.GestorFarmacia.view.submenus.insertion;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import org.uem.dam.GestorFarmacia.control.subcontrol.InsertArticleControl;
import org.uem.dam.GestorFarmacia.model.Article;
import org.uem.dam.GestorFarmacia.model.DBItem;
import org.uem.dam.GestorFarmacia.model.Medicine;
import org.uem.dam.GestorFarmacia.utils.WindowActionUtils;
import org.uem.dam.GestorFarmacia.view.submenus.DefaultInteractableSubmenu;

import net.miginfocom.swing.MigLayout;

public class InsertArticlePanel extends DefaultInteractableSubmenu<InsertArticleControl> implements FetchableSubmenu<DBItem> {

	public static final String ACTION_ADD = "Add Item";
	public static final String ACTION_CLEAR = "Clear Data";

	private static final long serialVersionUID = 1L;

	private JTextField txtName;
	private JTextField txtPrice;
	private ButtonGroup buttonGroup;
	private JTextField txtMedicineID;
	private JTextField txtMass;
	private JSpinner spnStock;
	private JSpinner spnSupplierID;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JButton btnClearData;
	private JButton btnAddItem;
	private JCheckBox chkbxPresc;
	private JComboBox<String> cmbxUnits;

	@SuppressWarnings("removal")
	@Override
	public void initComponents() {
		setLayout(new MigLayout("", "[grow][][][][][][][grow]", "[][5%][][][][][][][10%,center][25%:n,center]"));

		JLabel lblTitle = new JLabel("Add new item");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle, "cell 1 0 6 2,alignx center");

		JLabel lblName = new JLabel("Item Name:");
		add(lblName, "cell 1 2,alignx trailing");

		txtName = new JTextField();
		add(txtName, "cell 2 2 3 1,growx");
		txtName.setColumns(10);

		JLabel lblStock = new JLabel("Stock:");
		add(lblStock, "cell 5 2,alignx center");

		spnStock = new JSpinner();
		spnStock.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		add(spnStock, "cell 6 2");

		JLabel lblPrice = new JLabel("Price:");
		add(lblPrice, "cell 1 3,alignx trailing");

		txtPrice = new JTextField();
		add(txtPrice, "cell 2 3 3 1,growx");
		txtPrice.setColumns(10);

		JLabel lblSupplier = new JLabel("Supplier ID:");
		add(lblSupplier, "cell 5 3,alignx center");

		spnSupplierID = new JSpinner();
		spnSupplierID.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		add(spnSupplierID, "cell 6 3");

		JLabel lblMedicine = new JLabel("Is this item a medicine?");
		lblMedicine.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblMedicine, "cell 1 4 5 1,alignx center");

		buttonGroup = new ButtonGroup();

		rdbtnYes = new JRadioButton("Yes");
		buttonGroup.add(rdbtnYes);
		add(rdbtnYes, "cell 2 5");

		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setSelected(true);
		buttonGroup.add(rdbtnNo);
		add(rdbtnNo, "cell 4 5");

		JLabel lblMedicineID = new JLabel("Medicine ID:");
		add(lblMedicineID, "cell 1 6,alignx trailing");

		txtMedicineID = new JTextField();
		add(txtMedicineID, "cell 2 6 4 1,growx");
		txtMedicineID.setColumns(10);

		JLabel lblMass = new JLabel("Mass:");
		add(lblMass, "cell 1 7,alignx trailing");

		txtMass = new JTextField();
		add(txtMass, "cell 2 7 2 1,growx");
		txtMass.setColumns(10);

		cmbxUnits = new JComboBox<String>();
		cmbxUnits.setModel(
				new DefaultComboBoxModel<String>(
						new String[] {
								"mg",
								"g",
								"ml",
						"l" }
						)
				);
		add(cmbxUnits, "cell 4 7 2 1,growx");

		chkbxPresc = new JCheckBox("This medicine should require prescription");
		add(chkbxPresc, "cell 1 8 6 1,alignx center,aligny center");

		btnAddItem = new JButton(ACTION_ADD);
		add(btnAddItem, "cell 1 9 2 1,growx");

		btnClearData = new JButton(ACTION_CLEAR);
		add(btnClearData, "cell 4 9 3 1,growx");
	}

	@Override
	public void initAttributes() {
		rdbtnNo.setSelected(true);
	}

	@Override
	public void updateListeners(InsertArticleControl controller) {
		btnAddItem.addActionListener(controller);
		btnClearData.addActionListener(controller);
		rdbtnNo.addActionListener(controller);
		rdbtnYes.addActionListener(controller);
	}

	@Override
	public DBItem getInputItem() {
		Article item = null;
		try {
			item = new Article(
					-1,
					(int) spnSupplierID.getValue(),
					txtName.getText(),
					Double.parseDouble(txtPrice.getText()),
					(int) spnStock.getValue()
					);
			if (rdbtnYes.isSelected()) {
				return new Medicine(
						item,
						Integer.parseInt(txtMedicineID.getText()),
						Integer.parseInt(txtMass.getText()),
						(String) cmbxUnits.getSelectedItem(),
						chkbxPresc.isSelected()
						);
			}
		} catch (NullPointerException npe) {
			WindowActionUtils.promptInfoDialog(new JWindow(), "Please fill every field", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException nfe) {
			WindowActionUtils.promptInfoDialog(
					new JWindow(),
					"The %s field should be a number without any letter",
					JOptionPane.ERROR_MESSAGE
					);
		}
		return item;
	}

	@Override
	public void clearFields() {
		txtName.setText("");
		txtPrice.setText("");
		txtMedicineID.setText("");
		txtMass.setText("");
		spnStock.setValue(0);
		spnSupplierID.setValue(0);
		rdbtnYes.setSelected(true);
		chkbxPresc.setSelected(false);
		cmbxUnits.setSelectedIndex(0);
	}
}
