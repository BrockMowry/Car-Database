package cardatabase.window.panels.form.panels;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import cardatabase.window.Window;

public class Header extends JPanel {
	
	private final JLabel title;
	
	public Header() {
		super(new FlowLayout(FlowLayout.CENTER));
		
		/**
		 * Honestly, instead of doing .toUpperCase(), I could just make the String uppercase
		 * but I don't know whether it looks better uppercase or lowercase.
		 * 
		 * Also, sorry for the excessive use of 'this.'
		 * It's a habit I learned because when I first learned Java, I did it a lot.
		 */
		this.title = new JLabel("Add Car".toUpperCase());
		this.title.setFont(new Font("Arial Bold", Font.PLAIN, 20));
		this.title.setForeground(Window.TITLES);
		
		this.add(this.title);
		
		this.setBackground(Window.BACKGROUND);
	}

}
