package me.brockmowry.cardatabase.car;

import java.util.ArrayList;
import java.util.List;

public class CarManager {

	private final List<Car> carList = new ArrayList<>();

	public void addCar(final Car car) {
		this.carList.add(car);
	}

	public void removeCar(final Car car) {
		this.carList.remove(car);
	}
	
	public void clear() {
		carList.clear();
	}

	public List<Car> getCars() {
		return this.carList;
	}

	public Car getCarById(final int uniqueID) {
		return this.carList.stream().filter(car -> car.getUniqueID() == uniqueID).findFirst().orElse(null);
	}

}
