import java.util.Random;

public class Dice {
	
	private int die1 = 0;
	private int die2 = 0;
	private Random roll = new Random();

	
	public Dice(){
		
		this.die1 = die1;
		this.die2 = die2;
		
	}
	
	public int getDie1() {
		return die1;
		
	}
	
	public int getDie2() {
		return die2;
		
	}
	
	public int rollDice() {
		
		die1 = (roll.nextInt(6) + 1);
		die2 = (roll.nextInt(6) + 1);
		
		return (die1 + die2);	
		
	}
	
	public boolean isDouble() {
		
		if (die1 == die2) {
			return true;
		
		}else{
			return false;
			
		}
	}
	
	public String toString() {
		
		return "Die1: " + die1 + " Die2: " + die2;
		
	}

}
