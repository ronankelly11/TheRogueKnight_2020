import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

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
 */ 

//Singeton pattern
public class MouseController implements MouseListener {
        
	   private static boolean MouseButton1Pressed= false;
	   private static boolean MouseButton2Pressed= false;
	   private static boolean MouseButton3Pressed= false;
	   private static int mouseXCoordinate = 0;
	   private static int mouseYCoordinate = 0;
	   
	   
	   private static final MouseController instance = new MouseController();
	   
	 public MouseController() { 
	}
	 
	 public static MouseController getInstance(){
	        return instance;
	    }
	 
	@Override
		public void mousePressed(MouseEvent e) {
		this.setX(e.getX());
		this.setY(e.getY());
		System.out.println(e.getY());
		
			switch (e.getButton()) {
				case MouseEvent.BUTTON1:this.setMouseButton1Pressed(true);break;
				case MouseEvent.BUTTON2:this.setMouseButton2Pressed(true);break;
				case MouseEvent.BUTTON3:this.setMouseButton3Pressed(true);break;
				default:
					break;
			}
		} 
		
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		switch (e.getButton()) {
			case MouseEvent.BUTTON1:this.setMouseButton1Pressed(false);break;
			case MouseEvent.BUTTON2:this.setMouseButton2Pressed(false);break;
			case MouseEvent.BUTTON3:this.setMouseButton3Pressed(false);break;
			default:
		        break;
		}
	}


	public boolean isMouseButton1Pressed() { return MouseButton1Pressed; }
	public void setMouseButton1Pressed(boolean MouseButton1Pressed) { this.MouseButton1Pressed = MouseButton1Pressed; }
	
	public boolean isMouseButton2Pressed() { return MouseButton2Pressed; }
	public void setMouseButton2Pressed(boolean MouseButton2Pressed) { this.MouseButton2Pressed = MouseButton2Pressed;}
	
	public boolean isMouseButton3Pressed() {return MouseButton3Pressed;}
	public void setMouseButton3Pressed(boolean MouseButton3Pressed) {this.MouseButton3Pressed = MouseButton3Pressed;}
	
	public void setX(int mouseXCoordinate) { this.mouseXCoordinate = mouseXCoordinate;}
	public int getX() {return this.mouseXCoordinate;}
	
	public void setY(int mouseYCoordinate) { this.mouseYCoordinate = mouseYCoordinate;}
	public int getY() {return this.mouseYCoordinate;}

	public boolean isMousePressed() {
		return isMouseButton1Pressed() || isMouseButton2Pressed() || isMouseButton3Pressed();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	} 
	
	 
}

/*
 * 
 * KEYBOARD :-) . can you add a mouse or a gamepad 

 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ @@@@@@@@@@@@@@@

  @@@     @@@@    @@@@    @@@@    @@@@     @@@     @@@     @@@     @@@     @@@  

  @@@     @@@     @@@     @@@@     @@@     @@@     @@@     @@@     @@@     @@@  

  @@@     @@@     @@@     @@@@    @@@@     @@@     @@@     @@@     @@@     @@@  

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

@     @@@     @@@     @@@      @@      @@@     @@@     @@@     @@@     @@@     @

@     @@@   W   @@@     @@@      @@      @@@     @@@     @@@     @@@     @@@     @

@@    @@@@     @@@@    @@@@    @@@@    @@@@     @@@     @@@     @@@     @@@     @

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@N@@@@@@@@@@@@@@@@@@@@@@@@@@@

@@@     @@@      @@      @@      @@      @@@     @@@     @@@     @@@     @@@    

@@@   A   @@@  S     @@  D     @@      @@@     @@@     @@@     @@@     @@@     @@@    

@@@@ @  @@@@@@@@@@@@ @@@@@@@    @@@@@@@@@@@@    @@@@@@@@@@@@     @@@@   @@@@@   

    @@@     @@@@    @@@@    @@@@    $@@@     @@@     @@@     @@@     @@@     @@@

    @@@ $   @@@      @@      @@ /Q   @@ ]M   @@@     @@@     @@@     @@@     @@@

    @@@     @@@      @@      @@      @@      @@@     @@@     @@@     @@@     @@@

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

@       @@@                                                @@@       @@@       @

@       @@@              SPACE KEY       @@@        @@ PQ     

@       @@@                                                @@@        @@        

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * 
 * 
 * 
 * 
 * 
 */
