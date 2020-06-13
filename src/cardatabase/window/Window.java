package cardatabase.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cardatabase.car.Car;
import cardatabase.car.CarManager;
import cardatabase.utils.file.FileUtilities;
import cardatabase.window.panels.PanelHeader;
import cardatabase.window.panels.database.PanelDatabase;
import cardatabase.window.panels.form.PanelForm;
import cardatabase.window.panels.form.event.SubmitEvent;
import cardatabase.window.panels.form.event.SubmitListener;

public class Window extends JFrame {

	public static final Color TITLES = Color.WHITE;

	public static final Color BACKGROUND = new Color(35, 35, 35);
	public static final Color BACKGROUND_HOVERED = new Color(50, 50, 50);
	public static final Color BACKGROUND_CLICKED = new Color(65, 65, 65);

	private final PanelHeader header;
	private final PanelForm form;
	private final PanelDatabase database;

	public final CarManager carManager;

	private final FileUtilities fileUtilities;

	public Window() {
		super("Car Database | CCT");
		this.setLayout(new BorderLayout());
		
		this.carManager = new CarManager();
		
		this.fileUtilities = new FileUtilities();
		try {
			this.fileUtilities.loadFile(this);
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		
		this.header = new PanelHeader();
		this.add(this.header, BorderLayout.NORTH);
		
		this.form = new PanelForm();
		this.form.addSubmitListener(new SubmitListener() {
			@Override
			public void submitTriggered(final SubmitEvent event) {
				carManager.addCar(event.getCar());
				database.addCarToDatabase(event.getCar());
				
				for (final JTextField field : form.form.getTextFields().values()) {
					field.setText("");
				}
				
				JOptionPane.showMessageDialog(null, "The car has been successfully added to the database.", 
						"Car added", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		this.add(this.form, BorderLayout.WEST);
		
		this.database = new PanelDatabase(this);
		for (final Car car : this.carManager.getCars()) {
			this.database.addCarToDatabase(car);
		}
		
		this.add(this.database, BorderLayout.CENTER);
		
		final JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
		footer.setBackground(BACKGROUND);
		this.add(footer, BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		try {
			final InputStream stream = this.getClass().getResourceAsStream("icon/icon.png");
			final ImageIcon icon = new ImageIcon(ImageIO.read(stream));
			this.setIconImage(icon.getImage());
		} catch (final Exception exception) {
			System.out.println("I know for a fact that this exception will not get printed to the screen.");
		}
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent event) {
				closeWindow();
			}
		});

	}

	private void closeWindow() {
		final int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirm exit",
				JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		if (option == 0) {
			try {
				this.fileUtilities.saveFile(this);
			} catch (final Exception exception) {
				exception.printStackTrace();
			}
			
			System.exit(0);
		}
	}

}
