package main.java.util;

public class Collision{
	private boolean collisionDetected;
	private String collisionLocation;
	private float collisionDistance;
	
	public Collision() {
		this.setCollisionDetected(false);
	}
	
	public Collision(boolean collisionDetected, String collisionLocation, float collisionDistance) {
		this.setCollisionDetected(collisionDetected);
		this.setCollisionLocation(collisionLocation);
		this.setCollisionDistance(collisionDistance);
	}
	
	public void setCollisionDetected(boolean collisionDetected) { this.collisionDetected = collisionDetected;}
	public boolean getCollisionDetected() {return this.collisionDetected;}
	
	public void setCollisionLocation(String collisionLocation) {this.collisionLocation = collisionLocation;}
	public String getCollisionLocation() {return this.collisionLocation;}
	
	public void setCollisionDistance(float collisionDistance) { this.collisionDistance = collisionDistance;}
	public float getCollisionDistance() {return this.collisionDistance;}
}