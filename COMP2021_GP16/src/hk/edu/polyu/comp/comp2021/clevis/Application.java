package hk.edu.polyu.comp.comp2021.clevis;


import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.log.*;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.util.Scanner;

public class Application{
	
	//private Thread thread;
	//public int width, height;
	//String title;
	//private boolean running = false;
	private Clevis clevis;
	Log logger;
	//BufferStrategy bs;
	//public Graphics g;
	
	public Application(String html, String filename) {
		clevis = new Clevis();
		logger = new Log(html, filename);
		Scanner console = new Scanner(System.in);
		instruction();
		while (true) {
			menu();
			String input = "";
			String command ="";
			input = console.nextLine();
			String[] tokens = input.split("\\s+");
			command = tokens[0];
			logger.WriteLog(input);
			try {
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
				while (i < tokens.length) {
					String [] tempList = new String[nList.length+1];
					tempList = nList.clone();
					tempList[tempList.length-1] = tokens[i];
					nList = tempList;
					i++;
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
			case "pickandmove" :
				Double pmx =  Double.parseDouble(tokens[1]);
				Double pmy =  Double.parseDouble(tokens[2]);
				Double pmdx =  Double.parseDouble(tokens[3]);
				Double pmdy =  Double.parseDouble(tokens[4]);
				clevis.pickandmove(pmx,pmy,pmdx,pmdy);
			case "intersect" :
				String iname1 = tokens[1];
				String iname2 = tokens[2];
				clevis.intersect(iname1, iname2);
				break;
			case "list" :
				String liname = tokens[1];
				clevis.list(liname);
				break;
			case "listAll" :
				clevis.listAll();
				break;
			case "quit" :
				clevis.quit();
				break;
			default :
				System.out.println("Invalid input, please input again");
				break;
				
			}
			} catch (Exception e){
				System.out.println("An error has been detected, please input again");
			}
			
		}
		
	} 
	
	
	//print menu screen
	private static void menu() {
		System.out.println("Standing by for new command...");
		System.out.print(">_");
	}
	
	private static void instruction() {
        System.out.println("Reminders:");
        System.out.println("Please change the value in [] and separate each value inputted by a space.");
        System.out.println("X and Y coordinates for circle, square and rectangle represent the top left corner of the shape.");
        System.out.println("\nInput format:");
        System.out.println("To add a circle:                          circle [name] [X] [Y] [radius]");
        System.out.println("To add a square:                          square [name] [X] [Y] [width] [height]");
        System.out.println("To add a rectangle:                       rectangle [name] [X] [Y] [width] [height]");
        System.out.println("To add a line:                            line [name] [X1] [Y1] [X2] [Y2]");
        System.out.println("To group shapes:                          group [name of group] [name of shape1] [name of shape2]...");
        System.out.println("To ungroup shapes:                        ungroup [name of group]");
        System.out.println("To delete a shape:                        delete [name]");
        System.out.println("To check minimum bounding box of a shape: boundingbox [name]");
        System.out.println("To move a shape:                          move [name] [horizontal distance] [vertical distance]");
	System.out.println("To move the newest shape at point x,y:    pickandmove [x] [y] [horizontal distance] [vertical distance]");
        System.out.println("To check whether two shapes intersect:    intersect [name of shape1] [name of shape2]");
        System.out.println("To check information of a shape:          list [name]");
        System.out.println("To check information of all shapes:       listAll");
        System.out.println("To terminate the program:                 quit");
        System.out.println();
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
