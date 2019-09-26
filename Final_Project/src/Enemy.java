import processing.core.*;

public class Enemy extends GameObject {
	protected double xSpeed;
	protected double ySpeed;
	protected double zSpeed;
	protected float ENEMY_SIZE;
	protected int enemy_color;
	protected boolean toBeRemoved;
	
	public Enemy(PApplet _p, PVector _position) {
		super(_p, _position);
		zSpeed = 0;
		ySpeed = 2 - Math.random()*4;
		xSpeed = 2 - Math.random()*4;
		ENEMY_SIZE = 6;
		enemy_color = (int)p.random(100) + 50;
		toBeRemoved = false;
	}
	
	public void buildObject() {
		
		p.noStroke();
		if(position.z > 10.5) {
			p.fill(0);
			p.pushMatrix();
				p.translate(0,  30 - 30* ENEMY_SIZE / position.z, 0);
				p.sphere(ENEMY_SIZE/ position.z * 7);
			p.popMatrix();
			}
		p.fill(enemy_color);
		p.box(ENEMY_SIZE);
		updatePosition();
	}
	
	public void updatePosition() {
		position.y += ySpeed;
		position.x += xSpeed;
		position.z += zSpeed;
		checkCollisions();
		position.y = PApplet.constrain(position.y,
				-Final_Project.FLOOR_HEIGHT/2 + ENEMY_SIZE,
				Final_Project.FLOOR_HEIGHT/2 - ENEMY_SIZE);
		position.x = PApplet.constrain(position.x, 
				-Final_Project.FLOOR_WIDTH/2 + ENEMY_SIZE,
				Final_Project.FLOOR_WIDTH/2 - ENEMY_SIZE);
		if (position.z <11) {
			position.z = 10;
		}
	}
	
	public void checkCollisions() {
		
		if (position.y > Final_Project.FLOOR_HEIGHT/2 - ENEMY_SIZE) {
			position.y =  Final_Project.FLOOR_HEIGHT/2 - ENEMY_SIZE;
			ySpeed = -ySpeed;
		}
		
		if(position.y < -Final_Project.FLOOR_HEIGHT/2 + ENEMY_SIZE) {
			position.y = -Final_Project.FLOOR_HEIGHT/2 + ENEMY_SIZE;
			ySpeed = -ySpeed;
		}
		
		if(position.x > Final_Project.FLOOR_WIDTH/2 - ENEMY_SIZE ) {
			position.x = Final_Project.FLOOR_WIDTH/2 - ENEMY_SIZE;
			xSpeed = -xSpeed;
		}
		
		if(position.x < -Final_Project.FLOOR_WIDTH/2 + ENEMY_SIZE ) {
			position.x = -Final_Project.FLOOR_WIDTH/2 + ENEMY_SIZE;
			xSpeed = -xSpeed;
		}
		
		
	}
	
	
	public boolean playerCollision(Player pl) {
		if (PApplet.dist(pl.position.x, pl.position.y, pl.position.z, position.x, position.y, position.z)<8 
				&& !pl.getPlayerImmunity()
				&& !pl.getExplosive()) {
			pl.setLivesNumber(pl.getLivesNumber() - 1);
			pl.setPlayerImmunity(true);
		}
		return(PApplet.dist(pl.position.x, pl.position.y, pl.position.z, position.x, position.y, position.z)<8 );
	}
	
	public boolean playerExploded(Player pl) {
		return(PApplet.dist(pl.position.x, pl.position.y, pl.position.z, position.x, position.y, position.z)<75); 
	}
}
