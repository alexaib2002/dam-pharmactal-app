package org.uem.dam.GestorFarmacia.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import org.uem.dam.GestorFarmacia.swing_theming.SwingThemeManager;
import org.uem.dam.GestorFarmacia.swing_theming.SwingThemeManager.LookAndFeelItem;
import org.uem.dam.GestorFarmacia.view.MainView;

public class MainController implements ActionListener {

	private MainView mainView;

	public MainController(MainView mainView) {
		this.mainView = mainView;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand();
		String callerID = ((String) ((JComponent) event.getSource()).getClientProperty("CallerID"));
		if (callerID != null) { // there's a source type attached, so we can filter the caller
			parseCallerIDAction(callerID, action);
		} else { // recognized as generic source, as it wasn't named on its constructor
			parseGenericAction(action);
		}
	}

	/* General action parsers */

	private void parseCallerIDAction(String callerID, String action) {
		switch (callerID) {
		case "ThemeMenu": {
			parseThemeMenuAction(action);
			break;
		}
		default:
			throw new IllegalArgumentException("Unnasinged ID action: " + action);
		}
	}

	private void parseGenericAction(String action) {
		switch (action.toLowerCase()) {
		case "exit": {
			mainView.requestExitAction();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

	/* Special action parsers */

	private void parseThemeMenuAction(String action) {
		switch (action.toLowerCase()) {
		case "light": {
			SwingThemeManager.switchTheme(LookAndFeelItem.LIGHT);
			break;
		}
		case "dark": {
			SwingThemeManager.switchTheme(LookAndFeelItem.DARK);
			break;
		}
		}
		SwingThemeManager.updateChildWindowLAF(mainView);
		System.out.println(String.format("Updating theme to %s based on user request", action.toLowerCase()));
	}
}
