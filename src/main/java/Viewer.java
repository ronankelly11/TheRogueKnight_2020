package main.java;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import main.java.util.AnimationObject;
import main.java.util.Camera;
import main.java.util.GameObject;


/*
 * Created by Abraham Campbell on 15/01/2020.
 *   Copyright (c) 2020  Abraham Campbell

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
   
   (MIT LICENSE ) e.g do what you want with this :-) 
 
 * Credits: Kelly Charles (2020)
 */ 
public class Viewer extends JPanel {
	private long CurrentAnimationTime= 0;
	private long playerAnimationTime = 0;
	Image playerImage;
	Image staticBackground;
	Image movingBackground;
	Image endScreenBackground;

	Image [] enemies = new Image[2];
	Image [] projectiles = new Image [2];
	Camera cam = new Camera(1595,400,800,600);
	int Score;
	boolean Level1 = true;
	boolean EndScreen = false;
	double timeSinceLastAnimation;
	double currentTime;
	int mapCurrentXPos; // Denotes the current centre of our overall map, 300 is beginning default before character moves.
	int mapPrevXPos; // Denotes the current centre of our overall map, 300 is beginning default before character moves.
	int mapXOffset; //Denotes whether the map is going left or right;
	AnimationObject lastPlayerAnimation = new AnimationObject();
	int enemyAnimationCount;

	KeyListener controller;
	Model gameworld =new Model(); 
	 
