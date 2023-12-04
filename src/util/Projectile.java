package util;

public class Projectile{
	private GameObject projectile;
	private boolean hasShotBullet;
	private float xVelocity = 0;
	private float yVelocity = 0;
	private float sx;
	private float sy;
	private float dx;
	private float dy;
	private double timeSinceLastAttack = 0;
	private boolean isDead = false;
	
	
	public Projectile() {}
	
	public Projectile(GameObject projectile) {
		this.setProjectile(projectile);
		this.setDX(0);
		this.setDY(0);
		this.setSX(projectile.getCentre().getX());
		this.setSY(projectile.getCentre().getY());
	}
	
	public Projectile(GameObject projectile, float sx, float sy, float dx, float dy) {
		this.setProjectile(projectile);
		this.setDX(dx);
		this.setDY(dy);
		this.setSX(sx);
		this.setSY(sy);
	}
	
	public void calculateProjectileTrajectory(float sx, float sy, float dx, float dy) {
		float [] velocity = new float[2];
		int projectileSpeed = 2;
		double angle = Math.atan2(dy-sy, dx-sx);
		
		//System.out.println("MouseX: " +MouseController.getInstance().getX() +" MOuse Y: " + MouseController.getInstance().getY());
		this.xVelocity = (float) (projectileSpeed * Math.cos(angle));
		this.yVelocity = (float) (projectileSpeed * -Math.sin(angle));
		
	}
	
	public void setIsDead(boolean isDead) {this.isDead = isDead;}
	public boolean isDead() {return this.isDead;}
	public void setTimeSinceLastAttack(double time) { this.timeSinceLastAttack = time;}
	public double getTimeSinceLastAttack() {return this.timeSinceLastAttack;}
	
	public void setHasShotBullet(boolean hasShotBullet) {this.hasShotBullet = hasShotBullet;}
	public boolean hasShotBullet() {return this.hasShotBullet;}
	
	public void setXVelocity(float xVelocity) {this.xVelocity = xVelocity;}
	public float getXVelocity() {return this.xVelocity;}
	
	public void setYVelocity(float yVelocity) {this.yVelocity = yVelocity;}
	public float getYVelocity() {return this.yVelocity;}
	
	public void setProjectile(GameObject projectile) {this.projectile=projectile;}
	public GameObject getProjectile() {return this.projectile;}
	
	public void setSX(float sx) {this.sx=sx;}
	public float getSX() {return this.sx;}
	
	public void setSY(float sy) {this.sy=sy;}
	public float getSY() {return this.sy;}
	
	public void setDX(float dx) {this.dx=dx;}
	public float getDX() {return this.dx;}
	
	public void setDY(float dy) {this.dy = dy;}
	public float getDY() {return this.dy;}
}