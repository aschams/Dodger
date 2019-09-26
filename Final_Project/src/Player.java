import processing.core.*;

public class Player extends GameObject {
	
	private float gravity = 0.05f;
	private float zSpeed;
	private float xSpeed;
	private float ySpeed;
	private int PLAYER_SIZE = 10;
	private int livesNumber;
	private int playerScore;
	private boolean playerImmunity;
	private int immunityCounter;
	private boolean explosive;
	private int explosiveCounter;
	private boolean playerAlive;
	
	public Player(PApplet _p, PVector _position) {
		super(_p, _position);
		livesNumber = 3;
		playerImmunity = false;
		immunityCounter = 0;
		playerScore = 0;
		explosive = false;
		explosiveCounter = 0;
		playerAlive = true;
	}
	
	
	public void buildObject() {
		// p.noStroke();
		if(position.z > 10.5) {
			p.fill(0);
			p.pushMatrix();
				p.translate(0,  30 - 30* PLAYER_SIZE / position.z, 0);
				p.sphere(PLAYER_SIZE/ position.z * 7);
			p.popMatrix();
			}
		p.fill(p.color(255,240, 240));
		p.sphere(PLAYER_SIZE);
		if (playerAlive) {
			updatePosition();
			playerScore++;
			immunityCounter++;
			checkImmunity();
		}
		
		if(livesNumber == 0) {
			playerAlive = false;
		}
		if (explosive) {
			explosiveCounter++;
			if (explosiveCounter>5){
				explosive=false;
				explosiveCounter = 0;
			}
		}
		
	}
	
	public void setXSpeed(float speed) {
		xSpeed = speed;
	}
	
	public void setZSpeed(float speed) {
		zSpeed = speed;
	}
	
	public void setYSpeed(float speed) {
		ySpeed = speed;
	}
	
	public int getLivesNumber() {
		return livesNumber;
	}
	
	public void setLivesNumber(int newLivesNumber) {
		livesNumber = newLivesNumber;
	}
	
	public int getPlayerScore() {
		return playerScore;
	}
	
	public void setPlayerImmunity(boolean b) {
		playerImmunity = b;
	}
	
	public boolean getPlayerImmunity() {
		return playerImmunity;
	}
	
	public void checkImmunity() {
		if (immunityCounter > 60) {
			setPlayerImmunity(false);
			immunityCounter = 0;
		}
	}
	
	public void increasePlayerScore(int i) {
		playerScore += i;
	}
	
	public void setExplosive() {
		explosive = true;
	}
	
	public boolean getExplosive() {
		return explosive;
	}
	
	public boolean getPlayerAlive() {
		return playerAlive;
	}
	
	public void upsideDown(boolean b) {
		if (b) {
			position.z = -position.z;
			position.y = -position.y;
		} else{
			position.z = -position.z;
			position.x = -position.x;
		}

	}
	public void updatePosition() {
		
		position.z += zSpeed;
		position.x += xSpeed;
		position.y += ySpeed;
		
		position.y = PApplet.constrain(position.y,
										-Final_Project.FLOOR_HEIGHT/2 + PLAYER_SIZE,
										Final_Project.FLOOR_HEIGHT/2 - PLAYER_SIZE);
		position.x = PApplet.constrain(position.x, 
										-Final_Project.FLOOR_WIDTH/2 + PLAYER_SIZE,
										Final_Project.FLOOR_WIDTH/2 - PLAYER_SIZE);
		if(position.z > 10.5) {
			zSpeed -= gravity;
		}else if(Math.abs(position.z)<10.5){
			position.z = 10.5f;
			zSpeed = 0;
		}
		if(position.z > 30) {
			position.z =30f;
		}
	}
	
	

}
