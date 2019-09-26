import processing.core.*;
public class HealthBar extends GameObject {
	
	Player pl;
	public HealthBar(PApplet _p, PVector _position, Player _pl) {
		super(_p, _position);
		pl = _pl;
	}

	public void buildObject(){
		p.getGraphics().hint(PConstants.DISABLE_DEPTH_TEST);
		p.fill(255);
		p.stroke(255);
		p.strokeWeight(3);
		p.rect(-300, -300, 1000, 125);
		p.fill(0);
		p.textSize(14);
		p.text("Health", -200, -200);
		p.getGraphics().hint(PConstants.ENABLE_DEPTH_TEST);
	    drawHealth(pl);
	    drawScore(pl);
	  }
	  
	  
	  public void drawHealth(Player pl){
	      PImage heart = p.loadImage("Heart.png");
	      heart.resize(25,25);
	      p.fill(0);
	    
	      for(int i =0; i< pl.getLivesNumber(); i++){
	        p.image(heart, -175 + 50*i,-200);}
	      }
	      
	      
	  public void drawScore(Player pl){
	      p.text("Score:" + PApplet.str(pl.getPlayerScore() / 30), 175, -200);}

}
