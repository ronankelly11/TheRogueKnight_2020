/*import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameStateManager extends JPanel{
	 private MouseController mousecontroller = MouseController.getInstance();
	 public GameStateManager() {}
	 
	 public void paintComponent(Graphics g) {
		 
	 }
	 
	 public void loadIntro() {
		 boolean introComplete = false;
		 int count = 0;
		 try {
			while (!introComplete) {
				//Image backdrop = ImageIO.read(new File("res/background.png"));				
				Image intro1 = ImageIO.read(new File("res/Intro1.png"));
				Image intro2 = ImageIO.read(new File("res/Intro2.png"));
				Image intro3 = ImageIO.read(new File("res/Intro3.png"));
				//g.drawImage(intro3, 0, 0, 0, 0, 0, 0, 0, 0, null)
				if (MouseController.getInstance().isMousePressed()) {
					switch(count) {
					case 0:g.drawImage(intro1, 200, 200, 400, 400, 0, 0, 1284, 452, null);count +=1;break;
					case 1:introComplete = true;break;
					default:break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	
	
	//Basic Model-View-controller pattern 
		public void gameloop(Model gameworld, Viewer canvas) { 
			// GAMELOOP  
			
			// controller input  will happen on its own thread 
			// So no need to call it explicitly 
			
			// model update   
			gameworld.gamelogic();
			// view update 
			
			  canvas.updateview(); 
			
			// Both these calls could be setup as  a thread but we want to simplify the game logic for you.  
			//score update  			
			 
		}
}
*/