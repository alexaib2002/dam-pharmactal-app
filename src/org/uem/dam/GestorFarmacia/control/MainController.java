package org.uem.dam.GestorFarmacia.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;

import org.uem.dam.GestorFarmacia.persist.DBItemMap;
import org.uem.dam.GestorFarmacia.persist.DBPersistence;
import org.uem.dam.GestorFarmacia.swing_theming.SwingThemeManager;
import org.uem.dam.GestorFarmacia.swing_theming.SwingThemeManager.LookAndFeelItem;
import org.uem.dam.GestorFarmacia.utils.WindowActionUtils;
import org.uem.dam.GestorFarmacia.view.MainFrame;

public class MainController implements ActionListener {

	private DBItemMap dbItemMap;

	private MainFrame mainFrame;
	private DBPersistence dbPersistence;
	private WindowAdapter winAdapter;

	public MainController(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.dbPersistence = new DBPersistence();
		this.dbItemMap = new DBItemMap();
		this.winAdapter = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) { // custom event on window closing
				WindowActionUtils.onExitEvent(mainFrame);
			}
		};
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

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public WindowAdapter getWinAdapter() {
		return winAdapter;
	}

	public DBPersistence getDbPersistence() {
		return dbPersistence;
	}

	public DBItemMap getDbItemMap() {
		return dbItemMap;
	}

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

	/* General action parsers */

	private void parseGenericAction(String action) {
		switch (action.toLowerCase()) {
		case "exit": {
			WindowActionUtils.onExitEvent(mainFrame);
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
		SwingThemeManager.updateChildWindowLAF(mainFrame);
		System.out.println(String.format("Updating theme to %s based on user request", action.toLowerCase()));
	}
}
