package cardatabase.window.panels.database;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import cardatabase.car.Car;
import cardatabase.window.Window;
import cardatabase.window.panels.database.panels.Database;
import cardatabase.window.panels.database.panels.Header;

public class PanelDatabase extends JPanel {
	
	private final Header header;
	private final Database database;
	
	public PanelDatabase(final Window window) {
		super(new BorderLayout());
		
		this.header = new Header();
		this.add(this.header, BorderLayout.NORTH);
		
		this.database = new Database(window);
		this.add(this.database, BorderLayout.CENTER);
		
		final LineBorder inner = new LineBorder(Window.BACKGROUND, 2);
		final EmptyBorder outer = new EmptyBorder(5, 5, 5, 5);
		this.setBorder(BorderFactory.createCompoundBorder(outer, inner));
	}
	
	public void addCarToDatabase(final Car car) {
		this.database.addCarToDatabase(car);
	}

}
