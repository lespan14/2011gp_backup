package hk.edu.polyu.comp.comp2021.clevis.model;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Clevis {
	public JFrame frame;
	public Canvas canvas;
	public Graphics g;
    public Clevis(){
    	this.frame = new JFrame();	
    	this.canvas = new Canvas();
    	init();
    }
    
    public void init() {
    	this.frame.setTitle("TestFrame");
    	this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	this.frame.setSize(500,400);
    	this.frame.setLocationRelativeTo(null);
    	this.frame.setResizable(false);
    	this.frame.setVisible(true);
    	
    	//JPanel pan = new JPanel();
    	//pan.setLayout(new FlowLayout(FlowLayout.CENTER, 200,200));
    	//pan.setBackground(Color.BLACK);
    	//this.frame.add(pan,BorderLayout.NORTH);
    	canvas.setBackground(Color.BLACK);
    	this.frame.add(canvas);
    }
    public void paint(Graphics g) {
    	g.setColor(Color.RED);
    	g.fillRect(10, 10, 50, 50);
    }
}

/*
public void rectangle (String n, int x, int y, int w, int h){
        g.drawRect(x, y, w, h);
    }

public void circle (String n, int x, int y, int r){
        g.drawOval(x, y, r, r);
    }
    
public void square (String n, int x, int y, int l){
        g.drawRect(x, y, l, l);
    }

public void line (String n, int x1, int y1, int x2, int y2){
        g.drawLine(x1, y1, x2, y2);
    }
*/
