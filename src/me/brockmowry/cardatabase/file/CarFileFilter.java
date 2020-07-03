package me.brockmowry.cardatabase.file;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class CarFileFilter extends FileFilter {
	
	@Override
	public String getDescription() {
		return ".car | Car Database file";
	}
	
	@Override
	public boolean accept(final File file) {
		if (file.isDirectory()) return true;
		
	    final String fileName = file.getName();
	    final int i = fileName.lastIndexOf('.');
	    if (i > 0 && i < fileName.length() - 1) {
	        if (fileName.substring(i + 1).toLowerCase().equals("car")) {
	            return true;
	        }
	    }

	    return false;
	}

}
