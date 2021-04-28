public class GOTaxFree extends Square
{
    private int choice;
    public GOTaxFree(int coordinate) 
    {
        super(coordinate);
        if (coordinate == 0)
            setTitle("GO");
        else if (coordinate == 4)
            setTitle("Income Tax");
        else if (coordinate == 20)
            setTitle("Free Parking");
        else if (coordinate == 38)
            setTitle("Luxury Tax");
        else if (coordinate == 30)
            setTitle("GO To Jail");
        choice = 0;
    }
    public void doAction(Player activePlayer)
    {
        if (getCoordinate() == 4)
        {
            //System.out.println("Do you wanna pay $200 OR 10% of your wealth?");
            if (choice == 1)
                activePlayer.receiveMoney(-200);
            else if (choice == 2)
                activePlayer.setMoney((int)(activePlayer.getMoney() * 0.9));
        }
        else if (getCoordinate() == 38)
        {
            activePlayer.receiveMoney(-75);
        }
        else if (getCoordinate() == 30){
            activePlayer.setIsPrisoned(true);
            activePlayer.setCoordinate(10);
        }
    }
    @Override
    public String getMessage() {
        switch(getCoordinate()){
            case 0:
                return "You are on the GO! Collect $200.";
            case 4:
                return "Do you wanna pay $200 OR 10% of your wealth?";
            case 20:
                return "Just have a rest";
            case 38:
                return "You have to pay 75$ as a luxury tax";
            case 30:
                return "Haha! You go to jail right now!";
        }
        return "";
    }

    public void setChoice(int choice){
        this.choice = choice;
    }
}
