package org.uem.dam.gestor_farmacia.view.submenus.login;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.uem.dam.gestor_farmacia.control.subcontrol.LoginSubmnControl;
import org.uem.dam.gestor_farmacia.model.SystemUser;
import org.uem.dam.gestor_farmacia.view.submenus.DefaultInteractableSubmenu;

import net.miginfocom.swing.MigLayout;

// FIXME login should extend class FetchableSubmenu
public class LoginSubmenu extends DefaultInteractableSubmenu<LoginSubmnControl> {

	private static final long serialVersionUID = 1L;
	private JTextField usrTxt;
	private JPasswordField psswdFld;
	private JLabel lblLogo;
	private JButton loginBtn;
	private JLabel lblsubtitle;

	@Override
	public void initComponents() {
		setLayout(new MigLayout("", "[][grow,center][]", "[][][grow,fill][][][30.00,center]"));
		JLabel titleLbl = new JLabel("Welcome!");
		titleLbl.setFont(new Font("Dialog", Font.BOLD, 28));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		add(titleLbl, "cell 1 0,grow");

		lblLogo = new JLabel("");
		Image logo = new ImageIcon(this.getClass().getResource("/farmacia.png")).getImage();

		lblsubtitle = new JLabel("Please Log In using your credentials");
		lblsubtitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblsubtitle, "cell 1 1");
		lblLogo.setIcon(new ImageIcon(logo));
		add(lblLogo, "cell 1 2,alignx center,aligny center");

		JLabel usrLbl = new JLabel("User");
		add(usrLbl, "cell 0 3,alignx trailing");

		usrTxt = new JTextField();
		add(usrTxt, "cell 1 3,growx");

		JLabel psswdLbl = new JLabel("Password");
		add(psswdLbl, "cell 0 4,alignx trailing");

		psswdFld = new JPasswordField();
		add(psswdFld, "cell 1 4,growx");

		loginBtn = new JButton("Login");
		add(loginBtn, "cell 1 5,growx");
	}

	@Override
	public void updateListeners(LoginSubmnControl controller) {
		loginBtn.addActionListener(controller);
	}

	@SuppressWarnings("deprecation")
	public SystemUser getFieldsData() {
		// both UID and admin are not going to be checked
		SystemUser introducedUser = new SystemUser(-1, usrTxt.getText(), psswdFld.getText(), false);
		return introducedUser;
	}

}
