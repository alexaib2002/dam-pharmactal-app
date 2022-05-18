package org.uem.dam.GestorFarmacia.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.uem.dam.GestorFarmacia.view.MainView;

public class MainController implements ActionListener {
	
	private MainView mainView;

	public MainController(MainView mainView) {
		this.mainView = mainView;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand();
		switch (action.toLowerCase()) {
		case "exit": {
			mainView.requestExitAction();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}
	
}
