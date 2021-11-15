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
	
	private static int GroupCounter = 0;
	/*
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
    */
	
	public static class NameShape {
            private String name;
            private Shape shape;
            private Boolean grouped = false;
            private int groupID;
            public NameShape (String name, Shape shape){
            this.name=name;
            this.shape=shape;
        }
        public String getName(){return name;}
        public Shape getShape(){return shape;}
        
        public void grouped(int ID){
        	grouped = true;
        	this.groupID = ID;}
        
        public void ungrouped(){grouped = false;}
    }
	
	public static class Groups {
        private String name;
        private int groupID;
        private List<NameShape> shapes= new ArrayList<>();
        public Groups(String name, int groupID){
        	this.name = name;
        	this.groupID = groupID;
        }
        
    public void add(NameShape adder) {
        this.shapes.add(adder);
    }
    public void remove(NameShape adder) {
    	this.shapes.remove(adder);
    }
    public String getName() {
    	return this.name;
    }
    public int getID() {
    	return this.groupID;
    }
	
}
	
	public List<Groups> groups = new ArrayList<>();
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
	
	public void group (String n, String[] nList) {
		Groups temp = new Groups(n, GroupCounter);
		for (String i : nList) {
			for (int j = 0; j< shapes.size(); j++) {
				if (shapes.get(j).getName() == i) {
					temp.add(shapes.get(j));
					shapes.get(j).grouped(GroupCounter);
					break;
				}
			}
		}
		groups.add(temp);
		GroupCounter++;
	}
	
	public void ungroup (String n) {
		for (int i = 0; i < groups.size(); i++) {
			if (groups.get(i).getName() == n) {
				Groups temp = groups.get(i);
				for (int y =0; y < temp.shapes.size(); y++) {
					temp.shapes.get(y).ungrouped();
				}
				groups.remove(i);
				break;
			}
		}
	}
	
	public void delete (String n){
        	boolean isgroup=false;
        	List<NameShape> shapesID= new ArrayList<>();
        	for (NameShape s: shapes){
            		if (s.getName()==n){
                		shapes.remove(s);
                		break;
            		}
        	}
		for (Groups g: groups){
            		if (g.getName()==n){
                		shapesID= g.shapes;
                		groups.remove(g);
                		isgroup=true;
                		break;
            		}
        	}
		if (isgroup){
            		for (NameShape sID: shapesID){
                		for (NameShape s: shapes){
                    			if (s.getName()==sID.getName()){
                        			shapes.remove(s);
                        			break;
                			}
            			}
        		}
    		}
	}
	
	public String boundingbox (String n){
		NameShape Shape = null;
		float x;
		float y;
		float w;
		float h;

		for (int i = 0; i < groups.size(); i++) {
			if (shapes.get(i).getName() == n) {
				Shape = shapes.get(i);
				break;
			}
		}

		Rectangle2D boundbox = Shape.getShape().getBounds2D();

		x = (float)boundbox.getX();
		y = (float)boundbox.getY();
		w = (float)boundbox.getWidth();
		h = (float)boundbox.getHeight();

		return String.format("%.2f",x) + " " + String.format("%.2f",y) + " " + String.format("%.2f",w) + " " + String.format("%.2f",h);
	}
	
	public boolean intersect(String n1, String n2) {
		Shape a = null, b = null;
		for (NameShape s: shapes){
            		if (s.getName()==n1){
            			a = s.getShape();
            			break;
            		}
		}
		for (NameShape t: shapes){
            		if (t.getName()==n2){
            			b = t.getShape();
            			break;
            		}
		}
		
		Area areaA = new Area(a);
		areaA.intersect(new Area(b));
		return !areaA.isEmpty();
	}
	
	public void move(String n, double dx, double dy) {
		
		Rectangle2D.Double recTemp = new Rectangle2D.Double();
		Line2D.Double lineTemp = new Line2D.Double();
		Ellipse2D.Double ellTemp = new Ellipse2D.Double();
		
		
		boolean isgroup = false;
		List<NameShape> shapesID= new ArrayList<>();
		for (Groups g:groups) {
			if (g.getName()==n) {
				isgroup = true;
				shapesID= g.shapes;
				break;
			}
		}
		if (isgroup) {
			for (NameShape sID : shapesID) {
				Shape temp = sID.getShape();
				if (temp.getClass() == recTemp.getClass()) {
					recTemp = (Rectangle2D.Double)temp;
					recTemp.setRect(recTemp.getX() +dx, recTemp.getY()+dy, recTemp.getWidth(), recTemp.getHeight());
				}
				else if (temp.getClass() == lineTemp.getClass()) {
					lineTemp = (Line2D.Double)temp;
					lineTemp.setLine(lineTemp.getX1()+dx, lineTemp.getY1()+dy, lineTemp.getX2()+dx, lineTemp.getY2()+dy);
				}
				else if (temp.getClass() == ellTemp.getClass()) {
					ellTemp = (Ellipse2D.Double)temp;
					ellTemp.setFrame(ellTemp.getX()+dx, ellTemp.getY()+dy, ellTemp.getWidth(), ellTemp.getHeight());
				}
			}
		}
		else {
			for (NameShape s: shapes){
	            if (s.getName()==n){
	            	Shape temp = s.getShape();
					if (temp.getClass() == recTemp.getClass()) {
						recTemp = (Rectangle2D.Double)temp;
						recTemp.setRect(recTemp.getX() +dx, recTemp.getY()+dy, recTemp.getWidth(), recTemp.getHeight());
					}
					else if (temp.getClass() == lineTemp.getClass()) {
						lineTemp = (Line2D.Double)temp;
						lineTemp.setLine(lineTemp.getX1()+dx, lineTemp.getY1()+dy, lineTemp.getX2()+dx, lineTemp.getY2()+dy);
					}
					else if (temp.getClass() == ellTemp.getClass()) {
						ellTemp = (Ellipse2D.Double)temp;
						ellTemp.setFrame(ellTemp.getX()+dx, ellTemp.getY()+dy, ellTemp.getWidth(), ellTemp.getHeight());
					}
	                break;
	            }
			}	
		}
		
	}

	
	/*
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
	*/
}
//ref: https://stackoverflow.com/questions/28839765/how-do-i-create-an-object-of-an-arraylist-shape-in-java
// https://xiu2.net/it/details/6102e2b679193629343164a5
