package classesPackage;

import java.util.*;

// A class that holds and calls all the necessary 'data' and 'functions'. THE GAME ITSELF! 

public class Game {
  public static Scanner r = new Scanner(System.in);
  public Player p1; // Player 1
  public Player p2; // Player 2
  public DominoMap dominoMap; // The game board
  public boolean turn; // Turn...
  public boolean running; // For 'main loop' to keep running or stop...
  int turnsCount; // Turns counter.
  public Game() {
    clearConsole();
    printRules();
    turnsCount = 0;
    running = true;
    dominoMap = new DominoMap();
    turn = Math.round(Math.random() * 2) == 0;
    p1 = new Player(getName("Player 1"), getDeck(), Colors.PURPLE);
    p2 = new Player(getName("Player 2"), getDeck(), Colors.CYAN);
    if (p1.name.equals(p2.name)) { // NO DUPLICATE NAMES :)
      p2.name = "theSecond" + p2.name;
    }
  }

  // Prints the game rules... In some way. . . My head really hurts :(
  public static void printRules() {
    System.out.println(Colors.BOLD +
        "----------     --------     ********   --------  ----    ----   --------        ------------    ------      ********   ------------ \n"
        +
        "************  **********   ----------  ********  *****   ****  **********       ************   ********    ----------  ************ \n"
        +
        "--        -- ----    ---- ************   ----    ------  ---- ----    ----      ----          ----------  ************ ----         \n"
        +
        "**        ** ***      *** ---  --  ---   ****    ************ ***      ***      ****  ****** ****    **** ---  --  --- ************ \n"
        +
        "--        -- ---      --- ***  **  ***   ----    ------------ ---      ---      ----  ------ ------------ ***  **  *** ------------ \n"
        +
        "**        ** ****    **** ---  --  ---   ****    ****  ****** ****    ****      ****    **** ************ ---  --  --- ****         \n"
        +
        "------------  ----------  ***  **  *** --------  ----   -----  ----------       ------------ ----    ---- ***  **  *** ------------ \n"
        +
        "**********     ********   ---      --- ********  ****    ****   ********        ************ ****    **** ---      --- ************ \n");
    System.out.println(Colors.CYAN_UNDERLINED + "RULES :\n" + Colors.RESET + Colors.RED +
        "1. Each one goes on his TURN!\n" + Colors.GREEN +
        "2. GREEN DOMINO means it has CONNECTIONS.\n" + Colors.BLUE +
        "3. CONNECTIONS are when two DOMINOS share the same VALUE.\n" + Colors.PURPLE +
        "4. I got bored explaining the rules... Guess the other rules by yourself :/" + Colors.RESET);
    System.out.print("Understood  :) (write something :3)? ");
    r.next();
  }

  // Logic -_- => The way the game works...
  public void logic() {
    turnsCount++;
    Player p; // Playing 'Player'
    int[] potentialDominoIndexes; // The green 'Dominos'
    int indexChoice; // Chosen 'index' of the 'Domino'
    Domino chosenDomino; // -_-
    clearConsole();

    // TODO: A draw moment.
    if (getPotentialDominoIndexes(p1.deck).length == 0 &&
        getPotentialDominoIndexes(p2.deck).length == 0 && 
        p1.deck.index >= 7 && p2.deck.index >= 7 && turnsCount != 1)
    {
      System.out.println(Colors.BLUE + p1.name + "'s deck:" + Colors.RESET);
      p1.deck.printColumns(new int[] {});
      System.out.println(Colors.PURPLE + p2.name + "'s deck:" + Colors.RESET);
      p2.deck.printColumns(new int[] {});
      System.out.println(Colors.GREEN_BG + "Both players have no possible connections and both have full decks! IT'S A DRAW!" + Colors.RESET);
      running = false;
      sleep(5000);
      return;
    }

    if (turn) {
      p = p1;
    } else {
      p = p2;
    }
    // If it's the first turn
    if (turnsCount == 1) {
      p.deck.printColumns(new int[] { 0, 1, 2, 3, 4, 5, 6 });
      sleep(500);
      System.out.print(p.color + p.name + "'s first turn!\nEnter the INDEX of your starting DOMINO: " + Colors.RESET);
      sleep(500);
      indexChoice = r.nextInt();
      dominoMap.add(p.deck.popAt(indexChoice));
      turn = !turn;
      return;
    }

    potentialDominoIndexes = getPotentialDominoIndexes(p.deck);
    // Check if 'p' can move.
    if (potentialDominoIndexes.length != 0) {
      p.deck.printColumns(potentialDominoIndexes);
    } else {
      /*
       * TODO: Change the taking a peace rule to 9*9
       * while (potentialDominoIndexes.length == 0 && p.deck.index < 7) {
       * addToDeckUnique(p.deck);
       * potentialDominoIndexes = getPotentialDominoIndexes(p.deck);
       * }
       * if (p.deck.index == 7) {
       * turn = !turn;
       * return;
       * }
       */
      // 'p' takes a peace and skips it's turn.
      if (p.deck.index >= 7) {
        System.out.println(
            Colors.RED + p.name + " has no possible connections and\nhas a full deck! NEXT TURN!" + Colors.RESET);
      } else {
        addToDeckUnique(p.deck);
        System.out.println(Colors.RED + p.name + " has no possible connection. +1 DOMINO. NEXT TURN!" + Colors.RESET);
      }
      sleep(1500);
      turn = !turn;
      return;
    }
    dominoMap.print();
    sleep(500);
    do {
      System.out.print(p.color + p.name + "'s turn. Enter the INDEX of the DOMINO you want to put: " + Colors.RESET);
      indexChoice = r.nextInt();
      chosenDomino = p.deck.getAt(indexChoice);
    } while (!dominoMap.add(chosenDomino)); // Until 'p' chosses a green "Domino".
    p.deck.popAt(indexChoice);
    checkWinner();
    sleep(750);
    turn = !turn;
  }

