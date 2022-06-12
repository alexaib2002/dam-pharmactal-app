package org.uem.dam.GestorFarmacia.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.uem.dam.GestorFarmacia.model.SystemUser;
import org.uem.dam.GestorFarmacia.persist.DBItemMap;
import org.uem.dam.GestorFarmacia.persist.DBPersistence;
import org.uem.dam.GestorFarmacia.swing_theming.SwingThemeManager;
import org.uem.dam.GestorFarmacia.swing_theming.SwingThemeManager.LookAndFeelItem;
import org.uem.dam.GestorFarmacia.utils.WindowActionUtils;
import org.uem.dam.GestorFarmacia.view.MainFrame;

public class MainController implements ActionListener {

	private final DBItemMap dbItemMap;

	private SystemState systemState;
	private SystemUser systemUser;

	private final MainFrame mainFrame;
	private final DBPersistence dbPersistence;
	private final WindowAdapter winAdapter;

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
		setSystemState(SystemState.NOUSER);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		switch (event.getActionCommand()) {
		case MainFrame.ACTION_LOG_OUT: {
			System.out.println("Log out not implemented yet");
			break;
		}
		case MainFrame.ACTION_CLOSE_APP: {
			WindowActionUtils.onExitEvent(mainFrame);
			break;
		}
		case MainFrame.ACTION_DARK: {
			SwingThemeManager.switchTheme(LookAndFeelItem.DARK);
			SwingThemeManager.updateChildWindowLAF(mainFrame);
			break;
		}
		case MainFrame.ACTION_LIGHT: {
			SwingThemeManager.switchTheme(LookAndFeelItem.LIGHT);
			SwingThemeManager.updateChildWindowLAF(mainFrame);
			break;
		}
		case MainFrame.ACTION_RETURN_TO_HOME: {
			System.out.println("Home not implemented yet");
			break;
		}
		// FIXME this could be refactored into their own controller
		case MainFrame.ACTION_NEW_ARTICLE: {
			break;
		}
		case MainFrame.ACTION_NEW_PROVIDER: {
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + event.getActionCommand());
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

	public void setSystemState(SystemState systemState) {
		this.systemState = systemState;
		mainFrame.onSystemStateChanged(systemState);
	}

	public SystemState getSystemState() {
		return systemState;
	}

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

}
