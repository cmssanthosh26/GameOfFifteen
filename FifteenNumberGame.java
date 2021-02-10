package fifteennumbergame;
import java.util.Random;
import java.util.Scanner;

public class FifteenNumberGame {
    private int spaceRow;
    private int spaceColumn;
    private int[][] board; 
    private static final Scanner scanner = new Scanner(System.in);
    FifteenNumberGame(int n)
    {
        this.board = new int[n][n];
        this.spaceRow=n-1;
        this.spaceColumn=n-1;
    }
    
    void initializeBoard()
    {
        int count=1;
        for(int i=0;i<this.board.length;i++)
        {
            for(int j=0;j<this.board.length;j++)
            {
                this.board[i][j]=count++;
            }
        }
        shuffleTheBoard();
    }
    
    void shuffleTheBoard()
    {
        Random r=new Random();
        int v;
        while(true)
        {
            v=r.nextInt(30);
            if(v>=15)
                break;
        }
        char[] symbol={'U','D','L','R'};
        for(int i=0;i<=v;i++)
            handleMove(symbol[r.nextInt(symbol.length)]);
            
    }
    
    void collectMove()
    {
        while(!this.isNumbersInOrder(board))
        {
            System.out.println("enter D for down, U for top,L for left R for right and h for help");
            char c=scanner.next().charAt(0);
            handleMove(c);
            this.displayBoard();
        }
        this.displayBoard();
        if(this.isNumbersInOrder(board)==true)
        {
            System.out.println("You won the match");
        }
    }
    
    void handleMove(char c)
    {
        int newRow = spaceRow;
        int newCol= spaceColumn;
        
        switch(c)
        {
            case 'U':
            case 'u':
                newRow-=1;
                break;
                
            case 'D':
            case 'd':
                newRow+=1;
                break;
                
            case 'L':
            case 'l':
                newCol-=1;
                break;
               
            case 'R':
            case 'r':
                newCol+=1;
                break;    
            
            default:
                return;
        }
        
        if(newRow<0 || newRow>=this.board.length || newCol<0 || newCol>=this.board.length)
        {
            return;
        }
        board[this.spaceRow][this.spaceColumn]=board[newRow][newCol];
        board[newRow][newCol]=this.board.length*this.board.length;
        this.spaceRow=newRow;
        this.spaceColumn=newCol;
    }
    
    void displayBoard()
    {
        int spaceNum = this.board.length * this.board[0].length;
        
        for(int i=0;i<this.board.length;i++)
        {
            for(int j=0;j<board[i].length;j++)
            {
                if(board[i][j]==spaceNum)
                {
                    System.out.print("     ");
                }
                else
                {
                    System.out.printf("%-5d",board[i][j]);
                }
            }
            System.out.println();
        }
    }
    
    boolean isNumbersInOrder(int[][] board)
    {
        int count=1;
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[i].length;j++)
            {
                if(board[i][j]!=count)
                {
                    return false;
                }
                count++;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println("Enter the dimension of the board");
        int n=scanner.nextInt();
        FifteenNumberGame f=new FifteenNumberGame(n);
        f.initializeBoard();
        f.displayBoard();
        f.collectMove();
    }
    
}
