package org.uem.dam.GestorFarmacia.control.subcontrol;

import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.view.MainFrame;

public abstract class DefaultSubcontrol {

	protected MainController mainController;
	protected MainFrame mainFrame;

	public DefaultSubcontrol(MainController mainController) {
		this.mainController = mainController;
		this.mainFrame = mainController.getMainView();
	}

	public void parseAction(String action) {
		System.err.println(String.format("%s does not implement method parseAction", this.getClass().getSimpleName()));
	}

}
