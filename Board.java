public class Board {
	
	private int [][][] board;
	static private final int MIN_LEVEL = 3;
	static private final int MIN_SIZE = 3;
	private int level = 0;
	private int size = 0;
	
	
	public Board() {
		this.level = 3;
		this.size = 4;
		createBoard(this.level, this.size);
	}
	
	public Board(int l, int x) {
		
		this.level = l;
		this.size = x;
		createBoard(this.level, this.size);
	}
	
	private int[][][] createBoard(int level, int size) {
		
		int x = size;
		int y = size;
		this.board = new int [level][x][y];
		
		for (int i = 0 ; i < level ; i++ ) {
			for (int j = 0 ; j < x ; j++) {
				for (int k = 0 ; k < y ; k++) {
					
					int sum = ( i + j + k);
					
					if (sum % 3 == 0 & sum !=0) {
						board[i][j][k] = -3;
					}
					else if (sum % 5 == 0 & sum !=0) {
						board[i][j][k] = -2;
					}
					else if (sum % 7 == 0 & sum !=0) {
						board[i][j][k] = 2;
					}else{
						board[i][j][k] = 0;
					}
				}
			}
		}
		
		return board;
		
	}
	
	public int getSize() {
		return size;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getEnergyAdj(int l, int x, int y) {
		return board[l][x][y]; 
	}
	
	public String toString(Player p1) {
		
		return "Your energy is adjusted by " + getEnergyAdj(p1.getLevel(),p1.getX(),p1.getY()) + " for landing at (" + p1.getX() + "," + p1.getY() + ") at level " + p1.getLevel() + "."; 
		
	}
	
}
