package hk.edu.polyu.comp.comp2021.clevis;
//hi

import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Application implements Runnable{
	
	private Thread thread;
	public int width, height;
	String title;
	private boolean running = false;
	private Clevis clevis;
	BufferStrategy bs;
	public Graphics g;
	
	public Application(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		clevis = new Clevis(title, width, height);
	} 

    
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
    	
    	//drawing currently performed here
    	//move this to clevis class for easier management
    	g.drawRect(10, 10, 50, 70);
    	
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
}
