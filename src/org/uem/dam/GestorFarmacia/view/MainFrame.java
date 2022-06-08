package org.uem.dam.GestorFarmacia.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.uem.dam.GestorFarmacia.view.submenus.DataInsertSubmenu;
import org.uem.dam.GestorFarmacia.view.submenus.LoginSubmenu;
import org.uem.dam.GestorFarmacia.view.submenus.TableSubmenu;

import net.miginfocom.swing.MigLayout;

public class MainFrame extends JFrame implements ComponentView {

	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JMenu fileMn;
	private JMenuItem exitMntm;

	private LoginSubmenu loginSubmn;
	private TableSubmenu tableSubmn;
	private DataInsertSubmenu dataInsertSubmn;
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

		tableSubmn = new TableSubmenu();

		dataInsertSubmn = new DataInsertSubmenu();

	}

	@Override
	public void initAttributes() {
		this.setVisible(true);
		this.setTitle("Gestor de Base de Datos");

		// pack the view and set it as minimum size
		this.pack();
		this.setMinimumSize(getSize());
		this.setSize(new Dimension(300, 300));

		// set main view content
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void updateListeners(ActionListener controller) {
		// update component listeners
		exitMntm.addActionListener(controller);
		lightMntm.addActionListener(controller);
		darkMntm.addActionListener(controller);
		// propagate call to child submenus
		loginSubmn.updateListeners(controller);
		tableSubmn.updateListeners(controller);
		dataInsertSubmn.updateListeners(controller);
	}

	public LoginSubmenu getLoginSubmn() {
		return loginSubmn;
	}

	public TableSubmenu getTableSubmn() {
		return tableSubmn;
	}

	public DataInsertSubmenu getDataInsertSubmn() {
		return dataInsertSubmn;
	}

	public void setSubmenuView(JPanel submenu) {
		getContentPane().removeAll();
		getContentPane().add(submenu, "grow");
		// redraw the frame after change
		onSubmenuUpdate();
	}

	private void onSubmenuUpdate() {
		repaint();
		revalidate();
	}

}
