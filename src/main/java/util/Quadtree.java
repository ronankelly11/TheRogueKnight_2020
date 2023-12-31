package main.java.util;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/*
 * References:
 * Quadtree Code: https://gamedevelopment.tutsplus.com/tutorials/quick-tip-use-quadtrees-to-detect-likely-collisions-in-2d-space--gamedev-374
 */

public class Quadtree{
	private int MAX_OBJECTS = 10; //Number of objects the quadtree can hold before a split.
	private int MAX_LEVELS = 5; //
	
	private int level;
	private List<Rectangle> objects;
	private Rectangle bounds;
	private Quadtree[] nodes;
	
	public Quadtree(int pLevel, Rectangle pBounds) {
		level = pLevel;
		objects = new ArrayList<Rectangle>();
		bounds = pBounds;
		nodes = new Quadtree[4]; //the 4 sub nodes
	}
	
	/*
	 * Clears the quadtree by recursively clearing all objects from all nodes.
	 */
	public void clear() {
		objects.clear();
		
		for(int i=0; i < nodes.length; i++) {
			if (nodes[i] != null) {
				nodes[i].clear();
				nodes[i] = null;
			}
		}
	}
	
	/*
	 * Split method splits the node into four subnodes by diviiding the node into four equal parts and
	 * initializing the four subnodes with the new bounds.
	 */
	private void split() {
		int subWidth = (int)(bounds.getWidth() / 2);
		int subHeight = (int)(bounds.getHeight() / 2);
		int x = (int)bounds.getX();
		int y = (int)bounds.getY();
		 
		nodes[0] = new Quadtree(level+1, new Rectangle(x + subWidth, y, subWidth, subHeight));
		nodes[1] = new Quadtree(level+1, new Rectangle(x, y, subWidth, subHeight));
		nodes[2] = new Quadtree(level+1, new Rectangle(x, y + subHeight, subWidth, subHeight));
		nodes[3] = new Quadtree(level+1, new Rectangle(x + subWidth, y + subHeight, subWidth, subHeight));
	}
	
	/*
	 * Determine which node the object belongs to. -1 means object cannot completely 
	 * fit within a child node and is part of the parent node
	 */
	 private int getIndex(Rectangle pRect) {
	   int index = -1;
	   double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
	   double horizontalMidpoint = bounds.getY() + (bounds.getHeight() / 2);
	 
	   // Object can completely fit within the top quadrants
	   boolean topQuadrant = (pRect.getY() < horizontalMidpoint && pRect.getY() + pRect.getHeight() < horizontalMidpoint);
	   // Object can completely fit within the bottom quadrants
	   boolean bottomQuadrant = (pRect.getY() > horizontalMidpoint);
	 
	   // Object can completely fit within the left quadrants
	   if (pRect.getX() < verticalMidpoint && pRect.getX() + pRect.getWidth() < verticalMidpoint) {
	      if (topQuadrant) {
	        index = 1;
	      }
	      else if (bottomQuadrant) {
	        index = 2;
	      }
	    }
	    // Object can completely fit within the right quadrants
	    else if (pRect.getX() > verticalMidpoint) {
	     if (topQuadrant) {
	       index = 0;
	     }
	     else if (bottomQuadrant) {
	       index = 3;
	     }
	   }
	 
	   return index;
	 }
	 
	 /*
	  * Insert the object into the quadtree. If the node
	  * exceeds the capacity, it will split and add all
	  * objects to their corresponding nodes.
	  */
	  public void insert(Rectangle pRect) {
	    if (nodes[0] != null) {
	      int index = getIndex(pRect);
	  
	      if (index != -1) {
	        nodes[index].insert(pRect);
	  
	        return;
	      }
	    }
	  
	    objects.add(pRect);
	  
	    if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
	       if (nodes[0] == null) { 
	          split(); 
	       }
	  
	      int i = 0;
	      while (i < objects.size()) {
	        int index = getIndex(objects.get(i));
	        if (index != -1) {
	          nodes[index].insert(objects.remove(i));
	        }
	        else {
	          i++;
	        }
	      }
	    }
	  }
	  
	  
	  /*
	   * Return all objects that could collide with the given object
	   */
	   public List retrieve(List returnObjects, Rectangle pRect) {
	     int index = getIndex(pRect);
	     if (index != -1 && nodes[0] != null) {
	       nodes[index].retrieve(returnObjects, pRect);
	     }
	   
	     returnObjects.addAll(objects);
	   
	     return returnObjects;
	   }
	   
	   // Insert all platform rectangles into the Quadtree.
	   public void fillQuadtree() {
		   this.clear();
		   
		   for (int i = 0; i < allObjects.length; i++) {
			   this.insert(allObjects[i]);
		   }
	   }
	   
	   public static final Rectangle[] allObjects = {
				new Rectangle(396,296,548,312),
				new Rectangle(584,180,608,180),
				new Rectangle(615,102,735,222),
				new Rectangle(680,320,712,448)
			};
}