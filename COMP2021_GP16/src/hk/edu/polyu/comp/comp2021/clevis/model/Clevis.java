package hk.edu.polyu.comp.comp2021.clevis.model;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Canvas;
import java.awt.Graphics;

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
