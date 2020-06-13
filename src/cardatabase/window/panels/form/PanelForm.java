package cardatabase.window.panels.form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.EventListenerList;

import cardatabase.car.Car;
import cardatabase.window.Window;
import cardatabase.window.panels.form.event.SubmitEvent;
import cardatabase.window.panels.form.event.SubmitListener;
import cardatabase.window.panels.form.panels.Button;
import cardatabase.window.panels.form.panels.Form;
import cardatabase.window.panels.form.panels.Header;

public class PanelForm extends JPanel {
	
	private final EventListenerList eventListenerList = new EventListenerList();
	
	private final Header header;
	public final Form form;
	private final Button button;
	
	public PanelForm() {
		super(new BorderLayout());
		
		final Dimension dimension = this.getPreferredSize();
		dimension.width = 250;
		this.setPreferredSize(dimension);
		
		this.header = new Header();
		this.add(this.header, BorderLayout.NORTH);
		
		this.form = new Form();
		this.add(this.form, BorderLayout.CENTER);
		
		this.button = new Button();
		this.add(this.button, BorderLayout.SOUTH);
		this.button.getSubmitButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				if (form.areTextFieldsEmpty()) {
					JOptionPane.showMessageDialog(null, "Invalid input. Please try again.", 
							"Invalid input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				final String brand = form.getTextFields().get("Brand").getText().trim();
				final String model = form.getTextFields().get("Model").getText().trim();
				
				int year = 0;
				try {
					year = Integer.parseInt(form.getTextFields().get("Year").getText().trim());
				} catch (final NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Invalid year input. Please try again.", 
							"Invalid year input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				final String engine = form.getTextFields().get("Engine").getText().trim();
				
				double kms = 0;
				try {
					kms = Double.parseDouble(form.getTextFields().get("Kms").getText().trim());
				} catch (final NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Invalid kms input. Please try again.", 
							"Invalid kms input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				final String color = form.getTextFields().get("Color").getText().trim();
				
				int price = 0;
				try {
					price = Integer.parseInt(form.getTextFields().get("Price").getText().trim());
				} catch (final NumberFormatException exception) {
					exception.printStackTrace();
				}
				
				fireSubmitEvent(new SubmitEvent(this, new Car(brand, model, year, engine, kms, color, price)));
			}
		});
		
		final LineBorder inner = new LineBorder(Window.BACKGROUND, 2);
		final EmptyBorder outer = new EmptyBorder(5, 5, 5, 0);
		this.setBorder(BorderFactory.createCompoundBorder(outer, inner));
	}
	
	/**
	 * I LOVE STACKOVERFLOW.com
	 */
	
	public void addSubmitListener(final SubmitListener event) {
		this.eventListenerList.add(SubmitListener.class, event);
	}
	
	private void fireSubmitEvent(final SubmitEvent event) {
		final Object[] listeners = this.eventListenerList.getListenerList();
		
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i].equals(SubmitListener.class)) {
				((SubmitListener) listeners[i + 1]).submitTriggered(event);
			}
		}
	}

}
