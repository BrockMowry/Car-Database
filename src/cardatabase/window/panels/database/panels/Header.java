package cardatabase.window.panels.database.panels;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import cardatabase.window.Window;

public class Header extends JPanel {
	
	private final JLabel title;

	public Header() {
		super(new FlowLayout(FlowLayout.CENTER));
		
		this.title = new JLabel("Database".toUpperCase());
		this.title.setFont(new Font("Arial Bold", Font.PLAIN, 20));
		this.title.setForeground(Window.TITLES);

		this.add(this.title);

		this.setBackground(Window.BACKGROUND);
	}
}
