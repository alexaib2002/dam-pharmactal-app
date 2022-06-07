package org.uem.dam.GestorFarmacia.control.subcontrol;

import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.view.submenus.DataInsertSubmenu;

public class DataInsertSubmnControl extends DefaultSubcontrol {

	private DataInsertSubmenu dataInsertSubmn;

	public DataInsertSubmnControl(MainController mainController, DataInsertSubmenu dataInsertSubmn) {
		super(mainController);
		this.dataInsertSubmn = dataInsertSubmn;
	}

}
