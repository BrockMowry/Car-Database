package me.brockmowry.cardatabase.window.components.form.event;

import java.util.EventObject;

import me.brockmowry.cardatabase.car.Car;

public class SubmitEvent extends EventObject {
	
	private final Car car;
	
	public SubmitEvent(final Object source, final Car car) {
		super(source);
		this.car = car;
	}
	
	public Car getCar() {
		return car;
	}

}
