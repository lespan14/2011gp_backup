package hk.edu.polyu.comp.comp2021.clevis.test;

import static org.junit.jupiter.api.Assertions.*;
import hk.edu.polyu.comp.comp2021.clevis.log.*;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis.NameShape;

import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D.Double;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class test {
    	Clevis clevis = new Clevis();
    	Clevis.NameShape t1;
	Clevis.NameShape t2;
    	Clevis.Groups g1;
    	String n1;
    	String n2;
    	String n3;
	String n4;
	
	@BeforeEach
	void setUp() throws Exception {
		n1 = "n1";
		n2 = "n2";
		n3 = "g1";
		n4 = "n4";
		
    	t1 = new Clevis.NameShape(n1, new Rectangle2D.Double(3, 3, 1, 1));
    	t2 = new Clevis.NameShape(n2, new Rectangle2D.Double(2, 5, 1, 1));
    	g1 = new Clevis.Groups("g1", 0);
    	g1.add(t1);
	}

	@Test
	void testNameShape() {
		Shape s1 = new Rectangle2D.Double(3, 3, 1, 1);
		assertTrue(t1.getName().equals(n1));
		assertTrue(t1.getShape().equals(s1));
	}
	
	@Test
	void testGroups1() {
		assertFalse(g1 == null);
		assertTrue(g1.getID()==0);
	}
	
	@Test
	void testGroups2() {
		clevis.rectangle(n1, 3, 3, 1, 1);
		clevis.rectangle(n2, 2, 5, 1, 1);
		String [] nList = {n1, n2};
		clevis.group(n3, nList);
		g1.add(t2);
		assertTrue(clevis.shapes.get(0).getID() == clevis.shapes.get(1).getID());
		assertTrue(g1.getName().equals(clevis.groups.get(0).getName()));
	}
	
	@Test
    	void testUngroup1() {
        	clevis.ungroup(n3);
        	assertTrue(!clevis.isContained(n3));
   	}

    	@Test
    	void testUngroup2() {
        	clevis.rectangle(n1, 3, 3, 1, 1);
        	clevis.rectangle(n2, 2, 5, 1, 1);
       	 	String [] nList = {n1, n2};
        	clevis.group(n3, nList);
        	clevis.ungroup(n3);
        	assertTrue(!clevis.isContained(n3));
    	}
	
	@Test
	void testIsContain() {
		clevis.shapes.add(t1);
		assertTrue(clevis.isContained(n1));
	}
	
	@Test
	void testIsContainGroup() {
		clevis.groups.add(g1);
		assertTrue(clevis.isContainedGroup("g1"));
	}
	
	@Test
	void testRec() {
		Shape s1 = new Rectangle2D.Double(3, 3, 1, 1);
		
		clevis.rectangle(n1, 3, 3, 1, 1);
		Clevis.NameShape temp = clevis.shapes.get(0);
		assertTrue(s1.equals(temp.getShape()));
		
		clevis.square(n2, 3, 3, 1);
		temp = clevis.shapes.get(1);
		assertTrue(s1.equals(temp.getShape()));
	}
	
	@Test
	void testCir() {
		Shape s1 = new Ellipse2D.Double(4, 4, 10, 10);
		
		clevis.circle(n1, 4, 4, 10);
		Clevis.NameShape temp = clevis.shapes.get(0);
		assertTrue(s1.equals(temp.getShape()));
	}
	
	@Test
	void testLine() {
		Line2D.Double s1 = new Line2D.Double(4, 4, 10, 10);
		
		clevis.line(n1, 4, 4, 10, 10);
		Line2D.Double temp = (Line2D.Double)clevis.shapes.get(0).getShape();
		assertTrue(temp.getX1() == s1.getX1());
		assertTrue(temp.getX2() == s1.getX2());
		assertTrue(temp.getY1() == s1.getY1());
		assertTrue(temp.getY2() == s1.getY2());
	}
	
	@Test
    	void testIntersect() {
        	clevis.rectangle(n1, 3, 3, 1, 1);
        	clevis.rectangle(n2, 3, 3, 10, 9);
        	clevis.rectangle(n3, 100, 100, 1, 1);
        	assertTrue(clevis.intersect(n1,n2));
       	 	assertFalse(clevis.intersect(n1,n3));
    	}
	
	@Test
	void testMove1() {
		Shape s1 = new Rectangle2D.Double(3, 3, 1, 1);
        	clevis.rectangle(n1, 5, 5, 1, 1);
        	Line2D.Double s2 = new Line2D.Double(9,9,9,9);
        	clevis.line(n2, 4,4,4,4);
        	Shape s3 = new Ellipse2D.Double(11,3,5,5);
        	clevis.circle(n4, 1,2,5);

        	clevis.move(n1, -2, -2);
        	clevis.move(n2, 5,5);
        	clevis.move(n4, 10,1);
        	Shape temp1 = clevis.shapes.get(0).getShape();
        	Line2D.Double temp2 = (Line2D.Double)clevis.shapes.get(1).getShape();
        	Shape temp3 = clevis.shapes.get(2).getShape();
        	assertTrue(temp1.equals(s1));
        	assertTrue(temp2.getX1() == s2.getX1());
        	assertTrue(temp2.getX2() == s2.getX2());
        	assertTrue(temp2.getY1() == s2.getY1());
        	assertTrue(temp2.getY2() == s2.getY2());
        	assertTrue(temp3.equals(s3));
	}
	
	@Test
	void testMove2() {
		Shape s1 = new Rectangle2D.Double(3, 3, 1, 1);
        	Line2D.Double s2 = new Line2D.Double(2,2,2,2);
        	Shape s3 = new Ellipse2D.Double(-1,0,5,5);
        	clevis.rectangle(n1, 5, 5, 1, 1);
        	clevis.line(n2, 4,4,4,4);
        	clevis.circle(n4, 1,2,5);

        	String [] nList = {n1, n2, n4};
        	clevis.group(n3, nList);
        	clevis.move(n3, -2, -2);
        	Shape temp1 = clevis.shapes.get(0).getShape();
        	Line2D.Double temp2 = (Line2D.Double)clevis.shapes.get(1).getShape();
		Shape temp3 = clevis.shapes.get(2).getShape();
        	assertTrue(temp1.equals(s1));
        	assertTrue(temp2.getX1() == s2.getX1());
        	assertTrue(temp2.getX2() == s2.getX2());
        	assertTrue(temp2.getY1() == s2.getY1());
        	assertTrue(temp2.getY2() == s2.getY2());
        	assertTrue(temp3.equals(s3));
	}
	
	@Test
	void testDelete1() {
		clevis.rectangle(n1, 3, 3, 1, 1);
		assertTrue(clevis.isContained(n1));
		clevis.delete(n1);
		assertFalse(clevis.isContained(n1));
	}
	
	@Test
	void testDelete2() {
		clevis.rectangle(n1, 3, 3, 1, 1);
		clevis.circle(n2, 4, 4, 4);
		String [] nList = {n1, n2};
		clevis.group(n3, nList);
		assertTrue(clevis.isContainedGroup(n3));
		assertTrue(clevis.isContained(n1));
		assertTrue(clevis.isContained(n2));
		clevis.delete(n3);
		assertFalse(clevis.isContainedGroup(n3));
		assertFalse(clevis.isContained(n1));
		assertFalse(clevis.isContained(n2));
	}

	@Test
	void testBoundingbox1() {
		clevis.rectangle(n1, 3, 3, 1, 1);
		clevis.square(n2, 3, 3, 1);
		String s1 = clevis.boundingbox(n1);
		String s2 = clevis.boundingbox(n2);
		assertTrue(s1.equals(s2));
	}

	@Test
    	void testBoundingbox2() {
        	clevis.square(n1, 4, 3, 1);
        	clevis.square(n2, 3, 3, 1);
        	clevis.rectangle(n3, 3, 3, 2,1);
       	 	String [] nList = {n1, n2};
        	clevis.group(n4, nList);
        	String s1 = clevis.boundingbox(n3);
        	String s2 = clevis.boundingbox(n4);
        	assertTrue(s1.equals(s2));
    	}
	
	@Test
    	void testPickandmove() {
        	clevis.rectangle(n1, 4, 3, 2,3);
        	clevis.square(n2, 4, 3, 1);
        	Shape s1 = new Rectangle2D.Double(9,13,1,1);
        	clevis.pickandmove(4,3,5,10);
        	Shape temp = clevis.shapes.get(1).getShape();
        	assertTrue(s1.equals(temp));
    	}
}
