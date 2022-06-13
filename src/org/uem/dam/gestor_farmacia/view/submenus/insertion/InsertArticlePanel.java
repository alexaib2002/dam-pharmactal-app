package org.uem.dam.gestor_farmacia.view.submenus.insertion;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import org.uem.dam.gestor_farmacia.control.subcontrol.InsertArticleControl;
import org.uem.dam.gestor_farmacia.model.Article;
import org.uem.dam.gestor_farmacia.model.DBItem;
import org.uem.dam.gestor_farmacia.model.Medicine;
import org.uem.dam.gestor_farmacia.utils.WindowActionUtils;
import org.uem.dam.gestor_farmacia.view.submenus.DefaultInteractableSubmenu;

import net.miginfocom.swing.MigLayout;

public class InsertArticlePanel extends DefaultInteractableSubmenu<InsertArticleControl>
		implements FetchableSubmenu<DBItem> {
	public InsertArticlePanel() {
	}

	public static final String ACTION_YES = "Yes";
	public static final String ACTION_NO = "No";
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
	private JPanel medInfoPanel;

	@SuppressWarnings("removal")
	@Override
	public void initComponents() {
		setLayout(new MigLayout("", "[grow][][][][][][grow]", "[][5%][][][][][grow][grow 50,center][5%:n,grow]"));

		JLabel lblTitle = new JLabel("Add new item");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle, "cell 1 0 5 2,alignx center");

		JLabel lblName = new JLabel("Item Name:");
		add(lblName, "cell 1 2,alignx trailing");

		txtName = new JTextField();
		add(txtName, "cell 2 2 2 1,growx");
		txtName.setColumns(10);

		JLabel lblStock = new JLabel("Stock:");
		add(lblStock, "cell 4 2,alignx center");

		spnStock = new JSpinner();
		spnStock.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		add(spnStock, "cell 5 2");

		JLabel lblPrice = new JLabel("Price:");
		add(lblPrice, "cell 1 3,alignx trailing");

		txtPrice = new JTextField();
		add(txtPrice, "cell 2 3 2 1,growx");
		txtPrice.setColumns(10);

		JLabel lblSupplier = new JLabel("Supplier ID:");
		add(lblSupplier, "cell 4 3,alignx center");

		spnSupplierID = new JSpinner();
		spnSupplierID.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		add(spnSupplierID, "cell 5 3");

		JLabel lblMedicine = new JLabel("Is this item a medicine?");
		lblMedicine.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblMedicine, "cell 1 4 5 1,alignx center");

		buttonGroup = new ButtonGroup();

		rdbtnYes = new JRadioButton(ACTION_YES);
		buttonGroup.add(rdbtnYes);
		add(rdbtnYes, "cell 2 5,alignx right");

		rdbtnNo = new JRadioButton(ACTION_NO);
		rdbtnNo.setSelected(true);
		buttonGroup.add(rdbtnNo);
		add(rdbtnNo, "cell 4 5");

		medInfoPanel = new JPanel();
		add(medInfoPanel, "cell 1 6 5 1,grow");
		medInfoPanel.setLayout(new MigLayout("", "[grow][grow][]", "[][][grow]"));

		JLabel lblMedicineID = new JLabel("Medicine ID:");
		medInfoPanel.add(lblMedicineID, "cell 0 0,alignx right");

		txtMedicineID = new JTextField();
		medInfoPanel.add(txtMedicineID, "cell 1 0,growx");
		txtMedicineID.setColumns(10);

		JLabel lblMass = new JLabel("Mass:");
		medInfoPanel.add(lblMass, "cell 0 1,alignx right");

		txtMass = new JTextField();
		medInfoPanel.add(txtMass, "cell 1 1,growx");
		txtMass.setColumns(10);

		cmbxUnits = new JComboBox<String>();
		medInfoPanel.add(cmbxUnits, "cell 2 1");
		cmbxUnits.setModel(
				new DefaultComboBoxModel<String>(
						new String[] {
								"mg",
								"g",
								"ml",
								"l" }
				)
		);

		chkbxPresc = new JCheckBox("This medicine should require prescription");
		medInfoPanel.add(chkbxPresc, "cell 0 2 3 1,alignx center,aligny center");

		btnAddItem = new JButton(ACTION_ADD);
		add(btnAddItem, "cell 1 7 2 1,growx");

		btnClearData = new JButton(ACTION_CLEAR);
		add(btnClearData, "cell 3 7 3 1,growx");
	}

	@Override
	public void initAttributes() {
		rdbtnNo.setSelected(true);
		setVisibleMedElements(false);
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
			if (txtName.getText().isBlank() || txtName.getText().isEmpty()) {
				throw new NullPointerException();
			}
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
					String.format("Cannot convert numeric field data to number. Cause: \n %s", nfe.getMessage()),
					JOptionPane.ERROR_MESSAGE
			);
		}
		return item;
	}

	public void setVisibleMedElements(boolean value) {
		medInfoPanel.setVisible(value);
		this.revalidate();
	}

	@Override
	public void clearFields() {
		txtName.setText("");
		txtPrice.setText("");
		txtMedicineID.setText("");
		txtMass.setText("");
		spnStock.setValue(0);
		spnSupplierID.setValue(0);
		rdbtnYes.setSelected(false);
		rdbtnNo.setSelected(true);
		chkbxPresc.setSelected(false);
		cmbxUnits.setSelectedIndex(0);
	}
}
