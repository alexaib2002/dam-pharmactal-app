package org.uem.dam.GestorFarmacia.control.subcontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.uem.dam.GestorFarmacia.contract.TableContract;
import org.uem.dam.GestorFarmacia.contract.UsersContract;
import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.model.DBItem;
import org.uem.dam.GestorFarmacia.model.SystemUser;
import org.uem.dam.GestorFarmacia.utils.ContractUtils;
import org.uem.dam.GestorFarmacia.utils.SQLQueryBuilder;
import org.uem.dam.GestorFarmacia.view.submenus.login.LoginSubmenu;

public class LoginSubmnControl extends DefaultSubcontrol implements ActionListener {

	private static final String[] USER_COLS = ContractUtils.getAllCols(UsersContract.class);

	private final LoginSubmenu loginSubmn;

	public LoginSubmnControl(MainController mainController,
			LoginSubmenu submenu) {
		super(mainController);
		this.loginSubmn = submenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().toLowerCase().equals("login")) {
			if (validateLogin()) {
				System.out.println("User authenticated");
				mainFrame.setSubmenuView(mainFrame.getDataViewSubmn());
				// TODO log user who has entered the system
			} else {
				System.err.println("Access denied");
			}
		}
	}

	private boolean validateLogin() {
		SystemUser insertedUser = loginSubmn.getFieldsData();

		ArrayList<DBItem> systemUserList = persistence.executeSelect((con, pstmt) -> {
			String query = SQLQueryBuilder.buildSelectQuery(
					TableContract.USERS.toString(),
					USER_COLS,
					new String[] {
							String.format("USERNAME LIKE ?", UsersContract.USERNAME.toString()) },
					null,
					false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, insertedUser.name());
			return pstmt;
		}, SystemUser.class, USER_COLS.length);
		if (systemUserList.size() > 1) {
			System.err.println("Cannot authenticate user, more than one user with the same name detected");
			return false;
		} else if (systemUserList.size() == 0) {
			System.err.println("Username not recognized");
			return false;
		}
		SystemUser registeredUser = (SystemUser) systemUserList.get(0);
		if (!insertedUser.psswd().equals(registeredUser.psswd())) {
			System.err.println("Invalid password");
			return false;
		}
		return true;
	}
}