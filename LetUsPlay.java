import java.util.Scanner;
import java.util.Random;


public class LetUsPlay {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Dice dice = new Dice();
		boolean b1 = false;
		boolean b2 = false;
		boolean b4 = false;
		
		
		System.out.println("\t***************************************************************\n"
						 + "\t*                                                             *\n"
						 + "\t*            Welcome to Nancy's 3D Warrior Game               *\n"
						 + "\t*                                                             *\n"
						 + "\t***************************************************************");
		
		System.out.print("\n\n\nThe default game board has 3 levels and each level has a 4x4 board. \n" 
					   + "You can use this default board size or change the size:\n"
					   + "Enter 0 to use the default board\n"
					   + "Enter -1 to use your own size\n"
					   + "--> What do you want to do? ");
	
		
		int boardsize1 = input.nextInt();
		int levels = 0;
		int boardsize2 = 0;
		System.out.println();
		
		if (boardsize1 == -1) {
			b2 = true;
		}
		
		do {
		while(!b1) {
		if (boardsize1 == -1) {
			
			System.out.println("How many levels would you like? (minimum size 3, max 10)");
			levels = input.nextInt();
			System.out.println();
			
			if (levels < 3 || levels > 10) {
				System.out.println("Sorry but " + levels + " is not a legal choice.\n");
				levels = input.nextInt();
				System.out.println();
				
				if (levels >= 3 || levels <= 10) {
					b1 = true;
				}
				
			}else {
				b1 = true;
			}
		
			System.out.println("What size to you want the nxn boards on each level to be?\n"
					         + "Minimum size is 3x3, max is 10x10.\n"
					         + "--> Enter the value of n: ");
			boardsize2 = input.nextInt();
			System.out.println();
			
			if (boardsize2 < 3 || boardsize2 > 10) {
				System.out.println("Sorry but " + boardsize2 + " is not a legal choice.");
				boardsize2 = input.nextInt();
				break;
			}else {
				b1 = true;
			}
			
		}else if (boardsize1 == 0) {
			b1 = true;
		
		}else{
			System.out.println("Sorry but " + boardsize1 + " is not a legal choice.");
			boardsize1 = input.nextInt();
			System.out.println();
		}
		
		}
		if (b1) {
			break;
		}
		}while(!b1);	
		
		Board board = new Board();
		
		if (b2) {
			board = new Board(levels, boardsize2);	
		}
		
		System.out.println("\n\nYour 3D board has been set up and looks like this: \n\n");

		
		for (int i = 0; i < board.getLevel() ; i++) {
			
			System.out.println("Level " +  i + "\n"
							 + "-----------");
			
			for (int j = 0; j < board.getSize(); j++) {
				for (int k = 0; k < board.getSize(); k++) {
					System.out.print(board.getEnergyAdj(i, j, k) + "\t");
				}
				System.out.println("\n");
			}
			
			System.out.println("\n");
			
		}
		
		System.out.print("What is player 0's name (one word only): ");
		Player p1 = new Player(input.next());
		System.out.print("What is player 1's name (one word only): ");
		Player p2 = new Player(input.next());
		System.out.println("\n");

		
		Random random = new Random();
		Boolean b3 = false;

		if (random.nextInt(2) == 1) {
			b3 = true;
			System.out.println("The game has started, " + p2.getName() + " goes first!\n" 
							 + "--------------------------------------------------\n"
							 + "--------------------------------------------------\n");
		}else{
			System.out.println("The game has started, " + p1.getName() + " goes first!\n" 
					         + "--------------------------------------------------\n"
					         + "--------------------------------------------------\n");
		}
		

		//GAME BEGINS OVER HERE 
		
		do {
		
		do {
		if(b3) {
			checkIfZero(p2);
			if (p2.getEnergy() == 0) {
				checkIfZero(p1);
				if (p1.getEnergy() != 0) {
					b3 = false;
				}
			}
				
		}else {
			checkIfZero(p1);	
			if (p1.getEnergy() == 0) {
				checkIfZero(p2);
				if (p2.getEnergy() != 0) {
					b3 = true;
				}
			}
		}
		}while(p2.getEnergy() == 0 && p1.getEnergy() == 0);
		
		int roll = 0;
		
		if (b3) {
			System.out.println("It is " + p2.getName() + "'s turn!");
			roll = dice.rollDice();
			System.out.println("\t" + p2.getName() + " you rolled " + dice);
			
			if (dice.isDouble()) {
				System.out.println("\tCongrats, you rolled a double " + dice.getDie1() + ". Your energy went up by 2 units!");
				addTwoEnergy(p2);
			}
			
			findLXY(p2, p1, roll, board);
			
			
			System.out.println("\t" + board.toString(p2));
			System.out.println("\n");

			
		}else{
			System.out.println("It is " + p1.getName() + "'s turn!");
			roll = dice.rollDice();
			System.out.println("\t" + p1.getName() + " you rolled " + dice);
			
			if (dice.isDouble()) {
				System.out.println("\tCongrats, you rolled a double " + dice.getDie1() + ". Your energy went up by 2 units!");
				addTwoEnergy(p1);
			}
			
			findLXY(p1, p2, roll, board);
			
			System.out.println("\t" + board.toString(p1));
			System.out.println("\n");


		}
		
		if (b3) {
			System.out.println("It is " + p1.getName() + "'s turn!");
			roll = dice.rollDice();
			System.out.println("\t" + p1.getName() + " you rolled " + dice);
			
			if (dice.isDouble()) {
				System.out.println("\tCongrats, you rolled a double " + dice.getDie1() + ". Your energy went up by 2 units!");
				addTwoEnergy(p1);
			}
			
			findLXY(p1, p2, roll, board);
			
			
			System.out.println("\t" + board.toString(p1));
			System.out.println("\n");

			
		}else{
			System.out.println("It is " + p2.getName() + "'s turn!");
			roll = dice.rollDice();
			System.out.println("\t" + p2.getName() + " you rolled " + dice);
			
			if (dice.isDouble()) {
				System.out.println("\tCongrats, you rolled a double " + dice.getDie1() + ". Your energy went up by 2 units!");
				addTwoEnergy(p2);
			}
			
			findLXY(p2, p1, roll, board);
			
			System.out.println("\t" + board.toString(p2));
			System.out.println("\n");

		}
		
		System.out.println("At the end of this round:\n"
				         + "\t" + p1 + "\n\t" + p2 + "\n\n");
		
		System.out.print("Press any key to continue...");
		String cont = input.next();
		
		if (p1.won(board) || p2.won(board)) {
			b4 = true;
		}
		
		}while(!b4);
		
