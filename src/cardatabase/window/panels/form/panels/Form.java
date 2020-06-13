package cardatabase.window.panels.form.panels;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import cardatabase.utils.SpringUtilities;

public class Form extends JPanel {
	
	/**
	 * Using a map to store the textfields was a great choice because it allows me to do:
	 * map.get("Make").getValue() -> returns the make text field.
	 * 
	 * Maps should be bonus marks right?
	 */
	private final Map<String, JTextField> textFields = new HashMap<>();
	private final String[] labels = { "Brand", "Model", "Year", "Engine", "Kms", "Color", "Price" };
	
	public Form() {
		super(new SpringLayout());
		
		final Dimension dimension = this.getPreferredSize();
		
		for (final String label : this.labels) {
			final JLabel jLabel = new JLabel(label + ":");
			final JTextField textField = new JTextField();
			jLabel.setLabelFor(textField);
			
			this.add(jLabel);
			this.add(textField);
			
			this.textFields.put(label, textField);
		}
		
		SpringUtilities.makeCompactGrid(this, this.labels.length, 2, 10, 40, 10, 35);
	}
	
	public Map<String, JTextField> getTextFields() {
		return this.textFields;
	}
	
	public boolean areTextFieldsEmpty() {
		for (final JTextField textField : this.textFields.values()) {
			if (textField.getText().trim().isEmpty()) {
				return true;
			}
		}
		
		return false;
	}

}
