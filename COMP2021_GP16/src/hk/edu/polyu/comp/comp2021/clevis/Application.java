package hk.edu.polyu.comp.comp2021.clevis;


import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.io.Console;

public class Application{
	
	//private Thread thread;
	//public int width, height;
	//String title;
	//private boolean running = false;
	private Clevis clevis;
	//BufferStrategy bs;
	//public Graphics g;
	
	public Application() {
		clevis = new Clevis();
		Console console = System.console();
		while (true) {
			menu();
			String input = "";
			String command ="";
			input = console.readLine();
			String[] tokens = input.split("\\s+");
			command = tokens[0];
			
			switch (command) {
			case "rectangle" :
				String recname = tokens[1];
				double rex = Double.parseDouble(tokens[2]);
				double rey = Double.parseDouble(tokens[3]);
				double rewidth = Double.parseDouble(tokens[4]);
				double reheight = Double.parseDouble(tokens[5]);
				clevis.rectangle(recname, rex, rey, rewidth, reheight);
				
				break;
			case "line" :
				String linename = tokens[1];
				double lix1 = Double.parseDouble(tokens[2]);
				double liy1 = Double.parseDouble(tokens[3]);
				double lix2 = Double.parseDouble(tokens[4]);
				double liy2 = Double.parseDouble(tokens[5]);
				clevis.line(linename, lix1, liy1, lix2, liy2);
				break;
			case "circle" :
				String cirname = tokens[1];
				double cix = Double.parseDouble(tokens[2]);
				double ciy = Double.parseDouble(tokens[3]);
				double cirad = Double.parseDouble(tokens[4]);
				clevis.circle(cirname, cix, ciy, cirad);
				break;
			case "square" :
				String squname = tokens[1];
				double squx = Double.parseDouble(tokens[2]);
				double squy = Double.parseDouble(tokens[3]);
				double squwidth = Double.parseDouble(tokens[4]);
				clevis.square(squname, squx, squy, squwidth);
				break;
			case "group" :
				String grname = tokens[1];
				int i =3;
				String [] nList = {tokens[2]};
				while (tokens[i] != null) {
					String [] tempList = new String[nList.length+1];
					tempList = nList.clone();
					tempList[tempList.length-1] = tokens[i];
					nList = tempList;
				}
				clevis.group(grname, nList);
				break;
			case "ungroup":
				String ungrname = tokens[1];
				clevis.ungroup(ungrname);
				break;
			case "delete":
				String delname = tokens[1];
				clevis.delete(delname);
				break;
			case "boundingbox" :
				String bouname = tokens[1];
				clevis.boundingbox(bouname);
				break;
			case "move" :
				String movname = tokens[1];
				Double dx =  Double.parseDouble(tokens[2]);
				Double dy =  Double.parseDouble(tokens[3]);
				clevis.move(movname, dx, dy);
				break;
			case "intersect" :
				String iname1 = tokens[1];
				String iname2 = tokens[2];
				clevis.intersect(iname1, iname2);
				break;
			case "quit" :
				clevis.quit();
				break;
			default :
				System.out.println("Invalid input, please input again\n");
				break;
				
			}
			
		}
		
	} 
	
	
	//print menu screen
	private static void menu() {
		System.out.println("Standing by for new command...");
		System.out.print(">_");
	}
	
	
	
	
	
	
	
	
	
	
	

    /*
    private void inital() {
    	
    }
    
    private void tick() {
    	
    }
    
    private void render() {
    	BufferStrategy bs = clevis.canvas.getBufferStrategy();
    	if (bs == null) {
    		clevis.canvas.createBufferStrategy(3);
    		return;
    	}
    	g = bs.getDrawGraphics();
    	
    	g.clearRect(10, 0, 500, 400);
    	
    	//drawing currently performed here.
    	//move this to clevis class for easier management
    	// g.drawRect(10, 10, 50, 70);
    	// Clevis.paint(g);
	    
    	bs.show();
    	g.dispose();
    }
    
    public void run() {
    	
    	inital();
    	
    	while(running) {
    		tick();
    		render();
    	}
    	
    	stop();
    }
    
    public synchronized void start() {
    	if(running) return;
    	running = true;
    	thread = new Thread(this);
    	thread.start();
    }
    
    public synchronized void stop() {
    	if (!running) return;
    	running = false;
    	try {
    		thread.join();
    	}
    	catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
    */
}
