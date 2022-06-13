package org.uem.dam.gestor_farmacia.view.submenus.insertion;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JWindow;

import org.uem.dam.gestor_farmacia.control.subcontrol.InsertProviderControl;
import org.uem.dam.gestor_farmacia.model.Provider;
import org.uem.dam.gestor_farmacia.utils.WindowActionUtils;
import org.uem.dam.gestor_farmacia.view.submenus.DefaultInteractableSubmenu;

import net.miginfocom.swing.MigLayout;

public class InsertProviderPanel extends DefaultInteractableSubmenu<InsertProviderControl>
implements FetchableSubmenu<Provider> {

	public static final String ACTION_ADD = "Add Provider";
	public static final String ACTION_CLEAR = "Clean Data";

	private static final long serialVersionUID = 1L;

	private JTextField txtPhone;
	private JTextField txtName;
	private JTextField txtAddress;
	private JButton btnAdd;
	private JButton btnClean;

	@Override
	public void initComponents() {

		setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow]", "[][5%][][][][grow 50][grow]"));

		JLabel lblTitle = new JLabel("Add new provider");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblTitle, "cell 1 0 4 2,alignx center,aligny center");

		JLabel lblProvName = new JLabel("Provider Name:");
		add(lblProvName, "cell 1 2,alignx trailing");

		txtName = new JTextField();
		add(txtName, "cell 2 2 3 1,growx");
		txtName.setColumns(10);

		JLabel lblProvPhone = new JLabel("Phone:");
		add(lblProvPhone, "cell 1 3,alignx trailing");

		txtPhone = new JTextField();
		add(txtPhone, "cell 2 3 3 1,growx");
		txtPhone.setColumns(10);

		JLabel lblAddress = new JLabel("Address:");
		add(lblAddress, "cell 1 4,alignx trailing");

		txtAddress = new JTextField();
		add(txtAddress, "cell 2 4 3 1,growx");
		txtAddress.setColumns(10);

		btnAdd = new JButton(ACTION_ADD);
		add(btnAdd, "cell 1 5 2 1,growx");

		btnClean = new JButton("Clean Data");
		add(btnClean, "cell 3 5 2 1,growx");
	}

	@Override
	public void updateListeners(InsertProviderControl controller) {
		btnAdd.addActionListener(controller);
		btnClean.addActionListener(controller);
	}

	@Override
	public Provider getInputItem() {
		Provider provider = null;
		try {
			provider = new Provider(-1, txtName.getText(), parseTxtPhone(), txtAddress.getText());
		} catch (NullPointerException npe) {
			WindowActionUtils.promptInfoDialog(new JWindow(), "Please fill every field", JOptionPane.ERROR_MESSAGE);
		}
		return provider;
	}

	@Override
	public void clearFields() {
		txtPhone.setText("");
		txtName.setText("");
		txtAddress.setText("");
	}

	private String parseTxtPhone() {
		String phone;
		phone = txtPhone.getText();

		if (phone.isBlank()) {
			WindowActionUtils.promptInfoDialog(new JWindow(), "Phone field cannot be empty", JOptionPane.ERROR_MESSAGE);
		} else if (phone.length() != 9) {
			WindowActionUtils.promptInfoDialog(
					new JWindow(),
					"Invalid phone format\nPhone should contain numeric 9 digits",
					JOptionPane.ERROR_MESSAGE
					);
		}
		return phone;
	}
}
