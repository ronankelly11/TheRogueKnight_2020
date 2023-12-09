package main.java.util;
/*
 * This class stores each animation state.
 */
public class AnimationObject{
	private int spriteY1;
	private int spriteY2;
	private int numOfAnimations;
	private int animationSpeed;
	private String animationName;
	
	public AnimationObject() {
		this.setSpriteY1(0);
		this.setSpriteY2(0);
		this.setNumOfAnimations(1);
		this.setAnimationSpeed(1);
	}
	
	public AnimationObject(int spriteY1, int spriteY2, int numOfAnimations, int animationSpeed) {
		this.setSpriteY1(spriteY1);
		this.setSpriteY2(spriteY2);
		this.setNumOfAnimations(numOfAnimations);
		this.setAnimationSpeed(animationSpeed);
	}
	
	public void setAnimationName(String animationName) {this.animationName = animationName;}
	public String getAnimationName() {return this.animationName;}
	
	public void setSpriteY1(int spriteY1) {this.spriteY1 = spriteY1;}
	public int getSpriteY1() {return this.spriteY1;}
	
	public void setSpriteY2(int spriteY2) { this.spriteY2 = spriteY2;}
	public int getSpriteY2() { return this.spriteY2;}
	
	public void setNumOfAnimations(int numOfAnimations) {this.numOfAnimations = numOfAnimations;}
	public int getNumOfAnimations() {return this.numOfAnimations;}
	
	public void setAnimationSpeed(int animationSpeed) {this.animationSpeed = animationSpeed;}
	public int getAnimationSpeed() {return this.animationSpeed;}
}