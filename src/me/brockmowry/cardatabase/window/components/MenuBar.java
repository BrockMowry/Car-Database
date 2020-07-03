package me.brockmowry.cardatabase.window.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import me.brockmowry.cardatabase.window.Window;

public class MenuBar extends JMenuBar {
	
	private final JMenu file;
	private final JMenuItem open, save, exit;
	
	private final JMenu edit;
	private final JMenuItem clear;
	
	private final JMenu view;
	private final JMenuItem showView;
	private final JCheckBoxMenuItem form, database;
	
	public MenuBar(final Window window) {
		file = new JMenu("File");
		open = new JMenuItem("Open");
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				final int value = window.getFileChooser().showOpenDialog(window);
				
				if (value == JFileChooser.APPROVE_OPTION) {
					final File file = window.getFileChooser().getSelectedFile();
					
					if (file == null) return;
					
					try {						
						window.getForm().clear();
						window.getDatabase().clear();
						window.getCarManager().clear();
						
						window.getFileManager().loadFile(file);
					} catch (final Exception e) {
						e.printStackTrace();
					}
					
					System.out.println(window.getCarManager().getCars().size());
				}
			}
		});
		save = new JMenuItem("Save");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				try {
					window.getFileManager().saveFile();
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
		exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				window.close();
			}
		});
		file.add(open);
		file.add(save);
		file.addSeparator();
		file.add(exit);
		add(file);
		
		edit = new JMenu("Edit");
		clear = new JMenuItem("Clear");
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				window.getForm().clear();
				window.getDatabase().clear();
				window.getCarManager().clear();
				
				JOptionPane.showMessageDialog(null, "The database has been cleared.", 
						"Database cleared", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		edit.add(clear);
		add(edit);
		
		view = new JMenu("View");
		showView = new JMenu("Show View");
		form = new JCheckBoxMenuItem("Form", true);
		form.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				window.getForm().setVisible(!window.getForm().isVisible());
			}
		});
		database = new JCheckBoxMenuItem("Database", true);
		database.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				window.getDatabase().setVisible(!window.getDatabase().isVisible());
			}
		});
		showView.add(form);
		showView.add(database);
		view.add(showView);
		add(view);
	}

}
