package org.uem.dam.GestorFarmacia.view;

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

	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JMenu fileMn;
	private JMenuItem exitMntm;

	private LoginSubmenu loginSubmn;
	private DataViewSubmenu dataViewSubmenu;
	private JMenu mnView;
	private JMenu mnTheme;
	private JMenuItem lightMntm;
	private JMenuItem darkMntm;

	public MainFrame() {
		initComponents();
		initAttributes();
		setSubmenuView(loginSubmn); // always want to start on login
	}

	@Override
	public void initComponents() {
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		fileMn = new JMenu("File");
		menuBar.add(fileMn);

		exitMntm = new JMenuItem("Exit");
		fileMn.add(exitMntm);

		mnView = new JMenu("View");
		menuBar.add(mnView);

		mnTheme = new JMenu("Theme");
		mnView.add(mnTheme);

		lightMntm = new JMenuItem("Light");
		lightMntm.putClientProperty("CallerID", "ThemeMenu");
		mnTheme.add(lightMntm);

		darkMntm = new JMenuItem("Dark");
		darkMntm.putClientProperty("CallerID", "ThemeMenu");
		mnTheme.add(darkMntm);

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
		switch (state) {
		case NOUSER: {

			break;
		}
		case VIEWER: {

			break;
		}
		case ADMIN: {

			break;
		}
		}
	}

	private void onSubmenuUpdate() {
		repaint();
		revalidate();
		// not-so-clean hack for ensuring theme gets applied to every component
		SwingThemeManager.updateChildWindowLAF(this);
	}

}
