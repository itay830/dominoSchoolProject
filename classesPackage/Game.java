package classesPackage;

import java.util.*;

public class Game {
  public static Scanner r = new Scanner(System.in);
  public Player p1;
  public Player p2;
  public DominoMap dominoMap;
  public boolean turn;
  public boolean running;

  public Game() {
    clearConsole();
    printRules();
    running = true;
    dominoMap = new DominoMap();
    turn = Math.round(Math.random() * 2) == 0;
    p1 = new Player(getUniqueName("Player 1"), getDeck());
    p2 = new Player(getUniqueName("Player 2"), getDeck());
    if (p1.name == p2.name) { // NO DUPLICATE NAMES :)
      p2.name = "theSecondOne'" + p2.name + "'";
    }
  }

  public static void printRules() {
    System.out.println("RULES :\n" + Colors.RED +
    "1. Each one goes on his TURN!\n" + Colors.GREEN +
    "2. GREEN DOMINO means it has CONNECTIONS.\n" + Colors.BLUE +
    "3. CONNECTIONS are when two DOMINOS share the same VALUE.\n" + Colors.PURPLE +
    "4. I got bored explaining the rules... Guess the other rules by yourself :/" + Colors.RESET);
    System.out.println("Understood  :) (write something :3)? ");
    r.next();
  }

  public void logic() {
    Player p;
    int[] potentialDominoIndexes;
    int indexChoice;
    Domino chosenDomino;
    clearConsole();

    // if (getPotentialDominoIndexes(p1.deck).length == 0 && getPotentialDominoIndexes(p2.deck).length == 0
    //     && p1.deck.index >= 7 && p2.deck.index >= 7) {
    //   System.out.println(Colors.BLUE + p1.name + "'s deck:" + Colors.RESET);
    //   p1.deck.printColumns(new int[] {});
    //   System.out.println(Colors.PURPLE + p2.name + "'s deck:" + Colors.RESET);
    //   p2.deck.printColumns(new int[] {});
    //   System.out.println(Colors.GREEN_BG
    //       + "Both players have no possible connections and both have full decks! IT'S A DRAW!" + Colors.RESET);
    //   running = false;
    //   sleep(5000);
    // }

    if (turn) {
      p = p1;
    } else {
      p = p2;
    }
    if (dominoMap.edges.getSidesX() == -1 || dominoMap.edges.getSidesY() == -1) {
      p.deck.printColumns(new int[] { 0, 1, 2, 3, 4, 5, 6 });
      sleep(500);
      System.out.print(p.name + "'s first turn!\nEnter the INDEX of your starting DOMINO: ");
      sleep(500);
      indexChoice = r.nextInt();
      dominoMap.add(p.deck.popAt(indexChoice));
      turn = !turn;
      return;
    }
    potentialDominoIndexes = getPotentialDominoIndexes(p.deck);
    if (potentialDominoIndexes.length != 0) {
      p.deck.printColumns(potentialDominoIndexes);
    } else {
      /*
       * while (potentialDominoIndexes.length == 0 && p.deck.index < 7) {
       * addToDeckUnique(p.deck);
       * potentialDominoIndexes = getPotentialDominoIndexes(p.deck);
       * }
       * if (p.deck.index == 7) {
       * turn = !turn;
       * return;
       * }
       */
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
      System.out.print(p.name + "'s turn. Enter the INDEX of the DOMINO you want to put: ");
      indexChoice = r.nextInt();
      chosenDomino = p.deck.getAt(indexChoice);
    } while (!dominoMap.add(chosenDomino));
    p.deck.popAt(indexChoice);
    checkWinner();
    turn = !turn;
  }

  public void sleep(int mls) {
    try {
      Thread.sleep(mls);
    } catch (InterruptedException e) {
      System.out.println("Interupted!");
    }
  }

  public void clearConsole() {
    System.out.print("\033[H\033[2J"); // ANSI to clear the console
    System.out.flush(); // Empting the buffer
  }

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

  public String getUniqueName(String greetingName) {
    String name = "";
    while (name.length() < 2) {
      System.out.print("Enter your name " + greetingName + ": ");
      name = r.next();
    }
    return name;
  }

  // TODO: FIX ME
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

  public void checkWinner() {
    if (p1.deck.index == 0) {
      System.out.println(Colors.GREEN_BG + p1.name + " HAS WON!!!" + Colors.RESET);
      running = false;
    }
    if (p2.deck.index == 0) {
      System.out.println(Colors.GREEN_BG + p2.name + " HAS WON!!!" + Colors.RESET);
      running = false;
    }
  }
}
