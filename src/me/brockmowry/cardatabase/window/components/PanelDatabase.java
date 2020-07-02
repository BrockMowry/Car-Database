package me.brockmowry.cardatabase.window.components;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import me.brockmowry.cardatabase.car.Car;
import me.brockmowry.cardatabase.window.Window;

public class PanelDatabase extends JPanel {
	
	private final String[] columns = { "ID", "Brand", "Model", "Year", 
			"Kms", "Color", "Price" };
	private final JTable table;
	private final DefaultTableModel tableModel;
	
	private final JPopupMenu popupMenu;
	private final JMenuItem remove;
	
	public PanelDatabase(final Window window) {
		super(new BorderLayout());
		
		tableModel = new DefaultTableModel(0, this.columns.length) {
			@Override
			public boolean isCellEditable(final int row, final int column) {
				return false;
			}
		};
		tableModel.setColumnIdentifiers(columns);
		
		table = new JTable(tableModel);
		
		popupMenu = new JPopupMenu();
		remove = new JMenuItem("Remove");
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				final int row = table.getSelectedRow();
				final int uniqueID = (Integer) tableModel.getValueAt(row, 0);
				final Car car = window.getCarManager().getCarById(uniqueID);
				window.getCarManager().removeCar(car);
				tableModel.removeRow(row);
			}
		});
		popupMenu.add(remove);
		
		add(new JScrollPane(table), BorderLayout.CENTER);
		
		final EmptyBorder inner = new EmptyBorder(5, 5, 5, 5);
		final CompoundBorder outer = new CompoundBorder(new EmptyBorder(5, 0, 5, 0), new TitledBorder("Database"));
		setBorder(BorderFactory.createCompoundBorder(outer, inner));
	}
	
	public void addCar(final Car car) {
		tableModel.addRow(new Object[] { car.getUniqueID(), car.getMake(), car.getModel(), car.getYear(), 
				car.getKms(), car.getColor(), car.getPrice() });
	}

}
