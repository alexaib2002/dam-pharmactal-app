package org.uem.dam.view.submenus.user_info_display;

import java.awt.event.ActionListener;

import org.uem.dam.GestorFarmacia.view.BuildableView;
import org.uem.dam.GestorFarmacia.view.submenus.DefaultInteractableSubmenu;
import net.miginfocom.swing.MigLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;

public class DisplayUserInfo extends DefaultInteractableSubmenu<ActionListener> implements BuildableView  {

	private static final long serialVersionUID = 1L;
	private JButton btnOk;

	public DisplayUserInfo() {
		initComponents();
	}


	@Override
	public void initComponents() {
		
		setLayout(new MigLayout("", "[grow]", "[][193.00,grow][grow][][][][grow]"));
		
		JLabel lbltitle = new JLabel("User Information");
		lbltitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lbltitle, "cell 0 0,alignx center");
		
		JLabel lblUser = new JLabel("");
		Image user = new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		lblUser.setIcon(new ImageIcon(user));
		add(lblUser, "cell 0 1,alignx center");
		
		JLabel lblCurrentUser = new JLabel("Current user is:");
		lblCurrentUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblCurrentUser, "cell 0 2,alignx center");
		
		JLabel lblUserName = new JLabel("User/Admin");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblUserName, "cell 0 3,alignx center");
		
		JLabel lblUserAbletTo = new JLabel("This User is able to:");
		add(lblUserAbletTo, "cell 0 4,alignx center");
		
		String userPrivileges = getUserPrivileges();
		JLabel lblUserPrivileges = new JLabel(userPrivileges);
		add(lblUserPrivileges, "cell 0 5,alignx center");
		
		btnOk = new JButton("");
		Image button = new ImageIcon(this.getClass().getResource("/check-mark.png")).getImage();
		btnOk.setIcon(new ImageIcon(button));
		add(btnOk, "cell 0 6,alignx center");
	}


	private String getUserPrivileges() {
		String privileges = null;
		
		//TODO: if user is admin privileges = "This User can add Users, modify, etc"
		//else privileges = "This User can modify stock, etc"
		
		return privileges;
	}


	@Override
	public void updateListeners(ActionListener controller) {
		btnOk.addActionListener(controller);
	}

}
