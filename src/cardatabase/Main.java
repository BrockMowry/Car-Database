package cardatabase;

import javax.swing.SwingUtilities;
import cardatabase.window.Window;

public class Main {
	
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> {
			new Window().setVisible(true);
		});
	}

}
