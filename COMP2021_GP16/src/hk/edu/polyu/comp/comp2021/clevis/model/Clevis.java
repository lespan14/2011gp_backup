package hk.edu.polyu.comp.comp2021.clevis.model;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Clevis {
	public JFrame canvas;
    public Clevis(){
    	canvas = new JFrame();
    	canvas.setBounds(10 , 10, 1000, 800);	
    	canvas.setDefaultCloseOperation(3);
    	JPanel pan = new JPanel() {
    		@Override
    		public void paint(Graphics g) {
    			
    		}
    	};
    	canvas.add(pan);
    	canvas.setVisible(true);
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
