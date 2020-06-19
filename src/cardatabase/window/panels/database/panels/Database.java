package cardatabase.window.panels.database.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cardatabase.car.Car;
import cardatabase.window.Window;

public class Database extends JPanel {

	private final String[] columns = { "ID", "Brand", "Model", "Year", "Engine", "Kms", "Color", "Price" };

	public final JTable table;
	public final DefaultTableModel tableModel;

	private final JPopupMenu popupMenu;
	private final JMenuItem delete;

	public Database(final Window window) {
		super(new BorderLayout());

		this.tableModel = new DefaultTableModel(0, this.columns.length) {
			@Override
			public boolean isCellEditable(final int row, final int column) {
				return false;
			}
		};
		this.tableModel.setColumnIdentifiers(this.columns);

		this.table = new JTable(this.tableModel);

		this.popupMenu = new JPopupMenu();
		this.delete = new JMenuItem("Delete");
		try {
			final ImageIcon icon = new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("icon/trash.png")));
			this.delete.setIcon(icon);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		this.delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				final int row = table.getSelectedRow();
				final int uniqueID = (Integer) tableModel.getValueAt(row, 0);

				final Car car = window.carManager.getCarById(uniqueID);
				window.carManager.removeCar(car);
				
				tableModel.removeRow(row);
			}
		});
		this.popupMenu.add(this.delete);

		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent event) {
				if ((table.getSelectedRow() < 0) && (event.isPopupTrigger())) {
					popupMenu.show(event.getComponent(), event.getX(), event.getY());
				}
			}
		});

		this.add(new JScrollPane(this.table), BorderLayout.CENTER);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
	}

	public void addCarToDatabase(final Car car) {
		this.tableModel.addRow(new Object[] { car.getUniqueID(), car.getBrand(), car.getModel(), car.getYear(),
				car.getEngine(), car.getKms(), car.getColor(), car.getPrice() });
	}

}
