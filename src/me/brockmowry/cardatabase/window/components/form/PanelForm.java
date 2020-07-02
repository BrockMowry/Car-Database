package me.brockmowry.cardatabase.window.components.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.EventListenerList;

import me.brockmowry.cardatabase.car.Car;
import me.brockmowry.cardatabase.window.components.form.event.SubmitEvent;
import me.brockmowry.cardatabase.window.components.form.event.SubmitListener;

public class PanelForm extends JPanel {

	private final EventListenerList eventListenerList = new EventListenerList();

	private final JPanel formPanel;
	private final String[] labels = { "Make", "Model", "Year", "Kms", "Color", "Price" };
	private final Map<String, JTextField> textFields = new HashMap<>();

	private final JPanel buttonPanel;
	private final JButton submitButton;

	public PanelForm() {
		setPreferredSize(new Dimension(225, getPreferredSize().height));

		setLayout(new BorderLayout());

		formPanel = new JPanel();
		final GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 86, 86, 0 };
		layout.rowHeights = new int[] { 20, 20, 20, 20, 20, 20, 20, 0 };
		layout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		layout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		formPanel.setLayout(layout);
		for (int i = 0; i < this.labels.length; i++) {
			final JLabel jLabel = new JLabel(this.labels[i] + ":");
			final GridBagConstraints labelConstraints = new GridBagConstraints();
			labelConstraints.fill = GridBagConstraints.BOTH;
			labelConstraints.insets = new Insets(5, 10, 10, 10);
			labelConstraints.gridx = 0;
			labelConstraints.gridy = i;
			formPanel.add(jLabel, labelConstraints);

			final JTextField textField = new JTextField();
			final GridBagConstraints textConstraints = new GridBagConstraints();
			textConstraints.fill = GridBagConstraints.BOTH;
			textConstraints.insets = new Insets(5, 10, 10, 10);
			textConstraints.gridx = 1;
			textConstraints.gridy = i;
			formPanel.add(textField, textConstraints);

			textFields.put(labels[i], textField);
		}
		add(formPanel, BorderLayout.CENTER);

		buttonPanel = new JPanel();
		submitButton = new JButton("Submit");
		submitButton.setPreferredSize(new Dimension(getPreferredSize().width - 10, 30));
		submitButton.setForeground(Color.white);
		submitButton.setBackground(new Color(30, 30, 30));
		submitButton.setBorderPainted(false);
		submitButton.setOpaque(true);
		submitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(final MouseEvent event) {
				submitButton.setBackground(new Color(50, 50, 50));
			}

			@Override
			public void mouseExited(final MouseEvent event) {
				submitButton.setBackground(new Color(30, 30, 30));
			}
		});
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				if (areTextFieldsEmpty()) {
					JOptionPane.showMessageDialog(null, "Invalid input. Please try again.", "Invalid input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				final String make = getText("Make").trim();
				final String model = getText("Model").trim();
				
				int year = 0;
				try {
					year = Integer.parseInt(getText("Year").trim());
				} catch (final NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Invalid year input. Please try again.", "Invalid input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				double kms = 0;
				try {
					kms = Double.parseDouble(getText("Kms").trim());
				} catch (final NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Invalid kms input. Please try again.", "Invalid input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				final String color = getText("Color").trim();
				
				int price = 0;
				try {
					price = Integer.parseInt(getText("Price").trim());
				} catch (final NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Invalid price input. Please try again.", "Invalid input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				fireSubmitEvent(new SubmitEvent(this, new Car(make, model, year, kms, color, price)));
			}
		});
		buttonPanel.add(submitButton);

		add(buttonPanel, BorderLayout.SOUTH);

		final EmptyBorder inner = new EmptyBorder(2, 2, 2, 2);
		final CompoundBorder outer = new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Add Car"));
		setBorder(BorderFactory.createCompoundBorder(outer, inner));
	}

	private void fireSubmitEvent(final SubmitEvent event) {
		final Object[] listeners = eventListenerList.getListenerList();

		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i].equals(SubmitListener.class)) {
				((SubmitListener) listeners[i + 1]).submitTriggered(event);
			}
		}
	}
	
	public void addSubmitListener(final SubmitListener listener) {
		eventListenerList.add(SubmitListener.class, listener);
	}
	
	public boolean areTextFieldsEmpty() {
		for (final JTextField textField : this.textFields.values()) {
			if (textField.getText().trim().isEmpty()) {
				return true;
			}
		}
		
		return false;
	}

	public String getText(final String textField) {
		return textFields.get(textField).getText();
	}

	public Map<String, JTextField> getTextFields() {
		return textFields;
	}

}
