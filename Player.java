public class Player {
	
	private String name; 
	private int level = 0;
	private int x = 0;
	private int y = 0;
	private int energy = 0;
	
	
	public Player() {
		name = " ";
		energy = 10;
		x = 0;
		y = 0;
		level = 0;
	}
	
	public Player(String n) {
		name = n;
		energy = 10;
		x = 0;
		y = 0;
		level = 0;
	}
	
	public Player(int level, int x, int y) {
		name = " ";
		this.x = x;
		this.y = y;
		this.level = level;
		energy = 10;
	}
	
	public Player(Player x) {
		
		this.name = x.name;
		this.level = x.level;
		this.x = x.x;
		this.y = x.y;
		this.energy = x.energy;
		
	}
	
	public String getName() {
		return name;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void moveTo(Player p) {
		this.level = p.level;
		this.x = p.x;
		this.y = p.y;
	}
	
	public boolean won(Board b) {
		
		if (b.getSize()-1 == this.x && b.getSize()-1 == this.y & b.getLevel()-1 == this.level) {
			return true;
		}else{
			return false;
		}
			
	}
	
	public boolean equals(Player p) {
		
		if (this.x == p.x & this.level == p.level & this.y == p.y) {
			return true;
		}else{
			return false;
		}
	}
	
	public String toString() {
		
		return this.name + " is on level " + this.level + " at location " + "(" + this.x + "," + this.y + ") and has " + this.energy + " units of energy.";
	}
	
}
