package gameoffifteen;
import java.util.Scanner;
public class GameOfFifteen {
    public static void main(String[] args) 
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the dimension n value");
        int n=scanner.nextInt();
        Game g=new Game(n);
        g.startExecution();
    }
}