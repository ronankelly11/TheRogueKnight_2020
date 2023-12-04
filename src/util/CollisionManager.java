package util;

import java.awt.Rectangle;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollisionManager{
	private static CopyOnWriteArrayList<Rectangle> objects = new CopyOnWriteArrayList<>();
	boolean collisionDetected = false;
	Collision collision = new Collision();
	
	
	public CollisionManager() {	
		// Add in all the platforms on the maps;
		this.addObject(new Rectangle(371, 368, 510-378, 385-378));
		this.addObject(new Rectangle(545, 288, 581-545, 300-295));
		this.addObject(new Rectangle(589, 233  ,677-560, 360-233));
		this.addObject(new Rectangle(630, 336, 673-630, 485-336));
		this.addObject(new Rectangle(757, 374, 828-757, 381-374));
		this.addObject(new Rectangle(1108 ,373, 1196-1108, 383-376));
		this.addObject(new Rectangle(1144, 385, 1196-1186, 485-285));
		this.addObject(new Rectangle(1187, 233, 1225-1187, 260-233));
		this.addObject(new Rectangle(1226, 173, 1291-1226, 307-173));
		this.addObject(new Rectangle(1503, 174, 1761-1503, 235-174));
		this.addObject(new Rectangle(1678, 0, 1761-1678, 455-0));

	}
	
	public void addObject(Rectangle object) {
		objects.add(object);
	}
	public void removeObject(Rectangle object) {
		objects.remove(object);
	}
	
	public void resetCollisionManager() {
		this.objects.clear();
	}
	
	private Collision detectCollisionSide(Rectangle objectRectangle, Rectangle platformRectangle) {
		double player_bottom = objectRectangle.getY() + platformRectangle.getHeight();
		double tiles_bottom = platformRectangle.getY() + platformRectangle.getHeight();
		double player_right = objectRectangle.getX() + objectRectangle.getWidth();
		double tiles_right = platformRectangle.getX() + platformRectangle.getWidth();

		double b_collision = tiles_bottom - objectRectangle.getY();
		double t_collision = player_bottom - platformRectangle.getY();
		double l_collision = player_right - platformRectangle.getX();
		double r_collision = tiles_right - objectRectangle.getX();

		if (t_collision < b_collision && t_collision < l_collision && t_collision < r_collision )
		{                           
			 collision.setCollisionLocation("top");
			 collision.setCollisionDistance((float) t_collision);
		}
		else if (b_collision < t_collision && b_collision < l_collision && b_collision < r_collision)                        
		{
			 collision.setCollisionLocation("bottom");
			 collision.setCollisionDistance((float) b_collision);
		}
		else if (l_collision < r_collision && l_collision < t_collision && l_collision < b_collision)
		{
			 collision.setCollisionLocation("left");
			 collision.setCollisionDistance((float) l_collision);
		}
		else if (r_collision < l_collision && r_collision < t_collision && r_collision < b_collision )
		{
			 collision.setCollisionLocation("right");
			 collision.setCollisionDistance((float) r_collision);
		}
		return collision;
		
	}
	
	public Collision detectCollisions(Rectangle objectRectangle) {
		for (int i = 0; i < objects.size(); i++) {
			collision.setCollisionDetected(false);
			double x = objectRectangle.getCenterX()- objects.get(i).getCenterX();
			double y = objectRectangle.getCenterY()- objects.get(i).getCenterY();
			double distance = Math.sqrt((x)*(x) + (y)*(y));

			if (distance < 400) {
				if (objectRectangle.intersects(objects.get(i))) {
					collision.setCollisionDetected(true);
					detectCollisionSide(objectRectangle, objects.get(i));
					break;
				}
				
			}
		}
		return collision;
	}
}

/* References:
 * Collision detection: https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection
 * Detecting Collision Side: https://stackoverflow.com/questions/5062833/detecting-the-direction-of-a-collision
*/