	public Viewer(Model World, KeyListener controller) {
		this.gameworld=World;
		this.controller=controller;
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public Viewer(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public void updateview() {
		
		this.repaint();
		// TODO Auto-generated method stub
		
	}
	
	public void loadGraphcis() {
		try {
			this.playerImage = ImageIO.read(new File("res/Fullmain.png"));
			this.staticBackground = ImageIO.read(new File("res/background.png"));
			this.endScreenBackground = ImageIO.read(new File("res/EndScreen.png"));
			this.movingBackground = ImageIO.read(new File("res/Starfield_MapFinal.png"));
			this.enemies[0] = ImageIO.read(new File("res/Full_Mushroom.png"));
			this.enemies[1] = ImageIO.read(new File("res/FullFlyingEye.png"));

		}
		catch (IOException e) {
			
		}
	}

	
	
	public void paintComponent(Graphics g) {
		super.repaint();
		//super.paintComponent(g);
		CurrentAnimationTime++; // runs animation time step
		
		//Draw player Game Object 
		int x = (int) gameworld.getPlayer().getCentre().getX();
		int y = (int) gameworld.getPlayer().getCentre().getY();
		int width = (int) gameworld.getPlayer().getWidth();
		int height = (int) gameworld.getPlayer().getHeight();
		int camX = cam.getCamXPos(x);
		String texture = gameworld.getPlayer().getTexture();
		AnimationObject animation = gameworld.getPlayer().getCurrentAnimation();
		System.out.println(Score);
		if (Score < 200) {
			mapXOffset = x - mapCurrentXPos;
			//Draw background 
			drawBackground(g, camX);		
			//Draw player
			drawPlayer(x-20, y, width, height, mapXOffset, texture, g, animation, camX, gameworld.getPlayer().getHealth(), gameworld.getPlayer().getTotalHealth(), gameworld.getPlayer().getMagic());
			mapCurrentXPos = x; //Recentre the map
	
			//System.out.println("Player X: " + x + " Y: " + y);
			  
			//Draw Bullets 
			// change back 
			gameworld.getBullets().forEach((temp) -> 
			{ 
				int w= (int) temp.getProjectile().getWidth();
				int h = (int) temp.getProjectile().getHeight();
				//g.drawRect((int)temp.getProjectile().getRectangle().getX()-camX, (int)temp.getProjectile().getRectangle().getY(),(int) temp.getProjectile().getRectangle().getWidth(), (int)temp.getProjectile().getRectangle().getHeight());
				drawBullet((int) temp.getProjectile().getCentre().getX()-camX-(w+w/2), (int) temp.getProjectile().getCentre().getY()-(h+h/2), (int)width+10, (int) height+10, mapXOffset, temp.getProjectile().getTexture(),temp.getProjectile().getCurrentAnimation(),g, camX);	 
			});
			gameworld.getEnemyBullets().forEach((temp) -> {
				int w= (int) temp.getProjectile().getWidth();
				int h = (int) temp.getProjectile().getHeight();
				//.drawRect((int)temp.getProjectile().getRectangle().getX()-camX, (int)temp.getProjectile().getRectangle().getY(),(int) temp.getProjectile().getRectangle().getWidth(), (int)temp.getProjectile().getRectangle().getHeight());
				drawBullet((int) temp.getProjectile().getCentre().getX()-camX-(w+w/2), (int) temp.getProjectile().getCentre().getY()-(h+h/2), (int)width+-10, (int) height-10, mapXOffset, temp.getProjectile().getTexture(),temp.getProjectile().getCurrentAnimation(),g, camX);	 
	
			});
			
			//Draw Enemies   
			gameworld.getEnemies().forEach((temp) -> 
			{
				int w= (int) temp.getProjectile().getWidth();
				int h = (int) temp.getProjectile().getHeight();
				//g.drawRect((int)temp.getProjectile().getRectangle().getX()-camX, (int)temp.getProjectile().getRectangle().getY(),(int) temp.getProjectile().getRectangle().getWidth(), (int)temp.getProjectile().getRectangle().getHeight());
				if (temp.getProjectile().getCentre().getX()-camX-w < 800)
					drawEnemies((int) temp.getProjectile().getCentre().getX()-camX-(w/2), (int) temp.getProjectile().getCentre().getY()-(h/2), w+100, h, temp.getProjectile().getTexture(),temp.getProjectile().getCurrentAnimation(),g, camX, temp.getXVelocity(), temp.getProjectile().getHealth(), temp.getProjectile().getTotalHealth());
			 
		    }); 
			mapPrevXPos = camX;
		}
		
		else {
			
			drawEndScreen(g);
		}
	}
	
	private void drawEnemies(int x, int y, int width, int height, String texture, AnimationObject animation, Graphics g, int camX, float xVelocity, int health, int totalHealth) {
		int animationSpeed = animation.getAnimationSpeed();
		int animationFrame = animationSpeed*animation.getNumOfAnimations();
		int textureNum;
		int w = 10;
		if (xVelocity < 0) {
			width *= -1;
			x -= width;
			w *= -1;
		}
		if (texture.toLowerCase().indexOf("mushroom") != -1)
			textureNum = 0;
		else
			textureNum = 1;
		try {
			//The spirte is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time 
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31  
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime%animationFrame)/animationSpeed))*150; //slows down animation so every 10 frames we get another frame so every 100ms 
			drawHealthBar(x,y,w,totalHealth, health, g, 4);
			g.drawImage(enemies[textureNum], x,y, x+width, y+height, currentPositionInAnimation  , animation.getSpriteY1(), currentPositionInAnimation+149, animation.getSpriteY2(), null); 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void drawBackground(Graphics g, int camX)
	{
		try {
			g.drawImage(staticBackground, 0,0, 1000, 900, 0 , 0, 300, 300, null);
			//g.drawImage(myImage2, 0,0, 1000, 900, mapCurrentXPos-300-mapXOffset , -50, mapCurrentXPos-mapXOffset, 300, null);
		    g.drawImage(movingBackground, 0,0, 1500, 600, camX, 0, camX+900, 353, null );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawBullet(int x, int y, int width, int height, int mapXOffset, String texture,AnimationObject animation,Graphics g, int camX)
	{
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		int animationSpeed = animation.getAnimationSpeed();
		int animationFrame = animationSpeed*animation.getNumOfAnimations();
		if (mapXOffset >= 0) {
			width *= -1;
			x -= width;
		}
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//Energy Ball = 128 by 128 
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime%animationFrame)/animationSpeed))*128; //slows down animation so every 10 frames we get another frame so every 100ms 
			g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , animation.getSpriteY1(), currentPositionInAnimation+127, animation.getSpriteY2(), null); 
			
			//g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 127, 63, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private void drawPlayer(int x, int y, int width, int height, int mapXOffset, String texture,Graphics g, AnimationObject animation, int camX, int health, int totalHealth, int magic) { 
		int animationSpeed = animation.getAnimationSpeed();
		int animationFrame = animationSpeed*animation.getNumOfAnimations();
		int w = 10;
		// Turn player sprite left or right depending on the map X axis offset.
		if (camX < mapPrevXPos) {
			width *= -1;
			x -= width;
		}

