package finalproject;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;


public class FinalProject extends PApplet 
{
	PImage background;
	
	public PFont font;  
		
	public float speed = 1;
	
	public float jumpSpeed =0;
	
	public float duckSpeed = 0;
	
	public float jumpHeight = 10;
	
	public float duckHeight= 3;
	
    public float x = 400;
    
    public float y = 375;
    
    public float posX, posY;
    
    public float speedX, speedY;
    
    int radius;
    
    int groundLevel = 375;
    
    int wallBarrier = 700;
    
    int startTime;
    
    final int DISPLAY_DURATION = 8000;
    
    public boolean bDisplayMessage = false;
    
    public boolean onPlatform = false;
    
    public boolean isJumping = false;
    
    public boolean isDucking = false;
    
    public boolean moveForward = false;
    
    public boolean moveBackward = false;
    
    public boolean duckDown = false; 
    
    public boolean jumpUp = false;
    
    public boolean moving = false;

	public void setup() {
		size(800,750);
		background = loadImage("sky.png");
		font = createFont("Arial", 28, true);
		textFont(font);
	}

	public void draw() {
		background(background);
		drawFloor();
		drawPlatform();
		
		//controls
		move();
		drawCharacter();
		
		//translate(x,y);
		fill(255,255,255);
		text("Welcome to T-Pose walking simulator!", 400,50); 
		text("w and spacebar = jump", 400,75); 
		text("Holding s = stop moving", 400,100);
		text("a = move left",400,130);
		text("d = move right",400, 160);
		textAlign(CENTER);
		
		//jumping onto platforms
		jumpingPlatforms();
		
		 if (bDisplayMessage)
		  {
		    text("You got it!", 300, 100);
		  }
	}
	public void drawPlatform()
	{
		//platform color
		fill(300,0,0);
		//platform
		rect(330, 610, 1000,80);
	}
	public void drawFloor()
	{
		//floor color
		fill(300, 0, 0);
		//floor
		rect( 0 , 688 , 2500, 800);
	}
	
	//set up for character
	public int xCharacter = 0;
	public int yCharacter = 0;
	
	public void drawCharacter()
	{
		//head color
		stroke(0,100, 5, 8);
		fill(255,255,255);
		//head
		rect(x -389 , y + 250,8, 15 );
		
		//neck color
		fill(255,255,255);
		//neck
		rect(x -386, y +265, 3,6); 
		
		//body color
		fill(255,255,255);
		//body
	    rect(x - 392, y +271,  15 , 25);
	   
	    //right leg 
	    rect(x - 381, y + 296, 4 , 16);
	    //left leg
	    rect(x -392, y + 296, 4, 16);
	   
	    //right arm
	    rect(x -377 ,y +271 , 10 , 5);
	    //left arm
	    rect(x -401, y +271 , 10 ,5);
		
		
		
	}
	
	public void mousePressed()
	{
	  bDisplayMessage = dist(mouseX, mouseY, posX, posY) < radius;
	  startTime = millis(); 
	}
	public void keyPressed()
	{
		if(key == 'd')
		{
			moveForward= true;
			moving= true;
		}
		if(key == 'a')
		{
			moving = true;
			moveBackward=true;
		}
		if((key == 'w' || key == ' ') && !isJumping)
		{
			jumpUp = true;
			isJumping=true;
			jumpSpeed = -10;
		}
		if(key == 's')
		{
			duckDown= true;
			isDucking=true;
			speed = 0; 
			moveForward=false;
			moving=false;
		}
	}
	public void keyReleased()
	{
		if(key == 'd')
		{
			moving = false;
			moveForward = false;
		}
		if(key == 'a')
		{
			moveBackward = false;
			moving = false;
		}
		if(key == ' ' || key == 'w')
		{
			jumpUp= false;
		}
		if(key == 's')
		{
			duckDown = false;
			isDucking= false;
			speed=1; 
		}
	}
	public void move()
	{
		if(moveForward)
		{
			x += speed;
		}
		if(moveBackward)
		{
			x -=speed; 
		}

		y+=jumpSpeed;
		if(isJumping)
		{
			System.out.println(jumpSpeed);
			isDucking=false; 
			jumpSpeed += 0.5;
			
		}
		if(y> groundLevel)
		{
			y=groundLevel;
			isJumping= false;
			jumpSpeed=0;
		}
		y-=duckSpeed; 	
		if(isDucking)
		{
			if(y>375)
			{
				y=375;
				isDucking=false;
				duckSpeed=0;
				moving=false;
				moveForward=false;
				moveBackward=false; 
				speed=0;
			}
		} 
	}
		
	public void jumpingPlatforms()
	{
		if(onPlatform)
		{
			if(x<700)
			{
				onPlatform=false;
				isJumping=true;
			}
		}else
		{
			if(x>700 && y>300)
			{
				x=700;
			}
		}
		if(x>700 && isJumping)
		{
			onPlatform=true;
			groundLevel=300;
		}else
		{ 
			groundLevel=375;
			
		}
		
//		if(onPlatform)
//		{	
//			y= 300;
//			if(y>300)
//			{
//				y=300;
//				isJumping=false; 
//				jumpSpeed=0;
//			}
//			
//			
//		}
			
	}
	
	

}
