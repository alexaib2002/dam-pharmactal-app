package org.uem.dam.gestor_farmacia.control.subcontrol;

import org.uem.dam.gestor_farmacia.control.MainController;
import org.uem.dam.gestor_farmacia.persist.DBItemMap;

public class InteractableControl extends DefaultSubcontrol {

	protected final DBItemMap dbItemMap;

	public InteractableControl(MainController mainController) {
		super(mainController);
		this.dbItemMap = mainController.getDbItemMap();

	}
}
