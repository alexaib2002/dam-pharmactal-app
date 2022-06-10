package org.uem.dam.GestorFarmacia.control.subcontrol;

import java.util.ArrayList;

import org.uem.dam.GestorFarmacia.contract.TableContract;
import org.uem.dam.GestorFarmacia.contract.UsersContract;
import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.model.SystemUser;
import org.uem.dam.GestorFarmacia.persist.DBPersistence;
import org.uem.dam.GestorFarmacia.utils.ContractUtils;
import org.uem.dam.GestorFarmacia.utils.SQLQueryBuilder;
import org.uem.dam.GestorFarmacia.view.submenus.login.LoginSubmenu;

public class LoginSubmnControl extends DefaultSubcontrol {

	static final String[] USER_COLS = ContractUtils.getAllCols(UsersContract.class);
	private LoginSubmenu loginSubmn;

	public LoginSubmnControl(MainController mainController, LoginSubmenu submenu) {
		super(mainController);
		this.loginSubmn = submenu;
	}

	@Override
	public void parseAction(String action) {
		switch (action) {
		case "login": {
			if (validateLogin()) {
				System.out.println("User authenticated");
				mainFrame.setSubmenuView(mainFrame.getTabbedSubmn());
			} else {
				System.err.println("Access denied");
			}
			break;
		}
		}
	}

	private boolean validateLogin() {
		System.out.println("Authenticating user against DDBB");
		SystemUser insertedUser = loginSubmn.getFieldsData();
		DBPersistence persist = mainController.getDbPersistence();

		ArrayList<SystemUser> systemUserList = persist.executeSelectUser((con, pstmt) -> {
			String query = SQLQueryBuilder.buildSelectQuery(TableContract.USERS.toString(), USER_COLS,
					new String[] { String.format("USERNAME LIKE ?", UsersContract.USERNAME.toString()) }, null, false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, insertedUser.userName());
			return pstmt;
		}, USER_COLS.length);
		if (systemUserList.size() > 1) {
			System.err.println("Cannot authenticate user, more than one user with the same name detected");
			return false;
		} else if (systemUserList.size() == 0) {
			System.err.println("Username not recognized");
			return false;
		}
		SystemUser registeredUser = systemUserList.get(0);
		if (!insertedUser.psswd().equals(registeredUser.psswd())) {
			System.err.println("Invalid password");
			return false;
		}
		return true;
	}
}
