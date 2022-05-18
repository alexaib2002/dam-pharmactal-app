package org.uem.dam.GestorFarmacia.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.model.ComponentView;

import net.miginfocom.swing.MigLayout;

public class MainView extends JFrame implements ComponentView {

	private MainController mainController;
	private JMenuBar menuBar;
	private JMenu fileMn;
	private JMenuItem exitMntm;

	private LoginPanel loginPanel;
	private TablePanel tablePanel;
	private DataInsertPanel dataInsertPanel;
	private JScrollPane rootPane;
	private JMenu mnView;
	private JMenu mnTheme;
	private JMenuItem lightMntm;
	private JMenuItem darkMntm;

	public MainView() {
		initComponents();
		initAttributes();
		setSubmenuView(loginPanel); // always want to start on login
	}

	@Override
	public void initComponents() {
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));

		rootPane = new JScrollPane();
		getContentPane().add(rootPane, "cell 0 0,grow");

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

		loginPanel = new LoginPanel();
		rootPane.add(loginPanel);

		tablePanel = new TablePanel();
		rootPane.add(tablePanel);
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

		// update
		repaint();

	}

	public void setController(MainController mainController) {
		this.mainController = mainController;
		exitMntm.addActionListener(mainController);
		lightMntm.addActionListener(mainController);
		darkMntm.addActionListener(mainController);
	}

	public void setSubmenuView(JPanel submenu) {
		rootPane.setViewportView(submenu);
	}

	public void requestExitAction() {
		if (JOptionPane.showConfirmDialog(this, "Se va a cerrar el programa, ¿confirmar?", "Confirmar cierre",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
			System.exit(NORMAL);
		}
	}

}
