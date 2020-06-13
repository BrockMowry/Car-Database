package cardatabase.car;

import java.io.Serializable;

public class Car implements Serializable {
	
	public static int carCount = 0;
	
	private final String brand;
	private final String model;
	private final int year;
	private final String engine;
	private final double kms;
	private final String color;
	private final int price;
	
	private final int uniqueID;
	
	public Car(final String brand, final String model, final int year, 
			final String engine, final double kms, final String color, final int price) {
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.engine = engine;
		this.kms = kms;
		this.color = color;
		this.price = price;
		
		this.uniqueID = carCount++;
	}

	public String getBrand() {
		return this.brand;
	}

	public String getModel() {
		return this.model;
	}

	public int getYear() {
		return this.year;
	}

	public String getEngine() {
		return this.engine;
	}

	public double getKms() {
		return this.kms;
	}

	public String getColor() {
		return this.color;
	}
	
	public int getUniqueID() {
		return this.uniqueID;
	}
	
	public int getPrice() {
		return this.price;
	}

	@Override
	public String toString() {
		return    "Brand: " + this.brand + "\n"
				+ "Model: " + this.model + "\n"
				+ "Year: " + this.year + "\n"
				+ "Engine: " + this.engine + "\n"
				+ "Kms: " + this.kms + "\n"
				+ "Color: " + this.color + "\n"
				+ "Price: " + this.price + "\n\n";
				
	}
}
