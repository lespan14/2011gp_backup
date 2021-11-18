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
	@BeforeEach
	void setUp() throws Exception {
		n1 = "n1";
		n2 = "n2";
		n3 = "g1";
		
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
		assertTrue(g1.getID() == clevis.groups.get(0).getID());
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
	void testMove() {
		Shape s1 = new Rectangle2D.Double(3, 3, 1, 1);
		clevis.rectangle(n1, 5, 5, 1, 1);
		clevis.move(n1, -2, -2);
		Shape temp = clevis.shapes.get(0).getShape();
		assertTrue(temp.equals(s1));
	}
	
	@Test
	void testDelete() {
		clevis.rectangle(n1, 3, 3, 1, 1);
		assertTrue(clevis.isContained(n1));
		clevis.delete(n1);
		assertFalse(clevis.isContained(n1));
	}

	
}
