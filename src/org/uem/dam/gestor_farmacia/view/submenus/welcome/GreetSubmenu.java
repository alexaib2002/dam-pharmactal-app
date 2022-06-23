package org.uem.dam.gestor_farmacia.view.submenus.welcome;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.uem.dam.gestor_farmacia.view.BuildableView;
import org.uem.dam.gestor_farmacia.view.submenus.DefaultInteractableSubmenu;
import org.uem.dam.gestor_farmacia.view.submenus.user_info_display.UserInfoPanel;

import net.miginfocom.swing.MigLayout;

public class GreetSubmenu extends DefaultInteractableSubmenu<ActionListener> implements BuildableView {

	public static final String ACTION_CONTINUE = "Continue";

	private static final long serialVersionUID = 1L;

	private UserInfoPanel userInfoPanel;
	private JButton btnContinue;

	@Override
	public void initComponents() {
		setLayout(new MigLayout("", "[163px,grow,center]", "[][][grow][]"));

		JLabel lblWelcome = new JLabel("Pharmactal Data Manager");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblWelcome, "cell 0 0");

		JLabel lblNewLabel = new JLabel("Your best bet for meds");
		add(lblNewLabel, "cell 0 1");

		userInfoPanel = new UserInfoPanel();
		add(userInfoPanel, "cell 0 2,grow");

		btnContinue = new JButton(ACTION_CONTINUE);
		add(btnContinue, "cell 0 3,growx,aligny center");

	}

	@Override
	public void updateListeners(ActionListener controller) {
		btnContinue.addActionListener(controller);
	}

	public UserInfoPanel getUserInfoPanel() {
		return userInfoPanel;
	}

	public JButton getBtnContinue() {
		return btnContinue;
	}
}
