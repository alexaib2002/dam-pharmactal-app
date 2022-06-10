package org.uem.dam.GestorFarmacia.view.submenus.login;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.uem.dam.GestorFarmacia.model.SystemUser;
import org.uem.dam.GestorFarmacia.view.submenus.DefaultSubmenu;

import net.miginfocom.swing.MigLayout;

public class LoginSubmenu extends DefaultSubmenu {

	private static final long serialVersionUID = 1L;
	private JTextField usrTxt;
	private JPasswordField psswdFld;
	private JButton loginBtn;

	@Override
	public void initComponents() {
		setLayout(new MigLayout("", "[][grow]", "[grow,fill][][][]"));

		JLabel titleLbl = new JLabel("Login");
		titleLbl.setFont(new Font("Dialog", Font.BOLD, 28));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		add(titleLbl, "cell 0 0 2 1,grow");

		JLabel usrLbl = new JLabel("User");
		add(usrLbl, "cell 0 1,alignx trailing");

		usrTxt = new JTextField();
		add(usrTxt, "cell 1 1,growx");

		JLabel psswdLbl = new JLabel("Password");
		add(psswdLbl, "cell 0 2,alignx trailing");

		psswdFld = new JPasswordField();
		add(psswdFld, "cell 1 2,growx");

		loginBtn = new JButton("Login");
		add(loginBtn, "cell 0 3 2 1,grow");
		loginBtn.putClientProperty("CallerID", "LoginSubmenu");
	}

	@Override
	public void updateListeners(ActionListener controller) {
		loginBtn.addActionListener(controller);
	}

	@SuppressWarnings("deprecation")
	public SystemUser getFieldsData() {
		// both UID and admin are not going to be checked
		SystemUser introducedUser = new SystemUser(-1, usrTxt.getText(), psswdFld.getText(), false);
		return introducedUser;
	}

}
