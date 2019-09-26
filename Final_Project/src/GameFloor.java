import processing.core.*;


public class GameFloor extends GameObject {
	public static int FLOOR_WIDTH, FLOOR_HEIGHT, FLOOR_LENGTH;
	public GameFloor(PApplet _p) {
		p = _p;
	}
	
	public GameFloor(PApplet _p, PVector _position) {
		super(_p, _position);
	}
	
	public void getDims(int w, int h, int l) {
		FLOOR_WIDTH = w;
		FLOOR_HEIGHT = h;
		FLOOR_LENGTH = l;
	}
	
	public void buildObject() {
		p.fill(p.color(0,255,255));
		p.pushMatrix();
			p.translate(-FLOOR_WIDTH/2, -FLOOR_HEIGHT/2,0);
			p.box(FLOOR_WIDTH, FLOOR_HEIGHT, FLOOR_LENGTH);
		p.popMatrix();
	}
}