		try {
			//The spirte is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time 
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31  
			//System.out.println("X: " + playerX + " Y: " + playerY);
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime%animationFrame)/animationSpeed))*50; //slows down animation so every 10 frames we get another frame so every 100ms 
			drawHealthBar(10, 10, w,totalHealth,health, g ,15);
			drawPlayerMagic(magic, width, g);
			g.drawImage(playerImage, x-camX, y, x-camX+width, y+height, currentPositionInAnimation  , animation.getSpriteY1(), currentPositionInAnimation+50, animation.getSpriteY2(), null); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
		//g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer));
		//Lighnting Png from https://opengameart.org/content/animated-spaceships  its 32x32 thats why I know to increament by 32 each time 
		// Bullets from https://opengameart.org/forumtopic/tatermands-art 
		// background image from https://www.needpix.com/photo/download/677346/space-stars-nebula-background-galaxy-universe-free-pictures-free-photos-free-images
		
		/*
		 * References:
		 * StarField background and Tileset for platforms:https://trixelized.itch.io/starstring-fields
		 * Player Character: https://darkpixel-kronovi.itch.io/rogue-knight
		 * Enemy characters: https://luizmelo.itch.io/monsters-creatures-fantasy
		 */
	}
	
	private void drawBossBackground(Graphics g) {
		//File TextureToLoad = new File("res/spacebackground.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
				try {
					if (mapCurrentXPos % 550 == 550) {
						
					}
					g.drawImage(staticBackground, 0,0, 1000, 900, 0 , 0, 300, 300, null);
					//g.drawImage(myImage2, 0,0, 1000, 900, mapCurrentXPos-300-mapXOffset , -50, mapCurrentXPos-mapXOffset, 300, null);
				    g.drawImage(movingBackground, 0,0, 1500, 600, 0, 0, 900, 353, null );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	private void drawEndScreen(Graphics g) {
		g.drawImage(endScreenBackground, 0,0, 800, 600, 0 , 0, 800, 600, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Papyrus", Font.BOLD, 50));
		g.drawString(""+Score, 300, 500);

	}
	 
	private void drawHealthBar(int x, int y,int width, int totalHealth, int health,Graphics g, int height) {
		if (width < 0){
			x -= Math.abs(width);
		}
		g.setColor(Color.RED);
		g.fillRect(x, y, Math.abs(width)*totalHealth, height);
		g.setColor(Color.GREEN);
		g.fillRect(x, y, Math.abs(width)*health, height);
	}
	
	private void drawPlayerMagic(int magic, int width, Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(10, 25,100, 15);
		g.setColor(Color.BLUE);
		g.fillRect(10, 25, magic, 15);
		g.setColor(Color.BLACK);
		g.drawString("Health", 20, 20);
		g.drawString("Magick", 20, 35);
	}
	
	public void setScore(int score) {
		this.Score = score;
	}
	
	public void setisLevel1(boolean level1) {
		this.Level1 = level1;
	}
	public boolean isLevel1() {
		return this.Level1;
	}
	
	public boolean isEndScreen() {
		return this.EndScreen;
	}
	
	public void setIsEndScreen(boolean endScreen) {
		this.EndScreen = endScreen;
	}

}


/*
 * 
 * 
 *              VIEWER HMD into the world                                                             
                                                                                
                                      .                                         
                                         .                                      
                                             .  ..                              
                               .........~++++.. .  .                            
                 .   . ....,++??+++?+??+++?++?7ZZ7..   .                        
         .   . . .+?+???++++???D7I????Z8Z8N8MD7I?=+O$..                         
      .. ........ZOZZ$7ZZNZZDNODDOMMMMND8$$77I??I?+?+=O .     .                 
      .. ...7$OZZ?788DDNDDDDD8ZZ7$$$7I7III7??I?????+++=+~.                      
       ...8OZII?III7II77777I$I7II???7I??+?I?I?+?+IDNN8??++=...                  
     ....OOIIIII????II?I??II?I????I?????=?+Z88O77ZZO8888OO?++,......            
      ..OZI7III??II??I??I?7ODM8NN8O8OZO8DDDDDDDDD8DDDDDDDDNNNOZ= ......   ..    
     ..OZI?II7I?????+????+IIO8O8DDDDD8DNMMNNNNNDDNNDDDNDDNNNNNNDD$,.........    
      ,ZII77II?III??????DO8DDD8DNNNNNDDMDDDDDNNDDDNNNDNNNNDNNNNDDNDD+.......   .
      7Z??II7??II??I??IOMDDNMNNNNNDDDDDMDDDDNDDNNNNNDNNNNDNNDMNNNNNDDD,......   
 .  ..IZ??IIIII777?I?8NNNNNNNNNDDDDDDDDNDDDDDNNMMMDNDMMNNDNNDMNNNNNNDDDD.....   
      .$???I7IIIIIIINNNNNNNNNNNDDNDDDDDD8DDDDNM888888888DNNNNNNDNNNNNNDDO.....  
       $+??IIII?II?NNNNNMMMMMDN8DNNNDDDDZDDNN?D88I==INNDDDNNDNMNNMNNNNND8:..... 
   ....$+??III??I+NNNNNMMM88D88D88888DDDZDDMND88==+=NNNNMDDNNNNNNMMNNNNND8......
.......8=+????III8NNNNMMMDD8I=~+ONN8D8NDODNMN8DNDNNNNNNNM8DNNNNNNMNNNNDDD8..... 
. ......O=??IIIIIMNNNMMMDDD?+=?ONNNN888NMDDM88MNNNNNNNNNMDDNNNMNNNMMNDNND8......
........,+++???IINNNNNMMDDMDNMNDNMNNM8ONMDDM88NNNNNN+==ND8NNNDMNMNNNNNDDD8......
......,,,:++??I?ONNNNNMDDDMNNNNNNNNMM88NMDDNN88MNDN==~MD8DNNNNNMNMNNNDND8O......
....,,,,:::+??IIONNNNNNNDDMNNNNNO+?MN88DN8DDD888DNMMM888DNDNNNNMMMNNDDDD8,.... .
...,,,,::::~+?+?NNNNNNNMD8DNNN++++MNO8D88NNMODD8O88888DDDDDDNNMMMNNNDDD8........
..,,,,:::~~~=+??MNNNNNNNND88MNMMMD888NNNNNNNMODDDDDDDDND8DDDNNNNNNDDD8,.........
..,,,,:::~~~=++?NMNNNNNNND8888888O8DNNNNNNMMMNDDDDDDNMMNDDDOO+~~::,,,.......... 
..,,,:::~~~~==+?NNNDDNDNDDNDDDDDDDDNNND88OOZZ$8DDMNDZNZDZ7I?++~::,,,............
..,,,::::~~~~==7DDNNDDD8DDDDDDDD8DD888OOOZZ$$$7777OOZZZ$7I?++=~~:,,,.........   
..,,,,::::~~~~=+8NNNNNDDDMMMNNNNNDOOOOZZZ$$$77777777777II?++==~::,,,......  . ..
...,,,,::::~~~~=I8DNNN8DDNZOM$ZDOOZZZZ$$$7777IIIIIIIII???++==~~::,,........  .  
....,,,,:::::~~~~+=++?I$$ZZOZZZZZ$$$$$777IIII?????????+++==~~:::,,,...... ..    
.....,,,,:::::~~~~~==+?II777$$$$77777IIII????+++++++=====~~~:::,,,........      
......,,,,,:::::~~~~==++??IIIIIIIII?????++++=======~~~~~~:::,,,,,,.......       
.......,,,,,,,::::~~~~==+++???????+++++=====~~~~~~::::::::,,,,,..........       
.........,,,,,,,,::::~~~======+======~~~~~~:::::::::,,,,,,,,............        
  .........,.,,,,,,,,::::~~~~~~~~~~:::::::::,,,,,,,,,,,...............          
   ..........,..,,,,,,,,,,::::::::::,,,,,,,,,.,....................             
     .................,,,,,,,,,,,,,,,,.......................                   
       .................................................                        
           ....................................                                 
               ....................   .                                         
                                                                                
                                                                                
                                                                 GlassGiant.com
                                                                 */
