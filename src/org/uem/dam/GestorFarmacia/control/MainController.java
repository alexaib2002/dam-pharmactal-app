package org.uem.dam.GestorFarmacia.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import org.uem.dam.GestorFarmacia.control.subcontrol.DataInsertSubmnControl;
import org.uem.dam.GestorFarmacia.control.subcontrol.LoginSubmnControl;
import org.uem.dam.GestorFarmacia.control.subcontrol.TableSubmnControl;
import org.uem.dam.GestorFarmacia.swing_theming.SwingThemeManager;
import org.uem.dam.GestorFarmacia.swing_theming.SwingThemeManager.LookAndFeelItem;
import org.uem.dam.GestorFarmacia.utils.WindowActionUtils;
import org.uem.dam.GestorFarmacia.view.MainFrame;

public class MainController implements ActionListener {

	private LoginSubmnControl loginControl;
	private TableSubmnControl tableControl;
	private DataInsertSubmnControl dataInsertControl;

	private MainFrame mainView;

	public MainController(MainFrame mainView) {
		this.mainView = mainView;
		this.loginControl = new LoginSubmnControl(mainView.getLoginSubmn());
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand().toLowerCase();
		String callerID = ((String) ((JComponent) event.getSource()).getClientProperty("CallerID"));
		if (callerID != null) { // there's a source type attached, so we can filter the caller
			parseCallerIDAction(callerID, action);
		} else { // recognized as generic source, as it wasn't named on its constructor
			parseGenericAction(action);
		}
	}

	private void parseCallerIDAction(String callerID, String action) {
		switch (callerID) {
		case "ThemeMenu": {
			parseThemeMenuAction(action);
			break;
		}
		case "LoginSubmenu": {
			loginControl.parseAction(action);
			break;
		}
		case "TableSubmenu": {
			tableControl.parseAction(action);
			break;
		}
		case "DataInsertSubmenu": {
			dataInsertControl.parseAction(action);
			break;
		}
		default:
			throw new IllegalArgumentException("Unnasinged ID action: " + action);
		}
	}

	/* General action parsers */

	private void parseGenericAction(String action) {
		switch (action.toLowerCase()) {
		case "exit": {
			WindowActionUtils.onExitEvent(mainView);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

	/* Special action parsers */

	private void parseThemeMenuAction(String action) {
		switch (action) {
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
