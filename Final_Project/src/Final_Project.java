import processing.core.*; 
import java.util.ArrayList;
import java.util.HashMap; 

public class Final_Project extends PApplet {

	HashMap<String, GameObject> go = new HashMap<String, GameObject>();
	PApplet p = this;
	Player player1;
	GameFloor gameFloor;
	HealthBar hb;
	ArrayList<String> indicesToRemove = new ArrayList<String>();
	
	public static final int FLOOR_HEIGHT = 325;
	public static final int FLOOR_WIDTH = 325;
	public static final int FLOOR_LENGTH = 5;
	public int counter;
	public boolean checkedColl, checkedExp;
	public boolean upsideDown;
	public int upsideDownTimer;
	public int upsideDownCooldown;
	public int upsideDownInt;
	
	public void setup() {
		go.clear();
		counter = 0;
		background(0);
		player1  = new Player(this, new PVector(0,0,10));
		gameFloor = new GameFloor(this, new PVector(FLOOR_WIDTH/2,FLOOR_HEIGHT/2,0));
		gameFloor.getDims(FLOOR_WIDTH, FLOOR_HEIGHT, FLOOR_LENGTH);
		go.put("GameFloor", gameFloor); 
		go.put("Player", player1);
		go.put("HealthBar", new HealthBar(this, new PVector(0,0,0),player1));
		p.getGraphics();
		upsideDown = false;
		upsideDownTimer = 0;
		upsideDownCooldown = 0;
		upsideDownInt = upsideDown ? -1 : 1;
	}
	public void settings() {
		size(600,500,P3D);
	}
	
	public void draw() {
		counter++;
		upsideDownCooldown++;
		background(0);
		p.lights();
		p.colorMode(RGB);
		
		if (!upsideDown) {
			p.directionalLight(255,255,255,0, -1, 0);
			p.camera(0, 100, 300, 0, 0, 0, 0, 1,0);
		} else if(upsideDown) {
			p.directionalLight(255,255,255,0, 0, 1);
			p.camera(0,100,-300, 0, 0, 0, 0, 1,0);
			upsideDownTimer++;
			if(upsideDownTimer > 180) {
				upsideDown = false;
				player1.upsideDown(upsideDown);
				upsideDownInt = upsideDown ? -1 : 1;
			}
		}
		spawnEnemies(counter);
		for(HashMap.Entry entry: go.entrySet()) {
			GameObject o = (GameObject)entry.getValue();
			o.render();
			if( o instanceof Enemy) {
				checkedColl = ((Enemy) o).playerCollision(player1);
				checkedExp = ((Enemy) o).playerExploded(player1);
				if(player1.getExplosive() && checkedExp) {
					indicesToRemove.add((String)entry.getKey());
				}
				if(o instanceof Exploder) {
					if(((Exploder) o).getExploded()){
						player1.setExplosive();
					}
				}
			}
		}
		
		for(String s : indicesToRemove)
		  {
		    go.remove(s);
		  }
	indicesToRemove.clear();	
	}
	
	
	public void spawnEnemies(int counter) {
		if (counter %100 == 0) {
			go.put("ENEMY_"+counter, new Follower(this, new PVector(p.random(325)-160, p.random(325)-160,10 + (counter/100 % 2) * 10), player1));
		}
		
		if (counter %199 == 0) {
			go.put("ENEMY_"+counter, new Enemy(this, new PVector(p.random(325)-160, p.random(325)-160,10 + (counter/199 % 2) * 10)));
		}
		
		
		if(counter % 500 ==0) {
			go.put("ENEMY_"+counter, new Exploder(this, new PVector(p.random(325)-160, p.random(325)-160,10)));
		}
	}
	
	public void keyPressed() {
		if(key == 'd' && player1.getPlayerAlive()) {
			player1.setXSpeed(upsideDownInt * 1);
		}
		if(key == 'a'&& player1.getPlayerAlive()) {
			player1.setXSpeed(upsideDownInt * -1);
		}
		
		if(keyCode == ' '&& player1.getPlayerAlive()) {
			player1.setZSpeed(1);
		}
		if(key=='w'&& player1.getPlayerAlive()) {
			player1.setYSpeed(-1);
		}
		if(key == 's'&& player1.getPlayerAlive()) {
			player1.setYSpeed(1);
		}
		if(keyCode == ENTER) {
			setup();
		}
		
		if(key =='x' && upsideDownCooldown > 360) {
			upsideDown = !upsideDown;
			upsideDownTimer = 0;
			upsideDownCooldown = 0;
			player1.upsideDown(upsideDown);
			upsideDownInt = upsideDown ? -1 : 1;
		}
	}
	
	public void keyReleased() {
		if(key == 'a') {
			player1.setXSpeed(0);
		}
		if(key=='d') {
			player1.setXSpeed(0);
		}
		if(key=='w') {
			player1.setYSpeed(0);
		}
		if(key == 's') {
			player1.setYSpeed(0);
		}
	}
	
	public static void main(String[] args) {
		PApplet.main(new String[] {"Final_Project"} );
	}

}