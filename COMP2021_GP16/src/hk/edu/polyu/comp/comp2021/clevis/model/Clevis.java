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
    public boolean isContained(String n){

        for (int i =0 ; i< shapes.size(); i++){
            if(shapes.get(i).getName().equals(n)) {
                return true;
            }
        }
        return false;
    }

    public boolean isContainedGroup (String n){
        for (int i =0 ; i< groups.size(); i++){
            if(groups.get(i).getName().equals(n)) {
                return true;
            }
        }
        return false;
    }

    public static class Groups {
        private String name;
        private int groupID;
        private List<NameShape> shapes= new ArrayList<>();
        private Area groupshape = new Area();
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
        public Area getShape() {
            return this.groupshape;
        }

    }

    public List<Groups> groups = new ArrayList<>();
    public List<NameShape> shapes= new ArrayList<>();

    public void rectangle (String n, double x, double y, double w, double h){
        if (isContained(n)){
            System.out.println("Name already existed");
            return;
        }
        shapes.add(new NameShape(n, new Rectangle2D.Double(x, y, w, h)));
        System.out.println("Rectangle " + n + " has been created");
    }

    public void circle (String n, double x, double y, double r){
        if (isContained(n)){
            System.out.println("Name already existed");
            return;
        }
        shapes.add(new NameShape(n, new Ellipse2D.Double(x, y, r, r)));
        System.out.println("Circle " + n + " has been created");
    }

    public void square (String n, double x, double y, double l){
        if (isContained(n)){
            System.out.println("Name already existed");
            return;
        }
        shapes.add(new NameShape(n, new Rectangle2D.Double(x, y, l, l)));
        System.out.println("Square " + n + " has been created");
    }

    public void line (String n, double x1, double y1, double x2, double y2){
        if (isContained(n)){
            System.out.println("Name already existed");
            return;
        }
        shapes.add(new NameShape(n, new Line2D.Double(x1, y1, x2, y2)));
        System.out.println("Lines " + n + " has been created");
    }

    public void group (String n, String[] nList) {
        if (isContained(n)){
            System.out.println("Name already existed");
            return;
        }
        Groups temp = new Groups(n, GroupCounter);
        for (String i : nList) {
            if (!isContained(i)){
                System.out.println("Shape does not exist");
                return;
            }
            for (int j = 0; j< shapes.size(); j++) {
                if (shapes.get(j).getName().equals(i)) {
                    temp.add(shapes.get(j));
                    shapes.get(j).grouped(GroupCounter);
                    temp.groupshape.add(new Area(shapes.get(j).getShape()));
                    break;
                }
            }
        }
        shapes.add(new NameShape(n,temp.groupshape));
        groups.add(temp);
        GroupCounter++;
        System.out.println("Group " + n + " has been formed");
    }


    public void ungroup (String n) {
        if (!isContainedGroup(n)){
            System.out.println("This group does not exist");
            return;
        }
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getName().equals(n)) {
                Groups temp = groups.get(i);
                for (int y = 0; y < temp.shapes.size(); y++) {
                    temp.shapes.get(y).ungrouped();
                }
                groups.remove(i);
                break;
            }
        }
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).getName().equals(n)) {
                shapes.remove(i);
                break;
            }
        }
        System.out.println("Group " + n + " has been ungrouped");
    }

    public void delete (String n){
        boolean isgroup=false;
        List<NameShape> shapesID= new ArrayList<>();
        
        if (!isContained(n)){
            System.out.println("Shape does not exist");
            return;
        }
        for (NameShape s: shapes){
            if (s.getName().equals(n) && s.grouped == false &&s.shape!=null){
                shapes.remove(s);
                System.out.println("Shape " + n + " has been deleted");
                break;
            }
        }
        for (Groups g: groups){
            if (g.getName().equals(n)){
                isgroup=true;
                shapesID= g.shapes;
                groups.remove(g);
                shapes.remove(g);
                System.out.println("Group " + n + " has been deleted");
                break;
            }
        }
        if (isgroup){
            for (NameShape sID: shapesID){
                for (NameShape s: shapes){
                    if (s.getName().equals(sID.getName())){
                        shapes.remove(s);
                        System.out.println("Shape " + s.getName() + " has been deleted");
                        break;
                    }
                }
            }
        }
    }

    public String boundingbox (String n){
        if (!isContained(n)){
            System.out.println("Shape does not exist");
            return "";
        }

        NameShape Shape = null;
        Area Group = null;
        double x;
        double y;
        double w;
        double h;
        if (isContainedGroup(n)){
            for (int i = 0; i < groups.size(); i++) {
                if (groups.get(i).getName().equals(n)) {
                    Group = groups.get(i).getShape();
                    break;
                }
            }
            Rectangle2D boundbox = Group.getBounds2D();

            x = boundbox.getX();
            y = boundbox.getY();
            w = boundbox.getWidth();
            h = boundbox.getHeight();
        }
        else {
            for (int i = 0; i < shapes.size(); i++) {
                if (shapes.get(i).getName().equals(n)) {
                    Shape = shapes.get(i);
                    break;
                }
            }
             Rectangle2D boundbox = Shape.getShape().getBounds2D();

            x = boundbox.getX();
            y = boundbox.getY();
            w = boundbox.getWidth();
            h = boundbox.getHeight();
        }

        System.out.println("Boundingbox result :");
        System.out.println(String.format("%.2f",x) + " " + String.format("%.2f",y) + " " + String.format("%.2f",w) + " " + String.format("%.2f",h));

        return String.format("%.2f",x) + " " + String.format("%.2f",y) + " " + String.format("%.2f",w) + " " + String.format("%.2f",h);
    }

    public void intersect(String n1, String n2) {
        if (!isContained(n1)||!isContained(n2)){
            System.out.println("Shape does not exist");
            return;
        }

        Shape a = null, b = null;
        for (NameShape s: shapes){
            if (s.getName().equals(n1)){
                a = s.getShape();
                break;
            }
        }
        for (NameShape t: shapes){
            if (t.getName().equals(n2)){
                b = t.getShape();
                break;
            }
        }
        Area areaA = new Area(a);
        areaA.intersect(new Area(b));
        if(!areaA.isEmpty()) {
            System.out.println(n1+" and "+n2+" intersect");
        }else {
            System.out.println(n1+" and "+n2+" do not intersect");
        }
    }

    public void move(String n, double dx, double dy) {
        if (!isContained(n)) {
            System.out.println("Shape does not exist");
            return;
        }

        Rectangle2D.Double recTemp = new Rectangle2D.Double();
        Line2D.Double lineTemp = new Line2D.Double();
        Ellipse2D.Double ellTemp = new Ellipse2D.Double();

        boolean isgroup = false;
        List<NameShape> shapesID = null;
        Area atemp=new Area();
        for (Groups g:groups) {
            if (g.getName().equals(n)) {
                isgroup = true;
                shapesID= g.shapes;
                System.out.println("group found");
                break;
            }
        }
        if (isgroup) {
            for (NameShape sID : shapesID) {
                Shape temp = sID.getShape();
                if (temp.getClass() == recTemp.getClass()) {
                    recTemp = (Rectangle2D.Double) temp;
                    recTemp.setRect(recTemp.getX() + dx, recTemp.getY() + dy, recTemp.getWidth(), recTemp.getHeight());
                    atemp.add(new Area(recTemp));
                } else if (temp.getClass() == lineTemp.getClass()) {
                    lineTemp = (Line2D.Double) temp;
                    lineTemp.setLine(lineTemp.getX1() + dx, lineTemp.getY1() + dy, lineTemp.getX2() + dx, lineTemp.getY2() + dy);
                    atemp.add(new Area(lineTemp));
                } else if (temp.getClass() == ellTemp.getClass()) {
                    ellTemp = (Ellipse2D.Double) temp;
                    ellTemp.setFrame(ellTemp.getX() + dx, ellTemp.getY() + dy, ellTemp.getWidth(), ellTemp.getHeight());
                    atemp.add(new Area(ellTemp));
                }
                System.out.println("Shape " + sID.getName() + " moved");
                for (Groups g : groups) {
                    if (g.getName().equals(n)) {
                        g.groupshape = atemp;
                    }
                }
            }
        }
        else {
            for (NameShape s: shapes){
               if (s.getName().equals(n)){
                   if (s.grouped){
                        System.out.println("cannot move a shape in group");
                        return;
                    }
                    System.out.println("shape found");
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
                    System.out.println("Shape " + s.getName() +" moved");
                    break;
                }
            }
        }
    }

    public void pickandmove (double x , double y, double dx, double dy){ //need fix
        Rectangle2D dot = new Rectangle2D.Double(x, y, 0.0001, 0.0001);
        Area areaA = new Area(dot);
        for (int i=shapes.size()-1;i>=0;i--){
            areaA.intersect(new Area(shapes.get(i).getShape()));
            if (!areaA.isEmpty()){
                System.out.println(shapes.get(i).getName()+" is at "+x+", "+y);
                move(shapes.get(i).getName(),dx,dy);
                return;
            }
        }
        System.out.println("Nothing in " + x +", " + y);
    }

    public void list(String n){
        if (!isContained(n)){
            System.out.println("shape does not exist");
            return;
        }

        Rectangle2D.Double recTemp = new Rectangle2D.Double();
        Line2D.Double lineTemp = new Line2D.Double();
        Ellipse2D.Double ellTemp = new Ellipse2D.Double();

        boolean isgroup = false;
        String groupname="";
        List<NameShape> shapesID = new ArrayList<>();
        for (Groups g : groups) {
            if (g.getName().equals(n)) {
                isgroup = true;
                shapesID = g.shapes;
                groupname = g.getName();
                break;
            }
        }
        if (isgroup) {
            System.out.println("The name of the shape is: "+ groupname);
            System.out.println("The shapes in group are: ");
            for (NameShape sID : shapesID) {
                System.out.println("        "+sID.getName());
            }

        }
        else {
            for (NameShape s : shapes) {
                if (s.getName().equals(n)) {
                    Shape temp = s.getShape();
                    if (temp.getClass() == recTemp.getClass()) {
                        recTemp = (Rectangle2D.Double) temp;
                        System.out.println("The name of the shape is: "+s.getName());
                        System.out.println("The width of the shape is: "+String.format("%.2f", recTemp.getWidth()));
                        System.out.println("The height of the shape is: "+String.format("%.2f", recTemp.getHeight()));
                        System.out.println("The X and Y of the shape is: "+String.format("%.2f", recTemp.getX()) + " " + String.format("%.2f", recTemp.getY()));
                    } else if (temp.getClass() == lineTemp.getClass()) {
                        lineTemp = (Line2D.Double) temp;
                        double x = lineTemp.getX1() - lineTemp.getX2();
                        double y = lineTemp.getY1() - lineTemp.getY2();
                        System.out.println("The name of the line is: "+s.getName());
                        System.out.println("The length of the line is: "+String.format("%.2f",Math.sqrt(x * x + y * y)));
                        System.out.println("The X1 and Y1 of the line is: "+String.format("%.2f",lineTemp.getX1())+" " + String.format("%.2f",lineTemp.getY1()));
                        System.out.println("The X2 and Y2 of the line is: "+String.format("%.2f",lineTemp.getX2())+" " + String.format("%.2f",lineTemp.getY2()));
                    } else if (temp.getClass() == ellTemp.getClass()) {
                        ellTemp = (Ellipse2D.Double) temp;
                        System.out.println("The name of the circle is: "+s.getName());
                        System.out.println("The x of the centre of the circle is: "+String.format("%.2f",ellTemp.getX()+ellTemp.getWidth()/2));
                        System.out.println("The y of the centre of the circle is: "+String.format("%.2f",ellTemp.getY()+ellTemp.getWidth()/2));
                        System.out.println("The radius of the circle is: "+String.format("%.2f",ellTemp.getWidth()/2));
                    }
                    break;
                }
            }
        }
    }


    public void listAll(){
        if (shapes.size()==0){
            System.out.println("The list is empty");
            return;
        }
        for (int i=shapes.size()-1;i>=0;i--){
            list(shapes.get(i).getName());
            System.out.println();
        }

        System.out.println("------------");
    }

    public void quit() {
        System.out.println("Shutting down...");
        System.exit(0);
    }

}
//ref: https://stackoverflow.com/questions/28839765/how-do-i-create-an-object-of-an-arraylist-shape-in-java
// https://xiu2.net/it/details/6102e2b679193629343164a5
