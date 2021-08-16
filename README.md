# Anahit's and Alexander's Monopoly

This is a program written in Java, which using Java Swing, simulates Monopoly: Classic Edition. Here is a guideline to help you get started to play it properly.

## Folder Structure

The workspace contains several folders, where:

- `src`: the folder with source code
- `images`: the folder with visuals

To run the code, compile the sources. The "Tester.java" file contains the entry to the program, and starts the game.

## Starting Screen

Click "Start". Select the number of players. Submit the names (don't repeat them!). The game starts.

## Main Window

Enjoy the game!

You can see the board on the left and the control panel on the right.

The active player's name is always bolded.

You can see your belongings lit up under the infopanel. You can click on them to perform various actions, including "Mortgage Property", "Lift Mortgage", "Erect House" and "Destroy House". The purposes of the buttons should be evident.

Click "Throw Dice" to roll dices. Click "Trade" to initialize a trade with a player. Click "Done" to pass the turn to the next player.

Click on any property on the board to get more info.
# Changelog
```
MONOPOLY 0.1.0           04/13/2021
An & Al:
1) created the whole hierarchy of the game;
2) created Player class.

MONOPOLY 0.2.0           04/14/2021
An & Al:
1) implemented the main gameplay loop;
2) created the rest of trivial classes.

MONOPOLY 0.2.1           04/15/2021
Al:
1) added getRent() computation method for Utility & Railroad;
2) improved overall performance of the gameplay loop;
3) implemented detection of Monopoly and count of owned props of the same color.
An:
3) implemented mortgaging;
4) implemented canBeMortgaged() and the bankruptcy loop.

MONOPOLY 0.3.0.          04/18/2021
Al:
1) moved dice[2] to Player;
2) created holdsDoubles() for Player class;
3) added getters and setters here and there;
4) added "Go To Jail" functionality and changed the startGame loop accordingly;
5) cleaned up the code a bit;
6) added daysInJail int in Player to force the player leave the jail after 3 days;
7) fixed a bug causing the player omit GO each time a new round around the map is passed;
8) modified movePlayer() such that if GO is passed, the player is granted $200.

MONOPOLY 0.4.0           04/19/2021
An:
1) added basic Swing support;
2) added a tester for play window; added basic interface;
3) added logo on the starting screen; added START button.

MONOPOLY 0.4.1           04/20/2021
An:
1) fixed an issue causing the content not to display until the window is resized.

MONOPOLY 0.5.0           04/21/2021
Al: 
1) movePlayer() now receives an int;
2) implemented the functionality of Community Chest and Chance squares;
3) movePlayer() improved further such that the renewal of coordinates depenends on getCoordinate();
4) scrapped index instance variable in Player class (was unused);
5) moved the board setup into separate static Board class to access it everywhere.
An:
6) overall improvement of interface.

MONOPOLY 0.6.0           04/22/2021
An:
1) added the basic grid for interface;
2) created a visual representation of the playboard;
3) created buttons that correspond to each square;
4) created basic layout of the second infobox;
5) overall polishing of the interface;
Al:
6) added functionality of the Community Chest;
7) made the board static to be able to access it everywhere;
8) made the players static to be able to access them everywhere;
9) implemented housing mechanics (RAW).

MONOPOLY 0.7.0           04/23/2021
Al:
1) commenced a major, comprehensive testing of all existing methods;
2) enterMortgageLoop() is now a separate method;
3) rent of unimproved monopolized properties is now doubled;
4) fixed an issue causing one of the inner mortgage loops to run endlessly;
5) implemented removePlayer() method, with passing props when losing;
6) added a price of $200 to Railroad class members;
7) Chance and Community Chest now properly trigger the mortgage loop;
8) mortgage loop now offers an option to sell houses;
9) added Utilities' and Railroads' coordinates to the COLORS array;
CLASSES TESTED: Board, Buyable, Chance, Chest, GOTaxFree, GoToJail, Railroad, Utility, Square
CLASSES PENGING TESTING: Jail, Property (housing features)
An:
10) Completely changed the board structure:D ;       // Al: haha it must've happened some day...
11) Added some colors to the board;
12) Added the logic for the information of titleDeeds when clicked
13) Changed getRent() in Property, Utility, and Railroad not to throw null pointer Exception
14) Not to forget: need to change the titleDeed layout, do the getRent() methods need to return 0 if the squares do not havener (Railroad, Utility)
P.S. I do not like writing, but it doesn't mean that I do not do work:D  // Al: yes dear i noticed xdddd no worries, everyoneows you do 10 times more you write about

04/24/2021: Alexander & Anahit had to take a break to rest and do some Calculus...

MONOPOLY 0.8.0           04/25/2021
Al:
1) testing of methods completely finished. the logic is ready to be connected with visuals;
2) improved the double-rolling mechanics: new static instance variable in Monopoly class was created;
3) rolling doubles 3 times in a row now results in imprisonment;
4) implemented trading (RAW);
5) implemented auction (RAW);
6) fixed an issue causing a removed player's properties to remain owned by them when failing to break 
   out of the mortgageLoop by taking a Chance/Chest card;
An:
7) added buttons THROW_DICE and DONE, and tied them to corresponding methods;
8) made the info header refresh each time DONE is pressed;
9) overall improvement of the UI.

MONOPOLY 0.9.0           04/26/2021
Al:
1) added dummy images for sprites;
An:
2) fixed an issue causing a player's name to be displayed in each title deed card as the owner after a
   property is bought for the first time;
3) fixed an issue causing the player's turn not to end;
4) fixed an issue causing the balance and coordinate refresh only after DONE is pressed;
5) properly connected Buyable to the interface. the program now correctly handles YES and NO input through
   the corresponding buttons;
6) properly connected Chance&Chest to the interface with corresponding message pop-ups.

MONOPOLY 1.0.0           04/27/2021
An:
1) created an enum for cards in Chance&Chest holding messages to easily display them;
2) added a getMessage() to collect the card desriptions in the Square class;
3) overall improvement of interface connection.

MONOPOLY 1.0.1           04/28/2021
Al:
1) implemented liftMortgage() in the Player class;
2) lower-cased the card messages so that Anahit doesn't get annoyed by capsed letters;
An:
3) merged GoToJail class with GOTaxFree class;

MONOPOLY 1.0.2           04/29/2021
Al:
1) implememted the sprites (RAW);
An:
2) changed the size of certain windows and edited the spacing.
3) reworked the MainWindow and Monopoly classes so that Monopoly is the game manager now;
4) the logic now is evenly implemented both in MainWindow and in Monopoly.

MONOPOLY 1.0.3           04/30/2021
An:
1) properly connected "Lift Mortgage", "Mortgage", "Buy House", "Sell House" to the interface.

MONOPOLY 1.0.4           05/01/2021
An:
1) implemented a panel for the owned properties;
2) a popUpWIndow each time a button from that panel is clicked will show up with different options;
3) I don't completely remember the methods I changed, but I commented the
   loops for build/destroy houses and mortgage/liftMortgage;
4) Instead, added/changed those methods, as well as the methods canBeImproved, canBeMortgaged and the others;
5) Moved some of the methods from Player to the Property or Buyable, such as canBeImproved, etc, because it's not 
   the player who can be improved, but the property:D
6) Please appreciate my work, thanks:D   // Al: done. you cannot believe what pleasure it is to work with you in a team. love you endlessly <3
Al:
7) info in the window pop-ups is now more detailed (mortgage state, houses, housePrice added);
8) fixed an issue causing the title deeds not be refreshed after a button in the pop-ups is pressed;
9) fixed an issue causing Erect House button stay active after 5 houses have been built;
10) numberOfPlayers is now correctly accessed and refreshed;
11) auction pop-up created;
An:
Thanks for the kind words:D
12) Separated the windows in the beginning from the MainWindow to make it look more organized;
13) Along with that got rid of the reduntant variable numOfNames.

MONOPOLY 1.1.0           05/02/2021
Al:
1) AuctionPopUp created; NO now properly start the auction;
2) fixed an issue causing the winner pay 2 times the bid;
3) fixed an issue causing the labels not get refreshed properly in bidders list;
4) made the auction window unclosable by the user;
5) the auction now automatically eliminates players who don't have enough money;
An:
6) fixed an issue causing the players not to get added to bidders when their balance is less than
   the price of the property being auctioned;
7) fixed an issue causing the belongings buttons display incorrectly on School 42's IMacs;
Al:
8) fixed an issue causing severe sync problems with the players list in infoTop (now utilizing arraylist
   of JLabels instead of an array);
An:
9) added winning screen with options to exit and replay;
10) added basic support for the mortgage loop;
11) added the removal of a lost player;
12) fixed an issue allowing Player 1 pass the turn right away because Done is enabled from the beginning;
13) fixed an issue causing Done stay enabled upon landing on Buyable and receiving YES/NO input;
14) fixed an issue causing Done stay enabled when OK is prompted during Chance&Chest interaction;
15) fixed an issue causing action buttons to light up incorrectly during a Jail interaction;
16) added some visuals to the board.

MONOPOLY 1.2.0           05/03/2021
Al:
1) the dices rolled are now displayed on the playing screen, as well as the getouttajail card;
2) trade wholly implemented! YAY!
An:
3) fixed an issue causing the sprite get duplicated after landing on GoToJail;
4) fixed various issues causing Done, Trade and Throw Dice to get disabled/enabled in an untimely manner;
Al:
5) fixed an issue causing handed-over properties' owner stay unchanged after a trade;
6) fixed an issue causing the tradee automatically accept a trade offer;
7) fixed an issue causing the trade slider to have a wrong range when a player's balance is beyond zero;

MONOPOLY 1.3.0           05/04/2021
An:
1) added the rest of the visuals;
2) improved greatly UI and the winning screen;
Al:
3) fixed an issue allowing the players to erect and destroy houses unevenly;
4) fixed an issue allowing a player with negative balance to pass the turn without paying off the debt;
5) fixed an issue causing severe sync problems with sprites after player removal;
6) removed chunks of comments throughout the code;
7) formatted the code to comply with School 42's coding norm (because it's beautiful!)

KNOWN ISSUES:
1. the layout of trade may not contain escape buttons such as Cancel, Confirm, Accept, Decline
   when there's too many belongings of either the trader or the tradee
2. the Accept/Decline mode of trade pop-up doesn't hide lobbyPanel during the very first trade
3. the sprites land on the top of each other upon entering Jail and Free Parking, even though 
   their layout is set to be a FlowLayout (which is not the case for GO, oddly enough)
```
# Credits

`Anahit Apresyan` -- lead programmer, lead UI designer

`Alexander Israelyan` -- lead programmer, lead debugger
