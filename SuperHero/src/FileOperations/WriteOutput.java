package FileOperations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class WriteOutput {
	 public void writing(String path,String toWrite) {
	        try {
	        	FileOutputStream is;
	        	File statText = new File(path+"/FurkanKayaSampleOutput.txt");
	        	if (statText.exists()) {
	        	     is = new FileOutputStream(statText);
	        	}
	        	else{
	        		 is = new FileOutputStream(statText);	
	        	}
	            
	            OutputStreamWriter osw = new OutputStreamWriter(is);    
	            Writer w = new BufferedWriter(osw);
	            w.write(toWrite);
	            w.close();
	        } catch (IOException e) {
	            System.err.println("Problem writing to the file SampleOutput.txt");
	        }
	        System.out.println("Output file created on "+path);
	    }
}
