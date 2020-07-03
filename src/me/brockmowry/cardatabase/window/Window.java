package me.brockmowry.cardatabase.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import me.brockmowry.cardatabase.CarDatabase;
import me.brockmowry.cardatabase.car.Car;
import me.brockmowry.cardatabase.car.CarManager;
import me.brockmowry.cardatabase.file.CarFileFilter;
import me.brockmowry.cardatabase.file.FileManager;
import me.brockmowry.cardatabase.window.components.MenuBar;
import me.brockmowry.cardatabase.window.components.PanelDatabase;
import me.brockmowry.cardatabase.window.components.form.PanelForm;
import me.brockmowry.cardatabase.window.components.form.event.SubmitEvent;
import me.brockmowry.cardatabase.window.components.form.event.SubmitListener;

public class Window extends JFrame {

	private final MenuBar menuBar;
	private final PanelForm form;
	private final PanelDatabase database;
	
	private final CarManager carManager;
	private final FileManager fileManager;
	
	private final JFileChooser fileChooser;

	public Window() {
		super("Car Database | " + CarDatabase.VERSION);
		setLayout(new BorderLayout());

		carManager = new CarManager();
		
		fileManager = new FileManager(this);
		
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new CarFileFilter());
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		menuBar = new MenuBar(this);
		setJMenuBar(menuBar);

		form = new PanelForm();
		form.addSubmitListener(new SubmitListener() {
			@Override
			public void submitTriggered(final SubmitEvent event) {
				carManager.addCar(event.getCar());
				database.addCar(event.getCar());
				
				for (final JTextField field : form.getTextFields().values()) {
					field.setText("");
				}
				
				JOptionPane.showMessageDialog(null, "The car has been successfully added to the database.", 
						"Car added", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		add(form, BorderLayout.WEST);
		
		database = new PanelDatabase(this);
		try {
			fileManager.loadFile();
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		
		add(database, BorderLayout.CENTER);
		
		final JPanel footer = new JPanel();
		footer.setPreferredSize(new Dimension(getWidth(), 5));
		footer.setBackground(new Color(30, 30, 30));
		add(footer, BorderLayout.SOUTH);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent event) {
				close();
			}
		});
		
		try {
			final InputStream stream = this.getClass().getResourceAsStream("icon/icon.png");
			final ImageIcon icon = new ImageIcon(ImageIO.read(stream));
			this.setIconImage(icon.getImage());
		} catch (final Exception exception) {}

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(1280, 720);
		setResizable(true);
		setLocationRelativeTo(null);
	}

	public void close() {
		final int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirm Exit",
				JOptionPane.ERROR_MESSAGE, JOptionPane.YES_NO_OPTION);
		if (option == 0) {
			try {
				fileManager.saveFile();
			} catch (final Exception exception) {
				exception.printStackTrace();
			}
			
			System.exit(0);
		}
	}

	public PanelForm getForm() {
		return form;
	}
	
	public PanelDatabase getDatabase() {
		return database;
	}
	
	public CarManager getCarManager() {
		return carManager;
	}
	
	public FileManager getFileManager() {
		return fileManager;
	}
	
	public JFileChooser getFileChooser() {
		return fileChooser;
	}

}
