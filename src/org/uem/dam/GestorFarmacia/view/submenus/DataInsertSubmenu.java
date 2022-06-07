package org.uem.dam.GestorFarmacia.view.submenus;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.uem.dam.GestorFarmacia.model.ComponentView;

import net.miginfocom.swing.MigLayout;

public class DataInsertSubmenu extends DefaultSubmenu implements ComponentView {
	private static final long serialVersionUID = 1L;
	private JTextField textField;

	@Override
	public void initComponents() {
		setLayout(new MigLayout("", "[][grow]", "[]"));

		// FIXME code as placeholder, needs to be created from columns of DDBB
		JLabel lblId = new JLabel("placeholderCol");
		add(lblId, "cell 0 0,alignx trailing");

		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);
	}

	@Override
	public void initAttributes() {
		// TODO Auto-generated method stub

	}

}
