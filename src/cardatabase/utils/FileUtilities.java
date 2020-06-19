package cardatabase.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import cardatabase.car.Car;
import cardatabase.window.Window;
import cardatabase.window.panels.database.PanelDatabase;

public class FileUtilities {
	
	private final Window window;
	private final File carFile = new File(System.getProperty("user.home"), "cars.car");
	
	public FileUtilities(final Window window) {
		this.window = window;
	}
	
	public void loadFile() throws Exception {
		if (!this.carFile.exists()) {
			this.carFile.createNewFile();
			return;
		}
		
		if (this.carFile.length() == 0) return;
		
		final FileInputStream input = new FileInputStream(this.carFile);
		final ObjectInputStream objectInput = new ObjectInputStream(input);

		try {
			boolean loop = true;
			
			while (loop) {
				final Object object = objectInput.readObject();
				
				if (object != null) {
					this.window.carManager.addCar((Car) object);
					Car.carCount++;
					
					continue;
				}
				
				loop = false;
			}
		} catch (final Exception exception) {}
		
		objectInput.close();
		input.close();
	}
	
	public void saveFile() throws Exception {
		if (!this.carFile.exists()) {
			this.carFile.createNewFile();
		}
		
		final FileOutputStream output = new FileOutputStream(this.carFile);
		final ObjectOutputStream objectOutput = new ObjectOutputStream(output);
		
		for (final Car car : this.window.carManager.getCars()) {
			objectOutput.writeObject(car);
		}
		
		objectOutput.close();
		output.close();
	}

}
