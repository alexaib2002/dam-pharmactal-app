package org.uem.dam.GestorFarmacia.control.subcontrol;

import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.persist.DBPersistence;
import org.uem.dam.GestorFarmacia.view.MainFrame;

public abstract class DefaultSubcontrol {

	protected final MainController mainController;
	protected final MainFrame mainFrame;
	protected final DBPersistence persistence;

	public DefaultSubcontrol(MainController mainController) {
		this.mainController = mainController;
		this.mainFrame = mainController.getMainFrame();
		this.persistence = mainController.getDbPersistence();
	}

}
