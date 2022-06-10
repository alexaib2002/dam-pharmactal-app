package org.uem.dam.GestorFarmacia.view.insertPopup;

import java.awt.event.ActionListener;

import org.uem.dam.GestorFarmacia.view.ComponentView;
import org.uem.dam.GestorFarmacia.view.submenus.DefaultSubmenu;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class AddItemsMedicinePopUp extends DefaultSubmenu implements ComponentView {

	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JTextField txtPrice;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtMedicineID;
	private JTextField txtMass;
	private JSpinner spnStock;
	private JSpinner spnSupplierID;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	
	public AddItemsMedicinePopUp() {
		initComponents();
		
	}


	public void initComponents() {
		setLayout(new MigLayout("", "[][grow,center][grow,center][grow][]", "[][][][][][][][grow]"));
		
		JLabel lblTitle = new JLabel("Insert Item/Medicine");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle, "cell 1 0 2 1");
		
		JLabel lblName = new JLabel("Item Name:");
		add(lblName, "cell 0 1,alignx trailing");
		
		txtName = new JTextField();
		add(txtName, "cell 1 1 2 1,growx");
		txtName.setColumns(10);
		
		JLabel lblStock = new JLabel("Stock:");
		add(lblStock, "cell 3 1,alignx center");
		
		spnStock = new JSpinner();
		spnStock.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		add(spnStock, "cell 4 1");
		
		JLabel lblPrice = new JLabel("Price:");
		add(lblPrice, "cell 0 2,alignx trailing");
		
		txtPrice = new JTextField();
		add(txtPrice, "cell 1 2 2 1,growx");
		txtPrice.setColumns(10);
		
		JLabel lblSupplier = new JLabel("Supplier ID:");
		add(lblSupplier, "cell 3 2,alignx center");
		
		spnSupplierID = new JSpinner();
		spnSupplierID.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		add(spnSupplierID, "cell 4 2");
		
		JLabel lblMedicine = new JLabel("Is this Item a Medicine?");
		lblMedicine.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblMedicine, "cell 1 3 2 1");
		
		rdbtnYes = new JRadioButton("Yes");
		buttonGroup.add(rdbtnYes);
		add(rdbtnYes, "cell 1 4");
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setSelected(true);
		buttonGroup.add(rdbtnNo);
		add(rdbtnNo, "cell 2 4");
		
		JLabel lblMedicineID = new JLabel("Medicine ID:");
		add(lblMedicineID, "cell 0 5,alignx trailing");
		
		txtMedicineID = new JTextField();
		add(txtMedicineID, "cell 1 5,growx");
		txtMedicineID.setColumns(10);
		
		JLabel lblMass = new JLabel("Mass:");
		add(lblMass, "cell 0 6,alignx trailing");
		
		txtMass = new JTextField();
		add(txtMass, "cell 1 6,growx");
		txtMass.setColumns(10);
		
		JLabel lblUnit = new JLabel("Units:");
		add(lblUnit, "cell 2 6,alignx center");
		
		JComboBox<String> cmbxUnits = new JComboBox<String>();
		cmbxUnits.setModel(new DefaultComboBoxModel<String>(new String[] {"mg", "g", "ml", "l"}));
		add(cmbxUnits, "cell 3 6,growx");
		
		JButton btnClearData = new JButton("Clear Data");
		add(btnClearData, "cell 1 7");
		
		JButton btnNewButton_1 = new JButton("Add Item");
		add(btnNewButton_1, "cell 2 7");
		
	}
	

	@Override
	public void updateListeners(ActionListener controller) {
		
	}

}
