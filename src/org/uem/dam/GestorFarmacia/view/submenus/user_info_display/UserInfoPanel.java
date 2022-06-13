package org.uem.dam.GestorFarmacia.view.submenus.user_info_display;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.uem.dam.GestorFarmacia.view.BuildableView;
import org.uem.dam.GestorFarmacia.view.DefaultComponent;

import net.miginfocom.swing.MigLayout;

public class UserInfoPanel extends DefaultComponent implements BuildableView {

	private JLabel lblUserName;
	private JLabel lblUserPrivileges;
	private static final long serialVersionUID = 1L;

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

		lblUserName = new JLabel();
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblUserName, "cell 0 3,alignx center");

		JLabel lblUserAbletTo = new JLabel("This User is able to:");
		add(lblUserAbletTo, "cell 0 4,alignx center");

		lblUserPrivileges = new JLabel();
		add(lblUserPrivileges, "cell 0 5,alignx center");
	}

	public void setUserData(String name, boolean admin) {
		lblUserName.setText(name);
		System.out.println(admin);
		if (admin) {
			lblUserPrivileges.setText("View, add, modify and delete records");
			return;
		}
		lblUserPrivileges.setText("View and modify records");
	}

}
