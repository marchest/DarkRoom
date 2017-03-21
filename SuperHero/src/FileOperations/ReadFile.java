package FileOperations;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JFileChooser;

public class ReadFile {
	public String pickFile (){
		JFileChooser openFile = new JFileChooser();
		openFile.showOpenDialog(null);
		File file=openFile.getSelectedFile();
		return file.getAbsolutePath();
	}

	public ArrayList<String> read(File file){
		int lineCount=0;
		ArrayList<String> lineList=new ArrayList<String>(); 
		try {
			File fileDir = file;
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF-8"));
			String str;
			while ((str = in.readLine()) != null) {
				lineList.add(str);		
			}
			in.close();
		} 
		catch (UnsupportedEncodingException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return lineList;
	}
	
}

