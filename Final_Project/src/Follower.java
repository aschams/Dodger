import processing.core.*;

public class Follower extends Enemy {

	Player pl;
	float distToPlayer;
	
	public Follower(PApplet _p, PVector _position, Player _pl) {
		super(_p, _position);
		pl = _pl;
		this.enemy_color = p.color(0,0,255);
	}
	
	public void buildObject() {
		updateSpeed();

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
	
	private void updateSpeed() {
		distToPlayer = PApplet.dist(position.x,  position.y,  position.z,  pl.position.x, pl.position.y, pl.position.z);
		xSpeed = (pl.position.x - position.x)/distToPlayer / 2;
		ySpeed = (pl.position.y - position.y)/distToPlayer / 2 ;
	}
}
