import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class game {
	int[] b1, b2, b3, b4, b5, b6, b7, b8, b9;
	int[][] gameBoard;
	int[][] gameBoardSolved = new int[9][9];
	Integer[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	int x;
	int y;
	ArrayList<Integer>possibleNumbers=new ArrayList<Integer>();
	
	ArrayList<move>moves=new ArrayList<move>();
	ArrayList<move>undone=new ArrayList<move>();
	ArrayList<move>hints=new ArrayList<move>();

	ArrayList <Integer>Y1=new ArrayList<Integer> ();
	ArrayList <Integer>Y2=new ArrayList<Integer> ();
	ArrayList <Integer>Y3=new ArrayList<Integer> ();
	ArrayList <Integer>Y4=new ArrayList<Integer> ();
	ArrayList <Integer>Y5=new ArrayList<Integer> ();
	ArrayList <Integer>Y6=new ArrayList<Integer> ();
	ArrayList <Integer>Y7=new ArrayList<Integer> ();
	ArrayList <Integer>Y8=new ArrayList<Integer> ();
	ArrayList <Integer>Y9=new ArrayList<Integer> ();
	
	ArrayList <Integer>kutu1=new ArrayList<Integer> ();
	ArrayList <Integer>kutu2=new ArrayList<Integer> ();
	ArrayList <Integer>kutu3=new ArrayList<Integer> ();
	ArrayList <Integer>kutu4=new ArrayList<Integer> ();
	ArrayList <Integer>kutu5=new ArrayList<Integer> ();
	ArrayList <Integer>kutu6=new ArrayList<Integer> ();
	ArrayList <Integer>kutu7=new ArrayList<Integer> ();
	ArrayList <Integer>kutu8=new ArrayList<Integer> ();
	ArrayList <Integer>kutu9=new ArrayList<Integer> ();

	List<Integer> numbers1 = new ArrayList<Integer>();
	
	
	//  Generate a game, assign X and Y , generate a board for game and possible
	// numbers to be entered to a cell from 1 to 9.
	public game(int y1, int x1) {
		this.y = y1;
		this.x = x1;
		gameBoard = new int[y][x];
			
	}
	
	public void prepareGameReady() {
		this.create();
		for (int i = 0 ; i < 9 ; i++) {
			for (int j = 0 ; j < 9 ; j++) {
				gameBoardSolved[i][j] = gameBoard[i][j];
			}
		}
		
		for (int i = 0 ; i < 7 ; i++) {
			gameBoard[new Random().nextInt(3)][new Random().nextInt(3)] = 0;
		}
		
		for (int i = 0 ; i < 7 ; i++) {
			gameBoard[3 + new Random().nextInt(3)][new Random().nextInt(3)] = 0;
		}
		
		for (int i = 0 ; i < 7 ; i++) {
			gameBoard[6 + new Random().nextInt(3)][new Random().nextInt(3)] = 0;
		}
		
		for (int i = 0 ; i < 7 ; i++) {
			gameBoard[new Random().nextInt(3)][3 + new Random().nextInt(3)] = 0;
		}
		
		for (int i = 0 ; i < 7 ; i++) {
			gameBoard[3 + new Random().nextInt(3)][3 + new Random().nextInt(3)] = 0;
		}
		
		for (int i = 0 ; i < 7 ; i++) {
			gameBoard[6 + new Random().nextInt(3)][3 + new Random().nextInt(3)] = 0;
		}
		
		for (int i = 0 ; i < 7 ; i++) {
			gameBoard[new Random().nextInt(3)][6 + new Random().nextInt(3)] = 0;
		}
		
		for (int i = 0 ; i < 7 ; i++) {
			gameBoard[3 + new Random().nextInt(3)][6 + new Random().nextInt(3)] = 0;
		}
		
		for (int i = 0 ; i < 7 ; i++) {
			gameBoard[6 + new Random().nextInt(3)][6 + new Random().nextInt(3)] = 0;
		}
	}
	
	public void hint() {
		boolean isItFound = false;
		while(!isItFound) {
		int row = new Random().nextInt(9);
		int column = new Random().nextInt(9);
			if(this.gameBoard[row][column] == 0) {
				this.gameBoard[row][column] = this.gameBoardSolved[row][column];
				hints.add(new move(row,column,gameBoard[row][column],0));
				moves.add(new move(row,column,gameBoard[row][column],0));
				isItFound = true;

			}
		}
	}


	//  Check all X's if a number repeats itself.
	public boolean checkX() {
		boolean controlX = true;
		for (int i = 0; i < this.y; i++) {
			for (int j = 0; j < this.x; j++) {
				for (int n = (j + 1); n < this.x; n++) {
					if (this.gameBoard[i][j] == this.gameBoard[i][n]) {

						controlX = false;
					}
				}
			}

		}
		return controlX;
	}

	//  Check all Y's if a number repeats itself.
	public boolean checkY() {
		boolean controlY = true;
		for (int i = 0; i < this.x; i++) {
			for (int j = 0; j < this.y; j++) {
				for (int n = (j + 1); n < this.y; n++) {
					if (gameBoard[j][i] == gameBoard[n][i]) {
						controlY = false;
					}
				}

			}
		}
		return controlY;
	}

	// Check every 3x3 boxes if a number repeats itself.
	public boolean checkGrids() {
		boolean box1 = true, box2 = true, box3 = true, box4 = true, box5 = true, box6 = true, box7 = true, box8 = true,
				box9 = true, allBoxes = true;
		b1 = new int[this.x];
		b2 = new int[this.x];
		b3 = new int[this.x];
		b4 = new int[this.x];
		b5 = new int[this.x];
		b6 = new int[this.x];
		b7 = new int[this.x];
		b8 = new int[this.x];
		b9 = new int[this.x];
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int e = 0;
		int f = 0;
		int g = 0;
		int h = 0;
		int s = 0;

		//  start of box 1
		for (int i = 0; i < (int) Math.sqrt(this.y); i++) {
			for (int j = 0; j < (int) Math.sqrt(this.x); j++) {
				b1[a] = this.gameBoard[i][j];
				a++;
			}
		}
		for (int m = 0; m < this.x; m++) {
			for (int n = (m + 1); n < this.x; n++) {
				if (b1[m] == b1[n]) {
					box1 = false;
				}
			}
		}
		//  end of box 1 / Start of box 2
		for (int i = 0; i < Math.sqrt(this.y); i++) {
			for (int j = (int) Math.sqrt(this.x); j < (int) (2 * Math.sqrt(this.x)); j++) {
				b2[b] = this.gameBoard[i][j];
				b++;
			}
		}
		for (int m = 0; m < this.x; m++) {
			for (int n = (m + 1); n < this.x; n++) {
				if (b2[m] == b2[n]) {
					box2 = false;
				}
			}
		}

		//  end of box 2 / Start of box 3
		for (int i = 0; i < Math.sqrt(this.y); i++) {
			for (int j = (int) (2 * Math.sqrt(this.x)); j < (int) (3 * (Math.sqrt(this.x))); j++) {
				b3[c] = this.gameBoard[i][j];
				c++;
			}
		}
		for (int m = 0; m < this.x; m++) {
			for (int n = (m + 1); n < this.x; n++) {
				if (b3[m] == b3[n]) {
					box3 = false;
				}
			}
		}

		//  end of box 3 / Start of box 4
		for (int i = (int) ((Math.sqrt(this.y))); i < (int) 2 * Math.sqrt(this.y); i++) {
			for (int j = 0; j < (Math.sqrt(this.x)); j++) {
				b4[d] = this.gameBoard[i][j];
				d++;

			}
		}
		for (int m = 0; m < this.x; m++) {
			for (int n = (m + 1); n < this.x; n++) {
				if (b4[m] == b4[n]) {
					box4 = false;
				}
			}
		}

		//  end of box 4 / Start of box 5
		for (int i = (int) Math.sqrt(this.y); i < (int) 2 * Math.sqrt(this.y); i++) {
			for (int j = (int) Math.sqrt(this.x); j < (int) 2 * Math.sqrt(this.x); j++) {
				b5[e] = this.gameBoard[i][j];
				e++;

			}
		}
		for (int m = 0; m < this.x; m++) {
			for (int n = (m + 1); n < this.x; n++) {
				if (b5[m] == b5[n]) {
					box5 = false;
				}
			}
		}
		//  end of box 5 / Start of box 6
		for (int i = (int) Math.sqrt(this.y); i < (int) 2 * Math.sqrt(this.y); i++) {
			for (int j = (int) (2 * Math.sqrt(this.x)); j < (int) 3 * Math.sqrt(this.x); j++) {
				b6[f] = this.gameBoard[i][j];
				f++;

			}
		}
		for (int m = 0; m < this.x; m++) {
			for (int n = (m + 1); n < this.x; n++) {
				if (b6[m] == b6[n]) {
					box6 = false;
				}
			}
		}
		//  end of box 6 / Start of box 7
		for (int i = (int) (2 * Math.sqrt(this.y)); i < (int) 3 * Math.sqrt(this.y); i++) {
			for (int j = 0; j < (int) Math.sqrt(this.x); j++) {
				b7[g] = this.gameBoard[i][j];
				g++;

			}
		}
		for (int m = 0; m < this.x; m++) {
			for (int n = (m + 1); n < this.x; n++) {
				if (b7[m] == b7[n]) {
					box7 = false;
				}
			}
		}
		//  end of box 7 / Start of box 8
		for (int i = (int) (2 * Math.sqrt(this.y)); i < (int) 3 * Math.sqrt(this.y); i++) {
			for (int j = (int) Math.sqrt(this.x); j < (int) 2 * Math.sqrt(this.x); j++) {
				b8[h] = this.gameBoard[i][j];
				h++;
			}
		}
		for (int m = 0; m < this.x; m++) {
			for (int n = (m + 1); n < this.x; n++) {
				if (b8[m] == b8[n]) {
					box8 = false;
				}
			}
		}
		//  end of box 8 / start of box 9
		for (int i = (int) (2 * Math.sqrt(this.y)); i < (int) 3 * Math.sqrt(this.y); i++) {
			for (int j = (int) (2 * Math.sqrt(this.x)); j < (int) 3 * Math.sqrt(this.x); j++) {
				b9[s] = this.gameBoard[i][j];
				s++;

			}
		}
		for (int m = 0; m < this.x; m++) {
			for (int n = (m + 1); n < this.x; n++) {
				if (b9[m] == b9[n]) {
					box9 = false;
				}
			}
		}
		if ((box1 != true) || (box2 != true) || (box3 != true) || (box4 != true) || (box5 != true) || (box6 != true)
				|| (box7 != true) || (box8 != true) || (box9 != true)) {
			allBoxes = false;
		}

		return allBoxes;
		//  end of box(3x3 Grid) check--- (Check for completed sudoku)
	}
	//Check to availibility for generating solved sudoku
	//TODO
	public boolean checkEmpty() {
		boolean emptyCheck=true;
		for(int i=0;i<this.y;i++) {
			for(int j=0;j<this.x;j++) {
				if(this.gameBoard[i][j]==0) {
					
					return true;
				}
			}
		}
		return false;
	}

	public void setGameBoard(int y, int x, int k) {
		this.gameBoard[y][x] = k;
	}
	public void setMove(int y,int x,int k) {
		
		this.gameBoard[y][x] = k;
	}
	public void setredo(int y,int x,int k) {
		this.gameBoard[y][x]=k;
	}
	
	public String toString() {
		for (int i = 0; i < this.y; i++) {
			for (int k = 0; k < this.x; k++) {
				System.out.printf("%5d", +this.gameBoard[i][k]);

			}
			System.out.println();
		}
		return "";

	}

	public int getX() {
		return x;
	}

	

	public int getY() {
		return y;
	}

		public boolean checkGame() {
		boolean gameCheck;

		if((this.checkY()!=true)||((this.checkX()!=true)||(this.checkGrids()!=true))) {
			gameCheck=false;
		}
		else {
			gameCheck=true;
		}
		return gameCheck;
	}
	
		public void shuffle() {
			numbers1=Arrays.asList(numbers);
			Collections.shuffle(numbers1);
			numbers1.toArray(numbers);
		}
					
			// There are 9 (3x3) boxes. 
		public boolean verifyY(){
			boolean checkY1=true;
			boolean checkY2=true;
			boolean checkY3=true;
			boolean checkY4=true;
			boolean checkY5=true;
			boolean checkY6=true;
			boolean checkY7=true;
			boolean checkY8=true;
			boolean checkY9=true;

			for(int i=0;i<this.y;i++) 
			{
				if (this.gameBoard[i][0]!=0)
                {
					Y1.add(this.gameBoard[i][0]);
				}
			}
		
			for(int i=0;i<Y1.size();i++) {
				for (int j=(i+1);j<Y1.size();j++) {
					if(Y1.get(i)==Y1.get(j)) {
						 checkY1 = false;
					}
				}
			}
		
			for(int i=0;i<this.y;i++) 
			{
				if (this.gameBoard[i][1]!=0)
                {
					Y2.add(this.gameBoard[i][1]);
				}
			}
		
			for(int i=0;i<Y2.size();i++) {
				for (int j=(i+1);j<Y2.size();j++) {
					if(Y2.get(i)==Y2.get(j)) {
						 checkY2 = false;
					}
				}
			}
			for(int i=0;i<this.y;i++) 
			{
				if (this.gameBoard[i][2]!=0)
                {
					Y3.add(this.gameBoard[i][2]);
				}
			}
		
			for(int i=0;i<Y3.size();i++) {
				for (int j=(i+1);j<Y3.size();j++) {
					if(Y3.get(i)==Y3.get(j)) {
						 checkY3 = false;
					}
				}
			}
			for(int i=0;i<this.y;i++) 
			{
				if (this.gameBoard[i][3]!=0)
                {
					Y4.add(this.gameBoard[i][3]);
				}
			}
		
			for(int i=0;i<Y4.size();i++) {
				for (int j=(i+1);j<Y4.size();j++) {
					if(Y4.get(i)==Y4.get(j)) {
						 checkY4 = false;
					}
				}
			}
			for(int i=0;i<this.y;i++) 
			{
				if (this.gameBoard[i][4]!=0)
                {
					Y5.add(this.gameBoard[i][4]);
				}
			}
		
			for(int i=0;i<Y5.size();i++) {
				for (int j=(i+1);j<Y5.size();j++) {
					if(Y5.get(i)==Y5.get(j)) {
						 checkY5 = false;
					}
				}
			}
			for(int i=0;i<this.y;i++) 
			{
				if (this.gameBoard[i][5]!=0)
                {
					Y6.add(this.gameBoard[i][5]);
				}
			}
		
			for(int i=0;i<Y6.size();i++) {
				for (int j=(i+1);j<Y6.size();j++) {
					if(Y6.get(i)==Y6.get(j)) {
						 checkY6 = false;
					}
				}
			}for(int i=0;i<this.y;i++) 
			{
				if (this.gameBoard[i][6]!=0)
                {
					Y7.add(this.gameBoard[i][6]);
				}
			}
		
			for(int i=0;i<Y7.size();i++) {
				for (int j=(i+1);j<Y7.size();j++) {
					if(Y7.get(i)==Y7.get(j)) {
						 checkY7 = false;
					}
				}
			}for(int i=0;i<this.y;i++) 
			{
				if (this.gameBoard[i][7]!=0)
                {
					Y8.add(this.gameBoard[i][7]);
				}
			}
		
			for(int i=0;i<Y8.size();i++) {
				for (int j=(i+1);j<Y8.size();j++) {
					if(Y8.get(i)==Y8.get(j)) {
						 checkY8 = false;
					}
				}
			}for(int i=0;i<this.y;i++) 
			{
				if (this.gameBoard[i][8]!=0)
                {
					Y9.add(this.gameBoard[i][8]);
				}
			}
		
			for(int i=0;i<Y9.size();i++) {
				for (int j=(i+1);j<Y9.size();j++) {
					if(Y9.get(i)==Y9.get(j)) {
						 checkY9 = false;
					}
				}
			}
			if(checkY1==false||checkY2==false||checkY3==false||checkY4==false||checkY5==false||checkY6==false||checkY7==false||checkY8==false||checkY9==false) {
				
				Y1.clear();
				Y2.clear();
				Y3.clear();
				Y4.clear();
				Y5.clear();
				Y6.clear();
				Y7.clear();
				Y8.clear();
				Y9.clear();

				return false;
			}
			
			Y1.clear();
			Y2.clear();
			Y3.clear();
			Y4.clear();
			Y5.clear();
			Y6.clear();
			Y7.clear();
			Y8.clear();
			Y9.clear();

		return true;
			
		
		
		}
		public boolean verifyGrid() {
			boolean box1=true;
			boolean box2=true;
			boolean box3=true;
			boolean box4=true;
			boolean box5=true;
			boolean box6=true;
			boolean box7=true;
			boolean box8=true;
			boolean box9=true;
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					if(this.gameBoard[i][j]!=0){
						kutu1.add(gameBoard[i][j]);
					}
				}
			}
			for(int k=0;k<kutu1.size();k++) {
				for(int m=k+1;m<kutu1.size();m++) {
					if(kutu1.get(k)==kutu1.get(m)) {
						box1=false;
					}
				}
			}
			for(int i=0;i<3;i++) {
				for(int j=3;j<6;j++) {
					if(this.gameBoard[i][j]!=0){
						kutu2.add(gameBoard[i][j]);
					}
				}
			}
			for(int k=0;k<kutu2.size();k++) {
				for(int m=k+1;m<kutu2.size();m++) {
					if(kutu2.get(k)==kutu2.get(m)) {
						box2=false;
					}
				}
			}

			for(int i=0;i<3;i++) {
				for(int j=6;j<9;j++) {
					if(this.gameBoard[i][j]!=0){
						kutu3.add(gameBoard[i][j]);
					}
				}
			}
			for(int k=0;k<kutu3.size();k++) {
				for(int m=k+1;m<kutu3.size();m++) {
					if(kutu3.get(k)==kutu3.get(m)) {
						box3=false;
					}
				}
			}
			for(int i=3;i<6;i++) {
				for(int j=0;j<3;j++) {
					if(this.gameBoard[i][j]!=0){
						kutu4.add(gameBoard[i][j]);
					}
				}
			}
			for(int k=0;k<kutu4.size();k++) {
				for(int m=k+1;m<kutu4.size();m++) {
					if(kutu4.get(k)==kutu4.get(m)) {
						box4=false;
					}
				}
			}
			for(int i=3;i<6;i++) {
				for(int j=3;j<6;j++) {
					if(this.gameBoard[i][j]!=0){
						kutu5.add(gameBoard[i][j]);
					}
				}
			}
			for(int k=0;k<kutu5.size();k++) {
				for(int m=k+1;m<kutu5.size();m++) {
					if(kutu5.get(k)==kutu5.get(m)) {
						box5=false;
					}
				}
			}
			for(int i=3;i<6;i++) {
				for(int j=6;j<9;j++) {
					if(this.gameBoard[i][j]!=0){
						kutu6.add(gameBoard[i][j]);
					}
				}
			}
			for(int k=0;k<kutu6.size();k++) {
				for(int m=k+1;m<kutu6.size();m++) {
					if(kutu6.get(k)==kutu6.get(m)) {
						box6=false;
					}
				}
			}
			for(int i=6;i<9;i++) {
				for(int j=0;j<3;j++) {
					if(this.gameBoard[i][j]!=0){
						kutu7.add(gameBoard[i][j]);
					}
				}
			}
			for(int k=0;k<kutu7.size();k++)
			{
				for(int m=k+1;m<kutu7.size();m++) 
				{
					if(kutu7.get(k)==kutu7.get(m)) 
					{
						box7=false;
					}
				}
			}
			for(int i=6;i<9;i++)
			{
				for(int j=3;j<6;j++) 
				{
					if(this.gameBoard[i][j]!=0)
					{
						kutu8.add(gameBoard[i][j]);
					}
				}
			}
			for(int k=0;k<kutu8.size();k++)
			{
				for(int m=k+1;m<kutu8.size();m++) 
				{
					if(kutu8.get(k)==kutu8.get(m)) 
					{
						box8=false;
					}
				}
			}
			for(int i=6;i<9;i++) 
			{
				for(int j=6;j<9;j++)
				{
					if(this.gameBoard[i][j]!=0)
					{
						kutu9.add(gameBoard[i][j]);
					}
				}
			}
			for(int k=0;k<kutu9.size();k++) 
			{
				for(int m=k+1;m<kutu9.size();m++)
				{
					if(kutu9.get(k)==kutu9.get(m))
					{
						box9=false;
					}
				}
			}
			if(!box1||!box2||!box3||!box4||!box5||!box6||!box7||!box8||!box9)
			{
				kutu1.clear();
				kutu2.clear();
				kutu3.clear();
				kutu4.clear();
				kutu5.clear();
				kutu6.clear();
				kutu7.clear();
				kutu8.clear();
				kutu9.clear();

				return false;
			}
			
			kutu1.clear();
			kutu2.clear();
			kutu3.clear();
			kutu4.clear();
			kutu5.clear();
			kutu6.clear();
			kutu7.clear();
			kutu8.clear();
			kutu9.clear();

			return true;
		}
		public void create() {
			this.shuffle();
			for(int i=0;i<9;i++) {
				this.gameBoard[0][i]=numbers[i];
			
		}
			this.shuffle();
			for(int i=0;i<9;i++) {
				this.gameBoard[1][i]=numbers[i];
			
		}
						
			while(!verifyY()||!verifyGrid()) {
				
				this.shuffle();
					for(int i=0;i<9;i++) {
						this.gameBoard[1][i]=numbers[i];
					}
							}
			this.shuffle();
			for(int i=0;i<9;i++) {
				this.gameBoard[2][i]=numbers[i];
			
		}
			while(!verifyY()||!verifyGrid()) {
				
				this.shuffle();
					for(int i=0;i<9;i++) {
						this.gameBoard[2][i]=numbers[i];
					}
							}
			this.shuffle();
			for(int i=0;i<9;i++) {
				this.gameBoard[3][i]=numbers[i];
			
		}	
			while(!verifyY()||!verifyGrid()) {
				
				this.shuffle();
					for(int i=0;i<9;i++) {
						this.gameBoard[3][i]=numbers[i];
					}
							}
			
			this.shuffle();
			for(int i=0;i<9;i++) {
				this.gameBoard[4][i]=numbers[i];
			
		}	
			while(!verifyY()||!verifyGrid()) {
				
				this.shuffle();
					for(int i=0;i<9;i++) {
						this.gameBoard[4][i]=numbers[i];
					}
							}
			this.shuffle();
			for(int i=0;i<9;i++) {
				this.gameBoard[5][i]=numbers[i];
			
		}	
			while(!verifyY()||!verifyGrid()) {
				
				this.shuffle();
					for(int i=0;i<9;i++) {
						this.gameBoard[5][i]=numbers[i];
					}
							}
			this.shuffle();
			for(int i=0;i<9;i++) {
				this.gameBoard[6][i]=numbers[i];
			
		}	
			while(!verifyY()||!verifyGrid()) {
				
				this.shuffle();
					for(int i=0;i<9;i++) {
						this.gameBoard[6][i]=numbers[i];
					}
				}
			this.shuffle();
			for(int i=0;i<9;i++) {
				this.gameBoard[7][i]=numbers[i];
			
		}	
			while(!verifyY()||!verifyGrid()) {
				
				this.shuffle();
					for(int i=0;i<9;i++) {
						this.gameBoard[7][i]=numbers[i];
					}
				}
			
			this.shuffle();
			for(int i=0;i<9;i++) {
				this.gameBoard[8][i]=numbers[i];
			
		}	
			while(!verifyY()||!verifyGrid()) {
				
				this.shuffle();
					for(int i=0;i<9;i++) {
						this.gameBoard[8][i]=numbers[i];
					}
				}
			/*
			System.out.println(this.toString());
			*/
		}
			
			public void undo(){
				if(!moves.isEmpty()) 
				{
				this.undone.add(new move(moves.get(moves.size()-1).getY(), moves.get(moves.size()-1).getX(), 0,moves.get(moves.size()-1).getNumber()));
				this.setMove(moves.get(moves.size()-1).getY(), moves.get(moves.size()-1).getX(), 0);
				}
			
			
			}
			public void redo() {
				if(!undone.isEmpty()) 
				{
					this.moves.add(new move(undone.get(undone.size()-1).getY(), undone.get(undone.size()-1).getX(),undone.get(undone.size()-1).getTempNumber(),0));

					this.setredo(undone.get(undone.size()-1).getY(), undone.get(undone.size()-1).getX(),undone.get(undone.size()-1).getTempNumber());
				}
			}
			
			
			
		
}

