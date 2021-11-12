package hk.edu.polyu.comp.comp2021.clevis.log;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
	private String filename;
	private String htmlname;
	
	public Log(String htmlname, String filename) {
		this.filename = filename;
		this.htmlname = htmlname;
		try {
			String currentPath = new java.io.File(".").getCanonicalPath();
			File logFile = new File(currentPath + "\\" + this.filename);
			logFile.setWritable(true);
			logFile.setReadable(true);
			
			//System.out.println("creating file as " + currentPath + "\\" + this.filename);
			
			FileWriter logger = new FileWriter(logFile);
			logger.write("overwritten");
			//System.out.println("written");
			logger.close();
		} catch (IOException e) {
			System.out.println("error");
			e.printStackTrace();
		}
		
	}
	
	
}

