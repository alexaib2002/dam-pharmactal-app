package org.uem.dam.GestorFarmacia.view.submenus.welcome;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.uem.dam.GestorFarmacia.view.BuildableView;
import org.uem.dam.GestorFarmacia.view.submenus.DefaultInteractableSubmenu;

import net.miginfocom.swing.MigLayout;

public class WelcomePage extends DefaultInteractableSubmenu<ActionListener> implements BuildableView {
	public WelcomePage() {
		initComponents();
	}

	private static final long serialVersionUID = 1L;
	private JButton btnContinue;

	@Override
	public void initComponents() {
		setLayout(new MigLayout("", "[163px,grow,center]", "[][][183.00,grow][grow]"));

		JLabel lblWelcome = new JLabel("Welcome to Pharmactal");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblWelcome, "cell 0 0");

		JLabel lblLogo = new JLabel("");
		Image logo = new ImageIcon(this.getClass().getResource("/pharmactal.png")).getImage();

		JLabel lblNewLabel = new JLabel("The best pharmacy manager");
		add(lblNewLabel, "cell 0 1");
		lblLogo.setIcon(new ImageIcon(logo));

		add(lblLogo, "cell 0 2,alignx center,aligny center");

		btnContinue = new JButton("Continue");
		add(btnContinue, "cell 0 3,alignx center");

	}

	@Override
	public void updateListeners(ActionListener controller) {
		btnContinue.addActionListener(controller);
	}
}
