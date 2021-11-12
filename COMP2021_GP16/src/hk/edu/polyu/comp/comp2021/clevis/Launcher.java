package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.Application;
import hk.edu.polyu.comp.comp2021.clevis.log.Log;

public class Launcher {
    public static void main(String[] args){
    	Log logger = new Log("", "LogTest.txt");
    	//System.out.println("logger creation done");
    	logger.WriteLog("testing message");
    	logger.WriteLog("testing message 2");
    	//Application game = new Application("Test", 500, 800);
    	//game.start();
    	
        // Initialize and utilize the system
    }
}
