import processing.core.*;
public class Exploder extends Enemy {
	
	private boolean exploded;
	
	Exploder(PApplet _p, PVector _position){
		super(_p, _position);
		this.enemy_color = p.color(255,0,0);
		exploded = false;
	}
	
	public boolean playerCollision(Player pl) {
		if (PApplet.dist(pl.position.x, pl.position.y, pl.position.z, position.x, position.y, position.z)<8 
				&& !pl.getExplosive()) {
			exploded = true;
		}
		
		return(PApplet.dist(pl.position.x, pl.position.y, pl.position.z, position.x, position.y, position.z)<8 );
	}
	
	public boolean getExploded() {
		return exploded;
	}
}