  // Makes the thread busy for some time. For better gameplay.
  public void sleep(int mls) {
    try {
      Thread.sleep(mls);
    } catch (InterruptedException e) {
      System.out.println(Colors.RED + "Interupted (System error, NOT PART OF THE GAME)!" + Colors.RESET);
    }
  }

  // You wont belive it... but it clears the console. . .
  public void clearConsole() {
    System.out.print("\033[H\033[2J"); // ANSI to clear the console
    System.out.flush(); // Empting the buffer
  }

  // Gives you a deck of random unique 'Dominos'.
  // IT WASN'T WRITTEN THAT TWO PLAYERS CAN'T HAVE SIMILAR DOMINOS => The players
  // have a chance to hold similar decks! It's not a bug it's a feature!
  public DominoList getDeck() {
    DominoList dList = new DominoList(7);
    for (int i = 0; i < 7; i++) {
      boolean legit = false;
      while (!legit) {
        Domino newD = new Domino();
        if (dList.findLight(newD) == -1) {
          dList.add(newD);
          legit = true;
        }
      }
    }
    return dList;
  }

  // Gets a unique name with more than a 1 character.
  public String getName(String greetingName) {
    String name = "";
    while (name.length() < 2) {
      System.out.print("Enter your name " + greetingName + ": ");
      name = r.next();
    }
    return name;
  }

  // Add to the 'p' deck a unique 'Domino'
  public void addToDeckUnique(DominoList deck) {
    int randCount = 0;
    Domino newDomino = new Domino();
    while (randCount < 50) {
      if (p1.deck.findLight(newDomino) == -1 && p2.deck.findLight(newDomino) == -1
          && dominoMap.rowMap.findLight(newDomino) == -1) {
        deck.add(newDomino);
        return;
      }
      newDomino = new Domino();
      randCount++;
    }
    deck.add(newDomino);
  }

  // Returns an 'int' arr ('int[]') with the indexes of the green 'Dominos' to
  // highlight (Color green).
  public int[] getPotentialDominoIndexes(DominoList deck) {
    List indexList = new List(7);
    for (int i = 0; i < deck.index; i++) {
      if (deck.getAt(i).getSides().compareLight(dominoMap.edges.getSidesX())
          || deck.getAt(i).getSides().compareLight(dominoMap.edges.getSidesY())) {
        indexList.add(i);
      }
    }
    return indexList.toArray();
  }

  // Check if one of the 'p's has an empty deck (Wins).
  public void checkWinner() {
    if (p1.deck.index == 0) {
      clearConsole();
      System.out.println(Colors.PURPLE_BG + p1.name + " HAS WON!!! YAHOO" + Colors.RESET);
      running = false;
    } else if (p2.deck.index == 0) {
      clearConsole();
      System.out.println(Colors.CYAN_BG + p2.name + " HAS WON!!! YEE PEE KA YEY" + Colors.RESET);
      running = false;
    }
  }
}
