package cardatabase.window.panels.form.event;

import java.util.EventObject;

import cardatabase.car.Car;

public class SubmitEvent extends EventObject {
	
	private final Car car;

	public SubmitEvent(final Object source, final Car car) {
		super(source);
		this.car = car;
	}
	
	public Car getCar() {
		return this.car;
	}
	
}
