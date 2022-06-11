package org.uem.dam.GestorFarmacia.control.subcontrol;

import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.persist.DBItemMap;

public class InteractableControl extends DefaultSubcontrol {

	protected final DBItemMap dbItemMap;

	public InteractableControl(MainController mainController) {
		super(mainController);
		this.dbItemMap = mainController.getDbItemMap();

	}
}
