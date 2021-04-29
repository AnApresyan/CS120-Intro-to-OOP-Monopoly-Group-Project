import java.util.Random;

public abstract class Deck extends Square
{


    private int             chance;
    private int[]           deck = new int[16];
    private int             deckIndex;

    public Deck(int coordinate) 
    {
        super(coordinate);
        for (int i = 0; i < 16; i++)
            this.deck[i] = i;
        shuffleDeck();
    }

    public void         shuffleDeck()
    {
        int swapIndex;
        int temp;

        this.deckIndex = 0;
        for (int i = 0; i < deck.length; i++)
        {
            swapIndex = new Random().nextInt(deck.length);
            temp = deck[swapIndex];
            deck[swapIndex] = deck[i];
            deck[i] = temp;
        }
    }
    
    public void         drawCard()
    {
        System.out.print("You're drawing a card: ");
        this.chance = this.deck[this.deckIndex++];
        System.out.println("Chance random number: " + this.chance);
        if (this.deckIndex == deck.length)
            shuffleDeck();
    }

  
    public int          getChance()
    {
        return (this.chance);
    }

    
}
