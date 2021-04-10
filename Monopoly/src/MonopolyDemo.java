import java.util.Scanner;


public class MonopolyDemo 
{

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input the number of the players");
        int num = input.nextInt();
        Player[] players = new Player[num];
        for (int i = 0; i < num; i++)
            players[i] = new Player(input.nextLine(), i);
            


        //Monopoly newMonopoly = new Monopoly(players);
        //newMonopoly.startGame();

    }

}
