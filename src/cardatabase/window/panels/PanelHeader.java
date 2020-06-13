package cardatabase.window.panels;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import cardatabase.window.Window;

public class PanelHeader extends JPanel {
	
	private final JLabel title;
	
	public PanelHeader() {
		super(new FlowLayout(FlowLayout.CENTER));
		
		this.title = new JLabel("Car Database".toUpperCase());
		this.title.setFont(new Font("Arial Bold", Font.PLAIN, 30));
		this.title.setForeground(Window.TITLES);
		
		this.setBackground(Window.BACKGROUND);
		
		this.add(this.title);
	}

}