		if (p1.won(board)) {
			System.out.println("\n\nThe winner is player " + p1.getName() + ". Well done!!!");
		}
		else if(p2.won(board)) {
			System.out.println("\n\nThe winner is player " + p2.getName() + ". Well done!!!");
		}
		
		
		input.close();
	}
	
	
	
	
	
	
	
	public static void checkIfZero(Player p) {
		
		int count = 0;
		Dice dice = new Dice();

		if (p.getEnergy()  <= 0) {
			
			do {
			dice.rollDice();
			count++;
			if (dice.isDouble()) {
				addTwoEnergy(p);
			}
			}while(count <=3);
		}
	}
	
	public static void addTwoEnergy(Player p) {
		p.setEnergy(p.getEnergy() + 2);
	}
	
	public static void findLXY(Player p, Player p2, int roll, Board b) {
		int x, y;
		int l = p.getLevel();
		boolean b1 = false;
		
		Player p3 = new Player(p);
		
		x = (p3.getX() + roll/b.getSize());
		y = (p3.getY() + roll%b.getSize());
		
		if (x >= b.getSize()) {
			if (y >= b.getSize()) {
				
				x = x + y/b.getSize();
				
				if (x >= b.getSize()) {
					x = x % b.getSize();
					l += 1;
				}
				
				y = y % b.getSize();
				
			}else {
			
			x = x % b.getSize();
			l += 1;
			}
			
		}
		
		if (l >= b.getLevel()) {
			p3.setEnergy(p3.getEnergy() - 2);
			System.out.println("!!! Sorry you need to stay where you are - that throw takes you off the grid and you loose 2 units of energy.");

		}else {
			p3.setX(x);
			p3.setY(y);
			p3.setLevel(l);
			p3.setEnergy(p3.getEnergy() + b.getEnergyAdj(l, x, y));  
		}
		
		if (p3.equals(p2)) {
			
			samePlace(p3, p2);
			
		}else {
			b1 = true;
		}
		
		if (b1) {
			p.setEnergy(p3.getEnergy());
			p.setX(p3.getX());
			p.setY(p3.getY());
			p.setLevel(p3.getLevel());
		}
		
	}
	
	public static boolean samePlace(Player p1, Player p2) {
		
		Scanner input = new Scanner(System.in);
		Random random = new Random();
		
		boolean b1 = false, b2 = false;
		int x, l = 0, y = 0;
		
		
			
			System.out.println("Player " + p2.getName() + " is at your new location\n" 
							 + "What do you want to do?\n"
					         + "\t0 - Challenge and risk loosing 50% of your energy units if you loose\n"
							 + "\t\tor move to new location and get 50% of other player’s energy units\n"
					         + "\t1 - to move down one level or move to (0,0) if at level 0 and lose 2 energy units");
			
			do {
				
				x = input.nextInt();
				
				if (x != 0 && x != 1) {
					System.out.println("That is not a valid choice");
				}else {
					b1 = true;
				}
				
			}while(!b1);
				
				if (x == 1) {
					l = p1.getLevel();
					
					if (l == 0) {
						p1.setX(0);
						p1.setY(0);
					}else {
						p1.setLevel(p1.getLevel() - 1);
					}
					
					p1.setEnergy(p1.getEnergy() - 2);
				}
				
				if (x == 0) {
					y = random.nextInt(11);
					
					if (y >= 6) {
						int tempX = p2.getX();
						int tempY = p2.getY();
						
						p2.setX(p1.getX());
						p2.setY(p1.getY());
						
						p1.setX(tempX);
						p1.setY(tempY);
						
						p2.setEnergy(p2.getEnergy()/2);
						
						b2 = true;
						
						System.out.println("\tBravo!! You won the challenge.\n"
						         + "\t" + p1);
					}else {
						p1.setEnergy(p1.getEnergy()/2);
						System.out.println("\tSorry you lost the challenge. You lose 2 energy points.\n"
								         + "\t" + p1);
					}
				}		
		
			
		if (b2) {
			return true;
		}else {
			return false;
		}
		
	}
 
}
