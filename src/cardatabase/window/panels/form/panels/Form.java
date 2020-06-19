package cardatabase.window.panels.form.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		final GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 86, 86, 0 };
		layout.rowHeights = new int[] { 20, 20, 20, 20, 20, 20, 20, 0 };
		layout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		layout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		
		this.setLayout(layout);
		
		for (int i = 0; i < this.labels.length; i++) {
			final JLabel jLabel = new JLabel(this.labels[i] + ":");
			final GridBagConstraints labelConstraints = new GridBagConstraints();
			labelConstraints.fill = GridBagConstraints.BOTH;
			labelConstraints.insets = new Insets(5, 10, 10, 10);
			labelConstraints.gridx = 0;
			labelConstraints.gridy = i;
			this.add(jLabel, labelConstraints);
			
			final JTextField textField = new JTextField();
			final GridBagConstraints textConstraints = new GridBagConstraints();
			textConstraints.fill = GridBagConstraints.BOTH;
			textConstraints.insets = new Insets(5, 10, 10, 10);
			textConstraints.gridx = 1;
			textConstraints.gridy = i;
			this.add(textField, textConstraints);
			
			this.textFields.put(this.labels[i], textField);
		}
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
