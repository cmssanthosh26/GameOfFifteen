package gameoffifteen;
import java.util.Scanner;
import java.util.Random;
public class Game {
    int[][] board;
    int[] shiffledValues;
    int n;
    Random r;
    static final Scanner scanner=new Scanner(System.in);
    int currentRowCell;
    int currentColumnCell;
    public Game(int n)
    {
        this.n=n;
        this.board=new int[n][n];
        this.shiffledValues=new int[n*n];
        this.r=new Random();
        this.initiallize();
        this.currentColumnCell=n-1;
        this.currentRowCell=n-1;
        
    }
    public void initiallize()
    {
        int count=1;
        while(count<=n*n-1)
        {
            int v=r.nextInt(n*n);
            if(v==0)
                continue;
            int flag=0,k=1;
            while(k<count)
            {
                if(this.shiffledValues[k++]==v){
                    flag=1;
                    break;
                }
            }
            if(flag==0)
                this.shiffledValues[count++]=v;
        }
        int value=1;
        for(int i=0;i<this.n;i++){
            for(int j=0;j<this.n;j++){
                if(i==n-1&&j==n-1)
                {
                    this.board[i][j]=-1;
                    continue;
                }
                this.board[i][j]=this.shiffledValues[value++];
            }
        }
        display();
    }
    public void display(){
        for(int i=0;i<this.n;i++){
            for(int j=0;j<this.n;j++)
                System.out.print(this.board[i][j]+" \t");
            System.out.println();
        }
    }
    public void startExecution()
    {
        while(this.isWon()==false){
            System.out.println("Enter U for Up, D for Down ,L for Left, R for Right");
            char ch=scanner.next().charAt(0);
            switch(ch){
                case 'U':
                    moveUp();
                    break;
                case 'D':
                    moveDown();
                    break;
                case 'L':
                    moveLeft();
                    break;
                case 'R':
                    moveRight();
                    break;  
                default:
                    System.out.println("Enter correct option");
            }
            if(this.isWon()==true)
            {
                System.out.println("Your game is completed");
                break;
            }
            this.display();
        }
    }
    public void moveUp(){
        if(this.currentRowCell-1 >= 0)
        {
            int temp=this.board[this.currentRowCell][this.currentColumnCell];
            this.board[this.currentRowCell][this.currentColumnCell]=this.board[this.currentRowCell-1][this.currentColumnCell];
            this.board[this.currentRowCell-1][this.currentColumnCell]=temp;
            this.currentRowCell=this.currentRowCell-1;
        }
    }
    public void moveDown(){
        if(this.currentRowCell+1  < this.n)
        {
            int temp=this.board[this.currentRowCell][this.currentColumnCell];
            this.board[this.currentRowCell][this.currentColumnCell]=this.board[this.currentRowCell+1][this.currentColumnCell];
            this.board[this.currentRowCell+1][this.currentColumnCell]=temp;
            this.currentRowCell=this.currentRowCell+1;
        }
    }
    public void moveLeft(){
        if(this.currentColumnCell-1 >= 0)
        {
            int temp=this.board[this.currentRowCell][this.currentColumnCell];
            this.board[this.currentRowCell][this.currentColumnCell]=this.board[this.currentRowCell][this.currentColumnCell-1];
            this.board[this.currentRowCell][this.currentColumnCell-1]=temp;
            this.currentColumnCell=this.currentColumnCell-1;
        }
    }
    public void moveRight(){
        if(this.currentColumnCell+1 < this.n)
        {
            int temp=this.board[this.currentRowCell][this.currentColumnCell];
            this.board[this.currentRowCell][this.currentColumnCell]=this.board[this.currentRowCell][this.currentColumnCell+1];
            this.board[this.currentRowCell][this.currentColumnCell+1]=temp;
            this.currentColumnCell=this.currentColumnCell+1;
        }
    }
    public boolean isWon()
    {
        int count=1;
        for(int i=0;i<this.n;i++)
        {
            for(int j=0;j<this.n;j++)
            {
                if(i==n-1&&j==n-1)
                    continue;
                else if(this.board[i][j]!=count)
                    return false;
                count++;
            }
        }
        return true;
    }
}
