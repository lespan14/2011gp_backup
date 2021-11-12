package hk.edu.polyu.comp.comp2021.clevis.model;
/*import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Canvas;
import java.awt.Graphics;*/
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Clevis {
	public JFrame frame;
	public Canvas canvas;
	private String title;
	private int width;
	private int height;
	public Clevis(){
    	this.frame = new JFrame();	
    	this.canvas = new Canvas();
    	this.width = 500;
    	this.height = 800;
    	this.title = "Testing";
    	init();
	}
	
    public Clevis(String title, int width, int height){
    	this.frame = new JFrame();	
    	this.canvas = new Canvas();
    	this.width = width;
    	this.height = height;
    	this.title = title;
    	init();
    	}
    
    public void init() {
    	this.frame.setTitle(title);
    	this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.frame.setSize(width,height);
    	this.frame.setLocationRelativeTo(null);
    	this.frame.setResizable(false);
    	this.frame.setVisible(true);
    	
    	//JPanel pan = new JPanel();
    	//pan.setLayout(new FlowLayout(FlowLayout.CENTER, 200,200));
    	//pan.setBackground(Color.BLACK);
    	//this.frame.add(pan,BorderLayout.NORTH);
    	this.canvas.setBackground(Color.WHITE);
    	this.canvas.setPreferredSize(new Dimension(width,height));
    	this.canvas.setMaximumSize(new Dimension(width,height));
    	this.canvas.setMinimumSize(new Dimension(width,height));
    	this.frame.add(canvas);
    	this.frame.pack();
    }
    
	
	public static class NameShape {
            private String name;
            private Shape shape;
            public NameShape (String name, Shape shape){
            this.name=name;
            this.shape=shape;
        }
        public String getName(){return name;}
        public Shape getShape(){return shape;}
        }
  
}
	public List<NameShape> shapes= new ArrayList<>();

	public void rectangle (String n, double x, double y, double w, double h){
        	// g.drawRect(x, y, w, h);
		shapes.add(new NameShape(n, new Rectangle2D.Double(x, y, w, h)));
    	}

	public void circle (String n, double x, double y, double r){
        	// g.drawOval(x, y, r, r);
		shapes.add(new NameShape(n, new Ellipse2D.Double(x, y, r, r)));
    	}
    
	public void square (String n, double x, double y, double l){
       	 	// g.drawRect(x, y, l, l);
		shapes.add(new NameShape(n, new Rectangle2D.Double(x, y, l, l)));
   	}

	public void line (String n, double x1, double y1, double x2, double y2){
        	// g.drawLine(x1, y1, x2, y2);
		shapes.add(new NameShape(n, new Line2D.Double(x1, y1, x2, y2)));
    	}

	//// Class used to define the shapes to be drawn
	public void paint(Graphics g){

        	ArrayList<Color> shapeStroke = new ArrayList<Color>();
        	Graphics2D graphSettings = (Graphics2D) g;
        	// Defines the line width of the stroke
        	graphSettings.setStroke(new BasicStroke(2));
        	// Iterators created to cycle through strokes and fills
        	Iterator<Color> strokeCounter = shapeStroke.iterator();
        	shapeStroke.add(Color.GREEN);
		
        	for (NameShape s: shapes){
            	graphSettings.setPaint(strokeCounter.next());
            	graphSettings.draw(s.getShape());
        	}
    	}


//ref: https://stackoverflow.com/questions/28839765/how-do-i-create-an-object-of-an-arraylist-shape-in-java
// https://xiu2.net/it/details/6102e2b679193629343164a5

