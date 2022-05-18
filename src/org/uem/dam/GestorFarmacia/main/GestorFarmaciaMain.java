package org.uem.dam.GestorFarmacia.main;

import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.view.MainView;

public class GestorFarmaciaMain {

	public static void main(String[] args) {
		
		java.awt.EventQueue.invokeLater(() -> {
			MainView mainView = new MainView();
			MainController mainController = new MainController(mainView);
			mainView.setController(mainController);
		});		

	}

}
