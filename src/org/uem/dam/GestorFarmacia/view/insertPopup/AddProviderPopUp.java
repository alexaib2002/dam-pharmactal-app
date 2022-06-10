package org.uem.dam.GestorFarmacia.view.insertPopup;

import java.awt.event.ActionListener;
import org.uem.dam.GestorFarmacia.view.ComponentView;
import org.uem.dam.GestorFarmacia.view.submenus.DefaultSubmenu;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddProviderPopUp extends DefaultSubmenu implements ComponentView {
	
	private static final long serialVersionUID = 1L;
	private JTextField txtPhone;
	private JTextField txtName;
	private JTextField txtAddress;
	private JButton btnAdd;
	private JButton btnClean;
	
	public AddProviderPopUp() {
		initComponents();
	}

	public void initComponents() {
		
		setLayout(new MigLayout("", "[124.00,grow][556.00,grow][556.00,grow]", "[][][][][grow]"));
		
		JLabel lblTitle = new JLabel("Insert a new provider");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblTitle, "cell 1 0 2 1,alignx center,aligny top");
		
		JLabel lblProvName = new JLabel("Provider Name:");
		add(lblProvName, "cell 0 1,alignx trailing");
		
		txtName = new JTextField();
		add(txtName, "cell 1 1 2 1,growx");
		txtName.setColumns(10);
		
		JLabel lblProvPhone = new JLabel("Phone:");
		add(lblProvPhone, "cell 0 2,alignx trailing");
		
		txtPhone = new JTextField();
		add(txtPhone, "cell 1 2 2 1,growx");
		txtPhone.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		add(lblAddress, "cell 0 3,alignx trailing");
		
		txtAddress = new JTextField();
		add(txtAddress, "cell 1 3 2 1,growx");
		txtAddress.setColumns(10);
		
		btnClean = new JButton("Clean Data");
		add(btnClean, "cell 1 4,alignx center");
		
		btnAdd = new JButton("Add Provider");
		add(btnAdd, "cell 2 4,alignx center");
	}
	
	
	private String getPhone() {
		String phone;
		phone = txtPhone.getText();
		
		if (phone.isBlank()) {
			showError("Debe introducir un telfono");
		} else if (!phone.matches("(\\+34|0034|34)?[ -]*(6|7)[ -]*([0-9][ -]*){8}")) {
			showError("El numero de telefono no es correcto");
		}
		return phone;
	}

	private void showError(String error) {
		JOptionPane.showMessageDialog(this , error, "Error", JOptionPane.ERROR_MESSAGE);		
	}

	@Override
	public void updateListeners(ActionListener controller) {
		btnAdd.addActionListener(controller);
		btnClean.addActionListener(controller);

	}
	

}
