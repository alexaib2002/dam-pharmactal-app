package org.uem.dam.gestor_farmacia.control.subcontrol;

import org.uem.dam.gestor_farmacia.control.MainController;
import org.uem.dam.gestor_farmacia.persist.DBPersistence;
import org.uem.dam.gestor_farmacia.view.MainFrame;

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
