package org.uem.dam.gestor_farmacia.view;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.uem.dam.gestor_farmacia.control.MainController;
import org.uem.dam.gestor_farmacia.control.SystemState;
import org.uem.dam.gestor_farmacia.control.subcontrol.GreetSubcontrol;
import org.uem.dam.gestor_farmacia.control.subcontrol.InsertArticleControl;
import org.uem.dam.gestor_farmacia.control.subcontrol.InsertProviderControl;
import org.uem.dam.gestor_farmacia.control.subcontrol.LoginSubmnControl;
import org.uem.dam.gestor_farmacia.swing_theming.SwingThemeManager;
import org.uem.dam.gestor_farmacia.view.submenus.data_view.list_perspective.DataViewSubmenu;
import org.uem.dam.gestor_farmacia.view.submenus.insertion.InsertArticlePanel;
import org.uem.dam.gestor_farmacia.view.submenus.insertion.InsertProviderPanel;
import org.uem.dam.gestor_farmacia.view.submenus.login.LoginSubmenu;
import org.uem.dam.gestor_farmacia.view.submenus.welcome.GreetSubmenu;

import net.miginfocom.swing.MigLayout;

public class MainFrame extends JFrame implements InteractableView<MainController> {

	public static final String ACTION_LOG_OUT = "Log out";
	public static final String ACTION_CLOSE_APP = "Close app";
	public static final String ACTION_NEW_PROVIDER = "New provider";
	public static final String ACTION_NEW_ARTICLE = "New article";
	public static final String ACTION_LIGHT = "Light";
	public static final String ACTION_DARK = "Dark";
	public static final String ACTION_RETURN_TO_HOME = "Return to home";

	public static final String POPUP_INSERT_ARTICLE = "InsertArticlePanel";
	public static final String POPUP_INSERT_PROVIDER = "InsertProviderPanel";

	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar;
	private JMenu systemMn;
	private JMenuItem exitMntm;

	private LoginSubmenu loginSubmn;
	private DataViewSubmenu dataViewSubmn;
	private GreetSubmenu greetSubmn;

	private JFrame insertFrame;
	private InsertArticlePanel insertArticlePanel;
	private InsertProviderPanel insertProviderPanel;

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

		// submenus
		loginSubmn = new LoginSubmenu();
		dataViewSubmn = new DataViewSubmenu();
		greetSubmn = new GreetSubmenu();

		// popups
		insertFrame = new JFrame();
		insertArticlePanel = new InsertArticlePanel();
		insertArticlePanel.setName(POPUP_INSERT_ARTICLE);
		insertFrame.add(insertArticlePanel);
		insertProviderPanel = new InsertProviderPanel();
		insertProviderPanel.setName(POPUP_INSERT_PROVIDER);
		insertFrame.add(insertProviderPanel);

	}

	@Override
	public void initAttributes() {

		// start with mainFrame initialization
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("Gestor de Base de Datos");
		this.pack();
		this.setMinimumSize(getSize());
		this.setSize(new Dimension(700, 500)); // FIXME hardcode
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		// insertFrame initialization
		insertFrame.setTitle("Add new item");
		insertFrame.setLocationRelativeTo(this);
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
		dataViewSubmn.updateListeners(controller);
		greetSubmn.updateListeners(new GreetSubcontrol(controller));
		insertArticlePanel.updateListeners(new InsertArticleControl(controller, insertArticlePanel));
		insertProviderPanel.updateListeners(new InsertProviderControl(controller, insertProviderPanel));
		// window adapter
		this.addWindowListener(controller.getWinAdapter());
	}

	public void popupInsertFrame(String componentName) {
		SwingThemeManager.updateChildWindowLAF(insertFrame);
		switch (componentName) {
		case POPUP_INSERT_ARTICLE: {
			insertFrame.setContentPane(insertArticlePanel);
			break;
		}
		case POPUP_INSERT_PROVIDER: {
			insertFrame.setContentPane(insertProviderPanel);
			break;
		}
		}
		insertFrame.setVisible(true);
		insertFrame.pack();
	}

	public JMenuItem getAddProvMntm() {
		return addProvMntm;
	}

	public void setAddProvMntm(JMenuItem addProvMntm) {
		this.addProvMntm = addProvMntm;
	}

	public InsertArticlePanel getInsertArticlePanel() {
		return insertArticlePanel;
	}

	public InsertProviderPanel getProviderInsertPanel() {
		return insertProviderPanel;
	}

	public LoginSubmenu getLoginSubmn() {
		return loginSubmn;
	}

	public GreetSubmenu getGreetSubmn() {
		return greetSubmn;
	}

	public DataViewSubmenu getDataViewSubmn() {
		return dataViewSubmn;
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
