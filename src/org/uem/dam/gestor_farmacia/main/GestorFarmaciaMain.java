package org.uem.dam.gestor_farmacia.main;

import org.uem.dam.gestor_farmacia.control.MainController;
import org.uem.dam.gestor_farmacia.swing_theming.SwingThemeManager;
import org.uem.dam.gestor_farmacia.view.MainFrame;

public class GestorFarmaciaMain {

	public static void main(String[] args) {

		SwingThemeManager.onApplicationStart();

		java.awt.EventQueue.invokeLater(() -> {
			MainFrame mainFrame = new MainFrame();
			MainController mainController = new MainController(mainFrame);
			mainFrame.updateListeners(mainController);
		});

	}

}
