package main.java.util;

public class Camera{
	private static int worldWidth;
	private static int worldHeight;
	private static int viewHeight;
	private static int viewWidth;
	private static int offsetMaxX;
	private static int offsetMaxY;
	private static int offsetMinX;
	private static int offsetMinY;
	private static int camXPos;
	
	private static final Camera instance = new Camera();
	
	public static Camera getInstance() {
		return instance;
	}
	
	public Camera() {}

	
	public Camera(int worldWidth, int worldHeight, int viewHeight, int viewWidth) {
		Camera.worldHeight=worldHeight;
		Camera.worldWidth=worldWidth;
		Camera.viewHeight=viewHeight;
		Camera.viewWidth=viewWidth;
		Camera.offsetMaxX = worldWidth-viewWidth;
		Camera.offsetMaxY = worldHeight-viewHeight;
		Camera.offsetMinX = 0;
		Camera.offsetMinY = 0;
		Camera.camXPos = 0;
	}
	
	public int getCamXPos(int x) {
		camXPos = x - this.getViewWidth()/2;
		if (camXPos > this.getOffsetMaxX())
			camXPos = this.getOffsetMaxX();
		else if (camXPos < this.getOffsetMinX())
			camXPos = this.getOffsetMinX();
		return camXPos;
	}

	
	public int getViewWidth() {return Camera.viewWidth;}
	
	public int getOffsetMaxX() {return Camera.offsetMaxX;}
	public int getOffsetMaxY() {return Camera.offsetMaxY;}
	public int getOffsetMinY() {return Camera.offsetMinY;}
	public int getOffsetMinX() {return Camera.offsetMinX;}
}

/*
 * References:
 * https://gamedev.stackexchange.com/questions/44256/how-to-add-a-scrolling-camera-to-a-2d-java-game
 * */
