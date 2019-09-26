import processing.core.*;
// import java.util.ArrayList;


abstract class GameObject {
	protected PApplet p;
	// protected ArrayList al;
	protected PImage myTexture;
	protected PVector position;
	protected int c;
	
	public GameObject() {}
	
	public GameObject(PApplet _p) {
		p = _p;
	}
	
	public GameObject(PApplet _p, PVector _position) {
		p = _p;
		position = _position;
	}
	
	public void setTexture(String texturePath) {
		myTexture = p.loadImage(texturePath);
	}
	
	public void buildObject() {
		p.fill(0);
		p.sphere(3);
	}
	
	public void updatePosition(PVector newPosition) {
		position = newPosition;
	}
	
	public void render() {
		p.pushMatrix();
			p.translate(position.x, position.y, position.z);
			buildObject();
		p.popMatrix();
	}
	
	
	
	
	
	
	
	
}
