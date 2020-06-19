package cardatabase.window.panels.form.panels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import cardatabase.window.Window;

public class Button extends JPanel {
	
	private final JButton submit;
	
	public Button() {
		this.submit = new JButton("Submit".toUpperCase());
		this.submit.setPreferredSize(new Dimension(225, 30));
		this.submit.setForeground(Window.TITLES);
		this.submit.setBackground(Window.BACKGROUND);
		this.submit.setBorderPainted(false);
		this.submit.setOpaque(true);

		this.submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(final MouseEvent event) {
				submit.setBackground(Window.BACKGROUND_HOVERED);
			}
			
			@Override
			public void mouseExited(final MouseEvent event) {
				submit.setBackground(Window.BACKGROUND);
			}
		});
		
		this.add(this.submit);
	}
	
	public JButton getSubmitButton() {
		return this.submit;
	}

}
