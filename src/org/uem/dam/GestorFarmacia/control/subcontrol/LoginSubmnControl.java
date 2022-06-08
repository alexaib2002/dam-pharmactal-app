package org.uem.dam.GestorFarmacia.control.subcontrol;

import org.uem.dam.GestorFarmacia.contract.TableContract;
import org.uem.dam.GestorFarmacia.contract.UsersContract;
import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.model.SystemUser;
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
		// draft, implement on persistence class methods
		String query = SQLQueryBuilder.buildSelectQuery(TableContract.USERS.toString(), UsersContract.getAuthCols(),
				new String[] { String.format("%s LIKE '%s'", UsersContract.USER.toString(), insertedUser.name()) },
				null, false);
		System.out.println(query);
		SystemUser registeredUser = new SystemUser(0, "asdf", "asdf", false); // FIXME placeholder
		// end draft
		if (!insertedUser.name().equals(registeredUser.name())) {
			System.err.println("Username not recognized");
			return false;
		}
		if (!insertedUser.psswd().equals(registeredUser.psswd())) {
			System.err.println("Invalid password");
			return false;
		}
		return true;
	}
}
