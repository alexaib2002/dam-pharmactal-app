package org.uem.dam.GestorFarmacia.control.subcontrol;

import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.view.submenus.TabbedSubmenu;

public class TabbedSubmnControl extends DefaultSubcontrol {

	private TabbedSubmenu tabbedSubmn;

	public TabbedSubmnControl(MainController mainController, TabbedSubmenu tabbedSubmn) {
		super(mainController);
		this.tabbedSubmn = tabbedSubmn;
	}

	@Override
	public void parseAction(String action) {
		// TODO Auto-generated method stub

	}

}
