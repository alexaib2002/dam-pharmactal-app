package org.uem.dam.GestorFarmacia.control.subcontrol;

import org.uem.dam.GestorFarmacia.contract.TableContract;
import org.uem.dam.GestorFarmacia.contract.UsersContract;
import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.utils.SQLQueryBuilder;
import org.uem.dam.GestorFarmacia.view.submenus.LoginSubmenu;

public class LoginSubmnControl extends DefaultSubcontrol {

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
				mainFrame.setSubmenuView(mainFrame.getTableSubmn());
			} else {
				System.err.println("Access denied");
			}
			break;
		}
		}
	}

	private boolean validateLogin() {
		System.out.println("Authenticating user against DDBB");
		String[] fieldsData = loginSubmn.getFieldsData();
		// draft, implement on persistence class methods
		String query = SQLQueryBuilder.buildSelectQuery(TableContract.USERS.toString(), UsersContract.getAuthCols(),
				new String[] { String.format("%s LIKE '%s'", UsersContract.USER.toString(), fieldsData[0]) }, null,
				false);
		System.out.println(query);
		String[] dbFieldResult = new String[] { "asdf", "asdf" }; // FIXME placeholder
		// end draft
		if (fieldsData[0].equals(dbFieldResult[0])) {
			System.out.println("Username check");
			if (fieldsData[1].equals(dbFieldResult[1])) {
				System.out.println("Password check");
				return true;
			} else {
				System.err.println("Invalid password");
			}
		} else {
			System.err.println("Username not recognized");
		}
		return false;
	}
}
