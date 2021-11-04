package hk.edu.polyu.comp.comp2021.clevis.model;
import java.awt.Graphics;

public class Clevis {

    public Clevis(){}

}

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

