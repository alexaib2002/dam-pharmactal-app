package org.uem.dam.GestorFarmacia.view.submenus.welcome;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.uem.dam.GestorFarmacia.view.BuildableView;
import org.uem.dam.GestorFarmacia.view.submenus.DefaultInteractableSubmenu;
import org.uem.dam.GestorFarmacia.view.submenus.user_info_display.UserInfoPanel;

import net.miginfocom.swing.MigLayout;

public class GreetSubmenu extends DefaultInteractableSubmenu<ActionListener> implements BuildableView {

	public static final String ACTION_CONTINUE = "Continue";

	private static final long serialVersionUID = 1L;

	private UserInfoPanel userInfoPanel;
	private JButton btnContinue;

	@Override
	public void initComponents() {
		setLayout(new MigLayout("", "[163px,grow,center]", "[][][grow][183.00,grow][grow]"));

		JLabel lblWelcome = new JLabel("Pharmactal Data Manager");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblWelcome, "cell 0 0");

		JLabel lblNewLabel = new JLabel("Your best bet for meds");
		add(lblNewLabel, "cell 0 1");

		userInfoPanel = new UserInfoPanel();
		add(userInfoPanel, "cell 0 2,grow");

		btnContinue = new JButton(ACTION_CONTINUE);
		add(btnContinue, "cell 0 4,alignx center");

	}

	@Override
	public void updateListeners(ActionListener controller) {
		btnContinue.addActionListener(controller);
	}

	public UserInfoPanel getUserInfoPanel() {
		return userInfoPanel;
	}

}
