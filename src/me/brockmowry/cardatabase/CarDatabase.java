package me.brockmowry.cardatabase;

import javax.swing.SwingUtilities;

import me.brockmowry.cardatabase.window.Window;

public class CarDatabase {
	
	public static final double VERSION = 1.2;
	
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> {
			new Window().setVisible(true);
		});
	}

}
