package hk.edu.polyu.comp.comp2021.clevis.log;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class Log {
	private String filename;
	private String htmlname;
	private static int counter; 
	
	public Log(String htmlname, String filename) {
		try {
			String currentPath = new java.io.File(".").getCanonicalPath();
			this.filename = currentPath + "\\" + filename;
			this.htmlname = currentPath + "\\" + htmlname;
			
			File logFile = new File(this.filename);
			logFile.setWritable(true);
			logFile.setReadable(true);
			
			File htmlFile = new File(this.htmlname);
			htmlFile.setWritable(true);
			htmlFile.setReadable(true);
			
			//----------Setting up txt log file-----------------//
			//System.out.println("creating file as " + currentPath + "\\" + this.filename);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			FileWriter logger = new FileWriter(logFile, true);
			logger.write(dtf.format(now) + '\n');
			logger.write("Clevis Log \n\n");
			//System.out.println("written");
			logger.close();
			
			//----------Setting up html log file-----------------//
			FileWriter html = new FileWriter(htmlFile, true);
			html.write("<!DOCTYPE html>\n"
					+ "<html>\n"
					+ "<style>\n"
					+ "table, th, td {\n"
					+ "  border:1px solid black;\n"
					+ "}\n"
					+ "</style>\n"
					+ "<body>\n"
					+ "<table style=\"width:100%\">\n");
			html.close();
			
		} catch (IOException e) {
			System.out.println("error");
			e.printStackTrace();
		}
		
	}
	
	public void WriteLog(String text) {
		try {
			File logFile = new File(this.filename);
			FileWriter logger = new FileWriter(logFile, true);
			logger.write(text);
			logger.write('\n');
			logger.close();
			
		}
		catch (IOException e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}
	
	
}
