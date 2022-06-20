package org.uem.dam.gestor_farmacia.utils;

import java.awt.Window;

import javax.swing.JOptionPane;

public abstract class WindowActionUtils {

	public static boolean promptWindowExit(Window window) {
		return (JOptionPane.showConfirmDialog(
				window,
				"Closing window, is that OK?",
				"Closing",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE
		) == JOptionPane.YES_OPTION);
	}

	public static void onExitEvent(Window window) {
		if (WindowActionUtils.promptWindowExit(window)) {
			window.dispose();
			System.exit(0);
		}
	}

	public static void promptInfoDialog(Window window, String mssg, String title, int icon) {
		JOptionPane.showMessageDialog(window, mssg, title, icon);
	}

	public static void promptInfoDialog(Window window, String mssg, int icon) {
		JOptionPane.showMessageDialog(window, mssg, "System info", icon);
	}

}