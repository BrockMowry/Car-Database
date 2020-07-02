package me.brockmowry.cardatabase.car;

import java.io.Serializable;

public class Car implements Serializable {
	
	public static int carCount = 0;
	
	private final String make;
	private final String model;
	private final int year;
	private final double kms;
	private final String color;
	private final int price;
	
	private final int uniqueID;
	
	public Car(final String make, final String model, final int year, final double kms, final String color, final int price) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.kms = kms;
		this.color = color;
		this.price = price;
		
		this.uniqueID = carCount++;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}

	public double getKms() {
		return kms;
	}

	public String getColor() {
		return color;
	}

	public int getPrice() {
		return price;
	}

	public int getUniqueID() {
		return uniqueID;
	}

}
