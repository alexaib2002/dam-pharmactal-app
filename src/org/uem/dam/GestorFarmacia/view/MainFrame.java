package org.uem.dam.GestorFarmacia.view;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.control.SystemState;
import org.uem.dam.GestorFarmacia.control.subcontrol.LoginSubmnControl;
import org.uem.dam.GestorFarmacia.swing_theming.SwingThemeManager;
import org.uem.dam.GestorFarmacia.view.submenus.data_view.list_perspective.DataViewSubmenu;
import org.uem.dam.GestorFarmacia.view.submenus.login.LoginSubmenu;

import net.miginfocom.swing.MigLayout;

public class MainFrame extends JFrame implements InteractableView<MainController> {

	public static final String ACTION_LOG_OUT = "Log out";
	public static final String ACTION_CLOSE_APP = "Close app";
	public static final String ACTION_NEW_PROVIDER = "New provider";
	public static final String ACTION_NEW_ARTICLE = "New article";
	public static final String ACTION_LIGHT = "Light";
	public static final String ACTION_DARK = "Dark";
	public static final String ACTION_RETURN_TO_HOME = "Return to home";

	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar;
	private JMenu systemMn;
	private JMenuItem exitMntm;

	private LoginSubmenu loginSubmn;
	private DataViewSubmenu dataViewSubmenu;
	private JMenu mnView;
	private JMenu mnTheme;
	private JMenuItem lightMntm;
	private JMenuItem darkMntm;
	private JMenu mnAdd;
	private JMenuItem addItemMntm;
	private JMenuItem addProvMntm;
	private JMenuItem homeMntm;
	private JMenuItem logOutmntm;

	public MainFrame() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		initComponents();
		initAttributes();
		setSubmenuView(loginSubmn); // always want to start on login
	}

	@Override
	public void initComponents() {
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		systemMn = new JMenu("System");
		menuBar.add(systemMn);

		logOutmntm = new JMenuItem(ACTION_LOG_OUT);
		systemMn.add(logOutmntm);

		exitMntm = new JMenuItem(ACTION_CLOSE_APP);
		systemMn.add(exitMntm);

		mnAdd = new JMenu("Add");
		menuBar.add(mnAdd);

		addItemMntm = new JMenuItem(ACTION_NEW_ARTICLE);
		mnAdd.add(addItemMntm);

		addProvMntm = new JMenuItem(ACTION_NEW_PROVIDER);
		mnAdd.add(addProvMntm);

		mnView = new JMenu("View");
		menuBar.add(mnView);

		mnTheme = new JMenu("Theme");
		mnView.add(mnTheme);

		lightMntm = new JMenuItem(ACTION_LIGHT);
		mnTheme.add(lightMntm);

		darkMntm = new JMenuItem(ACTION_DARK);
		mnTheme.add(darkMntm);

		homeMntm = new JMenuItem(ACTION_RETURN_TO_HOME);
		mnView.add(homeMntm);

		loginSubmn = new LoginSubmenu();

		dataViewSubmenu = new DataViewSubmenu();

	}

	@Override
	public void initAttributes() {
		this.setVisible(true);
		this.setTitle("Gestor de Base de Datos");

		// pack the view and set it as minimum size
		this.pack();
		this.setMinimumSize(getSize());
		this.setSize(new Dimension(700, 500)); // FIXME hardcode

		// set main view content
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	@Override
	public void updateListeners(MainController controller) {
		// update component listeners
		exitMntm.addActionListener(controller);
		lightMntm.addActionListener(controller);
		darkMntm.addActionListener(controller);
		addItemMntm.addActionListener(controller);
		addProvMntm.addActionListener(controller);
		logOutmntm.addActionListener(controller);
		homeMntm.addActionListener(controller);
		// propagate call to child submenus
		loginSubmn.updateListeners(new LoginSubmnControl(controller, loginSubmn));
		dataViewSubmenu.updateListeners(controller);
		// window adapter
		this.addWindowListener(controller.getWinAdapter());
	}

	public LoginSubmenu getLoginSubmn() {
		return loginSubmn;
	}

	public DataViewSubmenu getDataViewSubmn() {
		return dataViewSubmenu;
	}

	public void setSubmenuView(JPanel submenu) {
		getContentPane().removeAll();
		getContentPane().add(submenu, "grow");
		// redraw the frame after change
		onSubmenuUpdate();
	}

	public void onSystemStateChanged(SystemState state) {
		resetVariableLayout();
		switch (state) {
		case NOUSER: {
			break;
		}
		case ADMIN: {
			mnAdd.setVisible(true);
		}
		case VIEWER: {
			logOutmntm.setVisible(true);
			homeMntm.setVisible(true);
			break;
		}
		}
		updateMenuBar();
	}

	private void resetVariableLayout() {
		mnAdd.setVisible(false);
		logOutmntm.setVisible(false);
		homeMntm.setVisible(false);
	}

	private void updateMenuBar() {
		menuBar.revalidate();
	}

	private void onSubmenuUpdate() {
		repaint();
		revalidate();
		// not-so-clean hack for ensuring theme gets applied to every component
		SwingThemeManager.updateChildWindowLAF(this);
	}

}
