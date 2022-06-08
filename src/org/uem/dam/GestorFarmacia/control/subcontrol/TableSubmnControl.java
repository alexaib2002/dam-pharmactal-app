package org.uem.dam.GestorFarmacia.control.subcontrol;

import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.view.submenus.TableSubmenu;

public class TableSubmnControl extends DefaultSubcontrol {

	private TableSubmenu tableSubmn;

	public TableSubmnControl(MainController mainController, TableSubmenu tableSubmn) {
		super(mainController);
		this.tableSubmn = tableSubmn;
	}

	@Override
	public void parseAction(String action) {
		// TODO Auto-generated method stub

	}

}
