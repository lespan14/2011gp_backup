package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.Application;
import hk.edu.polyu.comp.comp2021.clevis.log.Log;

public class Launcher {
    public static void main(String[] args){
    	System.out.println("starting up...");
    	String html = "log.html";
    	String txt = "log.txt";
    	
    	if (args.length == 1) {
    		if (args[0] != null) {
        		html = args[0];
        	}
    	}
    	
    	else if (args.length > 1) {
    		if (args[0] != null) {
        		html = args[0];
        	}
    		if (args[1] != null) {
        		txt = args[1];
        	}
    	}
    	
    	
    	Application game = new Application(html, txt);
    	
        // Initialize and utilize the system
    }
}
