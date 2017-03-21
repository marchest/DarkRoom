package FileOperations;
import javax.swing.JFileChooser;
import javax.swing.plaf.FileChooserUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.io.File;     

public class Chooser {
	public File pickFile (){
		JFileChooser openFile = new JFileChooser();
		openFile.setDialogTitle("Choose input file");
		openFile.showOpenDialog(null);
		File file=openFile.getSelectedFile();
		return file;
	}
	public String outputDir(){
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Choose directory for output file");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File f=chooser.getSelectedFile();
			return f.getAbsolutePath().toString();
			
		}
		else{
			return "no selected directory";
		}
	}
}
