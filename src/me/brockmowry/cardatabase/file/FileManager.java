package me.brockmowry.cardatabase.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import me.brockmowry.cardatabase.car.Car;
import me.brockmowry.cardatabase.window.Window;

public class FileManager {

	private final Window window;
	private final File carFile = new File(System.getProperty("user.home"), "cars.car");

	public FileManager(final Window window) {
		this.window = window;
	}

	public void loadFile() throws Exception {
		loadFile(carFile);
	}
	
	public void loadFile(final File file) throws Exception {
		if (!file.exists()) {
			file.createNewFile();
			return;
		}

		if (file.length() == 0)
			return;

		final FileInputStream input = new FileInputStream(file);
		final ObjectInputStream objectInput = new ObjectInputStream(input);

		try {
			boolean loop = true;

			while (loop) {
				final Object object = objectInput.readObject();

				if (object != null) {
					window.getCarManager().addCar((Car) object);
					window.getDatabase().addCar((Car) object);
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
		if (!carFile.exists()) {
			carFile.createNewFile();
		}

		final FileOutputStream output = new FileOutputStream(carFile, false);
		final ObjectOutputStream objectOutput = new ObjectOutputStream(output);

		for (final Car car : window.getCarManager().getCars()) {
			objectOutput.writeObject(car);
		}

		objectOutput.close();
		output.close();
	}

}
