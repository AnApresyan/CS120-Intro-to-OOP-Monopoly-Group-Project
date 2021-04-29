import java.util.Random;

public abstract class Deck extends Square
{
    public enum CardMessages
    {
        ;

        private String message;

        // private String getMessage()
        // {
        //     return (this.message);
        // }
    };

    private CardMessages    card;
    private int             chance;
    private int[]           deck = new int[16];
    private int             deckIndex;

    public Deck(int coordinate) 
    {
        super(coordinate);
        if (coordinate == 2 || coordinate == 17 || coordinate == 33)
            setTitle("Community Chest");
        else if (coordinate == 7 || coordinate == 22 || coordinate == 36)
            setTitle("Chance");
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
        //this.card = CardMessages.values()[this.chance];
        // System.out.println("the message: " + this.card.getMessage());
        if (this.deckIndex == deck.length)
            shuffleDeck();
    }

    public CardMessages getCard()
    {
        return (this.card);
    }

    public int          getChance()
    {
        return (this.chance);
    }

    public void         setCard(CardMessages card)
    {
        this.card = card;
    }
}
